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
		},
		uploadApartment: function(apartmentConfig) {
			return $http({
				method : "post",
				url : "config/apartment",
				data : apartmentConfig
			});
		},
		uploadFloorplan: function(apartmentConfig) {
			return $http({
				method : "post",
				url : "config/apartment/" + apartmentConfig.apartmentType,
				data : apartmentConfig
			});
		},
		uploadPastWork: function(pastWork) {
			return $http({
				method : "post",
				url : "pastWork/upload",
				data : pastWork
			});
		}
	}
}] );

services.service('pastWorkService',['$http', function($http){
	return {
		getAllPastWork: function() {
			return $http({
				method : "get",
				url : "pastWork",
			});
		},
		getPastWorkById: function(id) {
			return $http({
				method : "get",
				url : "pastWork/" + id,
			});
		},
		uploadApartment: function(apartmentConfig) {
			return $http({
				method : "post",
				url : "config/apartment/" + apartmentConfig.apartmentType,
				data : apartmentConfig
			});
		}
	}
}] );

services.service('apartmentService',['$http', function($http){
	return {
		getAllApartments: function() {
			return $http({
				url: 'apartments',
				method: 'get'
			});
		},
		getAllApartments: function(apartmentName) {
			return $http({
				url: 'apartments/' + apartmentName,
				method: 'get'
			});
		}
	}
}] );
