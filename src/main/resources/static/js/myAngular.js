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
      template: function(){
      	return '<meta charset="utf-8"> <meta content="IE=edge" http-equiv="X-UA-Compatible"> <meta content="width=device-width, initial-scale=1" name="viewport"> <meta content="" name="description"> <meta content="" name="author"> <title>Elevator - Multipurpose Bootstrap Theme</title> <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"> <link href="css/font-awesome.min.css" rel="stylesheet"> <link href="css/animate.css" rel="stylesheet"> <link href="css/style.css" rel="stylesheet"> <link href="http://fonts.googleapis.com/css?family=Lobster" rel= "stylesheet" type="text/css"> <script src="js/jquery-2.1.1.min.js"></script> <script src="bootstrap/js/bootstrap.min.js"></script> <script src="js/jquery.appear.js"></script> <script src="js/contact_me.js"></script> <script src="js/jqBootstrapValidation.js"></script> <script src="js/modernizr.custom.js"></script> <script src="js/script.js"></script> <section id="logo-section" class="text-center"> <div class="container"> <div class="row"> <div class="col-md-12"> <div class="logo text-center"> <h1>elevator</h1><span>Your Business Field</span> </div> </div> </div> </div> </section> <div class="mainbody-section text-center"> <div class="container"> <section class="container" style="margin-left:0%;"> <div class="login"> <h1>Login</h1> <form method="post" ng-submit="login()"> <p><input type="text" name="login" value="" placeholder="Email" ng-model="user.email"></p> <p><input type="password" name="password" value="" placeholder="Password" ng-model="user.password"></p> <p class="remember_me"> <label> <input type="checkbox" name="remember_me" id="remember_me"> Remember me on this computer </label> </p> <a href="#/signup"><input type="submit" name="register" value="Register" style="margin-left:0%; display:inline-block;"></a> <p class="submit" style="display:inline-block; margin-left:38%;"><input type="submit" name="commit" value="Login"></p> </form> </div> <div class="login-help"> <p>Forgot your password? <a href="index.html">Click here to reset it</a>.</p> </div> </section> </div> </div> </div> </div> </div>'
      },
     // templateUrl:'login.html',
      controller: 'userController'
    })
    .state('signup', {
      url: '/signup',
      template: '<meta charset="utf-8"> <meta content="IE=edge" http-equiv="X-UA-Compatible"> <meta content="width=device-width, initial-scale=1" name="viewport"> <meta content="" name="description"> <meta content="" name="author"> <title>Elevator - Multipurpose Bootstrap Theme</title> <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"> <link href="css/font-awesome.min.css" rel="stylesheet"> <link href="css/animate.css" rel="stylesheet"> <link href="css/style.css" rel="stylesheet"> <link href="http://fonts.googleapis.com/css?family=Lobster" rel= "stylesheet" type="text/css"> <script src="js/jquery-2.1.1.min.js"></script> <script src="bootstrap/js/bootstrap.min.js"></script> <script src="js/jquery.appear.js"></script> <script src="js/contact_me.js"></script> <script src="js/jqBootstrapValidation.js"></script> <script src="js/modernizr.custom.js"></script> <script src="js/script.js"></script> <section id="logo-section" class="text-center"> <div class="container"> <div class="row"> <div class="col-md-12"> <div class="logo text-center"> <h1>elevator</h1><span>Your Business Field</span> </div> </div> </div> </div> </section> <div class="mainbody-section text-center"> <div class="container"> <div class="row"> <div class="col-lg-6 col-lg-offset-3 text-center"> <section class="container" style="margin-left:-54%;"> <div class="login" style="width:32%;"> <h1>Login</h1> <form method="post" ng-submit="signup()"> <p><input type="text" name="firstname" value="" placeholder="Firstname" ng-model="user.firstname"></p> <p><input type="text" name="lastname" value="" placeholder="Lastname" ng-model="user.lastname"></p> <p><input type="text" name="address" value="" placeholder="Address" ng-model="user.address"></p> <p><input type="text" name="phone" value="" placeholder="Phone" ng-model="user.phone"></p> <p><input type="text" name="login" value="" placeholder="Email" ng-model="user.email"></p> <p><input type="password" name="password" value="" placeholder="Password" ng-model="user.password"></p> <p class="submit"><input type="submit" name="register" value="Register"></p> </form> </div> </section> </div> </div> </div> </div>',
      //templateUrl:'signup.html',
      controller: 'userController'
    })
    .state('selectPlan', {
      url: '/selectPlan',
      templateUrl: 'selectionModel.html',
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
    })
    .state('activity', {
      url: '/activity/:recordId',
      templateUrl: 'activities.html',
      controller: 'ActivitiesController'
    })
    .state('createProject', {
      url: '/createProject',
      templateUrl: 'createProject.html',
      controller: 'projectController'
    })
    .state('createActivity', {
      url: '/createActivity',
      templateUrl: 'createActivity.html',
      controller: 'projectController'
    });
   
  $urlRouterProvider.otherwise('index');
}]);



