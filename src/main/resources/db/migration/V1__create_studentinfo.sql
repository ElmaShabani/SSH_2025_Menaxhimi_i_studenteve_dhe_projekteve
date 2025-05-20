CREATE TABLE studentinfo (
    id CHAR(36) NOT NULL PRIMARY KEY,
    fullname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    department VARCHAR(255),
    studyYear INT,
    degree VARCHAR(255),
    urlPhoto VARCHAR(255)
);