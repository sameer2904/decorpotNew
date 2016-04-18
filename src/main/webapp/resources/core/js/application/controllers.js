/**
 * @author sameer
 */

var decorpotCtrls = angular.module('decorpot');

decorpotCtrls.controller('homeController', [ '$scope', function($scope) {

} ]);

decorpotCtrls.controller('uploadController', [
		'$scope',
		'Upload', 'uploadService',
		function($scope, Upload, uploadService) {
			$scope.spaces = [ 'kitchen', 'kids_bedroom', 'master_bedroom',
					'guest_bedroom', 'living_dinning' ];
			$scope.printModel = function() {
				console.log($scope.spaceType);
			}

			$scope.uploadData = function() {
				var kitchenData = {};
				kitchenData.title = $scope.title;
				kitchenData.description = $scope.description;
				kitchenData.basePrice = $scope.basePrice;
				kitchenData.ht = $scope.ht;
				kitchenData.wdth = $scope.wdth;
				kitchenData.images = [];
				angular.forEach($scope.files, (file) => {
					kitchenData.images.push(file.name);
				} );
				
				
				
				uploadService.uploadSpace($scope.spaceType, kitchenData)
					.success(function(data) {
						angular.forEach($scope.files, f => {
							Upload.upload({
				                url: 'upload/images',
				                data: {
				                    file: f,
				                }
				            });
						})
						
					});
			}
		} ]);