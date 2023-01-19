create TABLE locations (
  id BIGINT AUTO_INCREMENT NOT NULL,
   location_name VARCHAR(255) NULL,
   latitude DOUBLE NOT NULL,
   longitude DOUBLE NOT NULL,
   CONSTRAINT pk_locations PRIMARY KEY (id)
);
insert into locations (latitude, longitude, location_name) values (47.497912, 19.040235, 'Budapest');
insert into locations (latitude, longitude, location_name) values (41.90383, 12.50557, 'Róma');
insert into locations (latitude, longitude, location_name) values (37.97954, 23.72638, 'Athén');