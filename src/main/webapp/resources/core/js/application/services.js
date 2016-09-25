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
				url : "pastWork/backend/upload",
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

services.service('userService', ['$http', function($http) {
	 this.isLoggedIn = localStorage.getItem('isLoggedIn') || false;
	
	return {
		createUser: function(user) {
			return $http({
				url: 'users',
				method: 'post',
				data: user
			})
		},
		login: function(user) {
			return $http({
				url: 'users/login',
				method: 'post',
				data: user
			})
		},
		getInternalUsers: function() {
			return $http({
				url: 'users/internal',
				method: 'get'
			})
		},
		isUserLoggedIn: function() {
			return this.isLoggedIn;
		},
		setLoggedIn: function() {
			this.isLoggedIn = true;
			localStorage.setItem('isLoggedIn', isLoggedIn);
		}
	}
}]);

services.service('taskService', ['$http', function($http) {
	return {
		getAllTask: function() {
			return $http({
				url: 'tasks',
				method: 'get'
			})
		},
		getTaskByCustomer: function(id) {
			return $http({
				url: 'tasks/customers/' + id,
				method: 'get'
			})
		},
		reassignTask: function(task) {
			return $http({
				url: 'tasks/reassign/' + task.taskId,
				method: 'put',
				data: task
			})
		},
		
		changeStatus: function(taskId, status) {
			return $http({
				url: 'tasks/status/' + taskId + '/' + status,
				method: 'put'
			})
		}
	}
}])

services.service('customerService', ['$http', function($http) {
	return {
		createCustomer: function(customer) {
			return $http({
				url: 'customers',
				method: 'post',
				data: customer
			})
		},
		
		getCustomerById: function(id) {
			return $http({
				url: 'customers/' + id,
				method: 'get'
			})
		},
		
		getAllCustomersTaskSummary: function() {
			return $http({
				url: 'customers/tasks/details',
				method: 'get'
			})
		},
		
		updateCustomer: function(customer) {
			return $http({
				url: 'customers',
				method: 'put',
				data: customer
			})
		}
	}
}])



