package com.github.scribejava;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import org.junit.Test;

import com.github.scribejava.core.model.Verb;
import com.github.scribejava.httpclient.armeria.ArmeriaHttpClient;
import com.linecorp.armeria.common.HttpMethod;

public class ArmeriaHttpCustomTest {
    @Test
    public void testGetHttpMethod_GET() {
        System.out.println("gang shit\n");
        System.out.println("THIS IS THE ." + ArmeriaHttpClient.getHttpMethod(Verb.GET) + ".");
        System.out.println("THIS IS THE pt 2 ." + HttpMethod.GET + ".");

        assertEquals(HttpMethod.GET, ArmeriaHttpClient.getHttpMethod(Verb.GET));
    }

    @Test
    public void testGetHttpMethod_POST() {
        assertEquals(HttpMethod.POST, ArmeriaHttpClient.getHttpMethod(Verb.POST));
    }

    @Test
    public void testGetHttpMethod_PUT() {
        assertEquals(HttpMethod.PUT, ArmeriaHttpClient.getHttpMethod(Verb.PUT));
    }

    @Test
    public void testGetHttpMethod_DELETE() {
        assertEquals(HttpMethod.DELETE,
                ArmeriaHttpClient.getHttpMethod(Verb.DELETE));
    }

    @Test
    public void testGetHttpMethod_HEAD() {
        assertEquals(HttpMethod.HEAD, ArmeriaHttpClient.getHttpMethod(Verb.HEAD));
    }

    @Test
    public void testGetHttpMethod_OPTIONS() {
        assertEquals(HttpMethod.OPTIONS,
                ArmeriaHttpClient.getHttpMethod(Verb.OPTIONS));
    }

    @Test
    public void testGetHttpMethod_TRACE() {
        assertEquals(HttpMethod.TRACE, ArmeriaHttpClient.getHttpMethod(Verb.TRACE));
    }

    @Test
    public void testGetHttpMethod_PATCH() {
        assertEquals(HttpMethod.PATCH, ArmeriaHttpClient.getHttpMethod(Verb.PATCH));
    }

    @Test
    public void testGetServicePath_withQueryAndFragment() throws Exception {
        URI uri = new URI("http://example.com/path?query=123#fragment");
        String result = ArmeriaHttpClient.getServicePath(uri);
        assertEquals("/path?query=123#fragment", result);
    }

    @Test
    public void testGetServicePath_withQuery() throws Exception {
        URI uri = new URI("http://example.com/path?query=123");
        String result = ArmeriaHttpClient.getServicePath(uri);
        assertEquals("/path?query=123", result);
    }

    @Test
    public void testGetServicePath_withFragment() throws Exception {
        URI uri = new URI("http://example.com/path#fragment");
        String result = ArmeriaHttpClient.getServicePath(uri);
        assertEquals("/path#fragment", result);
    }

    @Test
    public void testGetServicePath_withoutQueryAndFragment() throws Exception {
        URI uri = new URI("http://example.com/path");
        String result = ArmeriaHttpClient.getServicePath(uri);
        assertEquals("/path", result);
    }
}
