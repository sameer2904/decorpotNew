/**
 * @author govardhan
 */

var decorpot = angular.module('decorpot', [ 'ui.router', 'ngFileUpload','angular-loading-bar' ]);

decorpot.run(['$rootScope', '$state',function($rootScope, $state) {
	$rootScope.state = $state.this;
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
				url: '/{id}',
				templateUrl: 'resources/partials/pastWork.html',
				controller: 'pastWorkController'
			})
			.state('looks', {
				url: '/looks/:looksCategory',
				templateUrl: 'resources/partials/looks.html',
				controller: 'looksController'
			}).state('looks.look', {
				url: '/:id',
				templateUrl: 'resources/partials/look.html'
			})
			.state('contact', {
				url : '/contact',
				templateUrl : 'resources/partials/contact.html',
				controller : 'contactController'
			}).state('uploadApartment', {
				url : '/uploadApartment',
				templateUrl : 'resources/partials/uploadApartment.html',
				controller : 'uploadApartmentController'
			}).state('uploadFloorPlan', {
				url : '/uploadFloorPlan',
				templateUrl : 'resources/partials/uploadFloorplan.html',
				controller : 'uploadFloorPlanController'
			})
			.state('uploadPastWork', {
				url : '/uploadPastWork',
				templateUrl : 'resources/partials/uploadPastWork.html',
				controller : 'uploadPastWorkController'
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
				url : '/:apartmentName/:floorplanId',
				templateUrl : 'resources/partials/apartmentPackages.html',
				controller : 'apartmentPackagesController'
			}).state('apartments.floorplans.packages.package', {
				url : '/:spaceIds',
				templateUrl : 'resources/partials/apartment.html',
				controller : 'apartmentController'
			});
		} ]);
decorpot.config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
    cfpLoadingBarProvider.includeSpinner = false;
  }])
