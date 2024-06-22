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
    public static final ConcurrentHashMap<String, AtomicBoolean> branchCoverage = new ConcurrentHashMap<>();
     // Branch coverage data structure Nikola
    static {
        branchCoverage.put("NingHttpClientdoExecuteAsync.branch_1", new AtomicBoolean(false)); 
        branchCoverage.put("NingHttpClientdoExecuteAsync.branch_2", new AtomicBoolean(false)); 
        branchCoverage.put("NingHttpClientdoExecuteAsync.branch_3", new AtomicBoolean(false)); 
        branchCoverage.put("NingHttpClientdoExecuteAsync.branch_4", new AtomicBoolean(false)); 
        branchCoverage.put("NingHttpClientdoExecuteAsync.branch_5", new AtomicBoolean(false)); 
        branchCoverage.put("NingHttpClientdoExecuteAsync.branch_6", new AtomicBoolean(false)); 
        branchCoverage.put("NingHttpClientdoExecuteAsync.branch_7", new AtomicBoolean(false)); 
        branchCoverage.put("NingHttpClientdoExecuteAsync.branch_8", new AtomicBoolean(false)); 


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
    public <T> Future<T> doExecuteAsync(String userAgent, Map<String, String> headers, Verb httpVerb,
        String completeUrl, BodySetter bodySetter, Object bodyContents, OAuthAsyncRequestCallback<T> callback,
        OAuthRequest.ResponseConverter<T> converter) {
    final AsyncHttpClient.BoundRequestBuilder boundRequestBuilder;
    switch (httpVerb) {
        // ID: branch_1
        case GET:
            branchCoverage.get("NingHttpClientdoExecuteAsync.branch_1").set(true); 
            boundRequestBuilder = client.prepareGet(completeUrl);
            break;
        // ID: branch_2
        case POST:
            branchCoverage.get("NingHttpClientdoExecuteAsync.branch_2").set(true);
            boundRequestBuilder = client.preparePost(completeUrl);
            break;
        // ID: branch_3
        case PUT:
            branchCoverage.get("NingHttpClientdoExecuteAsync.branch_3").set(true); 
            boundRequestBuilder = client.preparePut(completeUrl);
            break;
        // ID: branch_4    
        case DELETE:
            branchCoverage.get("NingHttpClientdoExecuteAsync.branch_4").set(true); 
            boundRequestBuilder = client.prepareDelete(completeUrl);
            break;
        // ID: branch_5
        default:
            branchCoverage.get("NingHttpClientdoExecuteAsync.branch_5").set(true); 
            throw new IllegalArgumentException("message build error: unknown verb type");
    }

    // ID: branch_6
    if (httpVerb.isPermitBody()) {
        branchCoverage.get("NingHttpClientdoExecuteAsync.branch_6").set(true); 
        // ID: branch_7
        if (!headers.containsKey("Content-Type")) {
            branchCoverage.get("NingHttpClientdoExecuteAsync.branch_7").set(true); 
            boundRequestBuilder.addHeader("Content-Type", "application/x-www-form-urlencoded");
        }
        bodySetter.setBody(boundRequestBuilder, bodyContents);
    }

    for (Map.Entry<String, String> header : headers.entrySet()) {
        boundRequestBuilder.addHeader(header.getKey(), header.getValue());
    }

    // ID: branch_8
    if (userAgent != null) {
        branchCoverage.get("NingHttpClientdoExecuteAsync.branch_8").set(true); 
        boundRequestBuilder.setHeader(OAuthConstants.USER_AGENT_HEADER_NAME, userAgent);
    }

    return boundRequestBuilder.execute(new OAuthAsyncCompletionHandler<>(callback, converter));
}


    public enum BodySetter {
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
