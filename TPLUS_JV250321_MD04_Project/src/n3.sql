create database if not exists trainingManagement;
use trainingManagement;

create table if not exists admin
(
    id       int primary key auto_increment,
    username varchar(50)  not null unique,
    password VARCHAR(255) NOT NULL
    );
delete
from admin
where id = 1;
INSERT INTO admin (username, password)
VALUES ('admin', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9');

CREATE TABLE student
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    name      VARCHAR(100) NOT NULL,
    dob       DATE         NOT NULL,
    email     VARCHAR(100) NOT NULL UNIQUE,
    sex       BIT          NOT NULL, -- 1: Nam, 0: Nữ
    phone     VARCHAR(20),
    password  VARCHAR(255) NOT NULL,
    create_at DATE DEFAULT (CURRENT_DATE)
);
INSERT INTO student (name, dob, email, sex, phone, password, create_at)
VALUES ('Nguyễn Văn A',
        '2000-01-15',
        'user@gmail.com',
        1,
        '0123456789',
        '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', -- admin123 mã hóa SHA-256
        CURRENT_DATE);

delimiter &&
create procedure login_admin(iUsername varchar(50),
                             pass varchar(255))
begin
select * from admin where username = iUsername and password = pass;
end &&
delimiter &&

delimiter &&
create procedure login_student(iEmail varchar(100),
                               pass varchar(255))
begin
select * from student where email = iEmail and password = pass;
end &&
delimiter &&

delimiter &&
create procedure is_admin_username(iUsername varchar(50), out result int)
begin
    if exists(select 1 from admin where username = iUsername) then
        set result = 1;
else
        set result = -1;
end if;
end &&
delimiter &&

delimiter &&
create procedure get_pass_by_username_admin(iUsername varchar(50))
begin
select admin.password from admin where username = iUsername;
end &&
delimiter &&

delimiter &&
create procedure save_admin(iUsername varchar(50),
                            iPass varchar(255))
begin
insert into admin (username, password) VALUES (iUsername, iPass);
end &&
delimiter &&

delimiter &&
create procedure is_email_student(iEmail varchar(100), out result int)
begin
    if exists(select 1 from student where email = iEmail) then
        set result = 1;
else
        set result = -1;
end if;
end &&
delimiter &&

delimiter &&
create procedure get_pass_by_email(iEmail varchar(100))
begin
select password from student where email = iEmail;
end &&
delimiter &&

CREATE TABLE course
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(100) NOT NULL,
    duration   INT          NOT NULL,
    instructor VARCHAR(100) NOT NULL,
    create_at  DATE DEFAULT (CURRENT_DATE)
);
INSERT INTO course (name, duration, instructor)
VALUES ('Java Programming', 60, 'Nguyen Van A'),
       ('Web Development', 45, 'Tran Thi B'),
       ('Database Design', 50, 'Le Van C'),
       ('Data Structures', 55, 'Pham Thi D'),
       ('Networking Basics', 40, 'Hoang Van E'),
       ('Mobile App Development', 70, 'Dang Thi F');

delimiter &&
create procedure find_all_course()
begin
select * from course;
end &&
delimiter &&

delimiter &&
create procedure save_course(nName varchar(100), nDuration int, nIntructor varchar(100))
begin
insert into course (name, duration, instructor) VALUES (nName, nDuration, nIntructor);
end &&
delimiter &&

delimiter &&
create procedure is_course_name(iName varchar(100), out result int)
begin
    if exists(select 1 from course where name = iName) then
        set result = 1;
else
        set result = -1;
end if;
end &&

delimiter &&

delimiter &&
create procedure find_course_by_id(sId int)
begin
select * from course where id = sId;
end &&
delimiter &&

delimiter &&
create procedure is_course_id(sId int, out result int)
begin
    if exists(select 1 from course where id = sId)
    then
        set result = 1;
else
        set result = -1;
end if;
end
&&
delimiter &&

delimiter &&
create procedure update_course(nId int,
                               nName varchar(100),
                               nDuration int,
                               nIntructor varchar(100),
                               ncreat_at DATE)
begin
update course
set name       = nName,
    duration   = nDuration,
    instructor = nIntructor,
    create_at  = ncreat_at
where id = nId;
end &&
delimiter &&

delimiter &&
create procedure delete_by_id(dId int)
begin
delete from course where id = dId;
end &&
delimiter &&

delimiter &&
create procedure find_course_by_name(sName varchar(100))
begin
select * from course where name like concat('%', sName, '%');
end &&
delimiter &&

delimiter &&
create procedure find_all_student()
begin
select * from student;
end &&
delimiter &&

delimiter &&
create procedure save_student(nName varchar(100),
                              nDob date,
                              nEmail varchar(100),
                              nSex bit,
                              nPhone varchar(100),
                              nPass varchar(255))
begin
insert into student(name, dob, email, sex, phone, password) VALUES (nName, nDob, nEmail, nSex, nPhone, nPass);
end
&&
delimiter &&

delimiter &&
create procedure find_student_by_id(sId int)
begin
select * from student where id = sId;
end &&
delimiter &&

delimiter &&
create procedure is_student_id(sId int, out result int)
begin
    if exists(select 1 from student where id = sId) then
        set result = 1;
else
        set result = -1;
end if;
end &&
delimiter &&

delimiter &&
create procedure update_student(nId int,
                                nName varchar(100),
                                nDob date,
                                nEmail varchar(100),
                                nSex bit,
                                nPhone varchar(100),
                                nPass varchar(255))
begin
update student
set name     = nName,
    dob      = nDob,
    email    = nEmail,
    sex      =nSex,
    phone    = nPhone,
    password =nPass
