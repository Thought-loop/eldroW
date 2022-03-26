BEGIN TRANSACTION;
 DROP TABLE IF EXISTS solutions_5;
 DROP SEQUENCE IF EXISTS seq_word_id_solutions_5;
 DROP USER eldrow_appuser;

CREATE SEQUENCE seq_word_id_solutions_5
  INCREMENT BY 1
  START WITH 10000
  NO MAXVALUE;

CREATE TABLE solutions_5(
	word_id int NOT NULL DEFAULT nextval('seq_word_id_solutions_5'),
	word varchar(10),
	solution boolean,
	enabled boolean DEFAULT true,
	CONSTRAINT PK_solutions_5 PRIMARY KEY (word_id)
);

CREATE USER eldrow_appuser
WITH PASSWORD 'snakepit';

GRANT SELECT
ON ALL TABLES IN SCHEMA public
TO eldrow_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO eldrow_appuser;

COMMIT;



