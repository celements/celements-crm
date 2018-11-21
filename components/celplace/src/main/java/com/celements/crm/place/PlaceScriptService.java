package com.celements.crm.place;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.annotation.Requirement;
import org.xwiki.script.service.ScriptService;

import com.celements.crm.place.geocoding.IGeocodingServiceRole;
import com.celements.model.classes.ClassDefinition;

@Component("crmplace")
public class PlaceScriptService implements ScriptService {

  @Requirement
  private IGeocodingServiceRole geocodingService;

  public ClassDefinition getGeotagClassDefinition() {
    return geocodingService.getGeotagClass();
  }
}
