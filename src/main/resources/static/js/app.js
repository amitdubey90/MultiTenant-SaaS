var signUpUserModule = angular.module('SignUpUser', ['ngAnimate']);

signUpUserModule.controller('SignUpUserController', function ($scope,$http) {
	
	var urlBase="http://localhost:8080/SignUpUser";
	$http.defaults.headers.post["Content-Type"] = "application/json";

	var count=0;
    
	//add a new task
	$scope.addUser = function addUser() {
		alert("Adding User....");
		if($scope.username=="" || $scope.firstname=="" || $scope.lastname == "" || $scope.password == ""){
			alert("Insufficient Data! Please provide values for first name, last name, username and password");
		}
		else{
		 $http.post(urlBase + '/usersignup', {
		 	 user_id: count++,
             firstName: $scope.firstname,
             lastName: $scope.lastname,
             userName: $scope.username,
             password: $scope.password
         }).
		  success(function(data, status, headers) {
			 alert("User added");
		    });
		}
	};
 }]);
