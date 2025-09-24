-- 게시물/댓글 카테고리
CREATE TABLE `Report_Category` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL
);

-- 신고 유형
CREATE TABLE `Report_Type` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `type` VARCHAR(100) NOT NULL
);

-- 신고 내역
CREATE TABLE `Report` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `reported_member_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  `type_id` INT NOT NULL,
  `target_id` INT NOT NULL COMMENT '신고 대상 엔티티 PK',
  `content` VARCHAR(500) NULL,
  `created_at` VARCHAR(20) NOT NULL,
  CONSTRAINT `FK_Member_TO_Report` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`),
  CONSTRAINT `FK_Reported_Member_TO_Report` FOREIGN KEY (`reported_member_id`) REFERENCES `Member`(`id`),
  CONSTRAINT `FK_Report_Category_TO_Report` FOREIGN KEY (`category_id`) REFERENCES `Report_Category`(`id`),
  CONSTRAINT `FK_Report_Type_TO_Report` FOREIGN KEY (`type_id`) REFERENCES `Report_Type`(`id`)
);