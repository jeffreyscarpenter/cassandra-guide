CREATE KEYSPACE hotel 
	WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 3};

CREATE TABLE hotel.hotels_by_poi (
	poi_name text,
	id uuid,
	name text,
	phone text,
	address text,
	PRIMARY KEY ((poi_name), id)
) WITH CLUSTERING ORDER BY (id ASC);


CREATE TABLE hotel.hotels ( 
	id uuid PRIMARY KEY,
	name text,
	phone text,
	address text,
	pois set<uuid>
);

CREATE TABLE hotel.pois_by_hotel ( 
	hotel_id uuid,
	id uuid,
	name text,
	description text,
	PRIMARY KEY ((hotel_id), id)
);