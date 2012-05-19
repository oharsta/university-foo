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
package org.surfnet.example.api;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.surfnet.example.api.model.AccessToken;
import org.surfnet.example.api.model.ClientDetails;
import org.surfnet.example.api.model.Student;
import org.surfnet.example.api.oauth.InMemoryOAuthTokenStore;
import org.surfnet.example.api.oauth.JsonPrincipalService;
import org.surfnet.example.api.oauth.OAuthAccessTokenResource;
import org.surfnet.example.api.oauth.OAuthAuthenticator;
import org.surfnet.example.api.oauth.OAuthAuthorizeResource;
import org.surfnet.example.api.oauth.OAuthTokenStore;
import org.surfnet.example.api.oauth.PrincipalService;
import org.surfnet.example.api.resources.HelpResource;
import org.surfnet.example.api.resources.StudentHealthCheck;
import org.surfnet.example.api.resources.StudentResource;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.auth.oauth.OAuthProvider;
import com.yammer.dropwizard.bundles.AssetsBundle;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.ViewBundle;

/**
 * Main Entry Point
 * 
 */
public class UniversityFooService extends Service<UniversityFooConfiguration> {

  /*
   * Used by DropWizard to bootstrap the application. See README.md
   */
  public static void main(String[] args) throws Exception {
    if (args == null || args.length != 2) {
      args = new String[] { "server", "university-foo-dev.yml" };
    }
    new UniversityFooService().run(args);
  }

  private UniversityFooService() {
    super("university-foo");
    /*
     * For serving static content
     */
    addBundle(new AssetsBundle());
    /*
     * For supporting freemarker views
     */
    addBundle(new ViewBundle());
  }

  @Override
  protected void initialize(UniversityFooConfiguration configuration, Environment environment)
      throws ClassNotFoundException {
    OAuthTokenStore<AccessToken, ClientDetails, String> tokenStore = new InMemoryOAuthTokenStore();
    PrincipalService<Student, UsernamePasswordCredentials> principalService = new JsonPrincipalService();

    /*
     * Add a OAuth Provider to secure REST endpoints
     */
    environment
        .addProvider(new OAuthProvider<ClientDetails>(new OAuthAuthenticator(tokenStore), "protected-resources"));
    environment.addHealthCheck(new StudentHealthCheck("student"));

    /*
     * Add all university Resources
     */
    
    /*
     * Hook for expanding the currently very limited REST api
     */
    environment.addResource(new StudentResource(principalService));
    
    /*
     * Hook for implementing the help section (analogue to http://developer.yammer.com/api/oauth2.html)
     */
    environment.addResource(new HelpResource());
    /*
     * The OAuth endpoint for obtaining an access token
     */
    environment.addResource(new OAuthAccessTokenResource(tokenStore));
    environment.addResource(new OAuthAuthorizeResource(tokenStore, principalService));
  }

}
