/**
 * @author govardhan
 */

var decorpot = angular.module('decorpot', [ 'ui.router', 'ngFileUpload' ]);

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
			}).state('projects', {
				url: '/projects',
				templateUrl: 'resources/partials/projects.html',
				controller: 'projectController'
			}).state('contact', {
				url : '/contact',
				templateUrl : 'resources/partials/contact.html',
				controller : 'contactController'
			});
		} ]);