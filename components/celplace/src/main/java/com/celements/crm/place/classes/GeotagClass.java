package com.celements.crm.place.classes;

import static com.celements.crm.place.classes.PlaceClassUtils.*;

import java.util.Date;

import javax.annotation.concurrent.Immutable;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;

import com.celements.model.classes.AbstractClassDefinition;
import com.celements.model.classes.fields.ClassField;
import com.celements.model.classes.fields.StringField;
import com.celements.model.classes.fields.number.DoubleField;

@Immutable
@Singleton
@Component(GeotagClass.CLASS_DEF_HINT)
public class GeotagClass extends AbstractClassDefinition implements CelPlaceClass {

  public static final String CLASS_NAME = "GeotagClass";
  public static final String CLASS_DEF_HINT = CLASS_SPACE + "." + CLASS_NAME;

  public static final ClassField<Double> FIELD_LONGITUDE = new DoubleField.Builder(CLASS_DEF_HINT,
      "longitude").prettyName("Longitude").size(15).build();

  public static final ClassField<Double> FIELD_LATITUDE = new DoubleField.Builder(CLASS_DEF_HINT,
      "latitude").prettyName("Latitude").size(15).build();

  public static final ClassField<Double> FIELD_ALTITUDE = new DoubleField.Builder(CLASS_DEF_HINT,
      "altitude").prettyName("Altitude").size(15).build();

  public static final ClassField<String> FIELD_ALTITUDE_MODE = new StringField.Builder(
      CLASS_DEF_HINT, "altitudeMode").prettyName("altitudeMode").size(30).validationRegExp(
          "/^.{0,128}$/").validationMessage("celcrm_invalid_geotag_altitudeMode_max").build();

  public static final ClassField<Date> FIELD_VALID_FROM = newValidFromField(CLASS_DEF_HINT);

  public static final ClassField<Date> FIELD_VALID_UNTIL = newValidUntilField(CLASS_DEF_HINT);

  @Override
  public String getName() {
    return CLASS_DEF_HINT;
  }

  @Override
  public boolean isInternalMapping() {
    return true;
  }

  @Override
  protected String getClassSpaceName() {
    return CLASS_SPACE;
  }

  @Override
  protected String getClassDocName() {
    return CLASS_NAME;
  }
}
