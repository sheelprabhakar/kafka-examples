-- Table: weather.log

-- DROP TABLE weather.log;

CREATE TABLE weather.log
(
    city character varying(255) COLLATE pg_catalog."default" NOT NULL,
    latitude double precision NOT NULL,
    longitude double precision NOT NULL,
    temp double precision NOT NULL,
    logdate date NOT NULL,
    id integer NOT NULL
) PARTITION BY RANGE (logdate);

ALTER TABLE weather.log
    OWNER to postgres;

-- Partitions SQL

CREATE TABLE weather.log_2021_02 PARTITION OF weather.log
    FOR VALUES FROM ('2021-02-01') TO ('2021-03-01');

CREATE TABLE weather.log_2021_03 PARTITION OF weather.log
    FOR VALUES FROM ('2021-03-01') TO ('2021-04-01');

CREATE TABLE weather.log_2021_04 PARTITION OF weather.log
    FOR VALUES FROM ('2021-04-01') TO ('2021-05-01');