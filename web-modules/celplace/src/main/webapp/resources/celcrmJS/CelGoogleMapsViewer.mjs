export class CelGoogleMapsViewer {
  
  mapsContainerSelector;
  pinColor;
  mapOptions;
  
  constructor(options) {
    const theOpt = options ?? {};
    this.mapsContainerSelector = theOpt.mapsContainerSelector ?? '#googleMapsContainer';
    this.pinColor = theOpt.pinColor ?? "FF0505";
    this.mapOptions = theOpt.mapOptions ?? {};
    this.mapOptions.styles = this.mapOptions.styles ?? this.getMapStyles();
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

  getMapStyles() {
    const attrStyle = this.getMapsContainer().dataset.styles;
    if (attrStyle) {
      try {
        return JSON.parse(attrStyle);
      } catch (error) {
        console.error('failed to parse styles', error);
      }
    }
    console.debug('getMapStyles returning empty array');
    return [];
  }

  getMapsContainer() {
    // Get the HTML DOM element that will contain your map
    // We are using a div with id="map" seen below in the <body>
    return document.body.querySelector(this.mapsContainerSelector);
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
//      mapTypeControlOptions : {
//        style : google.maps.MapTypeControlStyle.DROPDOWN_MENU,
//      },
//      overviewMapControlOptions : {
//        opened : false,
//      },
    return Object.assign({
      center : this.getPlaceCoordinates(),
      zoom : 16,
      zoomControl : false,
      zoomControlOptions : {
        style : google.maps.ZoomControlStyle.SMALL,
        position : google.maps.ControlPosition.LEFT_CENTER,
      },
      disableDoubleClickZoom : true,
      mapTypeControl : false,
      scaleControl : true,
      scrollwheel : false,
      panControl : false,
      streetViewControl : false,
      draggable : false,
      mapTypeId : google.maps.MapTypeId.ROADMAP,
      overviewMapControl : false
    }, this.mapOptions);
  }
  
  getRteDescriptionTemplate() {
    return this.getMapsContainer().parentNode.querySelector('template.gMapsRteDescription');
  }
  
  hasRteDescriptionTemplate() {
    const rteDesc = this.getRteDescriptionTemplate();
    console.debug('hasRteDescriptionTemplate: ', rteDesc);
    return rteDesc  && rteDesc.content && (rteDesc.content !== '');
  }

  getInfoWindow() {
    const descElem = document.createElement('div');
    descElem.style = "height:180px;width: 290px;";
    descElem.class = "cel_googleMap";
    descElem.replaceChildren(this.getRteDescriptionTemplate().content.cloneNode(true));
    return new google.maps.InfoWindow({
      content : descElem
    });
  }
  
  initLoadMap() {
    if (this.getMapsContainer()) {
      try {
        // Create the Google Map using out element and options defined above
        console.info('initLoadMap with options ', this.getMapOptions());
        const map = new google.maps.Map(this.getMapsContainer(), this.getMapOptions());
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
