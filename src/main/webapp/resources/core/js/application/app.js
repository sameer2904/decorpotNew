/**
 * @author govardhan
 */

var decorpot = angular.module('decorpot', [ 'ui.router', 'ngFileUpload','angular-loading-bar', 'bootstrapLightbox', 'ngAnimate','angularGrid']);

decorpot.run(['$rootScope', '$state', '$location', 'userService', function($rootScope, $state, $location, userService) {
	$rootScope.state = $state.this;
	var isLoggedIn = userService.isUserLoggedIn();
   
    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        // redirect to login page if not logged in
        if ($location.path().indexOf('auth') !== -1 && !isLoggedIn) {
            $location.path('/login');
        }
    });
}]);

decorpot.config([ '$stateProvider', '$urlRouterProvider', '$httpProvider',
		function($stateProvider, $urlRouterProvider, $httpProvider) {
			$urlRouterProvider.otherwise('/');

			$stateProvider.state('home', {
				url : '/',
				templateUrl : 'resources/partials/home.html',
				controller : 'homeController'
			}).state('uploadSpace', {
				url: '/uploadSpace',
				templateUrl: 'resources/partials/uploadSpace.html',
				controller: 'uploadController'
			}).state('pastWorks', {
				url: '/pastWorks',
				templateUrl: 'resources/partials/pastWorks.html',
				controller: 'pastWorksController'
			}).state('pastWorks.pastWork', {
				url: '/:id',
				templateUrl: 'resources/partials/pastWork.html',
				controller: 'pastWorkController'
			})
			.state('looks', {
				url: '/looks/:looksCategory',
				templateUrl: 'resources/partials/looks.html',
				controller: 'looksController'
			})
			.state('looks.look', {
				url: '/:id',
				templateUrl: 'resources/partials/look.html',
				controller: 'lookController'
			})
			.state('contact', {
				url : '/contact',
				templateUrl : 'resources/partials/contact.html',
				controller : 'contactController'
			})
			.state('apartments', {
				url : '/apartments',
				templateUrl : 'resources/partials/apartments.html',
				controller : 'apartmentsController'
			})
			.state('apartments.floorplans', {
				url : '/:apartmentName',
				templateUrl : 'resources/partials/floorplans.html',
				controller : 'floorPlansController'
			})
			.state('apartments.floorplans.packages', {
				url : '/:apartmentType/:floorplanId',
				templateUrl : 'resources/partials/apartmentPackages.html',
				controller : 'apartmentPackagesController'
			})
			.state('apartments.floorplans.packages.package', {
				url : '/:basePrice/:spaceIds',
				templateUrl : 'resources/partials/apartment.html',
				controller : 'apartmentController'
			})
			.state('login', {
				url : '/login',
				templateUrl : 'resources/partials/login.html',
				controller : 'loginController'
			}).state('auth', {
	            url: '/auth',
	            templateUrl: 'resources/partials/auth.html',
	            abstract: true
	        }).state('auth.create', {
				url : '/create',
				templateUrl : 'resources/partials/createUser.html',
				controller : 'createUserController'
			}).state('auth.tasks', {
				url : '/tasks',
				templateUrl : 'resources/partials/tasks.html',
				controller : 'tasksController'
			}).state('auth.customers', {
				url : '/customers',
				templateUrl: 'resources/partials/customer.html',
				abstract: true
			}).state('auth.customers.details', {
				url : '/:id',
				templateUrl: 'resources/partials/customerDetails.html',
				controller: 'customerController'
			}).state('auth.uploadApartment', {
				url : '/uploadApartment',
				templateUrl : 'resources/partials/uploadApartment.html',
				controller : 'uploadApartmentController'
			}).state('auth.uploadFloorPlan', {
				url : '/uploadFloorPlan',
				templateUrl : 'resources/partials/uploadFloorplan.html',
				controller : 'uploadFloorPlanController'
			}).state('auth.uploadPastWork', {
				url : '/uploadPastWork',
				templateUrl : 'resources/partials/uploadPastWork.html',
				controller : 'uploadPastWorkController'
			}).state('auth.myTasks', {
				url : '/myTasks',
				templateUrl : 'resources/partials/myTasks.html',
				controller : 'mytasksController'
			});
		} ]);
decorpot.config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
    cfpLoadingBarProvider.includeSpinner = false;
  }])
