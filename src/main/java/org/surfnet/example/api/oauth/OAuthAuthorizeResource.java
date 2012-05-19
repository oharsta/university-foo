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

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.surfnet.example.api.model.AccessToken;
import org.surfnet.example.api.model.ClientDetails;
import org.surfnet.example.api.model.Student;
import org.surfnet.example.api.views.LoginView;

/**
 * Entry point for oauth authorize calls
 * 
 */
@Path("/authorize")
@Produces(MediaType.TEXT_HTML)
public class OAuthAuthorizeResource {

  private OAuthTokenStore<AccessToken, ClientDetails, String> tokenStore;
  private PrincipalService<Student, UsernamePasswordCredentials> principalService;

  public OAuthAuthorizeResource(OAuthTokenStore<AccessToken, ClientDetails, String> tokenStore,
      PrincipalService<Student, UsernamePasswordCredentials> principalService) {
    super();
    this.tokenStore = tokenStore;
    this.principalService = principalService;
  }

  @GET
  public LoginView authorize(@QueryParam("response_type")
  String responseType, @QueryParam("client_id")
  String clientId, @QueryParam("redirect_uri")
  String redirectUri, @QueryParam("scope")
  String scope, @QueryParam("state")
  String state) {
    return new LoginView(new ClientDetails().setResponseType(responseType).setClientId(clientId)
        .setRedirectUri(redirectUri).setScope("read").setState(state == null ? "dummy" : state));
  }

  @POST
  public Response login(@FormParam("username")
  String username, @FormParam("password")
  String password, @FormParam("redirect_uri")
  String redirectUri, @FormParam("client_id")
  String clientId, @FormParam("state")
  String state) {
    /*
     * HTTP/1.1 302 Found Location:
     * https://client.example.com/cb?code=SplxlOBeZQQYbYS6WxSbIA &state=xyz
     */
    Student principal = principalService.getPrincipal(new UsernamePasswordCredentials(username, password));
    String authorizationCode = tokenStore.storeAuthorizationCode(new ClientDetails().setResponseType("code")
        .setClientId(clientId).setRedirectUri(redirectUri).setScope("read").setState(state).setPrincipal(principal));
    if (principal != null && authorizationCode != null) {
      String uri = String.format(redirectUri.concat("?").concat("code=%s").concat("&state=%s"), authorizationCode,
          state);
      try {
        return Response.seeOther(new URI(uri)).build();
      } catch (URISyntaxException e) {
        throw new RuntimeException(String.format("Redirect URI '%s' is not valid", uri));
      }
    } else {
      return Response.status(Response.Status.UNAUTHORIZED).build();
    }
  }
}
