select f.*,f.rowid from data_type f ;
select f.*,f.rowid from finance_order f order by f.id desc;
select f.*,f.rowid from finance_order f where f.tran_type=120130;
select f.*,f.rowid from finance_order f where f.memo like '%ª™Ω„%';
select f.* from finance_order f where f.memo like '%»ÿΩ„%';

select t.* from company t where t.name like '%–¬˜Ë˜Î%';

update finance_order f set f.cussent_company_id=538 where f.memo like '%»ÿΩ„%';
update finance_order f set f.cussent_company_id=539 where f.memo like '%¥Û∫£%';
update finance_order f set f.cussent_company_id=540 where f.memo like '%ª™Ω„%';
update finance_order f set f.cussent_company_id=542 where f.memo like '%º™÷Æµ∫%';
update finance_order f set f.cussent_company_id=543 where f.memo like '%–¬“ªº—%';
update finance_order f set f.cussent_company_id=547 where f.tran_type=120130;//…œÕ¯∑—
update finance_order f set f.cussent_company_id=549 where f.memo like '%–¬˜Ë˜Î%';
update finance_order f set f.cussent_company_id=561 where f.memo like '%Ã‘µ%';
update finance_order f set f.cussent_company_id=574 where f.memo like '%∆ﬂ Âπ´%';
update finance_order f set f.cussent_company_id=580 where f.memo like '%¬≥√Ê%';
update finance_order f set f.cussent_company_id=581 where f.memo like '%¿º÷›%';
update finance_order f set f.cussent_company_id=582 where f.memo like '%¿œ«ÿ≈£%';


select f.* from finance_order f where f.cussent_company_id>0;

update finance_order f set f.memo=replace(f.memo,'»ÿΩ„2','»ÿΩ„') where f.memo like '%»ÿΩ„%';


