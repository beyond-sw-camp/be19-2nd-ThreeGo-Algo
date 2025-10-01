-- 알고리즘 로드맵
CREATE TABLE `Algo_Roadmap` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `title` VARCHAR(255) NOT NULL,
  `description` VARCHAR(500) NULL,
  `order` INT NOT NULL,
  `created_at` VARCHAR(20) NOT NULL,
  `updated_at` VARCHAR(20) NULL,
  `question_count` INT NOT NULL DEFAULT 0
);

-- 알고리즘 학습 게시물
CREATE TABLE `Algo_Post` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `roadmap_id` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `content` LONGTEXT NOT NULL,
  `created_at` VARCHAR(20) NOT NULL,
  `updated_at` VARCHAR(20) NULL,
  `comment_count` INT NOT NULL DEFAULT 0,
  `like_count` INT NOT NULL DEFAULT 0,
  `visibility` CHAR(1) NOT NULL DEFAULT 'Y',
  CONSTRAINT `FK_Member_TO_Algo_Post` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`),
  CONSTRAINT `FK_Algo_Roadmap_TO_Algo_Post` FOREIGN KEY (`roadmap_id`) REFERENCES `Algo_Roadmap`(`id`)
);

-- 알고리즘 학습 게시물 이미지
CREATE TABLE `Algo_Post_Image` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `post_id` INT NOT NULL,
  `image_url` VARCHAR(255) NOT NULL,
  `created_at` VARCHAR(20) NOT NULL,
  CONSTRAINT `FK_Algo_Post_TO_Algo_Post_Image` FOREIGN KEY (`post_id`) REFERENCES `Algo_Post`(`id`)
);

-- 개념 확인 문제
CREATE TABLE `Algo_Quiz_Question` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `algo_post_id` INT NOT NULL,
  `question` VARCHAR(500) NOT NULL,
  `type` ENUM('MULTIPLE','OX') NOT NULL,
  CONSTRAINT `FK_Algo_Post_TO_Algo_Quiz_Question` FOREIGN KEY (`algo_post_id`) REFERENCES `Algo_Post`(`id`)
);

-- 개념 확인 문제 선택지
CREATE TABLE `Algo_Quiz_Option` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `question_id` INT NOT NULL,
  `option_text` VARCHAR(255) NOT NULL,
  `is_correct` TINYINT(1) NOT NULL,
  CONSTRAINT `FK_Algo_Quiz_Question_TO_Algo_Quiz_Option` FOREIGN KEY (`question_id`) REFERENCES `Algo_Quiz_Question`(`id`)
);

-- 회원별 퀴즈 맞춘 이력
CREATE TABLE `Member_Algo_Correct_Quiz_History` (
	`member_id` INT NOT NULL,
	`algo_quiz_question_id` INT NOT NULL,
	PRIMARY KEY (`member_id`, `algo_quiz_question_id`),
	CONSTRAINT `FK_Member_TO_Member_Algo_Correct_Quiz_History` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`),
	CONSTRAINT `FK_Algo_Quiz_TO_Member_Algo_Correct_Quiz_History` FOREIGN KEY (`algo_quiz_question_id`) REFERENCES `Algo_Quiz_Question`(`id`)
);

-- 알고리즘 학습 게시물 댓글
CREATE TABLE `Algo_Comment` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `post_id` INT NOT NULL,
  `parent_id` INT NULL,
  `member_id` INT NOT NULL,
  `content` VARCHAR(500) NOT NULL,
  `created_at` VARCHAR(20) NOT NULL,
  `updated_at` VARCHAR(20) NULL,
  `visibility` CHAR(1) NOT NULL DEFAULT 'Y',
  CONSTRAINT `FK_Algo_Post_TO_Algo_Comment` FOREIGN KEY (`post_id`) REFERENCES `Algo_Post`(`id`),
  CONSTRAINT `FK_Algo_Comment_TO_Algo_Comment` FOREIGN KEY (`parent_id`) REFERENCES `Algo_Comment`(`id`),
  CONSTRAINT `FK_Member_TO_Algo_Comment` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`)
);

-- 알고리즘 학습 진척도 (회원별 대분류별 진척도)
CREATE TABLE `Algo_Member_Progress` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `roadmap_id` INT NOT NULL,
  `count` INT NOT NULL,
  CONSTRAINT `FK_Member_TO_Algo_Member_Progress` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`),
  CONSTRAINT `FK_Algo_Roadmap_TO_Algo_Member_Progress` FOREIGN KEY (`roadmap_id`) REFERENCES `Algo_Roadmap`(`id`)
);