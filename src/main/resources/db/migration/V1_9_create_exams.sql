
CREATE TABLE exams (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    date DATE,
    max_points INT,
    subject_id BIGINT,
    semester_id BIGINT,
    CONSTRAINT fk_exam_subject FOREIGN KEY (subject_id) REFERENCES subject(id),
    CONSTRAINT fk_exam_semester FOREIGN KEY (semester_id) REFERENCES semester(id)
);

CREATE TABLE exam_students (
    exam_id BIGINT NOT NULL,
    student_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (exam_id, student_id),
    CONSTRAINT fk_exam_students_exam FOREIGN KEY (exam_id) REFERENCES exams(id) ON DELETE CASCADE,
    CONSTRAINT fk_exam_students_student FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE
);
