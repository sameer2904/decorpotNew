/**
 * @author sameer
 */

var decorpotCtrls = angular.module('decorpot');

decorpotCtrls.controller('homeController', [ '$scope', function($scope) {
	
} ]);


decorpotCtrls.controller('uploadController', [ '$scope', 'Upload', function($scope, Upload) {
	$scope.spaces = ['kitchen', 'kids_bedroom', 'master_bedroom', 'guest_bedroom', 'living_dinning'];
	$scope.files = [];
	$scope.printModel = function() {
		console.log($scope.spaceType);
	}
} ]);