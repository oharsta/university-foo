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
 * An AccessToken
 * 
 * See https://tools.ietf.org/html/draft-ietf-oauth-v2-26#section-4.1.3
 * 
 */
public class AccessToken {
  private String access_token;
  private Long expires_in;

  public AccessToken(String access_token, Long expires_in) {
    super();
    this.access_token = access_token;
    this.expires_in = expires_in;
  }

  /**
   * @return the access_token
   */
  public String getAccess_token() {
    return access_token;
  }

  /**
   * @return the expires_in
   */
  public Long getExpires_in() {
    return expires_in;
  }
}
