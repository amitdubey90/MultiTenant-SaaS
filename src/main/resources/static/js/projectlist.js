// JavaScript Document

var app = angular.module("SDLC", []);

app.config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        $httpProvider.defaults.headers.common = 'Content-Type: application/json';
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }]);

var urlBase = "http://localhost:8080";

app.controller('projecttable_controller', function($scope,$http) {
		
 // $scope.ordered_columns = [];
//  $scope.all_columns = [{
//    "title": "Project Name",
//    "type": "string"
//  }, {
//    "title": "Project Description",
//    "type": "string"
//  }, {
//    "title": "Owner",
//    "type": "string"
//  }, {
//    "title": "Start Date",
//    "type": "string"
//  }, {
//    "title": "End Date",
//    "type": "string"
//  },{
//    "title": "Record ID",
//    "type": "string"
//  }];

$http.get(urlBase+'/getprojects/1').
		 success(function(data) {
			//alert(data);	
			$scope.data=data; 
			console.log("get project data"+$scope.data[0].fieldList);
		 });

$scope.createUser = function()
{
	console.log("Values Submitted");
	console.log($scope.projectName +" "+ $scope.projectDesc +" "+ $scope.owner+" "+ $scope.startDate+" "+ $scope.endDate);
	$scope.projectdata={"projectName":$scope.projectName,"projectDesc":$scope.projectDesc,"owner":$scope.owner,"startDate":$scope.startDate,"endDate":$scope.endDate}
	console.log($scope.projectdata);
	$http.post(urlBase+'/createproject/1',$scope.projectdata).
	success(function(data, status, headers, config){
		console.log("Doneeee!!!!!!");
	}).
	error(function(data, status, headers, config){
		console.log("aaarrrggghhhh!!!!!!");
	});
}

$scope.getDetails = function(recordId,projectName)
{
	console.log(recordId,projectName);
	$http.get(urlBase+'/getprojectdetails/1/'+recordId).
		 success(function(data) {
			//alert(data);	
			
			var result = {};
			console.log("Size" + data.fieldList.length);
			for(var i = 0; i< data.fieldList.length; i++) {
				//console.log(JSON.stringify(data.fieldList[i]) + "<><><>" + data.valueList[i]);
				result[data.fieldList[i]] = data.valueList[i];
			}
			console.log("get project details---------"+JSON.stringify(result));
			$scope.projectDetails=result; 
			document.getElementById("projectList").style.display="none";
			document.getElementById("projectDetails").style.display="block";
		 });
}



//$scope.notSorted = function(obj){
//        if (!obj) {
//            return [];
//        }
//        return Object.keys(obj);
//    }
  

  //$http.get(urlBase+'/getprojectdetails/1/3').
//     success(function(data) {
//		//alert(data);	
//		$scope.data=data; 
//		console.log($scope.data);
//	 });


//$scope.init = function()
//{
//	
//}
  // i.e. the rows
 // $scope.data = [{
//    "Project Name": "aleck",
//    "Project Description": "aaa",
//    "Owner": "abc",
//    "Start Date": "03-06-2013",
//    "End Date": "03-08-2013"
//  }, {
//    "Project Name": "aleck",
//    "Project Description": "aaa",
//    "Owner": "abc",
//    "Start Date": "03-06-2013",
//    "End Date": "03-08-2013"
//  }, {
//    "Project Name": "aleck",
//    "Project Description": "aaa",
//    "Owner": "abc",
//    "Start Date": "03-06-2013",
//    "End Date": "03-08-2013"
//  }];
//

//console.log($scope.data);
//  $scope.$watch('all_columns', function() {
//    update_columns();
//  }, true);
////
//  var update_columns = function() {
//    $scope.ordered_columns = [];
//    for (var i = 0; i < $scope.all_columns.length; i++) {
//      var column = $scope.all_columns[i];
//     // if (column.checked) {
//        $scope.ordered_columns.push(column);
//     // }
//    }
//  };
});