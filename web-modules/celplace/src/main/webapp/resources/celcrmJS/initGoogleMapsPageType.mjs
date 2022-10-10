import { CelGoogleMapsViewer } from "./CelGoogleMapsViewer.mjs?version=202210100605";

const modernBlackWhite =  [ {
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

CEL_GOOGLE_MAPS_STYLER.addStylesTemplate("modernBlackWhite", modernBlackWhite);

const options = { styleTemplate : 'modernBlackWhite' };
const googleMapViewer = new CelGoogleMapsViewer(options);
if (['complete', 'interactive'].includes(document.readyState)) {
  console.debug("initGoogleMaps in dynamic mode loaded immediately", document.readyState);
  googleMapViewer.load();
} else {
  console.debug("initGoogleMaps in sync mode loaded on defer ", document.readyState);
  document.addEventListener('DOMContentLoaded', () => googleMapViewer.load());
}
