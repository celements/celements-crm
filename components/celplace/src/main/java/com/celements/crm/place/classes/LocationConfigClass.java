package com.celements.crm.place.classes;

import javax.annotation.concurrent.Immutable;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.model.reference.ClassReference;

import com.celements.model.classes.AbstractClassDefinition;
import com.celements.model.classes.fields.ClassField;
import com.celements.model.classes.fields.StringField;

@Immutable
@Singleton
@Component(LocationConfigClass.CLASS_DEF_HINT)
public class LocationConfigClass extends AbstractClassDefinition implements CelPlaceClass {

  public static final String DOC_NAME = "LocationConfigClass";
  public static final String CLASS_DEF_HINT = CLASS_SPACE + "." + DOC_NAME;
  public static final ClassReference CLASS_REF = new ClassReference(CLASS_SPACE, DOC_NAME);

  public static final ClassField<String> FIELD_MAPS_STYLE = new StringField.Builder(CLASS_REF,
      "mapsStyleTemplate").prettyName("maps style template name").size(30).build();

  public LocationConfigClass() {
    super(CLASS_REF);
  }

  @Override
  public String getName() {
    return CLASS_DEF_HINT;
  }

  @Override
  public boolean isInternalMapping() {
    return false;
  }

  @Override
  protected String getClassSpaceName() {
    return CLASS_SPACE;
  }

  @Override
  protected String getClassDocName() {
    return DOC_NAME;
  }
}
