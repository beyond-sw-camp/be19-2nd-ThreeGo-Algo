-- 기업별 정보 공유 게시물
CREATE TABLE `Career_Info_Post` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `status` ENUM('NONE','PENDING','APPROVED','REJECTED') NOT NULL DEFAULT 'NONE',
  `reject_reason` VARCHAR(255) NULL,
  `visibility` CHAR(1) NOT NULL DEFAULT 'Y',
  `content` TEXT NULL,
  `image_url` VARCHAR(255) NULL,
  `comment_count` INT NOT NULL DEFAULT 0,
  `like_count` INT NOT NULL DEFAULT 0,
  `created_at` VARCHAR(20) NOT NULL,
  CONSTRAINT `FK_Member_TO_Career_Info_Post` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`)
);

-- 기업별 정보 공유 게시물 댓글
CREATE TABLE `Career_Info_Comment` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `parent_id` INT NULL,
  `post_id` INT NOT NULL,
  `member_id` INT NOT NULL,
  `content` VARCHAR(500) NOT NULL,
  `visibility` CHAR(1) NOT NULL DEFAULT 'Y',
  `created_at` VARCHAR(20) NOT NULL,
  `updated_at` VARCHAR(20) NULL,
  CONSTRAINT `FK_Career_Info_Comment_TO_Career_Info_Comment` FOREIGN KEY (`parent_id`) REFERENCES `Career_Info_Comment`(`id`),
  CONSTRAINT `FK_Career_Info_Post_TO_Career_Info_Comment` FOREIGN KEY (`post_id`) REFERENCES `Career_Info_Post`(`id`),
  CONSTRAINT `FK_Member_TO_Career_Info_Comment` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`)
);