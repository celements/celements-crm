package com.celements.crm.place.classes;

import static com.celements.crm.place.classes.PlaceClassUtils.*;

import java.util.Date;

import javax.annotation.concurrent.Immutable;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;

import com.celements.model.classes.AbstractClassDefinition;
import com.celements.model.classes.fields.ClassField;
import com.celements.model.classes.fields.StringField;
import com.celements.model.classes.fields.number.IntField;

@Immutable
@Singleton
@Component(CityClass.CLASS_DEF_HINT)
public class CityClass extends AbstractClassDefinition implements CelPlaceClass {

  public static final String CLASS_NAME = "CityClass";
  public static final String CLASS_DEF_HINT = CLASS_SPACE + "." + CLASS_NAME;

  public static final ClassField<String> FIELD_ZIP = new StringField.Builder(CLASS_DEF_HINT,
      "zip").prettyName("ZIP").size(30).build();

  public static final ClassField<String> FIELD_SHORT_NAME = new StringField.Builder(CLASS_DEF_HINT,
      "shortName").prettyName("City Short Name").size(30).build();

  public static final ClassField<String> FIELD_NAME = new StringField.Builder(CLASS_DEF_HINT,
      "name").prettyName("City Name").size(30).build();

  public static final ClassField<String> FIELD_COUNTY = new StringField.Builder(CLASS_DEF_HINT,
      "county").prettyName("County").size(30).build();

  public static final ClassField<String> FIELD_PRIMARY_LANGUAGE = new StringField.Builder(
      CLASS_DEF_HINT, "primaryLanguage").prettyName("Primary Language").size(30).build();

  public static final ClassField<Integer> FIELD_COUNTRY_ISO_CODE = new IntField.Builder(
      CLASS_DEF_HINT, "countryISONum").prettyName("Country ISO Number").size(3).build();

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
