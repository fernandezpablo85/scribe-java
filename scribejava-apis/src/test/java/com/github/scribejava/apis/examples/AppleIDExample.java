package com.github.scribejava.apis.examples;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.github.scribejava.apis.apple.AppleIDApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.oauth.OAuth20Service;

public class AppleIDExample {
	
	public static void main(String[] args) throws Exception {
		final String apiKey = "your client id";
        final String apiSecret = "your client secret";
        final String secretState = "secret" + new Random().nextInt(999_999);
        
        OAuth20Service service = new ServiceBuilder(apiKey)
                .apiSecret(apiSecret)
                .callback("https://localhost/")
                .defaultScope("name email")
                .responseType("code id_token")
                .build(AppleIDApi.instance());

        Map<String, String> additionalParams = new HashMap<>();
		additionalParams.put(OAuthConstants.STATE, secretState);
		additionalParams.put("response_mode", "form_post");
        
        try (Scanner in = new Scanner(System.in)) {
			System.out.println("Fetching the Authorication URL...");
			System.out.println("Got the Authorization URL!");
			final String authorizationUrl = service.getAuthorizationUrl(additionalParams);
			System.out.println("Now go and authorize ScribeJava here:");
			System.out.println(authorizationUrl);
			System.out.println("And paste the authorization code here");
			System.out.print(">>");
			final String code = in.nextLine();
			System.out.println();

			System.out.println("Trading the Authorization Code for an Access Token...");
			OAuth2AccessToken accessToken = service.getAccessToken(code);
			System.out.println("Got the Access Token!");
			System.out.println("(The raw response looks like this: " + accessToken.getRawResponse() + "')");
			System.out.println();

			System.out.println("And paste the state from server here. We have set 'secretState'='" + secretState + "'.");
			System.out.print(">>");
			final String value = in.nextLine();
			if (secretState.equals(value)) {
			    System.out.println("State value does match!");
			} else {
			    System.out.println("Ooops, state value does not match!");
			    System.out.println("Expected = " + secretState);
			    System.out.println("Got      = " + value);
			    System.out.println();
			}

			System.out.println("Refreshing the Access Token...");
			accessToken = service.refreshAccessToken(accessToken.getRefreshToken());
			System.out.println("Refreshed the Access Token!");
			System.out.println("(The raw response looks like this: " + accessToken.getRawResponse() + "')");
		}

        System.out.println();

        System.out.println();
        System.out.println("Thats it man! Go and build something awesome with ScribeJava! :)");
    }
}
