package com.jeff.cassandra_tdg;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;

// Simple example to connect to a cluster and print metadata
public class SimpleConnectionExample {

	public static void main(String[] args) {
		
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").
				withCredentials("jeff", "i6XJsj!k#9").
				build();
		
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
