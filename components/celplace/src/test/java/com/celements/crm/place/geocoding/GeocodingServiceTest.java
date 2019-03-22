package com.celements.crm.place.geocoding;

import static com.celements.common.test.CelementsTestUtils.*;
import static org.junit.Assert.*;

import org.apache.commons.lang.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;

import com.celements.common.test.AbstractComponentTest;
import com.google.maps.GeoApiContext;
import com.xpn.xwiki.web.Utils;

public class GeocodingServiceTest extends AbstractComponentTest {

  private GeocodingService geocodingService;

  @Before
  public void setUp_GeocodingServiceTest() {
    geocodingService = (GeocodingService) Utils.getComponent(IGeocodingServiceRole.class);
  }

  @Test
  public void testGetGMapsContext() throws Exception {
    String apiKey = "asdf";
    getConfigurationSource().setProperty("celements.geocoding.googlemaps.apikey", apiKey);
    getConfigurationSource().setProperty("celements.geocoding.googlemaps.qps", 2);

    replayDefault();
    GeoApiContext ctx = geocodingService.getGMapsContext();
    verifyDefault();

    assertNotNull(ctx);
    assertEquals(apiKey, FieldUtils.readDeclaredField(ctx, "apiKey", true));
  }

}
