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

package com.cassandraguide.readwrite;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;

public class DeleteExample {
	
	public static void main(String[] args) {
		
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1")
				//.withCredentials("jeff", "i6XJsj!k#9")
				.build();
		
		// create session on the "hotel" keyspace
		Session session = cluster.connect("hotel");
		
		// create a Hotel ID
		String id="AZ123";
		
		// create parameterized INSERT statement
		SimpleStatement hotelInsert = new SimpleStatement(
				"INSERT INTO hotels (id, name, phone) VALUES (?, ?, ?)",
				id, "Super Hotel at WestWorld", "1-888-999-9999");
		
		session.execute(hotelInsert);
		
		// create parameterized DELETE statement
		SimpleStatement hotelDelete = new SimpleStatement(
				"DELETE FROM hotels WHERE id=?", id);
		
		ResultSet hotelDeleteResult = session.execute(hotelDelete);
		
		// result metadata
		System.out.println(hotelDeleteResult);
		System.out.println(hotelDeleteResult.wasApplied());
		System.out.println(hotelDeleteResult.getExecutionInfo());
        System.out.println("num results: " + hotelDeleteResult.all().size());
		
		// print results
		for (Row row : hotelDeleteResult) {
			System.out.format("id: %s, name: %s, phone: %s\n\n", row.getString("id"), 
				row.getString("name"), row.getString("phone"));
		}
		
		// close and exit
		cluster.close();
		System.exit(0);
	}
		
		
}
