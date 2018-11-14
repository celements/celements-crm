package com.celements.crm.place.geocoding;

import java.util.List;

import org.xwiki.component.annotation.ComponentRole;

import com.google.maps.model.LatLng;

@ComponentRole
public interface IGeocodingServiceRole {

  public static String defaultGeotagClassName = "CelementsPlaces.GeotagClass";

  public List<LatLng> geocodeAddress(String address) throws GeocodingException;

  public List<LatLng> geocodeAddress(List<String> addressParts) throws GeocodingException;

}
