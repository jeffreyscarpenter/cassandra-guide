package com.cassandraguide.readwrite;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.QueryTrace;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.utils.UUIDs;

import java.text.SimpleDateFormat;
import java.util.UUID;

public class BatchStatementExample {
	
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
		SimpleStatement hotelsByPoiInsert = session.newSimpleStatement(
				"INSERT INTO hotels_by_poi (poi_name, id, name, phone) VALUES (?, ?, ?, ?)",
				"WestWorld", uuid, "Super Hotel at WestWorld", "1-888-999-9999");
		
		BatchStatement hotelBatch = new BatchStatement();
		hotelBatch.add(hotelsByPoiInsert);
		hotelBatch.add(hotelInsert);
		
		ResultSet hotelInsertResult = session.execute(hotelBatch);
		
		System.out.println(hotelInsertResult);
		System.out.println(hotelInsertResult.wasApplied());
		System.out.println(hotelInsertResult.getExecutionInfo());
		System.out.println(hotelInsertResult.getExecutionInfo().getIncomingPayload());
		
		// create parameterized SELECT statement
		SimpleStatement hotelSelect = session.newSimpleStatement(
				"SELECT * FROM hotels WHERE id=?", uuid);
		hotelSelect.enableTracing();
		
		
		ResultSet hotelSelectResult = session.execute(hotelSelect);
		
		// result metadata
		System.out.println(hotelSelectResult);
		System.out.println(hotelSelectResult.wasApplied());
		System.out.println(hotelSelectResult.getExecutionInfo());
		System.out.println(hotelSelectResult.getExecutionInfo().getIncomingPayload());
		System.out.println(hotelSelectResult.getExecutionInfo().getQueryTrace());
		
		// print results
		for (Row row : hotelSelectResult) {
			System.out.format("id: %s, name: %s, phone: %s\n\n", row.getUUID("id"), row.getString("name"), row.getString("phone"));
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
		QueryTrace queryTrace = hotelSelectResult.getExecutionInfo().getQueryTrace();
		System.out.printf("Trace id: %s\n\n", queryTrace.getTraceId());
		System.out.printf("%-42s | %-12s | %-10s \n", "activity",
		   "timestamp", "source");
		System.out.println("-------------------------------------------+--------------+------------");
		      
		for (QueryTrace.Event event : queryTrace.getEvents()) {
		  System.out.printf("%42s | %12s | %10s\n",     
		     event.getDescription(),
		     dateFormat.format((event.getTimestamp())),
		     event.getSource());
		}

		
		// close and exit
		cluster.close();
		System.exit(0);
	}
		
		
}
