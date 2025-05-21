CREATE TABLE attendanceinfo (
  id VARCHAR(255) PRIMARY KEY,
  student_name VARCHAR(255),
  subject VARCHAR(255),
  date_time DATETIME,
  present BOOLEAN,
  notes TEXT
);