app.controller('userController', function($scope, userService, $location){
	console.log('inside userController');
	var promise;
	
	$scope.login= function() {
		console.log("logging in "+JSON.stringify($scope.user));
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

	$scope.gotoSignup = function(){
		console.log("Going to signup");
		$location.path("/signup");
	}

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

app.controller('projectController', function($scope, projectService, userService, $location){
	console.log('inside projectController');
	var promise;
	var userId = userService.data.userId;

	promise = projectService.getAllProjects(userId);
	promise.then(function (payload){
			console.log("projects: : "+JSON.stringify(payload));
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
			$scope.projectDetails.recordId = data.recordId;
			document.getElementById("projectList").style.display="none";
			document.getElementById("projectDetails").style.display="block";
		});
		
	}

	$scope.createProjectForm = function() {
		console.log("Open Project Form");
		//document.getElementById("projectList").style.display="none";
		//document.getElementById("addProject").style.display="block";
		$location.path("/createProject");
	}

	$scope.createProject = function() {
		console.log("Values Submitted");
		console.log($scope.projectName +" "+ $scope.projectDesc +" "+ $scope.owner+" "+ $scope.startDate+" "+ $scope.endDate);
		var projectData={"projectName":$scope.projectName,"projectDesc":$scope.projectDesc,"owner":$scope.owner,"startDate":$scope.startDate,"endDate":$scope.endDate}
		console.log(projectData);
		promise = projectService.createProject(userId, projectData);

		promise.then(function (payload){
			console.log("PROJECT CREATED!");
			$location.path("/project");
			promise = projectService.getAllProjects(userId);
			promise.then(function (payload){
				console.log("projects: : "+JSON.stringify(payload));
				$scope.data = payload.data;
				document.getElementById("projectList").style.display="block";
				document.getElementById("addProject").style.display="none";
		});
			
		});
	}

	$scope.getProjectActivities = function(projectId){
		//console.log(JSON.stringify($scope.projectDetails));
		console.log("Get activities for "+$scope.projectDetails.recordId);
		$location.path("/activity/"+$scope.projectDetails.recordId);
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

	project.createProject = function (userId, projectData){
		return $http.post(urlBase+'/createproject/'+userId, projectData)
		.success(function(data){
			console.log("Project detail response: "+JSON.stringify(data))
			angular.copy(data, project.data);
		});
	}
	
	return project;
});



app.controller('ActivitiesController', function($scope,
		ProjectActivitiesServices, $stateParams, userService, lookupService, $location) {
	
	
	//$scope.data = $stateParams.recordId;
	var userId = userService.data.userId;
	var projId = $stateParams.recordId;
	var projType = userService.data.sdlc;
	$scope.sdlc = projType;
	console.log("==========>"+$scope.sdlc)
	$scope.gotoCreateActivity = function() {
		console.log("redirecting to createActivity");
		$location.path("/createActivity");
	}
	
	$scope.getLookup = function (lookupType){
		console.log("Looking for "+lookupType+" user"+userId+" projjid"+projId);
		//lookupData
		var promise = lookupService.getLookupData(userId,projId,lookupType);
		promise.then(function (payload){
			console.log("lookup details : "+JSON.stringify(payload.data));
			document.getElementById("getLookup").style.display="block";
			document.getElementById("activityDetails").style.display="none";
			$scope.lookupData = payload.data;
		});
		
	}

	$scope.createProjectActivities = function(projId){
		
		ProjectActivitiesServices.createProjectActivities($scope.activity,userId, projId, projType).success(
				function(projActivities) {
					console.log("Inside Controller");
					$scope.data = projActivities.activityId;
					console.log("data received is :" + $scope.data);
				});
	};

	$scope.updateProjectActivities = function(){
		var userId = 1;
		var projId = 2;
		var projType = 3;
		ProjectActivitiesServices.updateProjectActivities($scope.projectActivity,userId, projId, projType).success(
				function(projActivities) {
					console.log("Inside Controller");
					$scope.data = projActivities.activityId;
					console.log("data received is :" + $scope.data);
				});
	};

	$scope.getProjectActivities = function(userId, projId, projType){

		ProjectActivitiesServices.getProjectActivities(userId, projId, projType).success(
				function(projActivities) {
					console.log("Inside Controller");
					$scope.data = projActivities.projActivitiesList;
					console.log("data received is :" + JSON.stringify($scope.data));
				});
	};

	$scope.deleteProjectActivities = function() {
		var userId = 1;
		var projId = 2;
		var projType = 3;
		ProjectActivitiesServices.deleteProjectActivities(userId, projId, projType).success(
				function(projActivities) {
					console.log("Inside Controller");
					$scope.data = projActivities.status;
					console.log("data received is :" + $scope.data);
				});
	}

	$scope.getProjectActivities(userId, projId, projType);
});

app.service('ProjectActivitiesServices', function($http, $stateParams) {

	return {
		getProjectActivities : function(userId, projId, projType) {
			// var userId = $stateParams.userId;
			console.log("user id is: " + userId);
			// var projId = $stateParams.projId;
			console.log("proj id is: " + projId);
			// var projType = $stateParams.projType;
			console.log("projtype is: " + projType);
			var url = urlBase+'/getprojectActivities/' + userId + '/' + projId + '/'+projType
			console.log("URL is: " + url);
			console.log("Inside getProjAct list");
			return $http.get(url);
		},
	
	createProjectActivities : function(projActivitiyData, userId, projId, projType){
		// var userId = $stateParams.userId;
		console.log("user id is: " + userId);
		// var projId = $stateParams.projId;
		console.log("proj id is: " + projId);
		// var projType = $stateParams.projType;
		console.log("projtype is: " + projType);
		// var url = '/createprojectActivity/'+userId+'/'+projId+'/'+projType
		console.log("URL is: " + url);
		console.log("Inside createProjAct list");
		return $http.post(url,projActivitiyData);
	},	
		
	updateProjectActivities : function(projActivitiyData, userId, projId, projType){
		// var userId = $stateParams.userId;
		console.log("user id is: " + userId);
		// var projId = $stateParams.projId;
		console.log("proj id is: " + projId);
		// var projType = $stateParams.projType;
		console.log("projtype is: " + projType);
		var url = '/updateprojectActivity/'+userId+ '/' + projId + '/'+projType
		console.log("URL is: " + url);
		console.log("Inside updateProjAct list");
		return $http.put(url,projActivitiyData);
	},
	
	deleteProjectActivities : function(userId, projId, projType){
		// var userId = $stateParams.userId;
		console.log("user id is: " + userId);
		// var projId = $stateParams.projId;
		console.log("proj id is: " + projId);
		// var projType = $stateParams.projType;
		console.log("projtype is: " + projType);
		var url = '/deleteprojectActivity/'+userId+'/'+activityId+'/'+projType
		console.log("URL is: " + url);
		console.log("Inside deleteProjAct list");
		return $http.delete(url);
	}
	
		
	};

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

	lookup.getLookupData = function(userId, projectId, lookupType){
		return $http.get(urlBase+'/getLookup/'+lookupType+'?userId='+userId+"&projectId="+projectId)
		.success(function(data){
			console.log("Lookup  "+JSON.stringify(data))
			angular.copy(data, lookup.data);
		});
	}

	return lookup;
});


