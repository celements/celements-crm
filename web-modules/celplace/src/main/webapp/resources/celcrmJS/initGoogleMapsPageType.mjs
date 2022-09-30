
export class CelGoogleMapsViewer {
  
  mapsContainerSelector;
  pinColor;
  mapOptions;
  
  construtor(options) {
    console.debug("start constructor");
    const theOpt = options ?? {};
    this.mapsContainerSelector = theOpt.mapsContainerSelector ?? '#googleMapsContainer';
    this.pinColor = theOpt.pinColor ?? "FF0505";
    this.mapOptions = theOpt.mapOptions ?? {};
    console.debug("end constructor");
  }

  load() {
    const metas = document.querySelectorAll('meta[name="cel-GMaps-ApiKey"]');
    if ((metas.length > 0) && (metas[0].content !== '')) {
      const gMapsApiKey = metas[0].content;
      const gmapJsElem = document.createElement('script');
      gmapJsElem.src = 'https://maps.googleapis.com/maps/api/js?libraries=places&sensor=false'
        + '&key=' + gMapsApiKey;
      gmapJsElem.type = 'text/javascript';
      gmapJsElem.addEventListener('load', () => this.initLoadMap());
      document.head.appendChild(gmapJsElem);
    } else {
      console.warn('Cannot initialize Google Maps, because of Google Maps Api key is missing.');
    }
  }

  getMapsContainer() {
    // Get the HTML DOM element that will contain your map
    // We are using a div with id="map" seen below in the <body>
    return document.body.querySelector(this.mapsContainerSelector);
  }

  getLongitude() {
   return parseFloat(this.getMapsContainer().getAttribute("data-longitude"));
  }

  getLatitude() {
   return parseFloat(this.getMapsContainer().getAttribute("data-latitude"));
  }
  
  getPlaceCoordinates() {
    return {lat: this.getLatitude(), lng: this.getLongitude()};
  }
  
  getPinImage() {
    return new google.maps.MarkerImage("https://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + this.pinColor,
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
    return Object.assign({
      center : this.getPlaceCoordinates(),
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
    }, this.mapOptions);
  }
  
  initLoadMap() {
    console.debug("start initLoadMap ", this.getMapsContainer());
    if (this.getMapsContainer()) {
      try {
        // Create the Google Map using out element and options defined above
        const map = new google.maps.Map(this.getMapsContainer(), this.getMapOptions());
        const marker = new google.maps.Marker({
          icon: this.getPinImage(),
          shadow: this.getPinShadow(),
          position : this.getPlaceCoordinates(),
          map : map
        });
        console.log("initLoadMap: created marker ", marker,  {
          icon: this.getPinImage(),
          shadow: this.getPinShadow(),
          position : this.getPlaceCoordinates(),
          map : map
        });
      } catch (error) {
        console.error("initLoadMap ", error);
      }
    } 
  }
}

if (['complete', 'interactive'].includes(document.readyState)) {
  console.debug("initGoogleMaps in dynamic mode loaded imediately", document.readyState);
  new CelGoogleMapsViewer().load();
} else {
  console.debug("initGoogleMaps in sync mode loaded on defer ", document.readyState);
  const mapsViewer = new CelGoogleMapsViewer();
  document.addEventListener('DOMContentLoaded', () => mapsViewer.load());
}
