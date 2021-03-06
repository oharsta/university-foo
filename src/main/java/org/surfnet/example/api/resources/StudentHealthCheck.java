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
package org.surfnet.example.api.resources;

import com.yammer.metrics.core.HealthCheck;

/**
 * {@link HealthCheck} that currently does not check anything (but does ensures
 * we don't get the annoying startup message that we don't have HealthChecks;-)
 * 
 */
public class StudentHealthCheck extends HealthCheck {

  /**
   * @param name
   */
  public StudentHealthCheck(String name) {
    super(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.yammer.metrics.core.HealthCheck#check()
   */
  @Override
  protected Result check() throws Exception {
    return Result.healthy();
  }

}
