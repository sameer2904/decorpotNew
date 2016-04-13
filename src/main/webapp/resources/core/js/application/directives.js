/**
 * 
 */

var decortDirectives = angular.module('decorpot');

decortDirectives.directive("fileBind", function() {
  return function( scope, elm, attrs ) {
    elm.bind("change", function( evt ) {
      scope.$apply(function() {
        scope[ attrs.fileBind ] = evt.target.files;
      });
    });
  };
});