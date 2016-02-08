package com.cassandraguide.readwrite;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.QueryTrace;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.utils.UUIDs;

import java.text.SimpleDateFormat;
import java.util.UUID;

public class DeleteExample {
	
	public static void main(String[] args) {
		
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").
				withCredentials("jeff", "i6XJsj!k#9").
				build();
		
		// create session on the "hotel" keyspace
		Session session = cluster.connect("hotel");
		
		// get a type 4 Random UUID to use as the Hotel ID
		UUID uuid = UUIDs.random();
		
		// create parameterized INSERT statement
		SimpleStatement hotelInsert = session.newSimpleStatement(
				"INSERT INTO hotels (id, name, phone) VALUES (?, ?, ?)",
				uuid, "Super Hotel at WestWorld", "1-888-999-9999");
		
		session.execute(hotelInsert);
		
		// create parameterized DELETE statement
		SimpleStatement hotelDelete = session.newSimpleStatement(
				"DELETE FROM hotels WHERE id=?", uuid);
		
		ResultSet hotelDeleteResult = session.execute(hotelDelete);
		
		// result metadata
		System.out.println(hotelDeleteResult);
		System.out.println(hotelDeleteResult.wasApplied());
		System.out.println(hotelDeleteResult.getExecutionInfo());
        System.out.println("num results: " + hotelDeleteResult.all().size());
		
		// print results
		for (Row row : hotelDeleteResult) {
			System.out.format("id: %s, name: %s, phone: %s\n\n", row.getUUID("id"), row.getString("name"), row.getString("phone"));
		}
		
		// close and exit
		cluster.close();
		System.exit(0);
	}
		
		
}
