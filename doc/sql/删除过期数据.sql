select f.id from finance_order  f where f.status=88;

select o.id from order_detail o where o.order_id in(select f.id from finance_order  f where f.status=88);

select c.id from commission c where c.order_id in(select f.id from finance_order  f where f.status=88);

select s.id from statement s where s.order_id in(select f.id from finance_order  f where f.status=88);

select * from operate_log t where t.order_id not in(select f.id from finance_order f where f.status not in(88));


----------
delete   statement s where s.id in(select s.id from statement s where s.order_id in(
select f.id from finance_order  f where f.status=88));

delete finance_order f where  f.id in(select f.id from finance_order  f where f.status=88);

delete operate_log o where o.id in(
select t.id from operate_log t where t.order_id not in(select f.id from finance_order f where f.status not in(88))
)


