package com.github.scribejava.apis.facebook;

import com.github.scribejava.core.model.Response;
import java.io.IOException;
import java.util.Collections;
import static org.junit.Assert.*;
import org.junit.Test;

public class FacebookAccessTokenErrorResponseTest {

    @Test
    public void testEquals_Self() throws IOException {
        Response response = new Response(200, "OK", Collections.<String, String>emptyMap(), "Body content here");
        FacebookAccessTokenErrorResponse errorResponse = new FacebookAccessTokenErrorResponse(
            "Message", "Type", 100, "TraceID", response);

        assertTrue("Should return true when comparing to itself.",
            errorResponse.equals(errorResponse));
    }

    @Test
    public void testEquals_NullObject() throws IOException {
        Response response = new Response(200, "OK", Collections.<String, String>emptyMap(), "Body content here");
        FacebookAccessTokenErrorResponse errorResponse = new FacebookAccessTokenErrorResponse(
            "Message", "Type", 100, "TraceID", response);

        assertFalse("Should return false when comparing to null.",
            errorResponse.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() throws IOException {
        Response response = new Response(200, "OK", Collections.<String, String>emptyMap(), "Body content here");
        FacebookAccessTokenErrorResponse errorResponse = new FacebookAccessTokenErrorResponse(
            "Message", "Type", 100, "TraceID", response);
        Object otherObject = new Object();

        assertFalse("Should return false when comparing different classes.",
            errorResponse.equals(otherObject));
    }

    @Test
    public void testEquals_DifferentErrorMessage() throws IOException {
        Response response = new Response(200, "OK", Collections.<String, String>emptyMap(), "Body content here");
        FacebookAccessTokenErrorResponse errorResponse1 = new FacebookAccessTokenErrorResponse(
            "Message1", "Type", 100, "TraceID", response);
        FacebookAccessTokenErrorResponse errorResponse2 = new FacebookAccessTokenErrorResponse(
            "Message2", "Type", 100, "TraceID", response);

        assertFalse("Should return false when errorMessages are different.",
            errorResponse1.equals(errorResponse2));
    }

    @Test
    public void testEquals_DifferentType() throws IOException {
        Response response = new Response(200, "OK", Collections.<String, String>emptyMap(), "Body content here");
        FacebookAccessTokenErrorResponse errorResponse1 = new FacebookAccessTokenErrorResponse(
            "Message", "Type1", 100, "TraceID", response);
        FacebookAccessTokenErrorResponse errorResponse2 = new FacebookAccessTokenErrorResponse(
            "Message", "Type2", 100, "TraceID", response);

        assertFalse("Should return false when types are different.",
            errorResponse1.equals(errorResponse2));
    }

    @Test
    public void testEquals_DifferentCode() throws IOException {
        Response response = new Response(200, "OK", Collections.<String, String>emptyMap(), "Body content here");
        FacebookAccessTokenErrorResponse errorResponse1 = new FacebookAccessTokenErrorResponse(
            "Message", "Type", 100, "TraceID", response);
        FacebookAccessTokenErrorResponse errorResponse2 = new FacebookAccessTokenErrorResponse(
            "Message", "Type", 101, "TraceID", response);

        assertFalse("Should return false when codes are different.",
            errorResponse1.equals(errorResponse2));
    }

    @Test
    public void testEquals_DifferentFbtraceId() throws IOException {
        Response response = new Response(200, "OK", Collections.<String, String>emptyMap(), "Body content here");
        FacebookAccessTokenErrorResponse errorResponse1 = new FacebookAccessTokenErrorResponse(
            "Message", "Type", 100, "TraceID1", response);
        FacebookAccessTokenErrorResponse errorResponse2 = new FacebookAccessTokenErrorResponse(
            "Message", "Type", 100, "TraceID2", response);

        assertFalse("Should return false when fbtraceIds are different.",
            errorResponse1.equals(errorResponse2));
    }
}
