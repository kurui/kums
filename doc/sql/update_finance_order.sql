select f.id,f.order_no,f.business_type,f.tran_type,f.business_time,f.memo from finance_order f;

select f.order_no,f.tran_type,f.business_time,f.memo,f.total_amount,f.cussent_company_id,f.rowid
 from finance_order f where f.tran_type=120112 and f.total_amount>10 and f.cussent_company_id is null;
 
 
 

