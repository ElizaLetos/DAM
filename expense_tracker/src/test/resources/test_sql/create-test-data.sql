--
-- Data for table `user`
--

INSERT INTO "USERS" (`name`, `email`, `password`) VALUES
                                                                       ('Leslie', 'leslie@gmail.com', '$2a$12$kAafc0OInCawdTNwJ/R/5.xMP5ZjvGoTob.rE3oavKiLn2t4kviQ.'),
                                                                       ('Emma', 'emma@gmail.com', '$2a$12$kAafc0OInCawdTNwJ/R/5.xMP5ZjvGoTob.rE3oavKiLn2t4kviQ.'),
                                                                       ('Avani', 'avani@gmail.com', '$2a$12$kAafc0OInCawdTNwJ/R/5.xMP5ZjvGoTob.rE3oavKiLn2t4kviQ.'),
                                                                       ('Ana', 'ana@gmail.com', '$2a$12$kAafc0OInCawdTNwJ/R/5.xMP5ZjvGoTob.rE3oavKiLn2t4kviQ.'),
                                                                       ('John', 'john@gmail.com', '$2a$12$kAafc0OInCawdTNwJ/R/5.xMP5ZjvGoTob.rE3oavKiLn2t4kviQ.'),
                                                                       ('Yuri', 'yuri@gmail.com', '$2a$12$kAafc0OInCawdTNwJ/R/5.xMP5ZjvGoTob.rE3oavKiLn2t4kviQ.');

--
-- Data for table `category`
--

INSERT INTO "CATEGORY"
(`name`, `description`)
VALUES
    ('Food', 'Expenses on restaurant, supermarket'),
    ('Transport', 'Taxi, train, bus'),
    ('Gift', 'Anniversary'),
    ('Beauty', 'Glam'),
    ('Social life', 'Going out with friends'),
    ('Clothes', null),
    ('Education', 'Books, courses'),
    ('Health', 'Hospital, farmacy');

--
-- Data for table `transaction`
--

INSERT INTO "TRANSACTION"
(`user_id`, `category_id`, `amount`, `date`, `payment_type`, `note`, `type_of_transaction`)
VALUES
    (1, 2, 200, '2024-10-02', 'CREDIT_CARD', 'good time with friends', 'EXPENSE'),
    (1, 3, 500, '2024-11-09', 'DEBIT_CARD', '1 year anniversary', 'INCOME'),
    (1, 8, 1000, '2024-10-05', 'CREDIT_CARD', null, 'EXPENSE'),
    (3, 1, 30, '2022-10-12', 'CASH', null, 'INCOME'),
    (6, 2, 45.5, '2023-11-02', 'BANK_TRANSFER', 'to work', 'EXPENSE'),
    (6, 6, 20.7, '2023-12-04', 'CREDIT_CARD', 'necessity', 'EXPENSE'),
    (6, 8, 3800, '2023-07-09', 'CRYPTOCURRENCY', null, 'EXPENSE');

--
-- Data for table `user_roles`
--

INSERT INTO "USER_ROLES" (`user_id`, `role`) VALUES
                                                                   (1, 'ROLE_USER'),
                                                                   (2, 'ROLE_USER'),
                                                                   (3, 'ROLE_USER'),
                                                                   (4, 'ROLE_USER'),
                                                                   (5, 'ROLE_USER'),
                                                                   (6, 'ROLE_ADMIN');
