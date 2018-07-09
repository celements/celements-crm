package com.celements.crm.place.classes;

import javax.annotation.concurrent.Immutable;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;

import com.celements.model.classes.AbstractClassDefinition;
import com.celements.model.classes.fields.ClassField;
import com.celements.model.classes.fields.StringField;
import com.celements.model.classes.fields.number.IntField;

@Immutable
@Singleton
@Component(CountryClass.CLASS_DEF_HINT)
public class CountryClass extends AbstractClassDefinition implements CelPlaceClass {

  public static final String CLASS_NAME = "CountryClass";
  public static final String CLASS_DEF_HINT = CLASS_SPACE + "." + CLASS_NAME;

  public static final ClassField<String> FIELD_NAME = new StringField.Builder(CLASS_DEF_HINT,
      "name").prettyName("Country Name").size(30).build();

  public static final ClassField<String> FIELD_ISO2 = new StringField.Builder(CLASS_DEF_HINT,
      "iso2").prettyName("iso country code (two letters)").size(2).build();

  public static final ClassField<String> FIELD_ISO3 = new StringField.Builder(CLASS_DEF_HINT,
      "iso3").prettyName("iso country code (three letters)").size(3).build();

  public static final ClassField<Integer> FIELD_ISO_CODE = new IntField.Builder(CLASS_DEF_HINT,
      "isoNum").prettyName("iso country number code (three digits)").size(3).build();

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
