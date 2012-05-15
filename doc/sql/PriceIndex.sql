 select p.*,p.rowid from Price_Index p where 1=1  
            and  p.snatch_Time  between to_date('2010-01-01 12:00:00','yyyy-mm-dd hh24:mi:ss')
                                  and to_date('2012-01-01 12:00:00','yyyy-mm-dd hh24:mi:ss') 
            and p.status=1  order by p.snatch_time


 select distinct p.snatch_time from Price_Index p where 1=1  
            and  p.snatch_Time  between to_date('2010-01-01 12:00:00','yyyy-mm-dd hh24:mi:ss')
                                  and to_date('2012-01-01 12:00:00','yyyy-mm-dd hh24:mi:ss') order by p.snatch_Time 
                                  
 select * from (
               select distinct to_char(pp.snatch_time,'yyyy-mm-dd') snatch_date from(
                       select p.snatch_time from Price_Index p where 1=1  
                                             and  p.snatch_Time   between to_date('2010-01-01 12:00:00','yyyy-mm-dd hh24:mi:ss')
                                              and to_date('2012-01-01 12:00:00','yyyy-mm-dd hh24:mi:ss') order by p.snatch_time desc 
                                        ) pp
               ) ppp order by ppp.snatch_date;
                                  
select  ppp.snatch_date from ( 
         select distinct to_char(pp.snatch_Time,'yyyy-mm-dd') snatch_date from( 
                select p.snatch_Time from Price_Index p where 1=1 and p.status=1 and p.reference_id in ( 62,44,57,41,55,45,66,61,60,65,63,40,24,25,58,59,21,64,42,20,56 )  
                                     and  p.snatch_Time  between to_date('2011-01-01 00:00:00','yyyy-mm-dd hh24:mi:ss')
                                        and to_date('2012-01-01 23:59:59','yyyy-mm-dd hh24:mi:ss') order by p.snatch_Time desc 
                                 ) pp 
                          ) ppp order by ppp.snatch_date 
                          
                          
                          
                          select t.*, t.rowid from finance_order t where t.tran_type in(1001,1011);

select t.*,t.rowid from price_reference t where t.type=20 order by code;
select t.*, t.rowid from price_index t


select tt.* from price_reference tt where tt.id in( select distinct t.reference_id from price_index t where t.id in(23,24,25));




港股 恒生:18130.55(4.15%)更新：16:00:00刷新02328中国财险9.363.65%  00883中海油12.807.74%
  01688阿里巴巴7.2512.93%  01919中国远洋3.255.18%  02318中国平安46.8010.12% 
   04338微软-T186.000.00%  02628中国人寿18.464.29% 
 00001长江实业89.002.95%  00700腾讯控股160.002.63%  
 
  insert into price_reference(id,code,name,money,type,status)  values(seq_pricereference.nextval,'02328','中国财险','港元',20,1);
    insert into price_reference(id,code,name,money,type,status)  values(seq_pricereference.nextval,'01688','阿里巴巴','港元',20,1);
     insert into price_reference(id,code,name,money,type,status)  values(seq_pricereference.nextval,'02318','中国平安','港元',20,1);
       insert into price_reference(id,code,name,money,type,status)  values(seq_pricereference.nextval,'00700','腾讯控股','港元',20,1);
   

   update price_index p set p.price=(p.max_price+p.min_price)/2
            where 1=1           and p.max_price>0 and p.min_price>0
             
             















