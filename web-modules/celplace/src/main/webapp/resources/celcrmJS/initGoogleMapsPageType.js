(function(window, undefined) {
  "use strict";
  
  var initGoogleMaps = function() {
    var mapContainer = $("googleMapsContainer");
    var longitude = parseFloat(mapContainer.getAttribute("data-longitude"));
    var latitude = parseFloat(mapContainer.getAttribute("data-latitude"));
    console.log("<<<<<<<<< initGoogleMapsPageType initGoogleMaps longitude: ", longitude);
    console.log("<<<<<<<<< initGoogleMapsPageType initGoogleMaps latitude: ", latitude);
    
    // The location of Uluru
    var uluru = {lat: latitude, lng: longitude};
    // The map, centered at Uluru
    var map = new google.maps.Map($('googleMapsContainer'), {zoom: 4, center: uluru});
    // The marker, positioned at Uluru
    var marker = new google.maps.Marker({position: uluru, map: map});

  };
  
  celAddOnBeforeLoadListener(function() {
    initGoogleMaps();
  });
  
})(window); 