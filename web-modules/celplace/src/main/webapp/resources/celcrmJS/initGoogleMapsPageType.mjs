const getMapsContainer = function() {
  // Get the HTML DOM element that will contain your map
  // We are using a div with id="map" seen below in the <body>
  return document.body.querySelector('#googleMapsContainer');
};

const getLongitude = function() {
 return parseFloat(getMapsContainer().getAttribute("data-longitude"));
};

const getLatitude = function() {
 return parseFloat(getMapsContainer().getAttribute("data-latitude"));
};

const getPlaceCoordinates = function() {
  return {lat: getLatitude(), lng: getLongitude()};
};

const getPinImage = function() {
  const pinColor = "FF0505";
  return new google.maps.MarkerImage("https://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
      new google.maps.Size(21, 34),
      new google.maps.Point(0,0),
      new google.maps.Point(10, 34));
};

const getPinShadow = function() {
  return new google.maps.MarkerImage("https://chart.apis.google.com/chart?chst=d_map_pin_shadow",
      new google.maps.Size(40, 37),
      new google.maps.Point(0, 0),
      new google.maps.Point(12, 35));
};

const getMapOptions = function() {
  return {
    center : getPlaceCoordinates(),
    zoom : 16,
    zoomControl : false,
    zoomControlOptions : {
      style : google.maps.ZoomControlStyle.SMALL,
      position : google.maps.ControlPosition.LEFT_CENTER,
    },
    mapTypeControl : false,
    disableDoubleClickZoom : true,
    scaleControl : true,
    scrollwheel : false,
    panControl : false,
    streetViewControl : false,
    draggable : false
  };
};

const initLoadMap = function() {
  if (getMapsContainer()) {
    // Create the Google Map using out element and options defined above
    const map = new google.maps.Map(getMapsContainer(), getMapOptions());
    const marker = new google.maps.Marker({
      icon: getPinImage(),
      shadow: getPinShadow(),
      position : getPlaceCoordinates(),
      map : map
    });
  }
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
