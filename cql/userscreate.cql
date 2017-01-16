CREATE KEYSPACE poc
WITH durable_writes = true
AND replication = {
	'class' : 'SimpleStrategy',
	'replication_factor' : 1
};

CREATE TABLE poc.users (
	id uuid,
	email text,
	name text,
	registrationdate timestamp,
	PRIMARY KEY (id)
);