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
package com.cassandraguide.integration;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import com.datastax.spark.connector.japi.CassandraJavaUtil;
import com.datastax.spark.connector.japi.rdd.CassandraTableScanJavaRDD;

public class SparkConnectorExample {
	
	public static void main(String[] args) {
		
		SparkConf sparkConf = new SparkConf()
			.setAppName("Spark Connector Example")
			.set("spark.cassandra.connection.host", "localhost")
			.setMaster("local")
			// optionally
			.set("cassandra.username", "cassandra")     
			.set("cassandra.password", "cassandra");

		JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
		
	    CassandraTableScanJavaRDD<String> usersRdd =
	        CassandraJavaUtil.javaFunctions(sparkContext)
	            .cassandraTable("my_keyspace", "user", 
	            		CassandraJavaUtil.mapColumnTo(String.class))
	           .select("last_name");
	    //usersRdd.collect();
	    
	    System.out.println("Number of users: " + usersRdd.count());
	}

}
