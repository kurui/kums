select t.*, t.rowid from agent t;


select t.name,t.position,t.id,t.agent_no, t.rowid from agent t order by t.name;

select t.name,t.memo,t.strong_suit,t.asset_info,t.credit_info,t.rowid from agent t order by t.name;

select t.name,t.reside,t.work_place,t.know_place,t.stamp_group,t.type,t.memo, t.rowid from agent t ;

select update_date from agent where update_date='';

update agent set update_date= (select to_date('2011-8-15 10:26:29','yyyy-mm-dd hh:mi:ss') from dual);

