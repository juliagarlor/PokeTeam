DROP SCHEMA IF EXISTS pokemon;
CREATE SCHEMA pokemon;
USE pokemon;

CREATE TABLE trainer(
id BIGINT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(255),
age INT,
hobby VARCHAR(255),
photo VARCHAR(255),
PRIMARY KEY (id)
);

CREATE TABLE team(
id BIGINT NOT NULL AUTO_INCREMENT,
trainer_id BIGINT,
PRIMARY KEY (id),
FOREIGN KEY (trainer_id) REFERENCES trainer(id)
);

CREATE TABLE team_mates(
team_id BIGINT,
team_mates BIGINT,
FOREIGN KEY (team_id) REFERENCES team(id)
);