package com.github.scribejava.apis.apple;

import java.io.OutputStream;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.oauth2.clientauthentication.ClientAuthentication;
import com.github.scribejava.core.oauth2.clientauthentication.RequestBodyAuthenticationScheme;

public class AppleIDApi extends DefaultApi20
{

	protected AppleIDApi()
	{
	}

	private static class InstanceHolder
	{
		private static final AppleIDApi INSTANCE = new AppleIDApi();
	}

	public static AppleIDApi instance()
	{
		return InstanceHolder.INSTANCE;
	}

	@Override
	public String getAccessTokenEndpoint()
	{
		return "https://appleid.apple.com/auth/token";
	}

	@Override
	protected String getAuthorizationBaseUrl()
	{
		return "https://appleid.apple.com/auth/authorize";
	}

	@Override
	public String getRevokeTokenEndpoint()
	{
		return "https://appleid.apple.com/auth/revoke";
	}
	
	@Override
	public ClientAuthentication getClientAuthentication()
	{
		return RequestBodyAuthenticationScheme.instance();
	}

	@Override
	public AppleIDService createService(String apiKey, String apiSecret, String callback, String defaultScope,
		String responseType, OutputStream debugStream, String userAgent, HttpClientConfig httpClientConfig,
		HttpClient httpClient)
	{
		return new AppleIDService(this, apiKey, apiSecret, callback, defaultScope, responseType, debugStream,
			userAgent, httpClientConfig, httpClient);
	}
}
