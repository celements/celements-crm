package com.celements.crm.place.geocoding;

public class GeocodingException extends Exception {

  private static final long serialVersionUID = 1L;

  public GeocodingException() {
    super();
  }

  public GeocodingException(String msg) {
    super(msg);
  }

  public GeocodingException(Throwable cause) {
    super(cause);
  }

  public GeocodingException(String msg, Throwable cause) {
    super(msg, cause);
  }

}
