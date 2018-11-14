package com.celements.crm.place.geocoding;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.xwiki.component.annotation.ComponentRole;

import com.celements.model.classes.ClassDefinition;
import com.google.maps.model.LatLng;

@ComponentRole
public interface IGeocodingServiceRole {

  public static String defaultGeotagClassName = "CelementsPlaces.GeotagClass";

  public List<LatLng> geocodeAddress(String address) throws GeocodingException;

  public List<LatLng> geocodeAddress(List<String> addressParts) throws GeocodingException;

  public @NotNull ClassDefinition getGeotagClass();

}
