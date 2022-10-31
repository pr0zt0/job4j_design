/*
    -- tran 1
    begin transaction isolation level serializable;
    delete from products;
    select avg(id) from products;
    commit;

    --tran 2
    set session transaction isolation level serializable;
    select * from products;
    update products set count = 23;
*/