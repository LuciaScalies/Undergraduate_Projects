#Dominic Scalies
#CIS 112 Database Concepts
#HW 3
#October 16, 2015
use lucias;

#qryA returns a purchase with the Customer's First Name, the Customer's Last Name, the Painting Title, and the Purchase Date
select tbl_customers.First_Name, tbl_customers.Last_Name, tbl_paintings.Painting, tbl_purchases.PurchaseDate 
	from tbl_purchases 
    inner join tbl_customers
    on tbl_purchases.CID = tbl_customers.CID 
    inner join tbl_paintings
    on tbl_purchases.PID = tbl_paintings.PID;

#qryB returns Painting Title, Painting Date, and Medium for all Oil on Canvas paintings made before 1900
#qryB cont. returned results are then sorted in reverse alphabetical order by Painting Title
select Painting, DateMade, PaintingMedium from tbl_paintings
	where DateMade < '1900-01-01' and PaintingMedium = 'Oil on Canvas'
    order by Painting desc;

#qryC returns Artist's Name and the total of money spent on that artist, not including tax. The total column is aliased Total Spent Pretax
select tbl_artist.Artist_First_Name, tbl_artist.Artist_Last_Name, sum(tbl_purchases.price) as 'Total Spent Pretax'
	from tbl_purchases 
    inner join tbl_paintings
    on tbl_purchases.PID = tbl_paintings.PID
    inner join tbl_artist
    on tbl_paintings.AID = tbl_artist.AID
    group by Artist_First_Name, Artist_Last_Name;
    
#qryD returns Customer Name and total amount of money spent on paintings, tax included, if that total is > $100,000.
#qryD cont. The spent column is aliased Total_Spent, items are sorted in ascending order in terms of total spent
select tbl_customers.First_Name, tbl_customers.Last_Name, sum(tbl_purchases.price + tbl_purchases.tax) as 'Total_Spent'
	from tbl_purchases
    inner join tbl_customers
    on tbl_purchases.CID = tbl_customers.CID
    group by First_Name, Last_Name
    having sum(tbl_purchases.price + tbl_purchases.tax) > 100000
    order by sum(tbl_purchases.price + tbl_purchases.tax) asc;
    
#qryE displays Customer Name and the number of paintings that customer purchased. Total Number of Paintings column is aliased Number of Paintings
select tbl_customers.First_Name, tbl_customers.Last_Name, count(tbl_purchases.CID) as 'Number of Paintings'
	from tbl_customers
    left join tbl_purchases
    on tbl_customers.CID = tbl_purchases.CID
    group by tbl_customers.First_Name, tbl_customers.Last_Name;