package com.jeff.cassandra_tdg;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Session;

public class SimpleSessionExample {

	public static void main(String[] args) {
		
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").
				//withCredentials("jeff", "i6XJsj!k#9").
				build();
		
		Session session = cluster.connect();
		Session.State state = session.getState();

		System.out.printf("New session created for keyspace: %s\n",
				session.getLoggedKeyspace());
		
		for (Host host : state.getConnectedHosts()) {
			System.out.printf("Data Center: %s; Rack: %s; Host: %s; Open Connections: %s\n",
					host.getDatacenter(), host.getRack(), host.getAddress(),
					state.getOpenConnections(host));
		}
		
		// close and exit
		cluster.close();
		System.exit(0);
	}

}
