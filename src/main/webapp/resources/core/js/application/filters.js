var decortFilters = angular.module('decorpot');

decortFilters.filter('underscoreless', function () {
	  return function (input) {
	      return input.replace(/_/g, ' ');
	  };
	});

decortFilters.filter('addunderscore', function () {
	  return function (input) {
		  return input.replace(/\s+/g, '_');  
	  };
	});

decortFilters.filter('roundoffnumber', function () {
	  return function (input) {
		  if(input > 100000){
			  input = input/100000;
			  return Math.round(input * 100) / 100 + ' lakhs';
		  }else if(input > 10000){
			  input = input/10000;
			  return Math.round(input * 100) / 100 + 'thousands';
		  }else{
			  return input;
		  }
			  
	  };
	});