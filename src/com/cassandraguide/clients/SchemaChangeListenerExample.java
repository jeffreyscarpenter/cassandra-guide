/*
 * Copyright (C) 2016 Jeff Carpenter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cassandraguide.clients;

import java.util.List;
import java.util.ArrayList;
import com.datastax.driver.core.AggregateMetadata;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.FunctionMetadata;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.MaterializedViewMetadata;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.SchemaChangeListener;
import com.datastax.driver.core.TableMetadata;
import com.datastax.driver.core.UserType;

public class SchemaChangeListenerExample implements SchemaChangeListener {

		
		public SchemaChangeListenerExample() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public String getHostString(Host host) {
			return new StringBuilder("Data Center: " + host.getDatacenter() +
					" Rack: " + host.getRack() + 
					" Host: " + host.getAddress()).toString() +
					" Version: " + host.getCassandraVersion() +
					" State: " + host.getState();
		}
		
		
		public static void main(String[] args) {
			
			List<Host.StateListener> list = new ArrayList<Host.StateListener>();
			list.add(new ConnectionListenerExample());
			Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1")
					//.withCredentials("jeff", "i6XJsj!k#9")
					.withInitialListeners(list)
					.build();
			
			cluster.init();
			
		}

		@Override
		public void onAggregateAdded(AggregateMetadata arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAggregateChanged(AggregateMetadata arg0,
				AggregateMetadata arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAggregateRemoved(AggregateMetadata arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onFunctionAdded(FunctionMetadata arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onFunctionChanged(FunctionMetadata arg0,
				FunctionMetadata arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onFunctionRemoved(FunctionMetadata arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onKeyspaceAdded(KeyspaceMetadata arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onKeyspaceChanged(KeyspaceMetadata arg0,
				KeyspaceMetadata arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onKeyspaceRemoved(KeyspaceMetadata arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onMaterializedViewAdded(MaterializedViewMetadata arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onMaterializedViewChanged(MaterializedViewMetadata arg0,
				MaterializedViewMetadata arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onMaterializedViewRemoved(MaterializedViewMetadata arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRegister(Cluster arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTableAdded(TableMetadata arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTableChanged(TableMetadata arg0, TableMetadata arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTableRemoved(TableMetadata arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onUnregister(Cluster arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onUserTypeAdded(UserType arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onUserTypeChanged(UserType arg0, UserType arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onUserTypeRemoved(UserType arg0) {
			// TODO Auto-generated method stub
			
		}


}
