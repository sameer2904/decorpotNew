/**
 * @author sameer
 */

var directives = angular.module('decorpot');

directives.directive('myMap', function() {
    // directive link function
    var link = function(scope, element, attrs) {
        var map, infoWindow;
        var markers = [];
        
        // map config
        var mapOptions = {
            center: new google.maps.LatLng(12.908815,77.644651),
            zoom: 18,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            scrollwheel: false
        };
        
        // init the map
        function initMap() {
            if (map === void 0) {
                map = new google.maps.Map(element[0], mapOptions);
            }
        }    
        
        // place a marker
        function setMarker(map, position, title, content) {
            var marker;
            var markerOptions = {
                position: position,
                map: map,
                title: title,
                icon: 'https://maps.google.com/mapfiles/ms/icons/green-dot.png'
            };

            marker = new google.maps.Marker(markerOptions);
            markers.push(marker); // add marker to array
            
            google.maps.event.addListener(marker, 'click', function () {
                // close window if not undefined
                if (infoWindow !== void 0) {
                    infoWindow.close();
                }
                // create new window
                var infoWindowOptions = {
                    content: content
                };
                infoWindow = new google.maps.InfoWindow(infoWindowOptions);
                infoWindow.open(map, marker);
            });
        }
        
        // show the map and place some markers
        initMap();
        
        setMarker(map, new google.maps.LatLng(12.908815,77.644651), 'DECORPOT.COM', '23rd Cross Rd, Garden Layout, Sector 2, HSR Layout, Bengaluru, Karnataka 560102');
    };
    
    return {
        restrict: 'A',
        template: '<div id="gmaps"></div>',
        replace: true,
        link: link
    };
});

directives.directive("checkboxGroup", function() {
    return {
        restrict: "A",
        link: function(scope, elem, attrs) {
            // Determine initial checked boxes
            if (scope.themes.indexOf(scope.theme.id) !== -1) {
                elem[0].checked = true;
            }

            // Update array on click
            elem.bind('click', function() {
                var index = scope.themes.indexOf(scope.theme.id);
                // Add if checked
                if (elem[0].checked) {
                    if (index === -1) scope.selectedThemes.push(scope.theme.id);
                }
                // Remove if unchecked
                else {
                    if (index !== -1) scope.selectedThemes.splice(index, 1);
                }
                // Sort and update DOM display
                scope.$apply(scope.themes.sort(function(a, b) {
                    return a - b
                }));
            });
        }
    }
});
