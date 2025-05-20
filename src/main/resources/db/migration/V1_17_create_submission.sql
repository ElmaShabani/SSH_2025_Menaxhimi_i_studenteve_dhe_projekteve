CREATE TABLE submission (
    id VARCHAR(255) PRIMARY KEY,
    student_id VARCHAR(255),
    assignment_id VARCHAR(255),
    file_url TEXT,
    submitted_at TIMESTAMP,
    comment TEXT,
    status VARCHAR(20),

    CONSTRAINT fk_submission_student
        FOREIGN KEY (student_id) REFERENCES studentinfo(id),

    CONSTRAINT fk_submission_assignment
        FOREIGN KEY (assignment_id) REFERENCES assignment(id)
);
