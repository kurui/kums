select * from agent t;

update agent set virtual_amount=0;
update agent set physical_amount=0;
update agent set total_integral=0;
update agent set grade=0;


select t.*,t.rowid from agent_relation t;


insert into agent_relation (id,root_agent_id,relation_type,status,user_no,relate_agent_id)
                             select seq_agentrelation.nextval,717,1,1,'admin',a.id 
                              from agent a ;

 update agent_relation t set t.update_date=( select sysdate from dual);

 update agent_relation t set t.root_agent_id=663  where t.relate_agent_id in(select a.id from agent a where a.know_place='ф╥ип');
 
insert into agent_relation(id,root_agent_id,relate_agent_id,relation_type,update_date,status,user_no)
  values(seq_agentrelation.nextval,647,655,1,sysdate,1,'admin');
  
  
  select a.* from Agent_Relation a where 1=1  and a.relate_agent_id=655
       and(  
            a.rootAgent.name like '%A20110815000007%' 
         or a.rootAgent.agentNo like '%A20110815000007%'  
         or a.rootAgent.qqCode like '%A20110815000007%'  
         or a.rootAgent.msnCode like '%A20110815000007%'  
         or a.rootAgent.email like '%A20110815000007%'  
         or a.rootAgent.mobilePhone like '%A20110815000007%'  
         ) 
           and a.status=1  order by a.rootAgent.id 

