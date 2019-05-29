INSERT INTO user_data (id, user_pass, user_role, user_name) VALUES
    (1, 'password', 'testUser1', 'USER'),
    (2, 'password', 'testUser2', 'USER'),
    (3, 'password', 'testUser3', 'USER'),
    (4, 'password', 'testUser4', 'REDACTOR'),
    (5, 'password', 'testUser5', 'REDACTOR'),
    (6, 'password', 'testUser6', 'REDACTOR'),
    (7, 'password', 'testUser7', 'REDACTOR'),
    (8, 'password', 'testUser8', 'MODERATOR'),
    (9, 'password', 'testUser9', 'MODERATOR');


INSERT INTO test_data (id, test_name, owner_id) VALUES
    (1, 'Simple test #1', 4),
    (2, 'Simple test #2', 4),
    (3, 'Simple test #3', 5),
    (4, 'Simple test #4', 6),
    (5, 'Simple test #5', 7),
    (6, 'Simple test #6', 7);

INSERT INTO question_data (id, question_desc, question_language,
    question_meta, question_number, question_type, test_id) VALUES
    (1, 'Pytanie 1:', 'PL', '|', 1, 'OPEN', 1),
    (2, 'Pytanie 2:', 'PL', '|', 2, 'OPEN', 2),
    (3, 'Pytanie 1:', 'PL', '|', 1, 'OPEN', 1),
    (4, 'Pytanie 2:', 'PL', '|', 2, 'OPEN', 2),
    (5, 'Pytanie 3:', 'PL', '|', 3, 'OPEN', 3),
    (6, 'Pytanie 4:', 'PL', '|', 4, 'OPEN', 4),
    (7, 'Pytanie 1:', 'PL', '|', 1, 'OPEN', 1),
    (8, 'Pytanie 2:', 'PL', '|', 2, 'OPEN', 2),
    (9, 'Pytanie 3:', 'PL', '|', 3, 'OPEN', 3),
    (10, 'Pytanie 4:', 'PL', '|', 4, 'OPEN', 4),
    (11, 'Question 1:', 'EN', '|', 1, 'OPEN', 1),
    (12, 'Question 2:', 'EN', '|', 2, 'OPEN', 2),
    (13, 'Question 3:', 'EN', '|', 3, 'OPEN', 3),
    (14, 'Question 4:', 'EN', '|', 4, 'OPEN', 4);

