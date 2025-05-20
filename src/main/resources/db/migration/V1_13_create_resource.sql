CREATE TABLE resource (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    description TEXT,
    upload_date TIMESTAMP,

    subject_id BIGINT,
    professor_id BINARY(16),
    semester_id BIGINT,
    file_upload_id BIGINT,

    CONSTRAINT fk_resource_subject FOREIGN KEY (subject_id) REFERENCES subject(id),
    CONSTRAINT fk_resource_professor FOREIGN KEY (professor_id) REFERENCES professor(id),
    CONSTRAINT fk_resource_semester FOREIGN KEY (semester_id) REFERENCES semester(id),
    CONSTRAINT fk_resource_file_upload FOREIGN KEY (file_upload_id) REFERENCES file_upload(id)
);