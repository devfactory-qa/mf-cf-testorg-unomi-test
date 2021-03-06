/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.unomi.graphql.types.output;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import graphql.schema.DataFetchingEnvironment;
import org.apache.unomi.api.Event;
import org.apache.unomi.graphql.schema.CDPEventInterfaceRegister;
import org.apache.unomi.graphql.services.ServiceManager;

@GraphQLName("CDP_EventEdge")
public class CDPEventEdge {

    private Event event;

    public CDPEventEdge(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    @GraphQLField
    @GraphQLNonNull
    public String cursor() {
        return getEvent().getItemId();
    }

    @GraphQLField
    public CDPEventInterface node(final DataFetchingEnvironment environment) {
        final ServiceManager serviceManager = environment.getContext();

        return serviceManager.getService(CDPEventInterfaceRegister.class).getEvent(getEvent());
    }

}
