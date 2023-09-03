import { Loader } from '/file/resources/celcrmJS/google/maps/index.esm.mjs?version=1.16.2';

class CelGoogleMapsStyler {
  #stylesTemplates;
  
  constructor() {
    this.#stylesTemplates = {'defaultStyle' : []};
  }
  
  addStylesTemplate(templName, template) {
    this.#stylesTemplates[templName] =  template;
  }

  getStylesForName(templName) {
    return this.#stylesTemplates[templName];
  }
}

export const CEL_GOOGLE_MAPS_STYLER = new CelGoogleMapsStyler();

CEL_GOOGLE_MAPS_STYLER.addStylesTemplate("modernBlackWhite",  [ {
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
      } ]
);

export class CelGoogleMapsViewer {
  
  #mapsContainerSelector;
  #pinColor;
  #mapOptions;
  #stylesTemplateName;

  constructor(options) {
    const theOpt = options ?? {};
    this.#mapsContainerSelector = theOpt.mapsContainerSelector ?? '#googleMapsContainer';
    this.#pinColor = theOpt.pinColor ?? "FF0505";
    this.#stylesTemplateName = theOpt.styleTemplate;
    this.#mapOptions = theOpt.mapOptions ?? {};
  }

  load() {
    const metas = document.querySelectorAll('meta[name="cel-GMaps-ApiKey"]');
    if ((metas.length > 0) && (metas[0].content !== '')) {
      const gMapsApiKey = metas[0].content;
      const loader = new Loader({
        apiKey: gMapsApiKey,
        version: "weekly",
        libraries: ["places"]
      });
      loader
        .load()
        .then((google) => this.initLoadMap(google))
        .catch(exp => {
          console.error('Cannot initialize Google Maps.', exp);
        });

      //old import version
      /**
      const gmapJsElem = document.createElement('script');
      gmapJsElem.src = 'https://maps.googleapis.com/maps/api/js?libraries=places&sensor=false'
        + '&key=' + gMapsApiKey;
      gmapJsElem.type = 'text/javascript';
      gmapJsElem.addEventListener('load', () => this.initLoadMap());
      document.head.appendChild(gmapJsElem);
    */
    } else {
      console.warn('Cannot initialize Google Maps, because of Google Maps Api key is missing.');
    }
  }

  getMapStyles() {
    const attrStyle = this.getMapsContainer().dataset.styles;
    if (attrStyle) {
      try {
        return JSON.parse(attrStyle);
      } catch (error) {
        console.error('getMapStyles failed to parse styles', error);
      }
    }
    const attrStyleTempl = this.getMapsContainer().dataset.styleTemplate ??
       this.#stylesTemplateName ?? 'defaultStyle';
    return CEL_GOOGLE_MAPS_STYLER.getStylesForName(attrStyleTempl);
  }
  
  getMapsContainer() {
    // Get the HTML DOM element that will contain your map
    // We are using a div with id="map" seen below in the <body>
    return document.body.querySelector(this.#mapsContainerSelector);
  }

  getLongitude() {
   return parseFloat(this.getMapsContainer().dataset.longitude);
  }

  getLatitude() {
   return parseFloat(this.getMapsContainer().dataset.latitude);
  }
  
  getPlaceCoordinates() {
    return {lat: this.getLatitude(), lng: this.getLongitude()};
  }
  
  getPinImage() {
    return new google.maps.MarkerImage("https://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + this.#pinColor,
        new google.maps.Size(21, 34),
        new google.maps.Point(0,0),
        new google.maps.Point(10, 34));
  }
  
  getPinShadow() {
    return new google.maps.MarkerImage("https://chart.apis.google.com/chart?chst=d_map_pin_shadow",
        new google.maps.Size(40, 37),
        new google.maps.Point(0, 0),
        new google.maps.Point(12, 35));
  }
  
  getMapOptions() {
    const mapOptions = Object.assign({
      center : this.getPlaceCoordinates(),
      zoom : 16,
      zoomControl : false,
      disableDoubleClickZoom : true,
      mapTypeControl : false,
      scaleControl : true,
      scrollwheel : false,
      panControl : false,
      streetViewControl : false,
      draggable : false,
      overviewMapControl : false,
      mapTypeId : google.maps.MapTypeId.ROADMAP
    }, this.#mapOptions);
    mapOptions.styles = this.#mapOptions.styles ?? this.getMapStyles();
    return mapOptions;
  }
  
  getRteDescriptionTemplate() {
    return this.getMapsContainer().parentNode.querySelector('template.gMapsRteDescription');
  }
  
  hasRteDescriptionTemplate() {
    const rteDesc = this.getRteDescriptionTemplate();
    return rteDesc  && rteDesc.content && (rteDesc.content !== '');
  }

  getInfoWindow() {
    const descElem = document.createElement('div');
    descElem.style.height = "180px";
    descElem.style.width = "290px";
    descElem.classList.add("cel_googleMap");
    descElem.replaceChildren(this.getRteDescriptionTemplate().content.cloneNode(true));
    return new google.maps.InfoWindow({
      content : descElem
    });
  }
  
  initLoadMap(google) {
    if (this.getMapsContainer()) {
      try {
        // Create the Google Map using out element and options defined above
        const map = new google.maps.Map(this.getMapsContainer(), this.getMapOptions());
        //Loads custom zoom controls
        const zoomDiv = document.createElement('div');
        zoomDiv.index = 1;
        map.controls[google.maps.ControlPosition.BOTTOM_CENTER].push(zoomDiv);
        const marker = new google.maps.Marker({
          icon: this.getPinImage(),
          shadow: this.getPinShadow(),
          position : this.getPlaceCoordinates(),
          map : map
        });
        if (this.hasRteDescriptionTemplate()) {
          const iw = this.getInfoWindow();
          google.maps.event.addListener(marker, 'click', () => iw.open(map, marker));
          iw.open(map, marker);
        }
      } catch (error) {
        console.error("initLoadMap ", error);
      }
    } 
  }
}
