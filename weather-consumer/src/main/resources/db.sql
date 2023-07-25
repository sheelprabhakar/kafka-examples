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

CREATE TABLE weather.log_2023_07 PARTITION OF weather.log
    FOR VALUES FROM ('2023-07-01') TO ('2023-08-01');

CREATE TABLE weather.log_2023_08 PARTITION OF weather.log
    FOR VALUES FROM ('2023-08-01') TO ('2023-09-01');

CREATE TABLE weather.log_2023_09 PARTITION OF weather.log
    FOR VALUES FROM ('2023-09-01') TO ('2023-10-01');