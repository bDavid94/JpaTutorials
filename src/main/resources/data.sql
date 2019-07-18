insert into course(id, name) values (10001, 'JPA in 50 Steps');
insert into course(id, name) values (10002, 'Spring in 50 Steps');
insert into course(id, name) values (10003, 'Spring Boot in 50 Steps');
insert into course(id, name) values (10004, 'jAVA 11');


insert into passport(id, number) values (30001, 'M1111');
insert into passport(id, number) values (30002, 'M2222');
insert into passport(id, number) values (30003, 'F3333');
insert into passport(id, number) values (30004, 'F4444');

insert into student(id, name, passport_id) values (20001, 'Bogdan', 30001);
insert into student(id, name, passport_id) values (20002, 'Raluca', 30004);
insert into student(id, name, passport_id) values (20003, 'Valentin', 30002);
insert into student(id, name, passport_id) values (20004, 'Daniela', 30003);

insert into review(id, description, rating, course_id) values (40001, 'very good', 5, 10001);
insert into review(id, description, rating, course_id) values (40002, 'very bad', 1, 10001);
insert into review(id, rating, course_id)              values (40003, 3, 10003);
insert into review(id, description, rating) values (40004, 'very good', 5);

insert into student_course(student_id, course_id) values (20001, 10001);
insert into student_course(student_id, course_id) values (20001, 10002);
insert into student_course(student_id, course_id) values (20002, 10003);
insert into student_course(student_id, course_id) values (20001, 10003);


