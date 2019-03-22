package com.celements.crm.place.geocoding;

/**
 * extends {@link com.google.maps.model.LatLng} with getters, mainly for velocity usage
 */
public class LatLng extends com.google.maps.model.LatLng {

  private static final long serialVersionUID = 7006718941704992677L;

  public LatLng(double lat, double lng) {
    super(lat, lng);
  }

  public LatLng(com.google.maps.model.LatLng latLng) {
    super(latLng.lat, latLng.lng);
  }

  public double lat() {
    return lat;
  }

  public double lng() {
    return lng;
  }

}
