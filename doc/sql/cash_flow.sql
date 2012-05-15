create or replace procedure create_cash_flow(sessionId String) is

begin
  delete from cash_flow o where o.type=1 and o.session_id=sessionId;

  insert into cash_flow
    (
      id,
      business_time,
      business_date,
      business_no,
      tran_type,
      item_NAME,
      in_amount,
      out_amount,
     cussent_Company,
     summary,
      type,
      session_id
    )
   select
     SEQ_cashflow.Nextval,
     a.entry_time,
     to_char(a.entry_time,'yyyy-mm-dd'),
     a.order_no,
     a.tran_type,
     cal_data_name(a.tran_type),
     a.total_amount,
     a.total_amount,
     a.company_id,
     a.memo,
     1,
     sessionId
     from finance_order a,dual
     where 1=1 and a.status not in(88);
     commit;
     -----------
/*update cash_flow r set r.end_amount=(r.begin_Amount+r.add_Amount-r.lessen_amount); */
     
     -------------
     commit;

end create_cash_flow;
