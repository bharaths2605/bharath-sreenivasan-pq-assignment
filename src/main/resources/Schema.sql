DROP TABLE if exists stocks;
CREATE TABLE if not exists stocks (id INT AUTO_INCREMENT PRIMARY KEY,name VARCHAR(255) NOT NULL,current_price INT,last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP);

ALTER TABLE stocks AUTO_INCREMENT=1001;


insert into stocks (name,current_price) values ("Lic",234);
insert into stocks (name,current_price) values ("NYKA",566);
insert into stocks (name,current_price) values ("FISV",455);
insert into stocks (name,current_price) values ("BOFA",2347);
insert into stocks (name,current_price) values ("INFY",5783);
insert into stocks (name,current_price) values ("WIPRO",1297);
insert into stocks (name,current_price) values ("PAYZ",2956);
insert into stocks (name,current_price) values ("PAYTM",9456);
insert into stocks (name,current_price) values ("GPAY",265);
insert into stocks (name,current_price) values ("TCS",934);
insert into stocks (name,current_price) values ("TataMotors",836);
insert into stocks (name,current_price) values ("EVT",824);
insert into stocks (name,current_price) values ("TVS",8423);
