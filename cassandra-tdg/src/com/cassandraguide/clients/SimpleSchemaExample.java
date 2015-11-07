package com.cassandraguide.clients;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;

public class SimpleSchemaExample {

	public static void main(String[] args) {

		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1")
				.withCredentials("jeff", "i6XJsj!k#9").build();

		cluster.init();

		Metadata metadata = cluster.getMetadata();
		System.out.printf("Connected to cluster: %s %s\n",
				metadata.getClusterName(), cluster.getClusterName());


		System.out.println("Schema:");
		System.out.println(metadata.exportSchemaAsString());
		System.out.println();
		
		System.out.printf("Schema agreement : %s\n",
				metadata.checkSchemaAgreement());
		
		// close and exit
		cluster.close();
		System.exit(0);
	}
}
