package com.celements.crm.place.classes;

import java.util.ArrayList;
import java.util.List;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.annotation.Requirement;

import com.celements.model.classes.AbstractLegacyClassPackage;
import com.celements.model.classes.ClassDefinition;

@Component(CelPlaceClassPackage.NAME)
public class CelPlaceClassPackage extends AbstractLegacyClassPackage {

  public static final String NAME = "crm-place";

  @Requirement
  private List<CelPlaceClass> classDefs;

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public String getLegacyName() {
    return "celcrmPlaces";
  }

  @Override
  public List<? extends ClassDefinition> getClassDefinitions() {
    return new ArrayList<>(classDefs);
  }

}
