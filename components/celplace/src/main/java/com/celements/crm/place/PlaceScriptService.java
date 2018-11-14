package com.celements.crm.place;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.annotation.Requirement;
import org.xwiki.model.reference.DocumentReference;
import org.xwiki.script.service.ScriptService;

import com.celements.crm.place.geocoding.GeocodingService;

@Component("crmplace")
public class PlaceScriptService implements ScriptService {

  @Requirement
  private GeocodingService geocodingService;

  public DocumentReference getGeotagClassDocRef() {
    return geocodingService.getGeotagClass().getDocRef();
  }
}
