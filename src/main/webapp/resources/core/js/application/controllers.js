/**
 * @author sameer
 */

var decorpotCtrls = angular.module('decorpot');

decorpotCtrls.controller('uploadController', [
        '$scope',
        'Upload', 'uploadService',
        function ($scope, Upload, uploadService) {
        $scope.spaces = ['kitchen', 'kids_bedroom', 'master_bedroom',
                    'guest_bedroom', 'living_dinning'];

        $scope.themes = [{
            "id": "modern",
            "value": "modern",
            }, {
            "id": "contemprory",
            "value": "contemprory",
            }, {
            "id": "bakwas",
            "value": "bakwas"
            }];
            
            $scope.kitchenTypes = ['L-shaped', 'u-shaped'];
            $scope.wardrobeTypes = ['4-door', '6-door'];

            $scope.selectedThemes = [];
            $scope.uploadData = function() {
                var spaceData = {};
                spaceData.title = $scope.title;
                spaceData.description = $scope.description;
                spaceData.basePrice = $scope.basePrice;
                spaceData.ht = $scope.ht;
                spaceData.wdth = $scope.wdth;
                spaceData.themes = $scope.selectedThemes.join();
                
                spaceData.images = [];
                if($scope.spaceType == 'kitchen'){
                	spaceData.kitchenType = $scope.kitchenType;
                }else if($scope.spaceType.indexOf('bedroom') != -1){
                	spaceData.wardrobeType = $scope.wardrobeType;
                }
                
                angular.forEach($scope.files, (file) => {
                	spaceData.images.push(file.name);
                } );
                
                
                
                uploadService.uploadSpace($scope.spaceType, spaceData)
                    .success(function(data) {
                        angular.forEach($scope.files, f => {
                            Upload.upload({
                                url: 'upload/space/images',
                                data: {
                                    file: f,
                                }
                            });
                        });
                        
                    })
                    .error((data) => {
                    	alert("data not uploaded");
                    });
            }
        } ]);


decorpotCtrls.controller('homeController', [ '$scope', function($scope) {
    // var timer; // timer for splash screen
    // create splash screen animation
    // function splashRotator() {
    // var cur = $('#jSplash').children('.selected');
    // var next = $(cur).next();

    // if ($(next).length !== 0) {
    // $(next).addClass('selected');
    // } else {
    // $('#jSplash').children('section:first-child').addClass('selected');
    // next = $('#jSplash').children('section:first-child');
    // }

    // $(cur).removeClass('selected').fadeOut(800, function () {
    // $(next).fadeIn(800);
    // });
    // }
    // calling jPreLoader
    // $('body').jpreLoader({
    // splashID: "#jSplash",
    // loaderVPos: '70%',
    // autoClose: true,
    // closeBtnText: "Let's Begin!",
    // splashFunction: function () {
    // // passing Splash Screen script to jPreLoader
    // $('#jSplash').children('section').not('.selected').hide();
    // $('#jSplash').hide().fadeIn(800);

    // timer = setInterval(function () {
    // splashRotator();
    // }, 4000);
    // }
    // }, function () { // callback function
    // clearInterval(timer);
    // });
    // Carousel
    $('.carousel, #mycarousel-proj').carousel({
        interval: 6000,
        pause: "false"
    });
    var item = $('.carousel .item');
    var wHeight = $(window).width() * 0.6; // Maintain aspect ratio
    function carReuse() {
        if (window.innerHeight < wHeight) {
            item.height(window.innerHeight);
        } else {
            item.height(wHeight);
        }
    }
    item.addClass('full-screen')
    carReuse()

    $('.carousel img').each(function () {
        var source = $(this).attr('src');
        var color = $(this).attr('data-color');
        $(this).parent().css({
            'background-image': 'url(' + source + ')',
            'background-color': color
        });
        $(this).remove();
    });

    $(window).on('resize', function () {
        carReuse()
    });
    // Preheader
    if ($(window).width() > 767) {
        $(document).bind('scroll', function () {
            if (window.scrollY > 60) {
                $('.preHeader').slideUp();
            } else {
                $('.preHeader').slideDown();
            }
        });
    } else {
        $('.preHeader').hide();
    }

    // Select steps
    $('#section-steps > div > div > div:nth-child(2) > div > ul > li').on('click', function () {
        $('#section-steps > div > div > div:nth-child(2) > div > ul > li').removeClass('active');
        $('#section-steps > div > div > div:nth-child(2) > div > ul > li:nth-child(' + parseInt($(this).index() + 1) + ')').addClass('active');
        $('.v-border').css('opacity', '0');
        $(this).find('.v-border').fadeTo(150, 1);
        // $(this).parent('li').children().find('div').css('opacity','1');
        // $(this).parent('li').find('.v-border').css('opacity','1');
        $('#tab1,#tab2,#tab3,#tab4').hide();
        $('#tab' + parseInt($(this).index() + 1) + '').show();
    });

    // Testimonial carousel
    $("#testimonial-carousel").owlCarousel({
        items: 2,
        itemsDesktop: [1199, 2],
        itemsDesktopSmall: [980, 2],
        itemsTablet: [768, 1],
        itemsTabletSmall: false,
        itemsMobile: [479, 1],
        navigation: false,
    });

// linking from index to others through ajax
    $('#navbar > ul > li:nth-child(6) > a').click(function () {
        $.ajax({
            url: "contact.html",
            success: function (result) {
                $('main').html(result);
            }
        });
    })
    $('.owl-carousel1').owlCarousel({
        items: 4,
        loop:true,
        slideSpeed : 1000,
        autoPlay: 3000,
        itemsDesktop: [1199, 4],
        itemsDesktopSmall: [980, 3],
        itemsTablet: [768, 3],
        itemsTabletSmall: false,
        itemsMobile: [479, 1],
        navigation: false,
})
    $('.owl-carousel2').owlCarousel({
        items: 4,
        loop:false,
        slideSpeed : 950,
        autoPlay: 3000,
        itemsDesktop: [1199, 4],
        itemsDesktopSmall: [980, 3],
        itemsTablet: [768, 3],
        itemsTabletSmall: false,
        itemsMobile: [479, 1],
        navigation: false,
    });
}]);


