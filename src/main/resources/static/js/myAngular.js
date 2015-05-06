var app = angular.module('SaasApp',['ui.router']);
var urlBase = 'http://localhost:8080';
app.config([
'$stateProvider',
'$urlRouterProvider',
function($stateProvider, $urlRouterProvider) {

  $stateProvider
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
    })
    .state('tenant', {
      url: '/tenant',
      template: '{{data}}',
      controller: 'tenantController'
    });
   
  $urlRouterProvider.otherwise('login');
}])



app.controller('userController', function($scope, userService, $location){
	console.log('inside userController');
	var promise;
	
	$scope.login= function() {
		
		promise = userService.login($scope.user);
		promise.then(function (payload){
			console.log("rendering "+JSON.stringify(payload.data));
			// TODO check if login in valid
			$location.path("/project");

		});
	};

	$scope.signup = function() {
		promise = userService.signup($scope.user);
		promise.then(function (payload){
			console.log("rendering "+JSON.stringify(payload.data));
			// TODO check if successful registration
			$location.path("/selectPlan");

		});
	};

	$scope.createTenant = function (sdlcType){
		user = userService.data;
		console.log("UserID : "+JSON.stringify(userService) + " sdlcType : "+sdlcType);
	}

});

app.factory('userService', function($http){
	
	var user = {
			data : []
	};

	user.login = function(user){
		console.log("logging in with "+user.email+" "+ user.password);
		return $http.post(urlBase+'/login',user)
		.success(function(userRes){
			//console.log("data from login "+JSON.stringify(data))
			angular.copy(userRes,user.data);
		});
	};

	user.signup = function(user){
		console.log("User sign up!"+JSON.stringify(user));
		return $http.post(urlBase+'/signup',user)
		.success(function(userRes){
			//console.log("data from login "+JSON.stringify(data))
			angular.copy(userRes,user.data);
		});
	}

	return user;
});



app.controller('lookupController', function($scope, lookupService){
	$scope.test = "Amit";
	console.log("haha");
	console.log(lookupService.getLookupData());
	$scope.data = lookupService.data;
});

app.controller('tenantController', function($scope, tenantService){
	var promise;
	var userId = 99;
	var sdlcType = 'waterfall';

	console.log('inside tenantController');
	$scope.data = "tenant data";
	promise = tenantService.createTenant(userId, sdlcType);

	promise.then(function (payload){
		console.log("rendering "+JSON.stringify(payload.data));
	})
});

app.factory('lookupService', function($http){
	
	var lookup = {
			data : []
	}

	lookup.getLookupData = function(){
		// return $http.get('/store/getCars').success(function(data){
		// 	//console.log("carlist from service: "+JSON.stringify(data))
		// 	angular.copy(data,productData.carList);
		// });
		return "hahah";
	}

	return lookup;
});

app.factory('tenantService', function($http){
	
	var tenant = {
			data : []
	}

	tenant.createTenant = function(userId, sdlcType){
		return $http.post('http://localhost:8080/createTenant/'+sdlcType+'?userId='+userId).success(function(data){
			console.log("create Tenant response: "+JSON.stringify(data))
			angular.copy(data,tenant.data);
		});
	}

	return tenant;
});
