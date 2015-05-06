var app = angular.module('SaasApp',['ui.router']);
var urlBase = 'http://localhost:8080';
app.config([
'$stateProvider',
'$urlRouterProvider',
function($stateProvider, $urlRouterProvider) {

  $stateProvider
   .state('index', {
      url: '/index',
      templateUrl: 'welcome.html'
    })
    .state('login', {
      url: '/login',
      templateUrl: 'login.html',
      controller: 'userController'
    })
    .state('signup', {
      url: '/signup',
      templateUrl: 'signup.html',
      controller: 'userController'
    })
    .state('selectPlan', {
      url: '/selectPlan',
      templateUrl: 'selectPlan.html',
      controller: 'userController'
    })
    .state('lookup', {
      url: '/lookup',
      templateUrl: 'lookup.html',
      controller: 'lookupController'
    })
    .state('project', {
      url: '/project',
      templateUrl: 'project.html',
      controller: 'projectController'
    });
   
  $urlRouterProvider.otherwise('index');
}]);



app.controller('userController', function($scope, userService, $location){
	console.log('inside userController');
	var promise;
	
	$scope.login= function() {
		
		promise = userService.login($scope.user);
		promise.then(function (payload){
			//console.log("rendering "+JSON.stringify(payload.data));
			// TODO check if login in valid
			$location.path("/project");

		});
	};

	$scope.signup = function() {
		promise = userService.signup($scope.user);
		promise.then(function (payload){
			//console.log("rendering "+JSON.stringify(payload.data));
			// TODO check if successful registration
			// user = userService.data;
			// console.log("Before path User : "+JSON.stringify(userService));
			$location.path("/selectPlan");

		});
	};

	$scope.createTenant = function (sdlcType){
		user = userService.data;
		//console.log("User : "+JSON.stringify(user) + " sdlcType : "+sdlcType);
		promise = userService.createTenant(user, sdlcType);
		promise.then(function (payload){
			$location.path("/project");
		});
	};

});

app.factory('userService', function($http){
	
	var userData = {
			data : {}
	};

	userData.login = function(user){
		console.log("logging in with "+user.email+" "+ user.password);
		return $http.post(urlBase+'/login',user)
		.success(function(userRes){
			console.log("data from login "+JSON.stringify(userRes));
			//user.data = userRes;
			angular.copy(userRes, userData.data);
		});
	};

	userData.signup = function(user){
		console.log("User sign up!"+JSON.stringify(user));
		return $http.post(urlBase+'/signup',user)
		.success(function(userRes){
			
			//user.data = userRes;
			angular.copy(userRes, userData.data);
			console.log("user from service "+JSON.stringify(user));
		});
	}

	userData.createTenant = function(user, sdlcType){
		return $http.post('http://localhost:8080/createTenant/'+sdlcType, user).success(function(data){
			console.log("create Tenant response: "+JSON.stringify(data))
			angular.copy(data, userData.data);
		});
	}
	return userData;
});

app.controller('projectController', function($scope, projectService, userService){
	console.log('inside projectController');
	var promise;

	promise = projectService.getAllProjects(1);
	promise.then(function (payload){
			//console.log("projects: : "+JSON.stringify(payload));
			$scope.data = payload.data;
		});

	$scope.getProjectDetails = function(recordId){
		userId = userService.data.userId;
		console.log("Getting project details for user "+userId+" and project "+recordId);
		promise = projectService.getProjectDetails(userId, recordId);

		promise.then(function (payload){
			console.log("project details : "+JSON.stringify(payload));

			var result = {};
			var data = payload.data;
			console.log("Size" + data.fieldList.length);
			for(var i = 0; i< data.fieldList.length; i++) {
				
				result[data.fieldList[i]] = data.valueList[i];
			}
			console.log("get project details---------"+JSON.stringify(result));
			$scope.projectDetails=result; 
			document.getElementById("projectList").style.display="none";
			document.getElementById("projectDetails").style.display="block";
		});
		
	}
	

});

app.factory('projectService', function($http){
	
	var project = {
			data : []
	}

	project.getAllProjects = function(userId){
		return $http.get('http://localhost:8080/getprojects/'+userId).success(function(data){
			//console.log("create Tenant response: "+JSON.stringify(data))
			angular.copy(data, project.data);
		});
	};

	project.getProjectDetails = function(userId, recordId){
		return $http.get(urlBase+'/getprojectdetails/'+userId+'/'+recordId)
		.success(function(data){
			console.log("Project detail response: "+JSON.stringify(data))
			angular.copy(data, project.data);
		});
	}
	
	return project;
});

app.controller('lookupController', function($scope, lookupService){
	$scope.test = "Amit";
	console.log("haha");
	console.log(lookupService.getLookupData());
	$scope.data = lookupService.data;
});



app.factory('lookupService', function($http){
	
	var lookup = {
			data : []
	}

	lookup.getLookupData = function(){
		return "hahah";
	}

	return lookup;
});


