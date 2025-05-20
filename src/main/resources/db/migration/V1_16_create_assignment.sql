CREATE TABLE assignment (
    id VARCHAR(36) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    due_date DATE NOT NULL,
    subject_id VARCHAR(36),
    CONSTRAINT fk_subject FOREIGN KEY (subject_id) REFERENCES subject(id)
);
