DROP TABLE IF EXISTS spittle;
DROP TABLE IF EXISTS spitter;

CREATE TABLE spitter (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(25) NOT NULL,
  PASSWORD VARCHAR(25) NOT NULL,
  fullname VARCHAR(100) NOT NULL,
  email VARCHAR(50) NOT NULL,
  update_by_email BOOLEAN NOT NULL
);

CREATE TABLE spittle (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  spitter_id INTEGER NOT NULL,
  spittleText VARCHAR(2000) NOT NULL,
  postedTime DATE NOT NULL,
  FOREIGN KEY (spitter_id) REFERENCES spitter(id)
);