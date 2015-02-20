package com.celements.crm.place.geocoding;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.annotation.Requirement;
import org.xwiki.configuration.ConfigurationSource;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

@Component
public class GeocodingService implements IGeocodingServiceRole {

  private static final Logger LOGGER = LoggerFactory.getLogger(GeocodingService.class);

  @Requirement
  private ConfigurationSource configSource;

  // TODO should this be cached?
  private GeoApiContext gMapsContext;

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

  public List<LatLng> geocodeAddress(List<String> addressParts) throws GeocodingException {
    return geocodeAddress(StringUtils.join(addressParts, ", "));
  }

  private GeoApiContext getGMapsContext() {
    if (gMapsContext == null) {
      gMapsContext = new GeoApiContext();
      gMapsContext.setApiKey(getGMapsApiKey());
      gMapsContext.setQueryRateLimit(getGMapsQueriesPerSecond());
    }
    return gMapsContext;
  }

  private String getGMapsApiKey() {
    String apiKey = configSource.getProperty("celements.geocoding.googlemaps.apikey", 
        String.class);
    LOGGER.debug("getGMapsApiKey: got '{}'", apiKey);
    return apiKey;
  }

  private int getGMapsQueriesPerSecond() {
    Integer qps = configSource.getProperty("celements.geocoding.googlemaps.qps", 5);
    LOGGER.debug("getGMapsQueriesPerSecond: got '{}'", qps);
    return qps;
  }

  private GeocodingResult[] awaitResult(GeocodingApiRequest request
      ) throws GeocodingException {
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
