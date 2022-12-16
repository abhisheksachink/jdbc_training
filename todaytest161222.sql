-- Create a database table that will hold all the transactions done on a credit card. 
-- It will havae fields such as account number, details of the transaction along with transaction date. 
-- Then write a stored procedure which will take account number and month as input and 
-- output the total amount due for that account. 
-- Since this is a promotional card 5% discount will be given on total amount. 
-- Note: if any outstatnding amount is there on an account that will be added to the amount. -- 


use infinite;
create table credit_info(Acc_no Varchar(15), Trans_No Varchar(15), date_of_trans date,amount double);
insert into credit_info(Acc_no,Trans_No,date_of_trans,amount) values
('SB00022121601','T020221216001','20221216',500),
('SB00022121602','T020221216002','20220516',600),
('SB00022121603','T020221216004','20220615',120),
('SB00022121603','T020221216005','20220103',120),
('SB00022121607','T020221216008','20221211',1300),
('SB00022121602','T020221216014','20221201',5008),
('SB00022121602','T020221216132','20221204',580),
('SB00022121602','T020221216013','20221213',556);
SELECT * FROM credit_info;
select month(date_of_trans) from credit_info;
select sum(amount)-sum(amount)*0.05 as total_due from credit_info where acc_no='SB00022121602' and month(date_of_trans)=12;
DELIMITER && 
CREATE PROCEDURE get_amnt_due(in ac_no varchar(15),in monthd int,out bal double)  
BEGIN

    
    select sum(amount)-sum(amount)*0.05 into bal 
    from credit_info where acc_no=ac_no and month(date_of_trans)<=monthd;
END &&  
DELIMITER ; 

call get_amnt_due('SB00022121602',12,@bal);
select @bal as balance;

