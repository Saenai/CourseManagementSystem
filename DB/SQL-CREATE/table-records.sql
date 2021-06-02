CREATE TABLE records(
    id VARCHAR(255) NOT NULL,
    w1 INT,
    w2 INT,
    w3 INT,
    w4 INT,
    w5 INT,
    w6 INT,
    w7 INT,
    w8 INT,
    w9 INT,
    w10 INT,
    score INT,
    final_score INT,
    PRIMARY KEY(id),
    FOREIGN KEY (id) REFERENCES student(id)
) -- INSERT INTO records VALUES('xs',1,1,1,1,1,1,1,1,1,1,85,85)