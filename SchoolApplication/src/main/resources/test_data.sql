CREATE TABLE `students` (
  `students_id` int NOT NULL AUTO_INCREMENT,
  `group_id` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `surname` varchar(100) DEFAULT NULL,
  `login` varchar(100) DEFAULT NULL,
  `password` date DEFAULT NULL
  
  PRIMARY KEY (`students_id`)
  --UNIQUE KEY `users_table_un` (`username`)
);

CREATE TABLE `courses` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `describtion` varchar(100) DEFAULT NULL
  
  PRIMARY KEY (`course_id`)
 -- UNIQUE KEY `users_table_un` (`username`)
);

CREATE TABLE `groups` (
  `group_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL
 
  PRIMARY KEY (`group_id`)
 -- UNIQUE KEY `users_table_un` (`username`)
);

CREATE TABLE `groups_courses` (
  `group_id` int NOT NULL AUTO_INCREMENT,
  `course_id` varchar(100) DEFAULT NULL
 
  PRIMARY KEY (`group_id`),
  PRIMARY KEY (`course_id`)
 -- UNIQUE KEY `users_table_un` (`username`)
);

CREATE TABLE `students_courses` (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `course_id` varchar(100) DEFAULT NULL
 
PRIMARY KEY (`student_id`),
PRIMARY KEY (`course_id`)
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
 INSERT INTO courses (course_id, title, desctibtion)
  VALUES(1, 'Math', 'Math analys');
  
INSERT INTO courses (course_id, title, desctibtion) 
 VALUES(2, 'Biology', 'Animals');
 
INSERT INTO courses (course_id, title, desctibtion) 
 VALUES(3, 'Geography', 'Oceans');

INSERT INTO courses (course_id, title, desctibtion)
 VALUES(4, 'History', 'Wars');
 

 
 --GROUPS
 INSERT INTO students (group_id, title)
  VALUES(1,'First');
  
INSERT INTO students (group_id, title) 
 VALUES(2, 'Second');
 
INSERT INTO students (group_id, title) 
 VALUES(3, 'Third');

INSERT INTO students (group_id, title)
 VALUES(4, 'Fourth');
 
 
 
 --GROUPS_COURSES
 
 --STUDENTS_COURSES
 
 