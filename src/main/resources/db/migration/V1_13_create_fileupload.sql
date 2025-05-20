CREATE TABLE file_upload (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    original_file_name VARCHAR(255),
    file_name VARCHAR(255),
    file_url VARCHAR(255),
    upload_date TIMESTAMP
);
