/*
    использование курсора
*/
do
$$
declare
    r record;
begin
    for r in select * from products
                order by id desc
    loop
        perform r;
    end loop;
end$$;

/*
    begin;
    declare
        cursor_products scroll cursor for
                            select * from products;

        fetch last from cursor_products;

        fetch PRIOR from cursor_products;

        CLOSE cursor_products;

    commit;
*/