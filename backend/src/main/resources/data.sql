INSERT INTO user_data (id, user_pass, user_role, user_name) VALUES
    (1, 'password', 'USER', 'testUser1'),
    (2, 'password', 'USER', 'testUser2'),
    (3, 'password', 'USER', 'testUser3'),
    (4, 'password', 'REDACTOR', 'testUser4'),
    (5, 'password', 'REDACTOR', 'testUser5'),
    (6, 'password', 'REDACTOR', 'testUser6'),
    (7, 'password', 'REDACTOR', 'testUser7'),
    (8, 'password', 'MODERATOR', 'testUser8'),
    (9, 'password', 'MODERATOR', 'testUser9');


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
