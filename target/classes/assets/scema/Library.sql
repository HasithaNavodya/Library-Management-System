drop database Library_System;
create database Library_System;
use Library_System;

create table Employee(
                         employee_id varchar(20) primary key,
                         employee_name varchar(20),
                         status varchar(20),
                         address varchar(100),
                         contact_no varchar(20)
);

create table Salary(
                       salary_id varchar(20) primary key,
                       bonus decimal(7,2),
                       date date,
                       amount decimal(7,2),
                       employee_id varchar(4),
                       constraint foreign key(employee_id) references Employee(employee_id)
                           ON DELETE CASCADE
                           ON UPDATE CASCADE
);

create table Inventory(
                          Item_id varchar(29) primary key,
                          Item_name varchar(20),
                          category varchar(20),
                          quantity int(4)
);

create table User(
                     username varchar(40) primary key,
                     password varchar(20),
                     email varchar(40)

);

create table Donators(
                         donator_id varchar(20) primary key,
                         name varchar(20),
                         contact varchar(20),
                         date date,
                         username varchar(40),
                         constraint foreign key(username ) references User(username )
                             ON DELETE CASCADE
                             ON UPDATE CASCADE
);

create table Books(
                      book_id varchar(20) primary key,
                      name varchar(30),
                      author varchar(20),
                      category varchar(30),
                      cupboard_no int(2)
);

create table book_donation(
                              donation_id varchar(15),
                              donator_id varchar(20),
                              book_id varchar(20),
                              constraint primary key(donation_id,donator_id,book_id),
                              constraint foreign key(donator_id) references Donators(donator_id)
                                  ON DELETE CASCADE
                                  ON UPDATE CASCADE
);

create table Members(
                        member_id varchar(20) primary key,
                        name varchar(30),
                        address varchar(100),
                        grade varchar(10),
                        member_fee decimal(8,2),
                        contact_no varchar(20)
);

create table Borrow_books(
                             issue_id varchar(20),
                             member_id varchar(20),
                             book_id varchar(20),
                             due_date date NOT NULL ,
                             constraint primary key(issue_id,member_id,book_id),
                             constraint foreign key(member_id) references Members(member_id)
                                 ON DELETE CASCADE
                                 ON UPDATE CASCADE
);

create table fines(
                      fine_id varchar(20),
                      amount decimal(7,2),
                      date date,
                      description varchar(20),
                      member_id varchar(4),
                      constraint foreign key(member_id) references Members(member_id)
                          ON DELETE CASCADE
                          ON UPDATE CASCADE
);



select book_donation.donation_id,book_donation.donator_id,book_donation.book_id,books.name,books.author,books.category,books.cupboard_no
FROM book_donation
         INNER JOIN books
                    ON books.book_id=book_donation.book_id
WHERE book_donation.donation_id=(select max(book_donation.donation_id) FROM book_donation)
ORDER BY book_donation.donation_id desc










