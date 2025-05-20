CREATE TABLE request (
    id VARCHAR(36) PRIMARY KEY,
    student_id VARCHAR(36) NOT NULL,
    type VARCHAR(50) NOT NULL,
    message TEXT NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    response TEXT,

    CONSTRAINT fk_request_student FOREIGN KEY (student_id)
        REFERENCES student(id) ON DELETE CASCADE
);
