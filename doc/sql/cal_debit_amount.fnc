create or replace function cal_debit_amount(agentId integer,ptype integer)
  return NUMBER is
  result  NUMBER(15,4);
  beginAmount  NUMBER(15,4):=0;--期初
  addAmount  NUMBER(15,4):=0;--增加
  lessenAmount number(15,4):=0;--减少
  endAmount  NUMBER(15,4):=0;--期末
  totalAmount NUMBER(15,4):=0;
begin
    if ptype=1 then
      select sum(o.total_amount) into beginAmount from finance_order o
      where 1=1
      and  o.status not in(88) 
      and o.business_type=2
      and o.tran_type in(2001)
      and o.agent_id=agentId;
     
      totalAmount :=beginAmount;
    end if;
     if ptype=2 then
       totalAmount :=0;
    end if;
     if ptype=3 then
       select sum(s.total_amount) into lessenAmount  from finance_order o,statement s
       where 1=1
       and o.status not in(88) and s.status not in(88)
       and o.business_type=1
       and o.tran_type in(2011)
        and o.id=s.order_id
       and o.agent_id=agentId;
      totalAmount :=lessenAmount;
    end if;
     if ptype=4 then
      totalAmount :=0;
    end if;

    if totalAmount is null then
      totalAmount:=0;
    end if;

    result :=totalAmount;
    return result;

  end cal_debit_amount;
/
