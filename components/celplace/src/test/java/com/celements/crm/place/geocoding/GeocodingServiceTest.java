package com.celements.crm.place.geocoding;

import static com.celements.common.test.CelementsTestUtils.*;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.xwiki.configuration.ConfigurationSource;

import com.celements.common.test.AbstractComponentTest;
import com.google.maps.GeoApiContext;
import com.xpn.xwiki.web.Utils;

public class GeocodingServiceTest extends AbstractComponentTest {

  private GeocodingService geocodingService;

  private ConfigurationSource confSrcMock;
  private GeoApiContext gMapsContextMock;

  @Before
  public void setUp_GeocodingServiceTest() {
    geocodingService = (GeocodingService) Utils.getComponent(IGeocodingServiceRole.class);
    confSrcMock = createMockAndAddToDefault(ConfigurationSource.class);
    geocodingService.configSource = confSrcMock;
    gMapsContextMock = createMockAndAddToDefault(GeoApiContext.class);
    geocodingService.gMapsContext = gMapsContextMock;
  }

  @Test
  public void testGetGMapsContext() throws Exception {
    String apiKey = "asdf";

    expect(confSrcMock.getProperty(eq("celements.geocoding.googlemaps.apikey"), same(
        String.class))).andReturn(apiKey).anyTimes();
    expect(gMapsContextMock.setApiKey(eq(apiKey))).andReturn(gMapsContextMock).once();
    expect(confSrcMock.getProperty(eq("celements.geocoding.googlemaps.qps"), eq(2))).andReturn(
        2).anyTimes();
    expect(gMapsContextMock.setQueryRateLimit(eq(2))).andReturn(gMapsContextMock).once();

    replayDefault();
    assertSame(gMapsContextMock, geocodingService.getGMapsContext());
    verifyDefault();
  }

}
