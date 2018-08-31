package com.celements.crm.place.classes;

import static com.celements.crm.place.classes.PlaceClassUtils.*;

import java.util.Date;

import javax.annotation.concurrent.Immutable;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;

import com.celements.model.classes.AbstractClassDefinition;
import com.celements.model.classes.fields.ClassField;
import com.celements.model.classes.fields.StringField;

@Immutable
@Singleton
@Component(AddressClass.CLASS_DEF_HINT)
public class AddressClass extends AbstractClassDefinition implements CelPlaceClass {

  public static final String CLASS_NAME = "AddressClass";
  public static final String CLASS_DEF_HINT = CLASS_SPACE + "." + CLASS_NAME;

  public static final ClassField<String> FIELD_STREET = new StringField.Builder(CLASS_DEF_HINT,
      "street").prettyName("Street").size(30).build();

  public static final ClassField<String> FIELD_HOUSE_NUMBER = new StringField.Builder(
      CLASS_DEF_HINT, "houseNumber").prettyName("House Number").size(30).build();

  public static final ClassField<String> FIELD_ZIP = new StringField.Builder(CLASS_DEF_HINT,
      "zip").prettyName("ZIP").size(30).build();

  public static final ClassField<String> FIELD_CITY = new StringField.Builder(CLASS_DEF_HINT,
      "city").prettyName("City").size(30).build();

  public static final ClassField<String> FIELD_COUNTRY = new StringField.Builder(CLASS_DEF_HINT,
      "country").prettyName("Country").size(30).build();

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
