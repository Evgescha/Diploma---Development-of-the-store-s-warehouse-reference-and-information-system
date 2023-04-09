MERGE INTO role(id, name) KEY(id) VALUES(1, 'ROLE_USER');
MERGE INTO role(id, name) KEY(id) VALUES(2, 'ROLE_ADMIN');

MERGE INTO order_status(id, name) KEY(id) VALUES(1, 'Creation');
MERGE INTO order_status(id, name) KEY(id) VALUES(2, 'On Warehouse');
MERGE INTO order_status(id, name) KEY(id) VALUES(3, 'Delivering');
MERGE INTO order_status(id, name) KEY(id) VALUES(4, 'Done');

MERGE INTO my_users(id, username, password, fio, role_id) KEY(id) VALUES(1, 'admin', '$2a$10$7kQ1nv74qr7CiGAouEzxUOqoD9Pylh7nKY6WXDzAV6O1IF5R21tz.', 'admin', 2);
