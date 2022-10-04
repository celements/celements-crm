import { CelGoogleMapsViewer } from "./CelGoogleMapsViewer.mjs?version=202210040728";

const modernBlackWhiteStyle =  [ {
        "featureType" : "landscape",
        "elementType" : "labels",
        "stylers" : [ {
          "visibility" : "off"
        } ]
      }, {
        "featureType" : "transit",
        "elementType" : "labels",
        "stylers" : [ {
          "visibility" : "off"
        } ]
      }, {
        "featureType" : "poi",
        "elementType" : "labels",
        "stylers" : [ {
          "visibility" : "off"
        } ]
      }, {
        "featureType" : "water",
        "elementType" : "labels",
        "stylers" : [ {
          "visibility" : "off"
        } ]
      }, {
        "featureType" : "road",
        "elementType" : "labels.icon",
        "stylers" : [ {
          "visibility" : "off"
        } ]
      }, {
        "stylers" : [ {
          "hue" : "#00aaff"
        }, {
          "saturation" : -100
        }, {
          "gamma" : 2.15
        }, {
          "lightness" : 12
        } ]
      }, {
        "featureType" : "road",
        "elementType" : "labels.text.fill",
        "stylers" : [ {
          "visibility" : "on"
        }, {
          "lightness" : 24
        } ]
      }, {
        "featureType" : "road",
        "elementType" : "geometry",
        "stylers" : [ {
          "lightness" : 57
        } ]
      } ];

const options = { mapOptions : { styles : modernBlackWhiteStyle }};
if (['complete', 'interactive'].includes(document.readyState)) {
  console.debug("initGoogleMaps in dynamic mode loaded immediately", document.readyState);
  new CelGoogleMapsViewer(options).load();
} else {
  console.debug("initGoogleMaps in sync mode loaded on defer ", document.readyState);
  const mapsViewer = new CelGoogleMapsViewer(options);
  document.addEventListener('DOMContentLoaded', () => mapsViewer.load());
}
