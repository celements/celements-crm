package com.celements.crm.place;

import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.annotation.Requirement;

import com.celements.metatag.MetaTag;
import com.celements.metatag.MetaTagProviderRole;
import com.celements.model.context.ModelContext;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

@Component(GoogleMapsApiKeyMetaTagProvider.NAME)
public class GoogleMapsApiKeyMetaTagProvider implements MetaTagProviderRole {

  public static final String NAME = "GoogleMapsApiKeyMetaTag";

  public static final String CEL_GOOGLE_MAPS_API_KEY = "celGoogleMapsApiKey";

  @Requirement
  private ModelContext context;

  @Override
  public @NotNull List<MetaTag> getBodyMetaTags() {
    return Collections.emptyList();
  }

  boolean hasGoogleMapsApiKey() {
    return !Strings.isNullOrEmpty(getGoogleMapsApiKey());
  }

  String getGoogleMapsApiKey() {
    return context.getXWikiContext().getWiki().getXWikiPreference(CEL_GOOGLE_MAPS_API_KEY, "",
        context.getXWikiContext());
  }

  @Override
  public @NotNull List<MetaTag> getHeaderMetaTags() {
    if (hasGoogleMapsApiKey()) {
      return ImmutableList.of(new MetaTag("name", "cel-GMaps-ApiKey", getGoogleMapsApiKey()));
    }
    return Collections.emptyList();
  }

}
