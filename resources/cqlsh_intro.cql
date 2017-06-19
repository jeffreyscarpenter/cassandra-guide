#
# cqlsh_intro.cql
#
# Copyright (C) 2017 Jeff Carpenter
# Execute the commands in this file for a short guided tour of the CQL Shell (cqlsh)
#
# For more description, see Cassandra, The Definitive Guide 2nd Ed., Chapter 3: Installing
# http://shop.oreilly.com/product/0636920043041.do
#

# To get help for cqlsh, type HELP or ? to see the list of available commands:
HELP

# To learn about the current cluster you’re working in, type:
DESCRIBE CLUSTER;

# To see which keyspaces are available in the cluster, issue the command below.
# What are these keyspaces for?
DESCRIBE KEYSPACES;

# Learn the client, server, and protocol versions in use
SHOW VERSION;

# View the default paging settings that will be used on reads
PAGING;

# View the default consistency level that will be used on all queries
CONSISTENCY

# View the default tracing options
TRACING

# Create your own keyspace. Try using tab completion as you enter this command
CREATE KEYSPACE my_keyspace WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

# Describe the keyspace you just created.
# What additional information do you notice?
DESCRIBE KEYSPACE my_keyspace;

# Use the keyspace so you don't have to enter it on every data manipulation
# Note how the prompt changes after you do this
USE my_keyspace;

# Create a simple table
# What other syntax could you use to designate a single column primary key?
CREATE TABLE user ( first_name text, last_name text, PRIMARY KEY (first_name));

# Describe the table you just created
# What additional information do you notice?
DESCRIBE TABLE user;

# Write some data
INSERT INTO user (first_name, last_name) VALUES ('Bill', 'Nguyen');

# See how many rows have been written into this table
# Warning - row scans are expensive operations on large tables
SELECT COUNT (*) FROM user;

# Read the data we just wrote
SELECT * FROM user WHERE first_name='Bill';

# Remove a non-primary key column
DELETE last_name FROM USER WHERE first_name='Bill';

# Check to see the value was removed
SELECT * FROM user WHERE first_name='Bill';

# Delete an entire row
DELETE FROM USER WHERE first_name='Bill';

# Check to make sure it was removed
SELECT * FROM user WHERE first_name='Bill';

# Add a column to the table
ALTER TABLE user ADD title text;

# Check to see that the column was added
DESCRIBE TABLE user;

# Write a couple of rows, populate different columns for each, and view the results:
INSERT INTO user (first_name, last_name, title) VALUES ('Bill', 'Nguyen', 'Mr.');
INSERT INTO user (first_name, last_name) VALUES ('Mary', 'Rodriguez');
SELECT * FROM user;

# View the timestamps generated for previous writes
SELECT first_name, last_name, writetime(last_name) FROM user;

# Note that we’re not allowed to ask for the timestamp on primary key columns:
SELECT WRITETIME(first_name) FROM user;

# Set the timestamp on a write
# Note, you will probably want to change this value to be closer to your current time
#   (similar to timestamp from previous set)
UPDATE user USING TIMESTAMP 1434373756626000 SET last_name = 'Boateng' WHERE first_name = 'Mary' ;

# Verify the timestamp used
SELECT first_name, last_name, WRITETIME(last_name) FROM user WHERE first_name = 'Mary';

# View the time to live value for a column
SELECT first_name, last_name, TTL(last_name) FROM user WHERE first_name = 'Mary';

# Set the TTL on the  last name column to one hour
UPDATE user USING TTL 3600 SET last_name = 'McDonald' WHERE first_name = 'Mary' ;

# View the resulting TTL
# Note that it will already be counting down
SELECT first_name, last_name, TTL(last_name) FROM user WHERE first_name = 'Mary';

# Empty the contents of the table
TRUNCATE user;

# Show that the table is empty
SELECT * FROM user;

# Remove the entire table
DROP TABLE user;

# Clear the screen of output from previous commands
CLEAR

# Exit cqlsh
EXIT