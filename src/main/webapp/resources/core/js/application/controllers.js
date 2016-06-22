/**
 * @author sameer
 */

var decorpotCtrls = angular.module('decorpot');

decorpotCtrls.controller('navCtrl',['$scope', function($scope){
    $(window).resize(function(){
if ($(window).width() >= 767) {console.log('entered');
        $('#navbar').attr('mobileView','false');
                $(document).bind('scroll', function () {
            if (window.scrollY > 60) {
                $('.preHeader').css({'visibility':'visible'}).slideUp();
            } else {
                $('.preHeader').css({'visibility':'visible'}).slideDown();
            }
        });
    } 
    else {
        $('#navbar').attr('mobileView','true');console.log('true');
        $(document).unbind('scroll');
        $('.preHeader').css({'position':'fixed','visibility':'hidden'});
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
            
            $scope.kitchenTypes = ['L-shaped', 'u-shaped', 'parallel', 'island'];
            $scope.wardrobeTypes = ['2-door', '3-door', '4-door', '5-door', '6-door'];
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
                name: "",
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
    $scope.steps= function(item) {
    $scope.selected = item; 
};
 $scope.isActive = function(item) {
      return $scope.selected === item;
};
$scope.steps(1);

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
decorpotCtrls.controller('looksController', ['$scope', '$stateParams', 'spaceService', function($scope, $stateParams, spaceService) {
	$scope.space = $stateParams.looksCategory;
    if($stateParams.looksCategory != 'all') {
    	spaceService.getAllLooks($stateParams.looksCategory)
    	.then(function(data) {
    		var looks = data.data;
    		angular.forEach(looks, function(look, index) {
    			look.thumbNail = "https://s3-ap-southeast-1.amazonaws.com/decorpotreponew/spaces/475x270" + look.images[0];
    		});
    		$scope.looks = data.data;
    		
    		
    	})
    }
}]);

decorpotCtrls.controller('lookController', ['$scope', '$stateParams', 'spaceService', function($scope, $stateParams, spaceService) {
    var includesMap = {
    		living_dining : 'Base Price Includes tv unit',
    		kitchen: 'Base Price includes complete kitchen',
    		master_bedroom: 'Base Price included wardrobe',
    		guest_bedroom: 'Base Price Includes Wardrobe',
    		kids_bedroom: 'Base Price Includes Wardrobe'
    }
	var params = $stateParams.id.split('-');
	spaceService.getLookById(params[0], params[1])
	.then(function(data) {
		let total = 0,
		thumbnails = [],
		space = data.data;
		
		angular.forEach(space.images, function(i, index) {
			thumbnails.push({
				thumb: "https://s3-ap-southeast-1.amazonaws.com/decorpotreponew/spaces/475x270" + i,
				thumbId: i
			})
		});
		angular.forEach($scope.space.addons, function(a, index) {
			total+= a.price;
		});
		space.basePriceDescription = includesMap[params[0]]
		space.total = total;
		$scope.thumbnails = thumbnails;
		$scope.space = space;
		$scope.toggleObject = {
				item : 0
			};
		
	});
	
	$scope.getHdImage = function(imageId) {
    	$scope.hdImage = "https://s3-ap-southeast-1.amazonaws.com/decorpotreponew/spaces/885x503" + imageId;
    }
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
                                                  $scope.kitchenTypes = ['L-shaped', 'u-shaped', 'parallel', 'island'];
                                                  $scope.wardrobeTypes = ['2-door','3-door','4-door','5-door', '6-door'];
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
                                                    	  apartmentConfig.kidsBedroom.length = $scope.kidLength;
                                                    	  apartmentConfig.kidsBedroom.width = $scope.kidWidth;
                                                    	  
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
    $scope.name = $filter('underscoreless')($stateParams.apartmentName);
	apartmentService.getAllFloorPlans($scope.name)
	.success(function(data) {
		$scope.data = data;
	});
}]);
decorpotCtrls.controller('apartmentPackagesController', ['$scope','apartmentService','$stateParams', function($scope, apartmentService,$stateParams) {
    $scope.apartmentType = $stateParams.apartmentType;
    $scope.floorplanId = $stateParams.floorplanId;
    
    apartmentService.getAllPAckagesForFloorPlan($stateParams.apartmentType, $stateParams.floorplanId)
    .success(function(data) {
    	
    	data.map(function(d) {
    		var url = d.spaceIds.map(function(spaceId) {
    				return spaceId.id + '-' + spaceId.space;
        		}).join();
    		d.url = url;
    	})
    		
    	$scope.packages = data;
    })
    .error(function(error) {
    	console.log(error);
    });
}]);

decorpotCtrls.controller('apartmentController', ['$scope','apartmentService','$stateParams', '$filter', 'spaceService', function($scope, apartmentService,$stateParams, $filter, spaceService) {
   console.log($stateParams);
   $scope.apartmentName = $stateParams.apartmentName;
   $scope.apartmentType = $stateParams.apartmentType;
   $scope.basePrice = $stateParams.basePrice;
   $scope.tabs = $stateParams.spaceIds.split(',').map(function(space){
  		var spaceMap = space.split('-');
  		return {
  			spaceName: spaceMap[1],
  			spaceId: spaceMap[0]
  		}
  	});
   $scope.toggleObject = {
			item : 0
		};
   $scope.toggleObjectSpace = {
			item : 0
		};
   $scope.getSpaceById = function(spaceMap) {
	   spaceService.getLookById(spaceMap.spaceName, spaceMap.spaceId)
	   		.success(function(data) {
	   			var total = 0,
	   			addons = [{name: 'Base Price', price: data.basePrice}],
	   			thumbnails = [];
	   			angular.forEach(data.addons, function(v, index) {
	   				addons.push(v);
	   			})
	   			
	   			$scope.addons = addons
	   			angular.forEach($scope.addons, function(a, index) {
	   				total += a.price;
	   			})
	   			$scope.total = total;
	   			$scope.space = data;
	   			console.log($scope);
	   			angular.forEach(data.images, function(i, index) {
	   				thumbnails.push({
	   					thumb: "https://s3-ap-southeast-1.amazonaws.com/decorpotreponew/spaces/475x270" + i,
	   					thumbId: i
	   				})
	   			});
	   			$scope.thumbnails = thumbnails;
	   			
	   		})
	   		$scope.getHdImage = function(imageId) {
	   		    	$scope.hdImage = "https://s3-ap-southeast-1.amazonaws.com/decorpotreponew/spaces/885x503" + imageId;
	   		    }
   }
   
}]);
decorpotCtrls.controller('pastWorkController', ['$scope','$stateParams','Lightbox', 'pastWorkService', function($scope, $stateParams, Lightbox, pastWorkService) {
    $scope.pastWork = $stateParams.id;
    pastWorkService.getPastWorkById($stateParams.id)
    					.success(function(data){
    						$scope.images = data.imageUrls;
    						$scope.pastWork = data.apartmentName;
    					})
    
    $scope.openLightboxModal = function (index) {
    Lightbox.openModal($scope.images, index);
  };
}]);
