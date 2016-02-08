package com.cassandraguide.clients;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.schemabuilder.SchemaBuilder;
import com.datastax.driver.core.schemabuilder.SchemaStatement;

public class SchemaBuilderExample {

	public static void main(String[] args) {

		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1")
				.withCredentials("jeff", "i6XJsj!k#9").build();

		// create session on the "hotel" keyspace
		Session session = cluster.connect("hotel");

		
		SchemaStatement hotelSchemaStatement = SchemaBuilder.createTable("hotels").
				addPartitionKey("id", DataType.uuid()).
				addColumn("name", DataType.text()).
				addColumn("phone", DataType.text()).
				addColumn("address", DataType.text()).
				addColumn("pois", DataType.set(DataType.uuid()));
		session.execute(hotelSchemaStatement);


		Metadata metadata = cluster.getMetadata();
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
