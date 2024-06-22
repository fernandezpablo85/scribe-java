package com.github.scribejava.apis.facebook;

import com.github.scribejava.core.model.OAuthResponseException;
import com.github.scribejava.core.model.Response;
import java.io.IOException;
import java.util.Objects;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
/**
 * non standard Facebook replace for {@link com.github.scribejava.core.model.OAuth2AccessTokenErrorResponse}
 *
 * examples:<br>
 *
 * '{"error":{"message":"This authorization code has been
 * used.","type":"OAuthException","code":100,"fbtrace_id":"DtxvtGRaxbB"}}'<br>
 *
 * '{"error":{"message":"Error validating application. Invalid application
 * ID.","type":"OAuthException","code":101,"fbtrace_id":"CvDR+X4WWIx"}}'
 */
public class FacebookAccessTokenErrorResponse extends OAuthResponseException {

    private static final long serialVersionUID = -1277129766099856895L;

    private final String errorMessage;
    private final String type;
    private final int codeInt;
    private final String fbtraceId;

     // Branch coverage data structure Nikola
    private static final ConcurrentHashMap<String, AtomicBoolean> branchCoverage = new ConcurrentHashMap<>();

    static {
        branchCoverage.put("FacebookAccessTokenErrorResponse.equals.branch_1", new AtomicBoolean(false));
        branchCoverage.put("FacebookAccessTokenErrorResponse.equals.branch_2", new AtomicBoolean(false));
        branchCoverage.put("FacebookAccessTokenErrorResponse.equals.branch_3", new AtomicBoolean(false));
        branchCoverage.put("FacebookAccessTokenErrorResponse.equals.branch_4", new AtomicBoolean(false));
        branchCoverage.put("FacebookAccessTokenErrorResponse.equals.branch_5", new AtomicBoolean(false));
        branchCoverage.put("FacebookAccessTokenErrorResponse.equals.branch_6", new AtomicBoolean(false));
        branchCoverage.put("FacebookAccessTokenErrorResponse.equals.branch_7", new AtomicBoolean(false));


        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (System.out) {
                    System.out.println("FacebookAccessTokenErrorResponse equals method coverage:");
                    for (Map.Entry<String, AtomicBoolean> entry : branchCoverage.entrySet()) {
                        System.out.println(entry.getKey() + ": " + (entry.getValue().get() ? "Taken" : "Not taken"));
                    }
                }
            }
        }));
    }

    public FacebookAccessTokenErrorResponse(String errorMessage, String type, int code, String fbtraceId,
            Response response)
            throws IOException {
        super(response);
        this.errorMessage = errorMessage;
        this.type = type;
        this.codeInt = code;
        this.fbtraceId = fbtraceId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getType() {
        return type;
    }

    public int getCodeInt() {
        return codeInt;
    }

    public String getFbtraceId() {
        return fbtraceId;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 83 * hash + Objects.hashCode(errorMessage);
        hash = 83 * hash + Objects.hashCode(type);
        hash = 83 * hash + Objects.hashCode(codeInt);
        hash = 83 * hash + Objects.hashCode(fbtraceId);
        return hash;
    }

    // branch coverage Nikola
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            branchCoverage.get("FacebookAccessTokenErrorResponse.equals.branch_1").set(true);
            return true;
        }
        if (obj == null) {
            branchCoverage.get("FacebookAccessTokenErrorResponse.equals.branch_2").set(true);
            return false;
        }
        if (getClass() != obj.getClass()) {
            branchCoverage.get("FacebookAccessTokenErrorResponse.equals.branch_3").set(true);
            return false;
        }
        FacebookAccessTokenErrorResponse other = (FacebookAccessTokenErrorResponse) obj;
        if (!Objects.equals(errorMessage, other.errorMessage)) {
            branchCoverage.get("FacebookAccessTokenErrorResponse.equals.branch_4").set(true);
            return false;
        }
        if (!Objects.equals(type, other.type)) {
            branchCoverage.get("FacebookAccessTokenErrorResponse.equals.branch_5").set(true);
            return false;
        }
        if (codeInt != other.codeInt) {
            branchCoverage.get("FacebookAccessTokenErrorResponse.equals.branch_6").set(true);
            return false;
        }
        if (!Objects.equals(fbtraceId, other.fbtraceId)) {
            branchCoverage.get("FacebookAccessTokenErrorResponse.equals.branch_7").set(true);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FacebookAccessTokenErrorResponse{'type'='" + type + "', 'codeInt'='" + codeInt
                + "', 'fbtraceId'='" + fbtraceId + "', 'response'='" + getResponse()
                + "', 'errorMessage'='" + errorMessage + "'}";
    }
}
