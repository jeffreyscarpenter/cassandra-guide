package com.cassandraguide.clients;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.utils.UUIDs;
import java.util.UUID;

public class SimpleStatementExample {
	
	public static void main(String[] args) {
		
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").
				//withCredentials("jeff", "i6XJsj!k#9").
				build();
		
		// create session on the "hotel" keyspace"
		Session session = cluster.connect("hotel");
		
		// get a type 4 Random UUID to use as the Hotel ID
		UUID uuid = UUIDs.random();
		
		// create parameterized INSERT statement
		SimpleStatement hotelInsert = session.newSimpleStatement(
				"INSERT INTO hotels (id, name, phone) VALUES (?, ?, ?)",
				uuid, "Super Hotel at WestWorld", "1-888-999-9999");
		
		ResultSet hotelInsertResult = session.execute(hotelInsert);
		
		System.out.println(hotelInsertResult);
		System.out.println(hotelInsertResult.wasApplied());
		System.out.println(hotelInsertResult.getExecutionInfo());
		System.out.println(hotelInsertResult.getExecutionInfo().getIncomingPayload());
		
		// create parameterized SELECT statement
		SimpleStatement hotelSelect = session.newSimpleStatement(
				"SELECT * FROM hotels WHERE id=?", uuid);
		
		ResultSet hotelSelectResult = session.execute(hotelSelect);
		
		System.out.println(hotelSelectResult);
		System.out.println(hotelSelectResult.wasApplied());
		System.out.println(hotelSelectResult.getExecutionInfo());
		System.out.println(hotelSelectResult.getExecutionInfo().getIncomingPayload());
		
		// close and exit
		cluster.close();
		System.exit(0);
	}
		
		
}
