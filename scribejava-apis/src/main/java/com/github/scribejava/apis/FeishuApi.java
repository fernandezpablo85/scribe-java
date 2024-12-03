package com.github.scribejava.apis;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.OAuth2AccessTokenExtractor;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.Verb;

public class FeishuApi extends DefaultApi20 {

    protected FeishuApi() {
    }

    private static class InstanceHolder {
        private static final FeishuApi INSTANCE = new FeishuApi();
    }

    public static FeishuApi instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.POST;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://open.feishu.cn/open-apis/authen/v2/oauth/token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://accounts.feishu.cn/open-apis/authen/v1/authorize";
    }

    @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
        return OAuth2AccessTokenExtractor.instance();
    }
}
