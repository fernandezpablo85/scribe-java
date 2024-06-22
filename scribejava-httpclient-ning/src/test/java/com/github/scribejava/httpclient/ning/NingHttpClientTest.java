package com.github.scribejava.httpclient.ning;

import com.github.scribejava.core.AbstractClientTest;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.model.Verb;
import org.junit.Test;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.Future;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.concurrent.Future;

import static org.junit.Assert.fail;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;




public class NingHttpClientTest extends AbstractClientTest {

    @Override
    protected HttpClient createNewClient() {
        return new NingHttpClient();
    }


//new tests


@Test
public void testDoExecuteAsyncPut() {
    NingHttpClient client = (NingHttpClient) createNewClient(); 
    String completeUrl = "http://example.com/put";
    Map<String, String> headers = new HashMap<>();
    
    Future<?> future = client.doExecuteAsync("TestAgent", headers, Verb.PUT, completeUrl, NingHttpClient.BodySetter.STRING, "", null, null);

    assertTrue(NingHttpClient.branchCoverage.get("NingHttpClientdoExecuteAsync.branch_3").get());
    assertNotNull(future); 
}



@Test
public void testDoExecuteAsyncDelete() {
    NingHttpClient client = (NingHttpClient) createNewClient();
    String completeUrl = "http://example.com/delete";
    Map<String, String> headers = new HashMap<>();
    Future<?> future = client.doExecuteAsync("TestAgent", headers, Verb.DELETE, completeUrl, NingHttpClient.BodySetter.STRING, "", null, null);

    assertTrue(NingHttpClient.branchCoverage.get("NingHttpClientdoExecuteAsync.branch_4").get());
    assertNotNull(future);
}


@Test(expected = IllegalArgumentException.class)
public void testDoExecuteAsyncDefault() {
    NingHttpClient client = (NingHttpClient) createNewClient(); 
    String completeUrl = "http://example.com/default";
    Map<String, String> headers = new HashMap<>();

    // Attempt to use the UNSUPPORTED verb, expecting an IllegalArgumentException
    client.doExecuteAsync("TestAgent", headers, Verb.TRACE, completeUrl, NingHttpClient.BodySetter.STRING, "", null, null);

    fail("Expected an IllegalArgumentException to be thrown for UNSUPPORTED verb.");
}





    
}
