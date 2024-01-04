DROP TABLE IF EXISTS `students`;
DROP TABLE IF EXISTS `courses`;
DROP TABLE IF EXISTS `groups`;
	
CREATE TABLE IF NOT EXISTS `students`
(
  `students_id` Long NOT NULL AUTO_INCREMENT,
  `group_id` Long NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `login` varchar(100) NOT NULL,
  `password` varchar(150) NOT NULL,
  
  PRIMARY KEY (`students_id`)
);

CREATE TABLE `courses` (
  `course_id` Long NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `describtion` varchar(100) DEFAULT NULL,
      
    PRIMARY KEY (`course_id`)

);

CREATE TABLE `groups` (
  `group_id` Long NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
 
  PRIMARY KEY (`group_id`)
);

CREATE TABLE `groups_courses` (
 `group_id` Long NOT NULL AUTO_INCREMENT,
 `course_id` Long NOT NULL AUTO_INCREMENT,
    
    PRIMARY KEY (`group_id`, `course_id`)

);

CREATE TABLE `students_courses` (
  `student_id` Long NOT NULL AUTO_INCREMENT,
  `course_id` Long NOT NULL AUTO_INCREMENT,
 
PRIMARY KEY (`student_id`, `course_id`)

);


--STUDENTS
INSERT INTO students (students_id, group_id, name, surname, login, password)
  VALUES(1, 1, 'Ivan', 'Ivanov', '1111', 'First');
  
INSERT INTO students (students_id, group_id, name, surname, login, password) 
 VALUES(2, 2, 'Mia', 'Tetrova', '2222', 'Second');
 
INSERT INTO students (students_id, group_id, name, surname, login, password) 
 VALUES(3, 3, 'Artem', 'Derenok', '3333', 'Third');

INSERT INTO students (students_id, group_id, name, surname, login, password)
 VALUES(4, 4, 'Dima', 'Grupnik', '4444', 'Fourth');
 
 
 
 --COURSES
 INSERT INTO courses (course_id, title, describtion)
  VALUES(1, 'Math', 'Math analys');
  
INSERT INTO courses (course_id, title, describtion) 
 VALUES(2, 'Biology', 'Animals');
 
INSERT INTO courses (course_id, title, describtion) 
 VALUES(3, 'Geography', 'Oceans');

INSERT INTO courses (course_id, title, describtion)
 VALUES(4, 'History', 'Wars');
 

 
 --GROUPS
 INSERT INTO groups (group_id, title)
  VALUES(1,'First');
  
INSERT INTO groups (group_id, title) 
 VALUES(2, 'Second');
 
INSERT INTO groups (group_id, title) 
 VALUES(3, 'Third');

INSERT INTO groups (group_id, title)
 VALUES(4, 'Fourth');
 
 
--ALTER TABLE `students` ALTER COLUMN `password` SET TYPE varchar(150);
 --GROUPS_COURSES
 
 --STUDENTS_COURSES
 
 