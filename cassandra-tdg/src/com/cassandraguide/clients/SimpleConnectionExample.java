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

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;

// Simple example to connect to a cluster and print metadata
public class SimpleConnectionExample {

	public static void main(String[] args) {
		
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1")
				//.withCredentials("jeff", "i6XJsj!k#9")
				.build();
		
		cluster.init();

		Metadata metadata = cluster.getMetadata();
		System.out.printf("Connected to cluster: %s %s\n",
				metadata.getClusterName(), cluster.getClusterName());
		
		for (Host host : metadata.getAllHosts()) {
			System.out.printf("Data Center: %s; Rack: %s; Host: %s\n",
					host.getDatacenter(), host.getRack(), host.getAddress());
		}
		
		System.out.printf("Protocol Version: %s\n", 
				cluster.getConfiguration()
			    .getProtocolOptions()
			    .getProtocolVersion());
		
		// close and exit
		cluster.close();
		System.exit(0);
	}

}
