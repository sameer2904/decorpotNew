/**
 * @author sameer
 */

var services = angular.module('decorpot');

services.service('uploadService',['$http', function($http){
	return {
		uploadSpace: function(spaceType, spaceData) {
			return $http({
				method : "post",
				url : "upload/space/" + spaceType,
				data : spaceData
			});
		}
	}
}] );
