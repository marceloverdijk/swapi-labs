CREATE TABLE planet
  ( id INT NOT NULL
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

CREATE TABLE people
  ( id INT NOT NULL
  , name VARCHAR(100) NOT NULL
  , height VARCHAR(10) DEFAULT NULL
  , mass VARCHAR(10) DEFAULT NULL
  , hair_color VARCHAR(20) DEFAULT NULL
  , skin_color VARCHAR(20) DEFAULT NULL
  , eye_color VARCHAR(20) DEFAULT NULL
  , birth_year VARCHAR(10) DEFAULT NULL
  , gender VARCHAR(40) DEFAULT NULL
  , homeworld INT NOT NULL
  , created DATETIME NOT NULL
  , edited DATETIME NOT NULL
  , CONSTRAINT people_pk PRIMARY KEY (id)
  , CONSTRAINT people_homeworld_fk FOREIGN KEY (homeworld) REFERENCES planet (id)
  );
CREATE INDEX people_name_index ON people (name);
