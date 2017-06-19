#
# learning_cql_query_clauses.cql
#
# Copyright (C) 2017 Jeff Carpenter
# Execute the commands in this file for a short guided tour of the filtering options supported on CQL queries
#
# For more description, see Cassandra, The Definitive Guide 2nd Ed., Chapter 9: Reading and Writing
# http://shop.oreilly.com/product/0636920043041.do
#

# Load the hotels schema used in these examples
# This command assumes you've cloned this repository in your home directory
# Or replace with the path to the actual file
SOURCE '~/cassandra-guide/resources/hotel.cql';
USE hotel;

# Load test data for the table containing available room data
COPY available_rooms_by_hotel_date FROM '~/cassandra-guide/resources/available_rooms.csv' WITH HEADER=true;

#
# WHERE clause examples
#

# Search for hotel rooms for a specific hotel and date range:
SELECT * FROM available_rooms_by_hotel_date WHERE hotel_id='AZ123' and date>'2016-01-05' and date<'2016-01-12';

# Why doesn't this query work?
SELECT * FROM available_rooms_by_hotel_date WHERE hotel_id='AZ123' and room_number=101;

# Look at the table again
DESCRIBE TABLE available_rooms_by_hotel_date;

# We can force it to work, but why is this not a good practice?
SELECT * FROM available_rooms_by_hotel_date WHERE date='2016-01-25' ALLOW FILTERING;

#
# IN clause example
#

# Use the IN clause to test equality with multiple possible values for a column
# Find inventory on two dates a week apart
SELECT * FROM available_rooms_by_hotel_date WHERE hotel_id='AZ123' AND date IN ('2016-01-05', '2016-01-12');

#
# SORT clause example
#

# Override the default sort order on the table
SELECT * FROM available_rooms_by_hotel_date
  WHERE hotel_id='AZ123' AND date>'2016-01-05' AND date<'2016-01-12'
  ORDER BY date DESC;

# Bonus: Assuming you're running a multi-node cluster, try running some the queries above with tracing on
# to see the interactions between nodes
TRACING ON;