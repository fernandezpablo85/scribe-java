package org.scribe.extractors.tokenExtractors;

import java.util.regex.*;

import org.scribe.exceptions.*;
import org.scribe.extractors.tokenExtractors.AccessTokenExtractor;
import org.scribe.model.*;
import org.scribe.utils.*;

public class JsonTokenExtractor implements AccessTokenExtractor
{
  private Pattern accessTokenPattern = Pattern.compile("\"access_token\"\\s*:\\s*\"(\\S*?)\"");

  public Token extract(String response)
  {
    Preconditions.checkEmptyString(response, "Cannot extract a token from a null or empty String");
    Matcher matcher = accessTokenPattern.matcher(response);
    if(matcher.find())
    {
      return new Token(matcher.group(1), "", response);
    }
    else
    {
      throw new OAuthException("Cannot extract an acces token. Response was: " + response);
    }
  }

}