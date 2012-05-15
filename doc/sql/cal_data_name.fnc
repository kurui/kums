create or replace function cal_data_name(vno varchar2)
  return varchar2 is
  result  varchar2(50);
  data_name varchar2(50):='';
begin
    select name into data_name from data_type t where t.no=vno;

    if data_name is null then
      data_name:='';
    end if;

    result :=data_name;
    return result;

  end cal_data_name;
/
