DROP TABLE IF EXISTS Subscribers;
DROP TABLE IF EXISTS Subscriptions;

CREATE TABLE Subscribers(
    subscriber_id INT PRIMARY KEY NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE Subscriptions(
    subscription_id INT PRIMARY KEY NOT NULL,
    subscriber_id INT NOT NULL,
    series_id INT NOT NULL,
    article_num INT NOT NULL,
    CONSTRAINT fk_subscriber
      FOREIGN KEY(subscriber_id) 
	  REFERENCES Subscribers(subscriber_id)
);

