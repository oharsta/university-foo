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

import java.io.InputStream;
import java.util.List;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.surfnet.example.api.model.Student;
import org.surfnet.example.api.model.University;

/**
 * In memory {@link PrincipalService}
 * 
 */
public class JsonPrincipalService implements PrincipalService<Student, UsernamePasswordCredentials> {

  private static final String UNIVERSITY_FOO_JSON = "university-foo.json";

  private final ObjectMapper objectMapper = new ObjectMapper().enable(
      DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY).setSerializationInclusion(
      JsonSerialize.Inclusion.NON_NULL);

  private final University university;

  public JsonPrincipalService() {
    super();
    try {
      university = objectMapper.readValue(
          Thread.currentThread().getContextClassLoader().getResourceAsStream(UNIVERSITY_FOO_JSON), University.class);
    } catch (Exception e) {
      throw new RuntimeException(String.format("Unable to parse %s", UNIVERSITY_FOO_JSON), e);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.surfnet.example.api.oauth.PrincipalService#getPrincipal(java.lang.Object
   * )
   */
  @Override
  public Student getPrincipal(UsernamePasswordCredentials c) {
    List<Student> students = university.getStudents();
    for (Student student : students) {
      if (student.getIdentifier().equalsIgnoreCase(c.getUserName())) {
        return student;
      }
    }
    throw new RuntimeException(String.format("Could not find student with identifier %s", c.getUserName()));
  }

}
