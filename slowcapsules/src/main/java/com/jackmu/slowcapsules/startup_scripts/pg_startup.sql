
TRUNCATE TABLE subscription, writer, entry, series;

INSERT INTO subscription(subscription_id, article_num, series_id, subscriber_email, send_date)
VALUES (1, 1, 1, 'email_1', CURRENT_DATE);
INSERT INTO subscription(subscription_id, article_num, series_id, subscriber_email, send_date)
VALUES (2, 5, 2, 'email_2', CURRENT_DATE);
INSERT INTO subscription(subscription_id, article_num, series_id, subscriber_email, send_date)
VALUES (3, 2, 3, 'email_3', '2022-01-01');

INSERT INTO series(series_id, datetime, num_entries, pen_name, summary, tags, title, cadence, email)
VALUES (1, '2022-01-01', 3, 'jack', 'summary-1', 'poetry', 'title-1', 1, 'email_1');
INSERT INTO series(series_id, datetime, num_entries, pen_name, summary, tags, title, cadence, email)
VALUES (2, '2022-02-01', 5, 'matt', 'summary-2', 'data science', 'title-2', 2, 'email_2');
INSERT INTO series(series_id, datetime, num_entries, pen_name, summary, tags, title, cadence, email)
VALUES (3, '2022-03-01', 2, 'john', 'summary-3', 'sports', 'title-3', 7, 'email_3');

INSERT INTO entry(entry_id, entry_text, series_id, order_num, title, email)
VALUES(1, 'a', 1, 1, 'title 1', 'email_1');
INSERT INTO entry(entry_id, entry_text, series_id, order_num, email)
VALUES(2, 'b', 1, 2, 'title 2', 'email_1');
INSERT INTO entry(entry_id, entry_text, series_id, order_num, email)
VALUES(3, 'c', 1, 3, 'title  3', 'email_1');
INSERT INTO entry(entry_id, entry_text, series_id, order_num, email)
VALUES(4, 'd', 2, 1, 'title 4', 'email_2');
INSERT INTO entry(entry_id, entry_text, series_id, order_num, email)
VALUES(5, 'e', 2, 2, 'title 5', 'email_2');
INSERT INTO entry(entry_id, entry_text, series_id, order_num, email)
VALUES(6, 'f', 2, 3, 'title 6','email_2');
INSERT INTO entry(entry_id, entry_text, series_id, order_num, email)
VALUES(7, 'g', 2, 4, 'title 7','email_2');
INSERT INTO entry(entry_id, entry_text, series_id, order_num, email)
VALUES(8, 'h', 2, 5, 'title 8','email_2');
INSERT INTO entry(entry_id, entry_text, series_id, order_num, email)
VALUES(9, 'i', 3, 1, 'title 9','email_3');
INSERT INTO entry(entry_id, entry_text, series_id, order_num, email)
VALUES(10, 'j', 3, 2, 'title 10','email_3');

INSERT INTO writer(writer_id, email, pen_name)
VALUES(1, 'email_4', 'jack');
INSERT INTO writer(writer_id, email, pen_name)
VALUES(2, 'email_5', 'matt');
INSERT INTO writer(writer_id, email, pen_name)
VALUES(3, 'email_6', 'john');

TRUNCATE TABLE users, users_roles, roles;

INSERT INTO users(id, email, password, username)
VALUES(1, 'jackmu@umich.edu', 'security', 'jackmu');

INSERT INTO  roles(id, name)
VALUES (1, 'ADMIN_ROLE');

INSERT INTO  roles(id, name)
VALUES (2, 'USER_ROLE');

INSERT INTO users_roles(user_id, role_id)
VALUES (1, 1);
