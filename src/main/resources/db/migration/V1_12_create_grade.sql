CREATE TABLE grades (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    value DOUBLE,
    student_id VARCHAR(255),
    exam_id BIGINT,
    CONSTRAINT fk_grade_student FOREIGN KEY (student_id) REFERENCES students(id),
    CONSTRAINT fk_grade_exam FOREIGN KEY (exam_id) REFERENCES exams(id)
);
