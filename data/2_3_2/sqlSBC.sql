-- 1. В одном запросе получить
select p.name, c.name
from person p
join company c
on p.company_id = c.id and c.id <> 5;

-- 2. Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании.
select c.name, count(p.id)
from person p
join company c
on p.company_id = c.id
group by c.name
having count(p.id) in
(select
    max(cnt)
    from (
        select count(p.id) as cnt from person p join company c on p.company_id = c.id group by c.id
)
t);