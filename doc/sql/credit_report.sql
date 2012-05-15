create or replace procedure create_credit_report(sessionId String) is

begin
  delete from credit_report o where o.session_id=sessionId;

  insert into credit_report
    (
      id,
      agent_id,
      agent_NAME,
      begin_amount,
      add_amount,
      lessen_amount,
      end_amount,
      type,
      session_id
    )
   select
     SEQ_creditreport.Nextval,
     a.id,
     a.name,
     cal_credit_amount(a.id,1),
     cal_credit_amount(a.id,2),
      cal_credit_amount(a.id,3),
       cal_credit_amount(a.id,4),
     1,
     sessionId
     from agent a;
     commit;
     -----------

     -------------
     commit;

end create_credit_report;
/
