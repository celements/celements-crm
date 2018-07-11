(function(window, undefined) {
  "use strict";
  
  var initGoogleMaps = function() {
    // The location of Uluru
    var uluru = {lat: -25.344, lng: 131.036};
    // The map, centered at Uluru
    var map = new google.maps.Map($('googleMapsContainer'), {zoom: 4, center: uluru});
    // The marker, positioned at Uluru
    var marker = new google.maps.Marker({position: uluru, map: map});

  };
  
  celAddOnBeforeLoadListener(function() {
    initGoogleMaps();
  });
  
})(window); 