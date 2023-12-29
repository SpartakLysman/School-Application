--CREATE USER 'postgre'@'localhost' IDENTIFIED BY '1234';

--CREATE DATABASE app;

--GRANT ALL PRIVILEGES ON app.* TO 'postgree'@'localhost';

--FLUSH PRIVILEGES;


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



--INSERT INTO students (username, firstName, lastName, userRole, createdAt, updatedAt)  VALUES('paul_pop', 'paulous', 'cool', 'admin', '2023-06-25','2023-06-25');
--INSERT INTO students (username, firstName, lastName, userRole, createdAt, updatedAt)  VALUES('kaySlow', 'Kay', 'Slow', 'admin', '2023-06-25','2023-06-25');
--INSERT INTO students (username, firstName, lastName, userRole, createdAt, updatedAt)  VALUES('ghostNinja', 'Ghost', 'Ninja', 'user', '2023-06-25','2023-06-25');
--INSERT INTO students (username, firstName, lastName, userRole, createdAt, updatedAt)  VALUES('fastBean', 'Fast', 'Bean', 'user', '2023-06-25','2023-06-25');