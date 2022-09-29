(function(window, undefined) {
  "use strict";
  
  const initLoadMap = function() {
    const mapContainer = $("googleMapsContainer");
    const longitude = parseFloat(mapContainer.getAttribute("data-longitude"));
    const latitude = parseFloat(mapContainer.getAttribute("data-latitude"));
    const place = {lat: latitude, lng: longitude};
    const map = new google.maps.Map($('googleMapsContainer'), {zoom: 15, center: place});
    const marker = new google.maps.Marker({position: place, map: map});
  };
  
  const initializeGoogleMaps = function() {
    const metas = document.querySelectorAll('meta[name="cel-GMaps-ApiKey"]');
    if ((metas.length > 0) && (metas[0].content !== '')) {
      const gMapsApiKey = metas[0].content;
      const gmapJsElem = document.createElement('script');
      gmapJsElem.src = 'https://maps.googleapis.com/maps/api/js?libraries=places&sensor=false'
        + '&key=' + gMapsApiKey;
      gmapJsElem.type = 'text/javascript';
      gmapJsElem.addEventListener('load', initLoadMap);
      document.head.appendChild(gmapJsElem);
    } else {
      console.warn('Cannot initialize Google Maps, because of Google Maps Api key is missing.');
    }
  };

  if (['complete', 'interactive'].includes(document.readyState)) {
    initializeGoogleMaps();
  } else {
    document.addEventListener('DOMContentLoaded', initializeGoogleMaps);
  }

})(window);
