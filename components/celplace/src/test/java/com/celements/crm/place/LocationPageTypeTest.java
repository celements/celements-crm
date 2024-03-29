package com.celements.crm.place;

import static com.celements.common.test.CelementsTestUtils.*;
import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.celements.common.test.AbstractComponentTest;
import com.celements.pagetype.category.IPageTypeCategoryRole;
import com.celements.pagetype.java.IJavaPageTypeRole;
import com.xpn.xwiki.web.Utils;

public class LocationPageTypeTest extends AbstractComponentTest {

  private LocationPageType locPageType;

  @Before
  public void setUp_LocationPageTypeTest() throws Exception {
    locPageType = (LocationPageType) Utils.getComponent(IJavaPageTypeRole.class,
        LocationPageType.PAGETYPE_NAME);
  }

  @Test
  public void test_getName() {
    replayDefault();
    assertEquals(LocationPageType.PAGETYPE_NAME, locPageType.getName());
    verifyDefault();
  }

  @Test
  public void test_displayInFrameLayout() {
    replayDefault();
    assertTrue(locPageType.displayInFrameLayout());
    verifyDefault();
  }

  @Test
  public void test_getCategories() {
    replayDefault();
    Set<IPageTypeCategoryRole> categories = locPageType.getCategories();
    assertNotNull(categories);
    assertEquals(1, categories.size());
    assertEquals(Utils.getComponent(IPageTypeCategoryRole.class), categories.toArray()[0]);
    verifyDefault();
  }

  @Test
  public void test_hasPageTitle() {
    replayDefault();
    assertFalse(locPageType.hasPageTitle());
    verifyDefault();
  }

  @Test
  public void test_isUnconnectedParent() {
    replayDefault();
    assertFalse(locPageType.isUnconnectedParent());
    verifyDefault();
  }

  @Test
  public void test_isVisible() {
    replayDefault();
    assertTrue(locPageType.isVisible());
    verifyDefault();
  }

  @Test
  public void test_getViewTemplateName() {
    replayDefault();
    assertEquals(LocationPageType.VIEW_TEMPLATE_NAME, locPageType.getViewTemplateName());
    verifyDefault();
  }

  @Test
  public void test_getEditTemplateName() {
    replayDefault();
    assertEquals(LocationPageType.EDIT_TEMPLATE_NAME, locPageType.getEditTemplateName());
    verifyDefault();
  }

  @Test
  public void test_getRenderTemplateForRenderMode_view() {
    replayDefault();
    assertEquals(LocationPageType.VIEW_TEMPLATE_NAME,
        locPageType.getRenderTemplateForRenderMode("view"));
    verifyDefault();
  }

  @Test
  public void test_getRenderTemplateForRenderMode_edit() {
    replayDefault();
    assertEquals(LocationPageType.EDIT_TEMPLATE_NAME,
        locPageType.getRenderTemplateForRenderMode("edit"));
    verifyDefault();
  }

}
