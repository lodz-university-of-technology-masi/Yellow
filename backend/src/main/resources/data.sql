INSERT INTO user_data (user_pass, user_role, user_name) VALUES
    ('password', 'USER', 'testUser1'),
    ('password', 'USER', 'testUser2'),
    ('password', 'USER', 'testUser3'),
    ('password', 'REDACTOR', 'testUser4'),
    ('password', 'REDACTOR', 'testUser5'),
    ('password', 'REDACTOR', 'testUser6'),
    ('password', 'REDACTOR', 'testUser7'),
    ('password', 'MODERATOR', 'testUser8'),
    ('password', 'MODERATOR', 'testUser9');


INSERT INTO test_data (test_name, owner_id) VALUES
    ('Simple test #1', 4),
    ('Simple test #2', 4),
    ('Simple test #3', 5),
    ('Simple test #4', 6),
    ('Simple test #5', 7),
    ('Simple test #6', 7);

INSERT INTO question_data (question_desc, question_language,
    question_meta, question_number, question_type, test_id) VALUES
    ('Pytanie 1:', 'PL', '|', 1, 'OPEN', 1),
    ('Pytanie 2:', 'PL', '|', 2, 'OPEN', 1),
    ('Pytanie 1:', 'PL', '|', 1, 'OPEN', 2),
    ('Pytanie 2:', 'PL', '|', 2, 'OPEN', 2),
    ('Pytanie 3:', 'PL', '|', 3, 'OPEN', 2),
    ('Pytanie 4:', 'PL', '|', 4, 'OPEN', 2),
    ('Pytanie 1:', 'PL', '|', 1, 'OPEN', 3),
    ('Pytanie 2:', 'PL', '|', 2, 'OPEN', 3),
    ('Pytanie 3:', 'PL', '|', 3, 'OPEN', 3),
    ('Pytanie 4:', 'PL', '|', 4, 'OPEN', 3),
    ('Question 1:', 'EN', '|', 1, 'OPEN', 4),
    ('Question 2:', 'EN', '|', 2, 'OPEN', 4),
    ('Question 3:', 'EN', '|', 3, 'OPEN', 4),
    ('Question 4:', 'EN', '|', 4, 'OPEN', 4);
