/*
    Добавьте процедуру и функцию, которая будет удалять записи.
    Условия выбирайте сами – удаление по id, удалить если количество товара равно 0 и т.п.
*/

create or replace function f_delete_prod(id_p int2)
returns void
language 'plpgsql'
as
$$
begin
    delete from products
    where id = id_p;

    analyze products;

end
$$;

select * from products;
select f_delete_prod(1::int2);


create or replace procedure p_delete_prod(name_p text)
language 'plpgsql'
as
$$
begin
	delete from products
	where name = name_p;
end
$$;

select * from products;
call p_delete_prod('prod_1'::text);