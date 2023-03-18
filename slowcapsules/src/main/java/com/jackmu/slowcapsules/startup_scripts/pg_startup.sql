
TRUNCATE TABLE subscription, writer, entry, series;

INSERT INTO subscription(subscription_id, article_num, series_id, subscriber_email, send_date)
VALUES (1, 1, 1, 'email_1', CURRENT_DATE);
INSERT INTO subscription(subscription_id, article_num, series_id, subscriber_email, send_date)
VALUES (2, 5, 2, 'email_2', CURRENT_DATE);
INSERT INTO subscription(subscription_id, article_num, series_id, subscriber_email, send_date)
VALUES (3, 2, 3, 'email_3', '2022-01-01');

INSERT INTO series(series_id, datetime, num_entries, pen_name, summary, tags, title, cadence)
VALUES (1, '2022-01-01', 3, 'jack', 'summary-1', 'poetry', 'title-1', 1);
INSERT INTO series(series_id, datetime, num_entries, pen_name, summary, tags, title, cadence)
VALUES (2, '2022-02-01', 5, 'matt', 'summary-2', 'data science', 'title-2', 2);
INSERT INTO series(series_id, datetime, num_entries, pen_name, summary, tags, title, cadence)
VALUES (3, '2022-03-01', 2, 'john', 'summary-3', 'sports', 'title-3', 7);

INSERT INTO entry(entry_id, entry_text, series_id, order_num)
VALUES(1, 'a', 1, 1);
INSERT INTO entry(entry_id, entry_text, series_id, order_num)
VALUES(2, 'b', 1, 2);
INSERT INTO entry(entry_id, entry_text, series_id, order_num)
VALUES(3, 'c', 1, 3);
INSERT INTO entry(entry_id, entry_text, series_id, order_num)
VALUES(4, 'd', 2, 1);
INSERT INTO entry(entry_id, entry_text, series_id, order_num)
VALUES(5, 'e', 2, 2);
INSERT INTO entry(entry_id, entry_text, series_id, order_num)
VALUES(6, 'f', 2, 3);
INSERT INTO entry(entry_id, entry_text, series_id, order_num)
VALUES(7, 'g', 2, 4);
INSERT INTO entry(entry_id, entry_text, series_id, order_num)
VALUES(8, 'h', 2, 5);
INSERT INTO entry(entry_id, entry_text, series_id, order_num)
VALUES(9, 'i', 3, 1);
INSERT INTO entry(entry_id, entry_text, series_id, order_num)
VALUES(10, 'j', 3, 2);

INSERT INTO writer(writer_id, email, pen_name)
VALUES(1, 'email_4', 'jack');
INSERT INTO writer(writer_id, email, pen_name)
VALUES(2, 'email_5', 'matt');
INSERT INTO writer(writer_id, email, pen_name)
VALUES(3, 'email_6', 'john')
