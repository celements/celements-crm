package com.celements.crm.place.geocoding;

import org.junit.Test;

import com.celements.common.test.AbstractBridgedComponentTestCase;
import com.xpn.xwiki.web.Utils;

public class GeocodingServiceTest extends AbstractBridgedComponentTestCase {

  private IGeocodingServiceRole geocodingService;

  @Test
  public void test() throws Exception {
    geocodingService = Utils.getComponent(IGeocodingServiceRole.class);
    System.out.println(geocodingService.geocodeAddress(""));
  }

}
