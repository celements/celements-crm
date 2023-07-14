package com.celements.crm.place;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.annotation.Requirement;
import org.xwiki.model.reference.ClassReference;
import org.xwiki.script.service.ScriptService;

import com.celements.crm.place.geocoding.IGeocodingServiceRole;

@Component("crmplace")
public class PlaceScriptService implements ScriptService {

  private static final Logger LOGGER = LoggerFactory.getLogger(PlaceScriptService.class);

  @Requirement
  private IGeocodingServiceRole geocodingService;

  public ClassReference getGeoTagClassRef() {
    try {
      return geocodingService.getGeotagClass().getClassReference();
    } catch (Exception exc) {
      LOGGER.info("Failed to get getGeoTagClassRef.", exc);
      return null;
    }
  }

}
