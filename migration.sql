ALTER TABLE quiz_answers ADD INDEX (quiz_id);

ALTER TABLE quiz_groups ADD INDEX (user_id, created_at);

ALTER TABLE quizzes ADD INDEX (quiz_group_id,created_at);
ALTER TABLE quizzes ADD INDEX (user_id,created_at);

ALTER TABLE quiz_options ADD INDEX (quiz_id,sequence);