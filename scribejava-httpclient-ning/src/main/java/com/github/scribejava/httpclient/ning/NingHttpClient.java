package com.github.scribejava.httpclient.ning;

import com.github.scribejava.core.httpclient.AbstractAsyncOnlyHttpClient;
import com.github.scribejava.core.httpclient.multipart.MultipartPayload;
import com.github.scribejava.core.model.OAuthAsyncRequestCallback;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.ning.http.client.AsyncHttpClient;


import java.util.Map;
import java.util.concurrent.Future;

import com.ning.http.client.AsyncHttpClientConfig;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class NingHttpClient extends AbstractAsyncOnlyHttpClient {

    private final AsyncHttpClient client;
    private static final ConcurrentHashMap<String, AtomicBoolean> branchCoverage = new ConcurrentHashMap<>();
    // data structure for info about the branches 
    static {
        branchCoverage.put("branch_1", new AtomicBoolean(false)); // GET
        branchCoverage.put("branch_2", new AtomicBoolean(false)); // POST
        branchCoverage.put("branch_3", new AtomicBoolean(false)); // PUT
        branchCoverage.put("branch_4", new AtomicBoolean(false)); // DELETE
        branchCoverage.put("branch_5", new AtomicBoolean(false)); // DEFAULT

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Branch coverage results:");
                for (Map.Entry<String, AtomicBoolean> entry : branchCoverage.entrySet()) {
                    System.out.println(entry.getKey() + ": " + (entry.getValue().get() ? "Taken" : "Not taken"));
                }
            }
        }));
    }

    public NingHttpClient() {
        this(NingHttpClientConfig.defaultConfig());
    }

    public NingHttpClient(NingHttpClientConfig ningConfig) {
        final String ningAsyncHttpProviderClassName = ningConfig.getNingAsyncHttpProviderClassName();
        AsyncHttpClientConfig config = ningConfig.getConfig();
        if (ningAsyncHttpProviderClassName == null) {
            client = config == null ? new AsyncHttpClient() : new AsyncHttpClient(config);
        } else {
            if (config == null) {
                config = new AsyncHttpClientConfig.Builder().build();
            }
            client = new AsyncHttpClient(ningAsyncHttpProviderClassName, config);
        }
    }

    public NingHttpClient(AsyncHttpClient client) {
        this.client = client;
    }

    @Override
    public void close() {
        client.close();
    }

    @Override
    public <T> Future<T> executeAsync(String userAgent, Map<String, String> headers, Verb httpVerb, String completeUrl,
            final byte[] bodyContents, OAuthAsyncRequestCallback<T> callback,
            OAuthRequest.ResponseConverter<T> converter) {

        return doExecuteAsync(userAgent, headers, httpVerb, completeUrl, BodySetter.BYTE_ARRAY, bodyContents, callback,
                converter);
    }

    @Override
    public <T> Future<T> executeAsync(String userAgent, Map<String, String> headers, Verb httpVerb, String completeUrl,
            MultipartPayload bodyContents, OAuthAsyncRequestCallback<T> callback,
            OAuthRequest.ResponseConverter<T> converter) {

        throw new UnsupportedOperationException("NingHttpClient does not support MultipartPayload yet.");
    }

    @Override
    public <T> Future<T> executeAsync(String userAgent, Map<String, String> headers, Verb httpVerb, String completeUrl,
            final String bodyContents, OAuthAsyncRequestCallback<T> callback,
            OAuthRequest.ResponseConverter<T> converter) {

        return doExecuteAsync(userAgent, headers, httpVerb, completeUrl, BodySetter.STRING, bodyContents, callback,
                converter);
    }

    @Override
    public <T> Future<T> executeAsync(String userAgent, Map<String, String> headers, Verb httpVerb, String completeUrl,
            final File bodyContents, OAuthAsyncRequestCallback<T> callback,
            OAuthRequest.ResponseConverter<T> converter) {

        return doExecuteAsync(userAgent, headers, httpVerb, completeUrl, BodySetter.FILE, bodyContents, callback,
                converter);
    }

    // branch coverage: Nikola
    private <T> Future<T> doExecuteAsync(String userAgent, Map<String, String> headers, Verb httpVerb,
        String completeUrl, BodySetter bodySetter, Object bodyContents, OAuthAsyncRequestCallback<T> callback,
        OAuthRequest.ResponseConverter<T> converter) {
    final AsyncHttpClient.BoundRequestBuilder boundRequestBuilder;
    switch (httpVerb) {
        // ID: branch_1
        case GET:
            branchCoverage.get("branch_1").set(true); // Update branch coverage for GET
            boundRequestBuilder = client.prepareGet(completeUrl);
            break;
        // ID: branch_2
        case POST:
            branchCoverage.get("branch_2").set(true); // Update branch coverage for POST
            boundRequestBuilder = client.preparePost(completeUrl);
            break;
        // ID: branch_3
        case PUT:
            branchCoverage.get("branch_3").set(true); // Update branch coverage for PUT
            boundRequestBuilder = client.preparePut(completeUrl);
            break;
        // ID: branch_4    
        case DELETE:
            branchCoverage.get("branch_4").set(true); // Update branch coverage for DELETE
            boundRequestBuilder = client.prepareDelete(completeUrl);
            break;
        // ID: branch_5
        default:
            branchCoverage.get("branch_5").set(true); // Update branch coverage for DEFAULT case
            throw new IllegalArgumentException("message build error: unknown verb type");
    }

    if (httpVerb.isPermitBody()) {
        // Check if the Content-Type header is already present
        if (!headers.containsKey("Content-Type")) {
            // Set the Content-Type header if not present
            boundRequestBuilder.addHeader("Content-Type", "application/x-www-form-urlencoded");
        }
        bodySetter.setBody(boundRequestBuilder, bodyContents);
    }

    for (Map.Entry<String, String> header : headers.entrySet()) {
        boundRequestBuilder.addHeader(header.getKey(), header.getValue());
    }

    if (userAgent != null) {
        boundRequestBuilder.setHeader(OAuthConstants.USER_AGENT_HEADER_NAME, userAgent);
    }

    return boundRequestBuilder.execute(new OAuthAsyncCompletionHandler<>(callback, converter));
}


    private enum BodySetter {
        BYTE_ARRAY {
            @Override
            AsyncHttpClient.BoundRequestBuilder setBody(AsyncHttpClient.BoundRequestBuilder requestBuilder,
                    Object bodyContents) {
                return requestBuilder.setBody((byte[]) bodyContents);
            }
        },
        STRING {
            @Override
            AsyncHttpClient.BoundRequestBuilder setBody(AsyncHttpClient.BoundRequestBuilder requestBuilder,
                    Object bodyContents) {
                return requestBuilder.setBody((String) bodyContents);
            }
        },
        FILE {
            @Override
            AsyncHttpClient.BoundRequestBuilder setBody(AsyncHttpClient.BoundRequestBuilder requestBuilder,
                    Object bodyContents) {
                return requestBuilder.setBody((File) bodyContents);
            }
        };

        abstract AsyncHttpClient.BoundRequestBuilder setBody(AsyncHttpClient.BoundRequestBuilder requestBuilder,
                Object bodyContents);
    }
}
