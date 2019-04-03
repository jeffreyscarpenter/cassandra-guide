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
- Mention the problems with `list` type, and that the `set` is more preferable. And to use
  `frozen` collections where possible.
- Mention JSON support
- Move SASI indexes into separate section instead of Note

## Chapter 5 - Data Modeling
- Add a sample query to materialized views section to demonstrate it is no different
- Remove mention of DevCenter?
- Mention KDM (http://kdm.dataview.org/) & http://www.sestevez.com/sestevez/CassandraDataModeler/ ?

## Chapter 6 - The Cassandra Architecture
- Default `SimpleSnitch` doesn't know anything about DC or Racks, but in first sentences
  book says that Cassandra comes with default configuration of `DC1` & `RAC1`. (and they
  are really `dc1` & `rack1`);
- When talking about Snitches, recommend to use `GossipingPropertyFileSnitch` as much as
  possible, as it will allow to build hybrid solutions;
- Mention TimeWindowCompactionStrategy
- Add better options to get CCM installed?
- Pluggable storage API?

## Chapter 7 - Configuring Cassandra
- CCM was moved into riptano: https://github.com/riptano/ccm
- it would be easier to install it via `pip`: https://pypi.org/project/ccm/
- When talking about Snitches, recommend to use `GossipingPropertyFileSnitch` - 
  right now the `PropertyFileSnitch` is better described, but it may require more
  maintenance.
- Recommend to change from 256 vnodes to lower number
- Recommend to put Java settings into `jvm.options` file instead of `cassandra-env.sh`
- Recommend to put commit log into separate disk if possible
- Remind people a lot of log messages (including flush and compaction) are now in debug.log, separate from system.log

## Chapter 8 - Clients
- Rework the Clients chapter to provide the common overview of statements, connection
  pooling, load balancing, etc., and then proceed with examples in specific languages;
- In source examples - there are changes to the Java driver APIs in latest versions that need to be made everywhere. 
The most common is that creation of SimpleStatement is no longer done via the Session.
- With respect to the discussion of listenable future - is this still a Guava class or in Java now?
- Move information about Java custom Codecs into separate section instead of Note, and
  mention the extra-codecs package
- In new versions of Java driver it's possible to explicitly set keyspace for statements
  (when using C* V5 protocol) & mappers - this should especially simplify re-use of mapping classes;
- Explicitly write that MappingManager's instance should be re-used, like a Session object.
- Mention Spring Data Cassandra, and comment on it's drawbacks?
- Mention GoCQL (https://github.com/gocql/gocql)?
- (not sure, it should be here, or in Integration chapter) - mention support of Cassandra
  in Apache NiFi, Apache Camel, Apache Zeppelin, Kafka Connect, ...
- Move spark-cassandra-connector here?
- Point to client documentation on docs.datastax.com instead of datastax.github.io?
- Would be nice to add links to demo projects in different languages

## Chapter 9 - Reading and Writing Data
- Provide a bit more depth on paging
- Explicitly say that aggregates are only effective when they are using inside single or
  few partitions, like examples for `min` & `max`, or `sum`. Example for `count` is bad
  because it will timeout on the big tables

## Chapter 10 - Monitoring
- none

## Chapter 11 - Maintenance
- Mention Cassandra reaper in repair section, possibly DSE NodeSync as well
- Mention "nodetool verify" as a way to check for bitrot?
- Mention "nodetool garbagecollect"
  (http://cassandra.apache.org/doc/latest/tools/nodetool/garbagecollect.html)
- in "SSTable Utilities" section mention `sstabledump`
- OpsCenter could be used only with DSE - should we remove it from book?
- Check new `nodetool` commands, like, changing the list of seeds in run-time, etc.

## Chapter 12 - Performance Tuning
- Discuss TimeWindowCompactionStrategy
- Remove DevCenter?
- G1GC is default when using Java 8
- off-heap memtable

## Chapter 13 - Security
- new audit functionality? https://issues.apache.org/jira/browse/CASSANDRA-12151

## Chapter 14 - Deploying and Integrating
- Docker and Kubernetes deployments
- DSE 6

## Unfiled - Chapter TBD
- Embedded Cassandra for testing - scassandra, etc.
- Zipkin tracing
- Change data capture CDC 
http://cassandra.apache.org/doc/latest/operating/cdc.html
- Cassandra Dataset Manager (CDM)
https://github.com/riptano/cdm-java
http://cdm.readthedocs.io/en/latest/index.html
