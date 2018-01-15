CREATE TABLE planet
  ( id VARCHAR(255) NOT NULL
  , name VARCHAR(100) NOT NULL
  , rotation_period VARCHAR(40) NOT NULL
  , orbital_period VARCHAR(40) NOT NULL
  , diameter VARCHAR(40) NOT NULL
  , climate VARCHAR(40) NOT NULL
  , gravity VARCHAR(40) NOT NULL
  , terrain VARCHAR(40) NOT NULL
  , surface_water VARCHAR(40) NOT NULL
  , population VARCHAR(40) NOT NULL
  , created DATETIME NOT NULL
  , edited DATETIME NOT NULL
  , CONSTRAINT planet_pk PRIMARY KEY (id)
  );
CREATE INDEX planet_name_index ON planet (name);
