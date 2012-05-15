select t.*, t.rowid from agent t;


select count(*) as allCount from agent t where 1=1 and t.status=1;

select t.type,count(*)/50 from agent t where  t.status=1 group by t.type order by t.type

select t.type,count() from agent t where  t.status=1  order by t.type
