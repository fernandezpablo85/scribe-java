package com.github.scribejava.core.oauth;

import com.ning.http.client.AsyncHttpClient;
import com.github.scribejava.core.exceptions.OAuthException;
import com.github.scribejava.core.model.AbstractRequest;
import com.github.scribejava.core.model.AccessToken;
import com.github.scribejava.core.model.ForceTypeOfHttpRequest;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.OAuthConfigAsync;
import com.github.scribejava.core.model.ScribeJavaConfig;
import com.github.scribejava.core.model.Token;

/**
 * The main ScribeJava object.
 *
 * A facade responsible for the retrieval of request and access tokens and for the signing of HTTP requests.
 *
 * @author Pablo Fernandez
 */
public abstract class OAuthService {

    private final OAuthConfig config;
    private AsyncHttpClient asyncHttpClient;

    public OAuthService(OAuthConfig config) {
        this.config = config;
        final ForceTypeOfHttpRequest forceTypeOfHttpRequest = ScribeJavaConfig.getForceTypeOfHttpRequests();
        if (config instanceof OAuthConfigAsync) {
            if (ForceTypeOfHttpRequest.FORCE_SYNC_ONLY_HTTP_REQUESTS == forceTypeOfHttpRequest) {
                throw new OAuthException("Cannot use async operations, only sync");
            }
            if (ForceTypeOfHttpRequest.PREFER_SYNC_ONLY_HTTP_REQUESTS == forceTypeOfHttpRequest) {
                config.log("Cannot use async operations, only sync");
            }
            final OAuthConfigAsync asyncConfig = (OAuthConfigAsync) config;
            final String asyncHttpProviderClassName = asyncConfig.getAsyncHttpProviderClassName();

            asyncHttpClient = asyncHttpProviderClassName == null
                    ? new AsyncHttpClient(asyncConfig.getAsyncHttpClientConfig())
                    : new AsyncHttpClient(asyncHttpProviderClassName, asyncConfig.getAsyncHttpClientConfig());
        } else {
            if (ForceTypeOfHttpRequest.FORCE_ASYNC_ONLY_HTTP_REQUESTS == forceTypeOfHttpRequest) {
                throw new OAuthException("Cannot use sync operations, only async");
            }
            if (ForceTypeOfHttpRequest.PREFER_ASYNC_ONLY_HTTP_REQUESTS == forceTypeOfHttpRequest) {
                config.log("Cannot use sync operations, only async");
            }
        }
    }

    public AsyncHttpClient getAsyncHttpClient() {
        return asyncHttpClient;
    }

    public void closeAsyncClient() {
        asyncHttpClient.close();
    }

    public OAuthConfig getConfig() {
        return config;
    }

    /**
     * Signs an OAuth request
     *
     * @param accessToken access token (obtained previously)
     * @param request request to sign
     */
    public abstract void signRequest(Token accessToken, AbstractRequest request);

    /**
     * Signs an OAuth request.
     *
     * @param accessToken access token (obtained previously)
     * @param request request to sign
     */
    public abstract void signRequest(AccessToken accessToken, AbstractRequest request);

    /**
     * Returns the OAuth version of the service.
     *
     * @return oauth version as string
     */
    public abstract String getVersion();
}
