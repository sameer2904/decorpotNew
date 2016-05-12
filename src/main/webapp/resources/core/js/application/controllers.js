/**
 * @author sameer
 */

var decorpotCtrls = angular.module('decorpot');

decorpotCtrls.controller('navCtrl',['$scope', function($scope){
    $(window).resize(function(){
if ($(window).width() > 767) {
        $('#navbar').attr('mobileView','false')
                $(document).bind('scroll', function () {
            if (window.scrollY > 60) {
                $('.preHeader').slideUp();
            } else {
                $('.preHeader').slideDown();
            }
        });
    } else {
        $('#navbar').attr('mobileView','true')
        $('.preHeader').hide();
    }
})
$(window).resize();
}])
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
            $scope.addons = ["Bed", "Bed with Side Table", "Book Shelf", "Cabinets", 
                             "Chest of Drawers", "Crockery Unit", "Dining Table", "Drawer",
                             "Dresser", "False Ceiling", "False ceiling", "Loft", "Mirror Paneling",
                             "Open Shelf", "Open Shelves", "Partition and Mirror wall paneling", "Rafters",
                             "Side Table", "Sofa Set with Table", "Study Table", "Study Unit", "TV Unit", 
                             "Top Paneling", "Utility", "crockery", "false ceiling with rafter", "puja unit",
                             "shoe rack", "tall unit"];
            
            $scope.selectedThemes = [];
            $scope.items = [];
            
            $scope.add = function () {
              $scope.items.push({ 
                addon: "",
                price:0
              });
            };
            $scope.uploadData = function() {
                var spaceData = {};
                spaceData.title = $scope.title;
                spaceData.description = $scope.description;
                spaceData.basePrice = $scope.basePrice;
                spaceData.ht = $scope.ht;
                spaceData.wdth = $scope.wdth;
                spaceData.themes = $scope.selectedThemes;
                spaceData.addons = $scope.items;
                
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


decorpotCtrls.controller('homeController', [ '$scope','$rootScope','$state', 'pastWorkService', function($scope, $rootScope, $state, pastWorkService) {
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
    $scope.owlCarouselPastWork1 = function(){
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
            navigation: false
        });
    };
    $scope.owlCarouselPastWork2 = function(index){
        console.log(index);
        $('.owl-carousel2').owlCarousel({
            items: 4,
            loop:true,
            slideSpeed : 1000,
            autoPlay: 3000,
            itemsDesktop: [1199, 4],
            itemsDesktopSmall: [980, 3],
            itemsTablet: [768, 3],
            itemsTabletSmall: false,
            itemsMobile: [479, 1],
            navigation: false
        });
    };
    
    pastWorkService.getAllPastWork()
    .success(function(data) {
        
        var len = data.length;
        $scope.pastWorkData1 = data.slice(0, len/2);
        $scope.pastWorkData2 = data.slice(len/2, len - 1);
        
    });
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
}]);


// contact controller
decorpotCtrls.controller('contactController', ['$scope', 'uploadService',function ($scope, uploadService) {
        $scope.submitEnquiry = function() {
            $scope.enquiryForm.floorPlan = $scope.file.name;
            uploadService.uploadEnquiry($scope.enquiryForm)
            .success(function(data){
                Upload.upload({
                    url: '/enquiry/floorPlan',
                    data: {
                        file: $scope.file,
                    }
                });
            })
        }
                                            }]);
// project controller
decorpotCtrls.controller('pastWorksController', ['$scope', 'pastWorkService', function($scope, pastWorkService) {
    pastWorkService.getAllPastWork()
    .success(function(data){
        $scope.pastWorks = data;
    })
}]);
// looks controller
decorpotCtrls.controller('looksController', ['$scope', '$stateParams', function($scope, $stateParams) {
    $scope.looks = [{
            "id":"1",
            "mainImage":"resources/core/images/apartments/apartments.jpg",
            "apartmentName":"Shobha Apartment"
        },
        {
            "id":"2",
            "mainImage":"resources/core/images/apartments/apartments.jpg",
            "apartmentName":"Shobha Apartment"
        },
        {
            "id":"3",
            "mainImage":"resources/core/images/apartments/apartments.jpg",
            "apartmentName":"Shobha Apartment"
        },
        {
            "id":"4",
            "mainImage":"resources/core/images/apartments/apartments.jpg",
            "apartmentName":"Shobha Apartment"
        },
        {
            "id":"5",
            "mainImage":"resources/core/images/apartments/apartments.jpg",
            "apartmentName":"Shobha Apartment"
        },
        {
            "id":"6",
            "mainImage":"resources/core/images/apartments/apartments.jpg",
            "apartmentName":"Shobha Apartment"
        }]
}]);

