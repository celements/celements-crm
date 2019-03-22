package com.celements.crm.place.geocoding;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.annotation.Requirement;
import org.xwiki.script.service.ScriptService;

import com.celements.model.classes.ClassDefinition;
import com.celements.rights.access.IRightsAccessFacadeRole;

@Component("geocoding")
public class GeocodingScriptService implements ScriptService {

  private static final Logger LOGGER = LoggerFactory.getLogger(GeocodingScriptService.class);

  @Requirement
  private IGeocodingServiceRole geocodingService;

  @Requirement
  private IRightsAccessFacadeRole rightsAccess;

  public List<LatLng> geocodeAddress(String address) {
    List<LatLng> ret = new ArrayList<>();
    if (rightsAccess.isLoggedIn()) {
      try {
        ret = geocodingService.geocodeAddress(address);
      } catch (Exception exc) {
        LOGGER.error("geocodeAddress - failed for [{}]", address, exc);
      }
    }
    return ret;
  }

  public ClassDefinition getGeotagClass() {
    return geocodingService.getGeotagClass();
  }

}
