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
decortFilters.filter("formatPrice", function() {
  return function(price, digits, thoSeperator, decSeperator, bdisplayprice) {
    var i;
    digits = (typeof digits === "undefined") ? 2 : digits;
    bdisplayprice = (typeof bdisplayprice === "undefined") ? true : bdisplayprice;
    thoSeperator = (typeof thoSeperator === "undefined") ? "." : thoSeperator;
    decSeperator = (typeof decSeperator === "undefined") ? "," : decSeperator;
    price = price.toString();
    var _temp = price.split(".");
    var dig = (typeof _temp[1] === "undefined") ? "00" : _temp[1];
    if (bdisplayprice && parseInt(dig,10)===0) {
        dig = "-";
    } else {
        dig = dig.toString();
        if (dig.length > digits) {
            dig = (Math.round(parseFloat("0." + dig) * Math.pow(10, digits))).toString();
        }
        for (i = dig.length; i < digits; i++) {
            dig += "0";
        }
    }
    var num = _temp[0];
    var s = "",
        ii = 0;
    for (i = num.length - 1; i > -1; i--) {
        s = ((ii++ % 3 === 2) ? ((i > 0) ? thoSeperator : "") : "") + num.substr(i, 1) + s;
    }
    return s + decSeperator + dig;
}
})