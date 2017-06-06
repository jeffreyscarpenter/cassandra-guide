# Suggested Improvements
This is a list of changes under considerations for future revisions of the O'Reilly book [Cassandra: The Definitive Guide, 2nd Edition](http://shop.oreilly.com/product/0636920043041.do).

![Book Cover](cassandra-tdg.jpg)


## Chapter 1 - Beyond Relational Databases
- Add discussion of multi-model databases and the relational counter-reformation

## Chapter 2 - Introducing Cassandra
- The tick-tock release plan is now considered dead, or at least it will be as of 4.0

## Chapter 3 - Installing Cassandra
- DataStax has deprecated the Community Edition
- There is now a docs directory in the Cassandra installation, also mention improved docs on Apache site vs. old wiki?

## Chapter 4 - The Cassandra Query Language
- Add discussion of the tuple data type

## Chapter 5 - Data Modeling
- Add a sample query to materialized views section to demonstrate it is no different

## Chapter 6 - The Cassandra Architecture
- Mention TimeWindowCompactionStrategy
- Add better options to get CCM installed?

## Chapter 7 - Configuring Cassandra
- none

## Chapter 8 - Clients
- In source examples - there are changes to the Java driver APIs in latest versions that need to be made everywhere. 
The most common is that creation of SimpleStatement is no longer done via the Session.
- With respect to the discussion of listenable future - is this still a Guava class or in Java now?

## Chapter 9 - Reading and Writing Data
- Provide a bit more depth on paging

## Chapter 10 - Monitoring
- none

## Chapter 11 - Maintenance
- Mention Cassandra reaper in repair section
- Mention "nodetool verify" as a way to check for bitrot?


## Chapter 12 - Performance Tuning
- Discuss TimeWindowCompactionStrategy

## Chapter 13 - Security
- none

## Chapter 14 - Deploying and Integrating
- none

## Unfiled - Chapter TBD
- Embedded Cassandra for testing - scassandra, etc.
- Zipkin tracing
- Change data capture CDC 
http://cassandra.apache.org/doc/latest/operating/cdc.html
- Cassandra Dataset Manager (CDM)
https://github.com/riptano/cdm-java
http://cdm.readthedocs.io/en/latest/index.html