where id = nId;
end &&
delimiter &&

delimiter &&
create procedure delete_student_by_id(dId int)
begin
delete from student where id = dId;
end &&
delimiter &&

delimiter &&
create procedure find_by_name(sName varchar(100))
begin
select * from student where name like concat('%', sName, '%');
end &&
delimiter &&

delimiter &&
create procedure find_by_email(sEmail varchar(100))
begin
select * from student where email like concat('%', sEmail, '%');
end &&
delimiter &&
create procedure find_by_id(sId int)
begin
select * from student where id = sId;
end &&
delimiter &&
CREATE TABLE enrollment
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    student_id    INT NOT NULL,
    course_id     INT NOT NULL,
    registered_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    status        ENUM ('WAITING', 'DENIED', 'CANCER', 'CONFIRM') DEFAULT 'WAITING',
    FOREIGN KEY (student_id) REFERENCES student (id),
    FOREIGN KEY (course_id) REFERENCES course (id)
);

delimiter &&
create procedure save_enrollment(nStudentId int,
                                 nCourseId int)
begin
insert into enrollment (student_id, course_id) VALUES (nStudentId,nCourseId);
end &&
delimiter &&

INSERT INTO student (name, dob, email, sex, phone, password, create_at)
VALUES ('Nguyễn Văn b',
        '2002-01-15',
        'user1@gmail.com',
        0,
        '0123498765',
        '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', -- admin123 mã hóa SHA-256
        CURRENT_DATE);
INSERT INTO enrollment (student_id, course_id, registered_at, status)
VALUES
    (1, 1, '2025-07-24 08:30:00', 'WAITING'),
    (1, 2, '2025-07-24 09:00:00', 'CONFIRM'),
    (1, 3, '2025-07-23 14:00:00', 'CANCER'),
    (2, 1, '2025-07-22 10:00:00', 'DENIED');
INSERT INTO enrollment (student_id, course_id, status)
VALUES (1, 1, 'WAITING'),
       (1, 2, 'CONFIRM'),
       (1, 3,  'CANCER'),
       (2, 1,  'WAITING');




delimiter &&
create procedure save_enrollment(nStudentId int,
                                 nCourseId int)
begin
insert into enrollment (student_id, course_id) VALUES (nStudentId, nCourseId);
end &&
delimiter &&
call delete_enrollment (1,2);
delimiter &&
create procedure find_course_enrollment(sStudentId int)
begin
select c.id, c.name, c.duration, c.instructor, c.create_at
from course as c
         join enrollment e on c.id = e.course_id
where e.student_id = sStudentId;
end &&
delimiter &&

delimiter &&
create procedure delete_enrollment(sIdStudent int, sCourseId int)
begin
    if exists(select 1 from enrollment where course_id = sCourseId) then
delete from enrollment where student_id = sIdStudent and course_id = sCourseId and status = 'WAITING';
else
        SIGNAL SQLSTATE '45000' set message_text =
                'ID COURSE không đúng hoặc trạng thái không phù hợp mời bạn kiểm tra lại!';
end if;
end &&
delimiter &&

delimiter &&
create procedure update_pass_student(uId int, nPass varchar(255))
begin
update student set password = nPass where id = uId;
end &&
delimiter &&

delimiter &&
create procedure find_student_by_course(sId int)
begin
select *
from student as s
         join enrollment e on s.id = e.student_id
where course_id = sId;
end &&
delimiter &&

delimiter &&
create procedure admin_appect_enrollment(eId int)
begin
update enrollment set status = 'CONFIRM' where id = eId;
end &&
delimiter &&

drop procedure admin_appect_enrollment;

delimiter &&
create procedure find_all_enrollment()
begin
select * from enrollment;
end &&
delimiter &&

delimiter &&
create procedure total_course_and_student()
select count(s.id) as total_student, count(c.id) as total_course
from enrollment as e
         join course c on c.id = e.course_id
         join student s on s.id = e.student_id;
delimiter &&

delimiter &&
create procedure total_student_by_course()
begin
select c.id, c.name, count(e.student_id) as total_student
from enrollment as e
         join course c on c.id = e.course_id
group by c.id, c.name;
end &&
delimiter &&

call limit_five_course();

delimiter &&
create procedure limit_five_course()
select c.id, c.name, count(e.student_id) as total_student
from enrollment as e
         join course c on c.id = e.course_id
group by c.id, c.name
order by total_student desc
    limit 5;
delimiter &&

delimiter &&
create procedure student_by_email(sEmail varchar(100))
select * from student where email = sEmail;
delimiter &&

delimiter &&
create procedure up_ten_course()
begin
select c.id, c.name, count(e.student_id) as total_student from course as c
                                                                   join enrollment e on c.id = e.course_id
group by c.id, c.name
having total_student > 10;
end &&
delimiter &&

delimiter &&
create procedure course_enrollment_table(sIdStudent int)
begin
select c.id,c.name, c.duration, c.instructor,e.registered_at
from course as c
         join enrollment e on c.id = e.course_id
where e.student_id = sIdStudent;
end;
delimiter &&

delimiter &&
create procedure total_page_course(rowOfPage int,out totalPage int)
begin
    declare totalrow int;
select count(*) into  totalrow from course;
set totalPage = CEIL(totalrow/rowOfPage);
end &&
delimiter &&

delimiter &&
create procedure paging_course(page int,rowOfpage int)
begin
    declare off int;
    set off = (page - 1) * rowOfpage;
select * from course limit rowOfpage offset off;
end &&
delimiter &&
set @total = 0;
call total_page_course(5,@total);
select @total as total_page