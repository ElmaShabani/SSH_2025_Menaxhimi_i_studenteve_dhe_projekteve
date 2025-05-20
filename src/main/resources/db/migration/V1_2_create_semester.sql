CREATE TABLE semester (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    academic_year VARCHAR(20),
    semester_number INT,
    year_of_study INT,
    start_date DATE,
    end_date DATE
);
CREATE TABLE student_semester (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id VARCHAR(50),
    semester_id BIGINT,
    status VARCHAR(20), -- vlerat: 'PERFUNDUAR', 'NE_VAZHDIM', 'I_PLANIFIKUAR'

    FOREIGN KEY (student_id) REFERENCES studentinfo(id),
    FOREIGN KEY (semester_id) REFERENCES semester(id)
);
