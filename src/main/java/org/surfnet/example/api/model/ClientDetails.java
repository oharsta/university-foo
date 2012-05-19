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
package org.surfnet.example.api.model;

/**
 * Representation of an {@link ClientDetails}
 * 
 */
public class ClientDetails {
  private String responseType;
  private String clientId;
  private String redirectUri;
  private String scope;
  private String state;
  private String code;
  private Object principal;
  private String secret;

  /**
   * @return the responseType
   */
  public String getResponseType() {
    return responseType;
  }

  /**
   * @return the clientId
   */
  public String getClientId() {
    return clientId;
  }

  /**
   * @return the redirectUri
   */
  public String getRedirectUri() {
    return redirectUri;
  }

  /**
   * @return the scope
   */
  public String getScope() {
    return scope;
  }

  /**
   * @return the state
   */
  public String getState() {
    return state;
  }

  /**
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * @param responseType
   *          the responseType to set
   * @return
   */
  public ClientDetails setResponseType(String responseType) {
    this.responseType = responseType;
    return this;
  }

  /**
   * @param clientId
   *          the clientId to set
   * @return
   */
  public ClientDetails setClientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

  /**
   * @param redirectUri
   *          the redirectUri to set
   * @return
   */
  public ClientDetails setRedirectUri(String redirectUri) {
    this.redirectUri = redirectUri;
    return this;
  }

  /**
   * @param scope
   *          the scope to set
   * @return
   */
  public ClientDetails setScope(String scope) {
    this.scope = scope;
    return this;
  }

  /**
   * @param state
   *          the state to set
   * @return
   */
  public ClientDetails setState(String state) {
    this.state = state;
    return this;
  }

  /**
   * @param code
   *          the code to set
   * @return
   */
  public ClientDetails setCode(String code) {
    this.code = code;
    return this;
  }

  /**
   * @return the principal
   */
  public Object getPrincipal() {
    return principal;
  }

  /**
   * @param principal
   *          the principal to set
   * @return
   */
  public ClientDetails setPrincipal(Object principal) {
    this.principal = principal;
    return this;
  }

  /**
   * @return the secret
   */
  public String getSecret() {
    return secret;
  }

  /**
   * @param secret
   *          the secret to set
   * @return
   */
  public ClientDetails setSecret(String secret) {
    this.secret = secret;
    return this;
  }
}
