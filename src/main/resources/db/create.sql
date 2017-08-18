SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS hackathons (
    id int PRIMARY KEY auto_increment,
    focus VARCHAR,
    location VARCHAR
);

CREATE TABLE IF NOT EXISTS teams (
    teamId int PRIMARY KEY auto_increment,
    hackId INTEGER,
    name VARCHAR,
    blurb VARCHAR,
);

CREATE TABLE IF NOT EXISTS members (
    memberId int PRIMARY KEY auto_increment,
    teamId INTEGER,
    hackId INTEGER,
    name VARCHAR,
    location VARCHAR,
    age INTEGER
);
