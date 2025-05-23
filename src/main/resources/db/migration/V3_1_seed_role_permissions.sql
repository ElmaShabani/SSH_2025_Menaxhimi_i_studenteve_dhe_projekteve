-- Rolet
INSERT INTO role (name) VALUES ('ADMIN'), ('STUDENT'), ('PROFESSOR');

-- Lejet
INSERT INTO permission (name, verb, resource) VALUES
  ('Create Student', 'POST', '/students'),
  ('Create Professor', 'POST', '/professors'),
  ('Create User', 'POST', '/users');

-- Lidh ADMIN me te gjitha lejet
INSERT INTO role_permission (role_id, permission_id)
SELECT r.id, p.id FROM role r, permission p WHERE r.name = 'ADMIN';
