select * from user_role where user_id=1;

select user_id,user_name from sys_user order by user_type desc,user_name;

select * from role_right where role_id in(select role_id from user_role where user_id=1);

select * from role where role_status=1 and role_system=0;


select * from user_role;
select r.*, r.rowid from role r;
select r.*,r.rowid  from role_right r;

delete  user_role where role_id not in(select r.role_id from role r);

insert into role (role_id,role_key,role_name,role_type,role_status,role_system)
                 values(seq_role.nextval,'ss','系统管理',1,1,0)

insert into role_right(right_key,right_name,right_code,right_status,role_id)
                values(seq_roleright.nextval,'','ss01',1,622)


