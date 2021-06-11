package com.celements.crm.place;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.annotation.Requirement;
import org.xwiki.configuration.ConfigurationSource;
import org.xwiki.script.service.ScriptService;

import com.celements.crm.place.geocoding.IGeocodingServiceRole;
import com.celements.model.classes.ClassDefinition;

@Component("crmplace")
public class PlaceScriptService implements ScriptService {

  @Requirement
  private IGeocodingServiceRole geocodingService;

  @Requirement
  private ConfigurationSource configSource;

  public ClassDefinition getGeotagClassDefinition() {
    return geocodingService.getGeotagClass();
  }

  public String getGoogleMapsApiKey() {
    return configSource.getProperty("celements.googlemaps.apikey", "");
  }
}
