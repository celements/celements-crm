package com.celements.crm.place.classes;

import java.util.Date;

import com.celements.model.classes.fields.ClassField;
import com.celements.model.classes.fields.DateField;
import com.celements.model.classes.fields.DateField.Builder;

class PlaceClassUtils {

  public static ClassField<Date> newValidFromField(String classDefName) {
    Builder builder = new DateField.Builder(classDefName, "validFrom");
    builder.prettyName("valid from date (dd.MM.yyyy)").size(20).dateFormat("dd.MM.yyyy");
    builder.emptyIsToday(0).validationRegExp(getRegexDate(false, false)).validationMessage(
        classDefName + "_invalid_validFrom");
    return builder.build();
  }

  public static ClassField<Date> newValidUntilField(String classDefName) {
    Builder builder = new DateField.Builder(classDefName, "validUntil");
    builder.prettyName("valid until date (dd.MM.yyyy)").size(20).dateFormat("dd.MM.yyyy");
    builder.emptyIsToday(0).validationRegExp(getRegexDate(false, false)).validationMessage(
        classDefName + "_invalid_validUntil");
    return builder.build();
  }

  public static String getRegexDate(boolean allowEmpty, boolean withTime) {
    String regex = "(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[012])\\.([0-9]{4})";
    if (withTime) {
      regex += " ([01][0-9]|2[0-4])(\\:[0-5][0-9]){2}";
    }
    return "/" + (allowEmpty ? "(^$)|" : "") + "^(" + regex + ")$" + "/";
  }
}
