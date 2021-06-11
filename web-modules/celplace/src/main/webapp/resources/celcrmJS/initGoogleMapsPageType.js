(function(window, undefined) {
  "use strict";
  
  const initGoogleMaps = function(mapContainer) {
    const longitude = parseFloat(mapContainer.dataset.longitude);
    const latitude = parseFloat(mapContainer.dataset.latitude);    
    const place = {lat: latitude, lng: longitude};
    const map = new google.maps.Map(mapContainer, {zoom: 15, center: place});
    const marker = new google.maps.Marker({position: place, map: map});
    console.debug('initGoogleMaps: map', map, 'with marker', marker);
  };
  
  celAddOnBeforeLoadListener(function() {
    document.querySelectorAll('.googleMapsContainer')
        .forEach(initGoogleMaps);
  });
  
})(window);
