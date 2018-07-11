(function(window, undefined) {
  "use strict";
  
  var initGoogleMaps = function() {
    var mapContainer = $("googleMapsContainer");
    var longitude = parseFloat(mapContainer.getAttribute("data-longitude"));
    var latitude = parseFloat(mapContainer.getAttribute("data-latitude"));
    
    var place = {lat: latitude, lng: longitude};
    var map = new google.maps.Map($('googleMapsContainer'), {zoom: 15, center: place});
    var marker = new google.maps.Marker({position: place, map: map});

  };
  
  celAddOnBeforeLoadListener(function() {
    initGoogleMaps();
  });
  
})(window); 