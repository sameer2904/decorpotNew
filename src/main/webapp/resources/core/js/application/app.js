/**
 * @author govardhan
 */

var decorpot = angular.module('decorpot', [ 'ui.router' ]);

decorpot.config([ '$stateProvider', '$urlRouterProvider', '$httpProvider',
		function($stateProvider, $urlRouterProvider, $httpProvider) {
			$urlRouterProvider.otherwise('/');

			$stateProvider.state('home', {
				url : '/',
				templateUrl : 'resources/partials/home.html',
				controller : 'homeController'
			});
			$stateProvider.state('contact', {
				url : '/contact',
				templateUrl : 'resources/partials/contact.html',
				controller : 'contactController'
			});
		} ]);