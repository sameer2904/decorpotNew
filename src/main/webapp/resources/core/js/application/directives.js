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

directives.directive('preventRightClick', [ function() {
	document.oncontextmenu = function (e) {
	       if(e.target.hasAttribute('right-click')) {
	           return false;
	       }
	    };
	    return function(scope,el,attrs){
	        el.bind('contextmenu',function(e){
	        	 return false;            
	        }) ;
	    };
} ]);

directives.directive('imageonload', function() {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            element.bind('load', function() {
            	 $('#mydiv1').hide();
            });
        }
    };
});

directives
.directive(
		"bnLazySrc",
		function($window, $document) {
			// I manage all the images that are currently being
			// monitored on the page for lazy loading.
			var lazyLoader = (function() {
				// list of images that lazy-loading and have yet to be
				// rendered
				var images = [];

				// render timer for lazy loading images so that
				// DOM-querying (for
				// offsets) is chunked in groups
				var renderTimer = null;
				var renderDelay = 100;

				// cache the window element as a jQuery reference
				var win = $($window);

				// cache the document document height so that we can
				// respond to changes
				// in the height due to dynamic content
				var doc = $document;
				var documentHeight = doc.height();
				var documentTimer = null;
				var documentDelay = 2000;

				// determine if window dimension events (ie. resize,
				// scroll) are
				// currenlty being monitored for changes
				var isWatchingWindow = false;

				// ---
				// PUBLIC METHODS.
				// ---

				// I start monitoring the given image for visibility
				// and then render it when necessary.
				function addImage(image) {
					images.push(image);
					if (!renderTimer)
						startRenderTimer();
					if (!isWatchingWindow)
						startWatchingWindow();
				}

				// I remove the given image from the render queue.
				function removeImage(image) {
					// Remove the given image from the render queue.
					for (var i = 0; i < images.length; i++) {
						if (images[i] === image) {
							images.splice(i, 1);
							break;
						}
					}

					// If removing the given image has cleared the
					// render queue, we can
					// stop monitoring window and image queue
					if (!images.length) {
						clearRenderTimer();
						stopWatchingWindow();
					}
				}

				// ---
				// PRIVATE METHODS.
				// ---

				// I check the document height to see if it's changed.
				function checkDocumentHeight() {
					// If render time is currently active: don't bother
					// getting the
					// document height - it won't actually do anything
					if (renderTimer)
						return;

					var currentDocumentHeight = doc.height();

					// Cancel if height has not changed - no more images
					// could have come
					// into view
					if (currentDocumentHeight === documentHeight)
						return;

					// Cache the new document height.
					documentHeight = currentDocumentHeight;

					startRenderTimer();
				}

				// I check the lazy-load images that have yet to be
				// rendered.
				function checkImages() {
					// Log here so we can see how often this gets called
					// during page
					// activity.
					console.log("Checking for visible images...");

					var visible = [];
					var hidden = [];

					// Determine the window dimensions.
					var windowHeight = win.height();
					var scrollTop = win.scrollTop();

					// Calculate the viewport offsets.
					var topFoldOffset = scrollTop;
					var bottomFoldOffset = (topFoldOffset + windowHeight);

					// Query the DOM for layout and seperate the
					// images into two different categories: those
					// that are now in the viewport and those that
					// still remain hidden.
					for (var i = 0; i < images.length; i++) {
						var image = images[i];
						if (image.isVisible(topFoldOffset,
								bottomFoldOffset)) {
							visible.push(image);
						} else {
							hidden.push(image);
						}
					}

					// Update the DOM with new image source values.
					for (var i = 0; i < visible.length; i++) {
						visible[i].render();
					}

					// Keep the still-hidden images as the new image
					// queue to be
					// monitored.
					images = hidden;

					// Clear the render timer so that it can be set
					// again in response to
					// window changes.
					clearRenderTimer();

					// If we've rendered all the images, then stop
					// monitoring the window
					// for changes.
					if (!images.length) {
						stopWatchingWindow();
					}
				}

				// I clear the render timer so that we can easily
				// check to see if the timer is running.
				function clearRenderTimer() {
					clearTimeout(renderTimer);
					renderTimer = null;
				}

				// I start the render time, allowing more images to
				// be added to the images queue before the render
				// action is executed.
				function startRenderTimer() {
					renderTimer = setTimeout(checkImages, renderDelay);
				}

				// I start watching the window for changes in dimension.
				function startWatchingWindow() {
					isWatchingWindow = true;

					// Listen for window changes.
					win.on("resize.bnLazySrc", windowChanged);
					win.on("scroll.bnLazySrc", windowChanged);

					// Set up a timer to watch for document-height
					// changes.
					documentTimer = setInterval(checkDocumentHeight,
							documentDelay);
				}

				// I stop watching the window for changes in dimension.
				function stopWatchingWindow() {
					isWatchingWindow = false;

					// Stop watching for window changes.
					win.off("resize.bnLazySrc");
					win.off("scroll.bnLazySrc");

					// Stop watching for document changes.
					clearInterval(documentTimer);
				}

				// I start the render time if the window changes.
				function windowChanged() {
					if (!renderTimer)
						startRenderTimer();
				}

				// Return the public API.
				return ({
					addImage : addImage,
					removeImage : removeImage
				});

			})();

			// ------------------------------------------ //
			// ------------------------------------------ //

			// representation of a single lazy-load image
			function LazyImage(element) {
				// I am the interpolated LAZY SRC attribute of the image
				// as reported by
				// AngularJS.
				var source = null;

				// I determine if the image has already been rendered
				// (ie, that it has
				// been exposed to the
				// viewport and the source had been loaded).
				var isRendered = false;

				// cached height of the element (we assume that the
				// image doesn't change
				// height over time)
				var height = null;

				// ---
				// PUBLIC METHODS.
				// ---

				// I determine if the element is above the given fold of
				// the page.
				function isVisible(topFoldOffset, bottomFoldOffset) {
					// If the element is not visible because it is
					// hidden, don't bother
					// testing it.
					if (!element.is(":visible"))
						return (false);

					// If the height has not yet been calculated, the
					// cache it for the
					// duration of the page.
					if (height === null) {
						height = element.height();
					}

					// Update the dimensions of the element.
					var top = element.offset().top;
					var bottom = (top + height);

					// Return true if the element is:
					// 1. The top offset is in view.
					// 2. The bottom offset is in view.
					// 3. The element is overlapping the viewport.
					return (((top <= bottomFoldOffset) && (top >= topFoldOffset))
							|| ((bottom <= bottomFoldOffset) && (bottom >= topFoldOffset)) || ((top <= topFoldOffset) && (bottom >= bottomFoldOffset)));
				}

				// move cached source into the live source
				function render() {
					isRendered = true;
					renderSource();
				}

				// set the interpolated source value reported by the
				// directive /
				// AngularJS
				function setSource(newSource) {
					source = newSource;
					if (isRendered)
						renderSource();
				}

				// ---
				// PRIVATE METHODS.
				// ---

				// load the lazy source value into the actual source
				// value of the image
				// element.
				function renderSource() {
					element[0].src = source;
				}

				// Return the public API
				return ({
					isVisible : isVisible,
					render : render,
					setSource : setSource
				});

			}

			// ------------------------------------------ //
			// ------------------------------------------ //

			// bind the UI events to the scope.
			function link($scope, element, attributes) {
				var lazyImage = new LazyImage(element);

				// Start watching the image for changes in its visibility.
				lazyLoader.addImage(lazyImage);

				// Since the lazy-src will likely need some sort of string
				// interpolation, we don't want to
				attributes.$observe("bnLazySrc", function(newSource) {
					lazyImage.setSource(newSource);
				});

				// When the scope is destroyed, we need to remove the image from the
				// render queue.
				$scope.$on("$destroy", function() {
					lazyLoader.removeImage(lazyImage);
				});
			}

			// Return the directive configuration.
			return ({
				link : link,
				restrict : "A"
			});
		});

//directive for pre-header
directives.directive("scroll", function ($window) {
    return function(scope, element, attrs) {
        angular.element($window).bind("scroll", function() {
             if (this.pageYOffset >= 60) {
                 $('.preHeader').slideUp();
             } else {
                 $('.preHeader').slideDown();
                 
             }
            scope.$apply();
        });
    };
});
//directive for nested nav
directives.directive('windowResize',function($window){
	return function(scope,element,attrs){
		angular.element($window).bind('resize', function() {
                resize();
                console.log(angular.element.attr('mobileView','true'))
            })
		function resize(){
			if($window.innerWidth<767){
                	element.attr('mobileView','true');
                }
                else{
                	element.attr('mobileView','false');
                }
		}
		resize();
}
})