decorpotCtrls.controller('uploadPastWorkController', ['$scope', 'Upload', 'uploadService', function ($scope, Upload, uploadService) {

    $scope.uploadData = function() {
        var pastProjectData = {};
        pastProjectData.apartmentName = $scope.apartmentName;
        pastProjectData.mainImage = $scope.mainImage;
        pastProjectData.images = [];
        angular.forEach($scope.files, (file) => {
            pastProjectData.images.push(file.name);
        } );
        
        uploadService.uploadPastWork(pastProjectData)
        .success(function(data) {
            angular.forEach($scope.files, f => {
                Upload.upload({
                    url: 'pastWork/upload/image',
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
}]);

decorpotCtrls.controller('uploadApartmentController', ['$scope', 'Upload', 'uploadService', function($scope, Upload, uploadService) {
    var apartmentConfig = {};
    $scope.uploadData = function() {
        apartmentConfig.apartmentName = $scope.apartmentName;
        apartmentConfig.address = $scope.address;
        apartmentConfig.image = $scope.file.name;
        uploadService.uploadApartment(apartmentConfig)
        .success(function(data) {
            Upload.upload({
                url: 'config/apartmentImage',
                data:{
                file: $scope.file,
                }
           });
        })
    }
}])
decorpotCtrls.controller('uploadFloorPlanController', [
                                              '$scope',
                                              'Upload', 'uploadService', 'apartmentService',
                                              function($scope, Upload, uploadService, apartmentService) {
                                                  $scope.kitchenTypes = ['L-shaped', 'u-shaped'];
                                                  $scope.wardrobeTypes = ['4-door', '6-door'];
                                                  $scope.apartmentTypes = ['2bhk', '3bhk'];
                                                  apartmentService.getAllApartments()
                                                  .success(function(data) {
                                                      $scope.apartmentNames = data.map(function(value, index){
                                                          return value.apartmentName;
                                                      })
                                                  })
                                                  var apartmentConfig = {};
                                                  $scope.uploadData = function() {                                               	  
                                                	  apartmentConfig.apartmentType = $scope.apartmentType;
                                                	  apartmentConfig.apartmentName = $scope.apartmentName;
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
                                                	  
                                                	  uploadService.uploadFloorplan(apartmentConfig)
                                                	  .success(function(data) {
                                                		  Upload.upload({
                                                			  url: 'config/floorPlans/',
                                                			  data:{
                                                				  	file: $scope.file,
                                                			  	}
                                                		  	});
                                                	  })
                                                	  .error((data) => {
                                                      	alert("data not uploaded"); 
                                                      });
                                                  }
                                              }]);

decorpotCtrls.controller('apartmentsController', ['$scope','apartmentService', function($scope, apartmentService) {
     apartmentService.getAllApartments()
     .success(function(data){
         $scope.apartments = data;
     });

}]);

decorpotCtrls.controller('floorPlansController', ['$scope','apartmentService','$stateParams', '$filter', function($scope, apartmentService,$stateParams, $filter) {
	var name = $filter('underscoreless')($stateParams.apartmentName);
	apartmentService.getAllFloorPlans(name)
	.success(function(data) {
		$scope.floopPlans = data.apartmentBaseConfigs;
	});
}]);
decorpotCtrls.controller('apartmentPackagesController', ['$scope','apartmentService', function($scope, apartmentService) {
    $scope.packages = [{
        "apartmentName": "abc",
        "apartmentType": "2bhk",
        "basePrice": 100000,
        "mainImage": "resources/core/images/apartments/apartments.jpg",
        "spaceIds": "1,2,3,4,5"
    },
    {
        "apartmentName": "xyz",
        "apartmentType": "2bhk",
        "basePrice": 200000,
        "mainImage": "resources/core/images/apartments/apartments.jpg",
        "spaceIds": "1,2,3,4,5"
    }]
}]);

decorpotCtrls.controller('apartmentController', ['$scope','apartmentService','$stateParams', '$filter', function($scope, apartmentService,$stateParams, $filter) {
    alert();
}]);

decorpotCtrls.controller('spaceController', ['$scope','spaceService','$stateParams', '$filter', function($scope, spaceService,$stateParams, $filter) {
    alert();
}]);

decorpotCtrls.controller('spacesController', ['$scope','spaceService','$stateParams', '$filter', function($scope, spaceService,$stateParams, $filter) {
    alert();
}]);
