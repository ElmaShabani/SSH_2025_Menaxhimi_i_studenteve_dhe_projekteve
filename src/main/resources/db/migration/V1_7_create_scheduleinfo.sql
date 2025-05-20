CREATE TABLE scheduleinfo (
    id VARCHAR(50) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    supervisor VARCHAR(255),
    duration INT,
    status VARCHAR(100),
    urlDocument VARCHAR(500),
    startTime VARCHAR(20),
    endTime VARCHAR(20)
);
