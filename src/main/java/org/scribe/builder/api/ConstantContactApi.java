package org.scribe.builder.api;

import org.scribe.model.Token;

public class ConstantContactApi extends DefaultApi10a {

	private static final String AUTHORIZE_URL = "https://oauth.constantcontact.com/ws/oauth/confirm_access?oauth_token=%s";
	@Override
	public String getAccessTokenEndpoint() {
		// TODO Auto-generated method stub
		return "https://oauth.constantcontact.com/ws/oauth/access_token";
	}

	@Override
	public String getAuthorizationUrl(Token requestToken) {
		// TODO Auto-generated method stub
		return String.format(AUTHORIZE_URL, requestToken.getToken());
	}

	@Override
	public String getRequestTokenEndpoint() {
		// TODO Auto-generated method stub
		return "https://oauth.constantcontact.com/ws/oauth/request_token";
	}

}



