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

import static org.junit.Assert.assertEquals;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.junit.Test;
import org.surfnet.example.api.model.Student;

/**
 * 
 *
 */
public class JsonPrincipalServiceTest {

  private final JsonPrincipalService service = new JsonPrincipalService();

  /**
   * Test method for
   * {@link org.surfnet.example.api.oauth.JsonPrincipalService#getPrincipal(org.apache.http.auth.UsernamePasswordCredentials)}
   * .
   */
  @Test
  public void testGetPrincipal() {
    Student principal = service.getPrincipal(new UsernamePasswordCredentials("foo8", "doen-not-matter"));
    assertEquals("foo8", principal.getIdentifier());
  }

}
