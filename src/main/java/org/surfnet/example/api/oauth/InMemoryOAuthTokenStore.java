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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.surfnet.example.api.model.AccessToken;
import org.surfnet.example.api.model.ClientDetails;

import com.google.common.base.Optional;

/**
 * Token store
 * 
 */
public class InMemoryOAuthTokenStore implements OAuthTokenStore<AccessToken, ClientDetails, String> {

  private final Map<String, ClientDetails> accessTokens = new HashMap<String, ClientDetails>();
  private final Map<String, ClientDetails> codes = new HashMap<String, ClientDetails>();
  private final String clientId = "university-client-key";
  private final String clientSecret = "university-client-key";

  @Override
  public AccessToken storeAccessToken(ClientDetails clientDetails) {
    verifyClientId(clientDetails);
    verifyClientSecret(clientDetails);
    AccessToken accessToken = new AccessToken(UUID.randomUUID().toString(), Long.MAX_VALUE);
    accessTokens.put(accessToken.getAccess_token(), clientDetails);
    return accessToken;
  }

  @Override
  public Optional<ClientDetails> getClientDetailsByAccessToken(String accessToken) {
    ClientDetails clientDetails = accessTokens.get(accessToken);
    return clientDetails != null ? Optional.of(clientDetails) : Optional.fromNullable(clientDetails);
  }

  @Override
  public String storeAuthorizationCode(ClientDetails clientDetails) {
    verifyClientId(clientDetails);
    String code = UUID.randomUUID().toString();
    clientDetails.setCode(code);
    codes.put(code, clientDetails);
    return code;
  }

  @Override
  public Optional<ClientDetails> getClientDetailsByAuthorizationCode(String code) {
    ClientDetails clientDetails = codes.get(code);
    return clientDetails != null ? Optional.of(clientDetails) : Optional.fromNullable(clientDetails);
  }

  private void verifyClientId(ClientDetails clientDetails) {
    if (!clientId.equalsIgnoreCase(clientDetails.getClientId())) {
      throw new ClientAuthenticationException(String.format("invalid clientId", clientDetails.getClientId()));
    }
  }

  private void verifyClientSecret(ClientDetails clientDetails) {
    String secret = clientDetails.getSecret();
    secret = (secret != null ? secret : clientSecret);
    if (!clientSecret.equalsIgnoreCase(secret)) {
      throw new ClientAuthenticationException(String.format("invalid secret", secret));
    }
  }

}
