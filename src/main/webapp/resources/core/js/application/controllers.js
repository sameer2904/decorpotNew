/**
 * @author govardhan
 */

var decorpotCtrls = angular.module('decorpot');

decorpotCtrls.controller('homeController', [ '$scope', function($scope) {
    var timer; //timer for splash screen
    //create splash screen animation
    function splashRotator() {
        var cur = $('#jSplash').children('.selected');
        var next = $(cur).next();

        if ($(next).length !== 0) {
            $(next).addClass('selected');
        } else {
            $('#jSplash').children('section:first-child').addClass('selected');
            next = $('#jSplash').children('section:first-child');
        }

        $(cur).removeClass('selected').fadeOut(800, function () {
            $(next).fadeIn(800);
        });
    }
    //calling jPreLoader
    $('body').jpreLoader({
        splashID: "#jSplash",
        loaderVPos: '70%',
        autoClose: true,
        closeBtnText: "Let's Begin!",
        splashFunction: function () {
            //passing Splash Screen script to jPreLoader
            $('#jSplash').children('section').not('.selected').hide();
            $('#jSplash').hide().fadeIn(800);

            timer = setInterval(function () {
                splashRotator();
            }, 4000);
        }
    }, function () { //callback function
        clearInterval(timer);
    });

    //Carousel
    $('.carousel, .carousel-proj').carousel({
        interval: 6000,
        pause: "false"
    });
    var item = $('.carousel .item');
    var wHeight = $(window).width() * 0.6; // Maintain aspect ratio
// alert(wHeight)
    item.height(wHeight);
    item.addClass('full-screen');

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
        wHeight = $(window).height();
        item.height(wHeight);
    });

//Preheader
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

//Select steps
$('#section-steps > div > div > div:nth-child(2) > div > ul > li').on('click', function () {
    $('#section-steps > div > div > div:nth-child(2) > div > ul > li').removeClass('active');
    $('#section-steps > div > div > div:nth-child(2) > div > ul > li:nth-child(' + parseInt($(this).index() + 1) + ')').addClass('active');
    $('.v-border').css('opacity', '0');
    $(this).find('.v-border').fadeTo(150, 1);
    // $(this).parent('li').children().find('div').css('opacity','1');
    // $(this).parent('li').find('.v-border').css('opacity','1');    
    $('#tab1,#tab2,#tab3,#tab4').hide();
    $('#tab' + parseInt($(this).index() + 1)+'').show();
});

//Testimonial carousel
$("#testimonial-carousel").owlCarousel({
    items: 2,
    itemsDesktop: [1199, 2],
    itemsDesktopSmall: [980, 2],
    itemsTablet: [768, 1],
    itemsTabletSmall: false,
    itemsMobile: [479, 1],
    navigation: false,
});
//linking from index to others through ajax
$('#navbar > ul > li:nth-child(6) > a').click(function (){
    $.ajax({url: "contact.html", success: function(result){
            $('main').html(result);
        }});
})
} ]);