// contact controller
decorpotCtrls.controller('contactController', ['$scope',function ($scope) {
        document.getElementById("uploadBtn").onchange = function () {
            document.getElementById("uploadFile").value = this.value;
        };
                                            }]);
//project controller
decorpotCtrls.controller('projectController', ['$scope', function ($scope) {

}]);


decorpotCtrls.controller('uploadApartmentController', [
                                              '$scope',
                                              'Upload', 'uploadService',
                                              function($scope, Upload, uploadService) {
                                            	  $scope.kitchenTypes = ['L-shaped', 'u-shaped'];
                                                  $scope.wardrobeTypes = ['4-door', '6-door'];
                                                  $scope.apartmentTypes = ['2bhk', '3bhk'];
                                                  var apartmentConfig = {};
                                                  $scope.uploadData = function() {
                                                	  apartmentConfig.apartmentName = $scope.apartmentName;
                                                	  apartmentConfig.apartmentType = $scope.apartmentType;
                                                	  apartmentConfig.planName = $scope.planName;
                                                	  apartmentConfig.kitchen = {};
                                                	  apartmentConfig.kitchen.kitchenType = $scope.kitchenType;
                                                	  apartmentConfig.kitchen.length = $scope.kLength;
                                                	  apartmentConfig.kitchen.width = $scope.kWidth;
                                                	  apartmentConfig.masterBedroom = {};
                                                	  apartmentConfig.masterBedroom.wardrobeType = $scope.mWardrobeType;
                                                	  apartmentConfig.masterBedroom.length = $scope.mLength;
                                                	  apartmentConfig.masterBedroom.width = $scope.mWidth;
                                                	  if($scope.apartmentType == '2bhk'){
                                                		  apartmentConfig.otherBedroom = {};
                                                    	  apartmentConfig.otherBedroom.wardrobeType = $scope.oWardrobeType;
                                                    	  apartmentConfig.otherBedroom.length = $scope.oLength;
                                                    	  apartmentConfig.otherBedroom.width = $scope.oWidth;
                                                	  }else {
                                                		  apartmentConfig.guestBedroom = {};
                                                    	  apartmentConfig.guestBedroom.wardrobeType = $scope.oWardrobeType;
                                                    	  apartmentConfig.guestBedroom.length = $scope.oLength;
                                                    	  apartmentConfig.guestBedroom.width = $scope.oWidth;
                                                    	  apartmentConfig.kidsBedroom = {};
                                                    	  apartmentConfig.kidsBedroom.wardrobeType = $scope.kWardrobeType;
                                                    	  apartmentConfig.kidsBedroom.length = $scope.kLength;
                                                    	  apartmentConfig.kidsBedroom.width = $scope.kWidth;
                                                    	  
                                                	  }
                                                	  apartmentConfig.floorPlan = $scope.file.name;
                                                	  
                                                	  uploadService.uploadApartment(apartmentConfig)
                                                	  .success(function(data) {
                                                		  Upload.upload({
                                                			  url: 'config/floorPlans/',
                                                			  data:{
                                                				  	file: f,
                                                			  	}
                                                		  	});
                                                	  })
                                                	  .error((data) => {
                                                      	alert("data not uploaded");
                                                      });
                                                  }
                                              }]);
