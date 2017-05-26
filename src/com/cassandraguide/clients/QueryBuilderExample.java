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
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.BuiltStatement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

public class QueryBuilderExample {
	
	public static void main(String[] args) {
		
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1")
			//.withCredentials("jeff", "i6XJsj!k#9")
			.build();
		
		// create session on the "hotel" keyspace
		Session session = cluster.connect("hotel");
		
		// create a Hotel ID
		String id="AZ123";
		
		// build an INSERT statement
		BuiltStatement hotelInsertBuilt = QueryBuilder.insertInto("hotels").
				value("id", id).
				value("name", "Super Hotel at WestWorld").
				value("phone", "1-888-999-9999");
		
		ResultSet hotelInsertResult = session.execute(hotelInsertBuilt);
		
		System.out.println(hotelInsertResult);
		System.out.println(hotelInsertResult.wasApplied());
		System.out.println(hotelInsertResult.getExecutionInfo());
		System.out.println(hotelInsertResult.getExecutionInfo().getIncomingPayload());
		
		// build a SELECT statement
		BuiltStatement hotelSelectBuilt = QueryBuilder.select().all().
				from("hotels").where(eq("id", id));
		
		ResultSet hotelSelectResult = session.execute(hotelSelectBuilt);
		
		// result metadata
		System.out.println(hotelSelectResult);
		System.out.println(hotelSelectResult.wasApplied());
		System.out.println(hotelSelectResult.getExecutionInfo());
		System.out.println(hotelSelectResult.getExecutionInfo().getIncomingPayload());
		
		// print results
		for (Row row : hotelSelectResult) {
			System.out.format("id: %s, name: %s, phone: %s\n", row.getString("id"), 
				row.getString("name"), row.getString("phone"));
		}
		
		// build a DELETE statement
		BuiltStatement hotelDeleteBuilt = QueryBuilder.delete().all().
				from("hotels").where(eq("id", id));
		
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
