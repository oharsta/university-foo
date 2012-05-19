/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.surfnet.example.api.oauth;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.surfnet.example.api.model.AccessToken;
import org.surfnet.example.api.model.ClientDetails;

import com.google.common.base.Optional;
import com.sun.jersey.core.util.Base64;
import com.yammer.dropwizard.logging.Log;

/**
 * Entry point for oauth calls
 * 
 */
@Path("/token")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class OAuthAccessTokenResource {

  private final OAuthTokenStore<AccessToken, ClientDetails, String> tokenStore;
  
  private static final Log LOG = Log.forClass(OAuthAccessTokenResource.class);

  public OAuthAccessTokenResource(OAuthTokenStore<AccessToken, ClientDetails, String> tokenStore) {
    super();
    this.tokenStore = tokenStore;
  }

  @POST
  public AccessToken accessToken(@HeaderParam("Authorization")
  String authorization, @FormParam("grant_type")
  String grantType, @FormParam("code")
  String code, @FormParam("redirect_uri")
  String redirectUri) {
    /*
     * http://tools.ietf.org/html/draft-ietf-oauth-v2-26#section-4.1.3
     * 
     * The Authorization header contains the <client_id>:<client_secret> string,
     * which is base64 encoded. The <client_id> is the application ID and
     * <client_secret> is the secret which is handed out-of-band
     */
    if (authorization == null) {
      throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
          .entity("Base64 encoded authorization header is required when obtaining an access token")
          .type(MediaType.TEXT_PLAIN_TYPE).build());
    }
    String[] values = new String(Base64.decode(authorization.substring("Basic ".length()))).split(":");
    String clientId = values[0];
    String secret = values[1];
    Optional<ClientDetails> opt = tokenStore.getClientDetailsByAuthorizationCode(code);
    if (opt.isPresent()) {
      ClientDetails clientDetails = opt.get();
      LOG.debug("Handing out access token for client {} with secret",clientId,secret);
      return tokenStore.storeAccessToken(clientDetails);
    }
    throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).entity("Invalid authorization")
        .type(MediaType.TEXT_PLAIN_TYPE).build());
  }

}
