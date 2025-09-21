DROP DATABASE IF EXISTS algo;
CREATE DATABASE algo CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE algo;

-- 1. 자식 테이블부터 DROP
DROP TABLE IF EXISTS `Likes`;
DROP TABLE IF EXISTS `Algo_Post_Image`;
DROP TABLE IF EXISTS `Algo_Quiz_Option`;
DROP TABLE IF EXISTS `Algo_Comment`;
DROP TABLE IF EXISTS `Algo_Member_Progress`;
DROP TABLE IF EXISTS `Coding_Post_Image`;
DROP TABLE IF EXISTS `Coding_Comment`;
DROP TABLE IF EXISTS `Career_Info_Comment`;
DROP TABLE IF EXISTS `Study_Post_Image`;
DROP TABLE IF EXISTS `Study_Comment`;
DROP TABLE IF EXISTS `Study_Recruit_Comment`;
DROP TABLE IF EXISTS `Study_Recruit_Member`;
DROP TABLE IF EXISTS `Study_Member`;
DROP TABLE IF EXISTS `Study_Milestone`;

-- 2. 중간 부모 테이블 DROP
DROP TABLE IF EXISTS `Algo_Post`;
DROP TABLE IF EXISTS `Algo_Quiz_Question`;
DROP TABLE IF EXISTS `Coding_Post`;
DROP TABLE IF EXISTS `Career_Info_Post`;
DROP TABLE IF EXISTS `Study_Post`;
DROP TABLE IF EXISTS `Study_Roadmap`;
DROP TABLE IF EXISTS `Study`;
DROP TABLE IF EXISTS `Study_Recruit_Post`;

-- 3. 최상위 부모 테이블 DROP
DROP TABLE IF EXISTS `Algo_Roadmap`;
DROP TABLE IF EXISTS `Coding_Problem`;
DROP TABLE IF EXISTS `Report`;
DROP TABLE IF EXISTS `Report_Category`;
DROP TABLE IF EXISTS `Report_Type`;
DROP TABLE IF EXISTS `Member_Password_History`;
DROP TABLE IF EXISTS `Member_Login_History`;
DROP TABLE IF EXISTS `Member_Login_Failed_History`;
DROP TABLE IF EXISTS `Member_Attendance_History`;
DROP TABLE IF EXISTS `Member_Role`;
DROP TABLE IF EXISTS `Member`;
DROP TABLE IF EXISTS `Member_Rank`;
DROP TABLE IF EXISTS `Role`;