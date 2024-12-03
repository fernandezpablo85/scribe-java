package com.github.scribejava.apis;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.OAuth2AccessTokenExtractor;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.Verb;

public class LarkApi extends DefaultApi20 {

    protected LarkApi() {
    }

    private static class InstanceHolder {
        private static final LarkApi INSTANCE = new LarkApi();
    }

    public static LarkApi instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.POST;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://open.larksuite.com/open-apis/authen/v2/oauth/token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://accounts.larksuite.com/open-apis/authen/v1/authorize";
    }

    @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
        return OAuth2AccessTokenExtractor.instance();
    }
}
