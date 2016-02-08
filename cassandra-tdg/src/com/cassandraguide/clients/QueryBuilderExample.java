package com.cassandraguide.clients;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.BuiltStatement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import com.datastax.driver.core.utils.UUIDs;

import java.util.UUID;

public class QueryBuilderExample {
	
	public static void main(String[] args) {
		
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").
				withCredentials("jeff", "i6XJsj!k#9").
				build();
		
		// create session on the "hotel" keyspace
		Session session = cluster.connect("hotel");
		
		// get a type 4 Random UUID to use as the Hotel ID
		UUID uuid = UUIDs.random();
		
		// create a query builder
		QueryBuilder queryBuilder = new QueryBuilder(cluster);
		
		// build an INSERT statement
		BuiltStatement hotelInsertBuilt = queryBuilder.insertInto("hotels").
				value("id", uuid).
				value("name", "Super Hotel at WestWorld").
				value("phone", "1-888-999-9999");
		
		ResultSet hotelInsertResult = session.execute(hotelInsertBuilt);
		
		System.out.println(hotelInsertResult);
		System.out.println(hotelInsertResult.wasApplied());
		System.out.println(hotelInsertResult.getExecutionInfo());
		System.out.println(hotelInsertResult.getExecutionInfo().getIncomingPayload());
		
		// build a SELECT statement
		BuiltStatement hotelSelectBuilt = queryBuilder.select().all().
				from("hotels").where(eq("id", uuid));
		
		ResultSet hotelSelectResult = session.execute(hotelSelectBuilt);
		
		// result metadata
		System.out.println(hotelSelectResult);
		System.out.println(hotelSelectResult.wasApplied());
		System.out.println(hotelSelectResult.getExecutionInfo());
		System.out.println(hotelSelectResult.getExecutionInfo().getIncomingPayload());
		
		// print results
		for (Row row : hotelSelectResult) {
			System.out.format("id: %s, name: %s, phone: %s\n", row.getUUID("id"), row.getString("name"), row.getString("phone"));
		}
		
		// build a DELETE statement
		BuiltStatement hotelDeleteBuilt = queryBuilder.delete().all().
				from("hotels").where(eq("id", uuid));
		
		ResultSet hotelDeleteResult = session.execute(hotelDeleteBuilt);
		
		// result metadata
		System.out.println(hotelSelectResult);
		System.out.println(hotelSelectResult.wasApplied());
		System.out.println(hotelSelectResult.getExecutionInfo());
		System.out.println(hotelSelectResult.getExecutionInfo().getIncomingPayload());		
		
		// close and exit
		cluster.close();
		System.exit(0);
	}
		
		
}
