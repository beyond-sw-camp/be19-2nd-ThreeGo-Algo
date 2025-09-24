-- 코딩 문제 (문제 원본)
CREATE TABLE `Coding_Problem` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `content` TEXT NOT NULL,
  `platform` ENUM('BOJ','PGS','ETC') NOT NULL,
  `difficulty` VARCHAR(50) NOT NULL,
  `input` TEXT NULL,
  `output` TEXT NULL,
  `problem_url` VARCHAR(255) NOT NULL,
  `constraints` TEXT NULL,
  `post_count` INT NOT NULL DEFAULT 0,
  `visibility` CHAR(1) NOT NULL DEFAULT 'Y',
  `created_at` VARCHAR(20) NOT NULL,
  CONSTRAINT `FK_Member_TO_Coding_Problem` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`)
);

-- 코딩 풀이 게시물
CREATE TABLE `Coding_Post` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `problem_id` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `content` TEXT NULL,
  `visibility` CHAR(1) NOT NULL DEFAULT 'Y',
  `ai_big_o` VARCHAR(100) NULL,
  `ai_good` VARCHAR(255) NULL,
  `ai_bad` VARCHAR(255) NULL,
  `ai_plan` VARCHAR(255) NULL,
  `comment_count` INT NOT NULL DEFAULT 0,
  `like_count` INT NOT NULL DEFAULT 0,
  `created_at` VARCHAR(20) NOT NULL,
  `updated_at` VARCHAR(20) NULL,
  CONSTRAINT `FK_Member_TO_Coding_Post` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`),
  CONSTRAINT `FK_Coding_Problem_TO_Coding_Post` FOREIGN KEY (`problem_id`) REFERENCES `Coding_Problem`(`id`)
);

-- 코딩 풀이 게시물 이미지
CREATE TABLE `Coding_Post_Image` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `post_id` INT NOT NULL,
  `image_url` VARCHAR(255) NOT NULL,
  `created_at` VARCHAR(20) NOT NULL,
  CONSTRAINT `FK_Coding_Post_TO_Coding_Post_Image` FOREIGN KEY (`post_id`) REFERENCES `Coding_Post`(`id`)
);

-- 코딩 풀이 댓글
CREATE TABLE `Coding_Comment` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id`  INT NOT NULL,
  `post_id` INT NOT NULL,
  `parent_id` INT NULL,
  `content` VARCHAR(500) NOT NULL,
  `created_at` VARCHAR(20) NOT NULL,
  `updated_at` VARCHAR(20) NULL,
  `visibility` CHAR(1) NOT NULL DEFAULT 'Y',
  CONSTRAINT `FK_Coding_Post_TO_Coding_Comment` FOREIGN KEY (`post_id`) REFERENCES `Coding_Post`(`id`),
  CONSTRAINT `FK_Coding_Comment_TO_Coding_Comment` FOREIGN KEY (`parent_id`) REFERENCES `Coding_Comment`(`id`),
  CONSTRAINT `FK_MEMBER_TO_Coding_Comment` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`)
);