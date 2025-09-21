-- 회원 등급
CREATE TABLE `Member_Rank` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` ENUM('코알못','코뉴비','코좀알','코잘알','코신') NOT NULL,
  `min_point` INT NOT NULL,
  `image_url` VARCHAR(255) NOT NULL
);

-- 권한
CREATE TABLE `Role` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` ENUM('MEMBER','ADMIN') NOT NULL DEFAULT 'MEMBER',
  `description` VARCHAR(255) NOT NULL
);

-- 회원
CREATE TABLE `Member` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `rank_id` INT NOT NULL,
  `email` VARCHAR(255) NOT NULL UNIQUE,
  `password` VARCHAR(255) NOT NULL,
  `nickname` VARCHAR(100) NOT NULL UNIQUE,
  `point` INT NOT NULL DEFAULT 0,
  `reported_count` INT NOT NULL DEFAULT 0,
  `status` ENUM('ACTIVE','INACTIVE','BLOCKED') NOT NULL DEFAULT 'ACTIVE',
  `created_at` VARCHAR(20) NOT NULL,
  CONSTRAINT `FK_Member_Rank_TO_Member` FOREIGN KEY (`rank_id`) REFERENCES `Member_Rank`(`id`)
);

-- 회원별 권한 매핑
CREATE TABLE `Member_Role` (
  `role_id` INT NOT NULL,
  `member_id` INT NOT NULL,
  PRIMARY KEY (`role_id`, `member_id`),
  CONSTRAINT `FK_Role_TO_Member_Role` FOREIGN KEY (`role_id`) REFERENCES `Role`(`id`),
  CONSTRAINT `FK_Member_TO_Member_Role` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`)
);

-- 비밀번호 변경 이력
CREATE TABLE `Member_Password_History` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `before_pwd` VARCHAR(255) NOT NULL,
  `updated_at` VARCHAR(20) NULL,
  CONSTRAINT `FK_Member_TO_Member_Password_History` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`)
);

-- 로그인 기록
CREATE TABLE `Member_Login_History` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `created_at` VARCHAR(20) NOT NULL,
  `ip_address` VARCHAR(50) NULL,
  CONSTRAINT `FK_Member_TO_Member_Login_History` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`)
);

-- 로그인 실패 기록
CREATE TABLE `Member_Login_Failed_History` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `created_at` VARCHAR(20) NOT NULL,
  `ip_address` VARCHAR(50) NULL,
  `reason` VARCHAR(255) NULL,
  CONSTRAINT `FK_Member_TO_Login_Failed_History` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`)
);

-- 출석 기록
CREATE TABLE `Member_Attendance_History` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `attend_at` VARCHAR(20) NOT NULL,
  CONSTRAINT `FK_Member_TO_Attendance_History` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`)
);