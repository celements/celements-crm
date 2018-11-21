package com.celements.crm.place.geocoding;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.annotation.Requirement;
import org.xwiki.configuration.ConfigurationSource;

import com.celements.model.classes.ClassDefinition;
import com.google.common.base.Strings;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.xpn.xwiki.web.Utils;

@Component
public class GeocodingService implements IGeocodingServiceRole {

  private static final Logger LOGGER = LoggerFactory.getLogger(GeocodingService.class);

  @Requirement
  ConfigurationSource configSource;

  GeoApiContext gMapsContext;

  @Override
  public List<LatLng> geocodeAddress(String address) throws GeocodingException {
    List<LatLng> ret = new ArrayList<LatLng>();
    if (StringUtils.isNotBlank(address)) {
      GeocodingApiRequest request = GeocodingApi.geocode(getGMapsContext(), address);
      for (GeocodingResult result : awaitResult(request)) {
        LatLng location = result.geometry.location;
        if (location != null) {
          location = new LatLng(location.lat, location.lng);
          ret.add(location);
        }
      }
    }
    return ret;
  }

  @Override
  public List<LatLng> geocodeAddress(List<String> addressParts) throws GeocodingException {
    return geocodeAddress(StringUtils.join(addressParts, " "));
  }

  GeoApiContext getGMapsContext() {
    if (gMapsContext == null) {
      gMapsContext = new GeoApiContext();
    }
    gMapsContext.setApiKey(getGMapsApiKey());
    gMapsContext.setQueryRateLimit(getGMapsQueriesPerSecond());
    return gMapsContext;
  }

  @Override
  public ClassDefinition getGeotagClass() {
    String geotagClassName = "";
    String geotagClassNameConfigSource = configSource.getProperty(
        "celements.geocoding.celementsPlaces.geotagClassName", String.class);
    ClassDefinition classDef = null;
    if (!Strings.isNullOrEmpty(geotagClassNameConfigSource)
        && Utils.getComponentManager().hasComponent(ClassDefinition.class,
            geotagClassNameConfigSource)) {
      geotagClassName = geotagClassNameConfigSource;
    } else {
      geotagClassName = IGeocodingServiceRole.CRM_PLACE_DEFAULT_GEOTAG_CLASSNAME;
    }
    classDef = Utils.getComponent(ClassDefinition.class, geotagClassName);
    return classDef;
  }

  private String getGMapsApiKey() {
    String apiKey = configSource.getProperty("celements.geocoding.googlemaps.apikey", String.class);
    LOGGER.debug("getGMapsApiKey: got '{}'", apiKey);
    return apiKey;
  }

  private int getGMapsQueriesPerSecond() {
    Integer qps = configSource.getProperty("celements.geocoding.googlemaps.qps", 2);
    LOGGER.debug("getGMapsQueriesPerSecond: got '{}'", qps);
    return qps;
  }

  private GeocodingResult[] awaitResult(GeocodingApiRequest request) throws GeocodingException {
    try {
      GeocodingResult[] result = request.await();
      if (result == null) {
        result = new GeocodingResult[0];
      }
      return result;
    } catch (Exception exc) {
      throw new GeocodingException(exc);
    }
  }

  void injectConfigSource(ConfigurationSource configSource) {
    this.configSource = configSource;
  }

}
