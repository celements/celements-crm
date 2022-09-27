package com.celements.crm.place;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.annotation.Requirement;
import org.xwiki.model.reference.ClassReference;
import org.xwiki.script.service.ScriptService;

import com.celements.crm.place.geocoding.IGeocodingServiceRole;

@Component("crmplace")
public class PlaceScriptService implements ScriptService {

  @Requirement
  private IGeocodingServiceRole geocodingService;

  public ClassReference getGeotagClassRef() {
    try {
      return geocodingService.getGeotagClass().getClassReference();
    } catch (Exception exc) {
      return null;
    }
  }

}
