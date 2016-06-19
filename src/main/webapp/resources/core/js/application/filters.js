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

decortFilters.filter('capitalize', function() {
    return function(input, all) {
        var reg = (all) ? /([^\W_]+[^\s-]*) */g : /([^\W_]+[^\s-]*)/;
        return (!!input) ? input.replace(reg, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();}) : '';
      }
    });
decortFilters.filter("displayprice", function() {
  return function(input,comma) {
    if (isNaN(parseFloat(input))) return "";
    comma = (typeof comma==='undefined') ? "." : ",";
    input = Math.round(parseFloat(input)*100)/100;
    var d = input.toString().split(".");
    if (d.length===1) return input+comma+"-";
    if (d[1].length<2) return input+"0";
    return input;
  }
})