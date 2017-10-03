
DROP TABLE IF EXISTS contact;

CREATE TABLE contact (
    contact_id INT NOT NULL auto_increment,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    adress VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255),

    PRIMARY KEY (contact_id)
) ENGINE=INNODB;