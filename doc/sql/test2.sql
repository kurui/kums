select a.id,a.name,a.asset_index from Agent a where 1=1 and a.status not in(0) 
 order by a.asset_Index desc ;

select a.*,a.rowid from agent a where a.id=719;

update agent a set a.asset_index=0 where a.asset_index is null


select f.*,f.rowid from finance_order f order by f.id desc;
select f.*,f.rowid from statement f order by f.id desc;
select f.*,f.rowid from operate_log f order by f.id desc;
select f.*,f.rowid from operate_log_detail f order by f.id desc;

select f.*,f.rowid from price_index f order by f.id desc;

select * from price_index p cross join price_reference f order by f.id desc;
select * from role;

update role_right set right_name='管理系统',right_code='sa01',right_action='1',right_event='1',right_description='2' where right_key=-1




































