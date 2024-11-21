package com.github.scribejava.apis.apple;

import java.io.OutputStream;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth.AccessTokenRequestParams;
import com.github.scribejava.core.oauth.OAuth20Service;

public class AppleIDService extends OAuth20Service
{
	public AppleIDService(DefaultApi20 api, String apiKey, String apiSecret, String callback, String defaultScope, String responseType,
		OutputStream debugStream, String userAgent, HttpClientConfig httpClientConfig, HttpClient httpClient)
	{
		super(api, apiKey, apiSecret, callback, defaultScope, responseType, debugStream, userAgent, httpClientConfig, httpClient);
	}

	@Override
	protected OAuthRequest createAccessTokenRequest(AccessTokenRequestParams params)
	{
		OAuthRequest req = super.createAccessTokenRequest(params);
		req.addHeader("Content-Type", "application/x-www-form-urlencoded");
		return req;
	}
}
