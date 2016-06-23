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
		},
		uploadEnquiry: function(enquiry) {
			return $http({
				method : "post",
				url : "enquiry",
				data : enquiry
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
		getAllFloorPlans: function(apartmentName) {
			return $http({
				url: 'apartments/' + apartmentName,
				method: 'get'
			});
		},
		getAllPAckagesForFloorPlan(apartmentType, floorPlanId) {
			return $http({
				method: 'get',
				url: 'apartments/' + apartmentType + '/' + floorPlanId
			})
		}
	}
}] );

services.service('spaceService',['$http', function($http){
	return {
		getAllLooks: function(space) {
			return $http({
				url: 'space/' + space,
				method: 'get'
			});
		},
		getLookById: function(space, id) {
			return $http({
				url: 'space/' + space + '/' + id,
				method: 'get'
			})
		}
	}
}] );
