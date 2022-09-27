package com.celements.crm.place;

import static com.celements.common.test.CelementsTestUtils.*;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.celements.common.test.AbstractComponentTest;
import com.celements.metatag.MetaTag;
import com.celements.metatag.MetaTagProviderRole;
import com.xpn.xwiki.web.Utils;

public class GoogleMapsApiKeyMetaTagTest extends AbstractComponentTest {

  private GoogleMapsApiKeyMetaTagService gmapsMetaTagSrv;

  @Before
  public void setUp_GoogleMapsApiKeyMetaTagTest() throws Exception {
    gmapsMetaTagSrv = (GoogleMapsApiKeyMetaTagService) Utils.getComponent(MetaTagProviderRole.class,
        GoogleMapsApiKeyMetaTagService.NAME);
  }

  @Test
  public void test_hasGoogleMapsApiKey_true() {
    expect(getWikiMock().getXWikiPreference(GoogleMapsApiKeyMetaTagService.CEL_GOOGLE_MAPS_API_KEY,
        "", getContext())).andReturn("sdfasdf");
    replayDefault();
    assertTrue(gmapsMetaTagSrv.hasGoogleMapsApiKey());
    verifyDefault();
  }

  @Test
  public void test_hasGoogleMapsApiKey_false() {
    expect(getWikiMock().getXWikiPreference(GoogleMapsApiKeyMetaTagService.CEL_GOOGLE_MAPS_API_KEY,
        "", getContext())).andReturn("");
    replayDefault();
    assertFalse(gmapsMetaTagSrv.hasGoogleMapsApiKey());
    verifyDefault();
  }

  @Test
  public void test_getGoogleMapsApiKey_withKey() {
    String expectedApiKey = "E8lkKj2348";
    expect(
        getWikiMock().getXWikiPreference(eq(GoogleMapsApiKeyMetaTagService.CEL_GOOGLE_MAPS_API_KEY),
            eq(""), same(getContext()))).andReturn(expectedApiKey);
    replayDefault();
    assertEquals(expectedApiKey, gmapsMetaTagSrv.getGoogleMapsApiKey());
    verifyDefault();
  }

  @Test
  public void test_getGoogleMapsApiKey_noKey() {
    expect(
        getWikiMock().getXWikiPreference(eq(GoogleMapsApiKeyMetaTagService.CEL_GOOGLE_MAPS_API_KEY),
            eq(""), same(getContext()))).andReturn("");
    replayDefault();
    assertEquals("", gmapsMetaTagSrv.getGoogleMapsApiKey());
    verifyDefault();
  }

  @Test
  public void test_getBodyMetaTags() {
    replayDefault();
    assertEquals(Collections.emptyList(), gmapsMetaTagSrv.getBodyMetaTags());
    verifyDefault();
  }

  @Test
  public void test_getHeaderMetaTags_noApiKey() {
    expect(
        getWikiMock().getXWikiPreference(eq(GoogleMapsApiKeyMetaTagService.CEL_GOOGLE_MAPS_API_KEY),
            eq(""), same(getContext()))).andReturn("");
    replayDefault();
    assertEquals(Collections.emptyList(), gmapsMetaTagSrv.getHeaderMetaTags());
    verifyDefault();
  }

  @Test
  public void test_getHeaderMetaTags_withApiKey() {
    String expectedApiKey = "E8lkKj2348";
    expect(
        getWikiMock().getXWikiPreference(eq(GoogleMapsApiKeyMetaTagService.CEL_GOOGLE_MAPS_API_KEY),
            eq(""), same(getContext()))).andReturn(expectedApiKey).atLeastOnce();
    replayDefault();
    List<MetaTag> tagList = gmapsMetaTagSrv.getHeaderMetaTags();
    assertNotNull(tagList);
    assertEquals(1, tagList.size());
    MetaTag apiKeyMetaTag = tagList.get(0);
    assertEquals("<meta name=\"cel-GMaps-ApiKey\" content=\"E8lkKj2348\" />",
        apiKeyMetaTag.display());
    verifyDefault();
  }

}
