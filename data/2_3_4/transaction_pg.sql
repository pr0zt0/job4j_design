/*
    start transaction isolation level read committed;
    select count(1) from products;
    savepoint first;
    insert into products (name, count, price) values ('1', 234, 2);
    select * from products;
    savepoint second;
    truncate products;
    rollback to savepoint second;
    select * from products;
    rollback to savepoint first;
    select * from products;
    commit;
*/