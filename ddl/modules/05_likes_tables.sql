-- 추천 (공통: 알고리즘/코딩/커리어 게시물에 사용)
CREATE TABLE `Likes` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `algo_post_id` INT NULL,
  `coding_post_id` INT NULL,
  `career_info_post_id` INT NULL,
  CONSTRAINT `FK_Member_TO_Likes` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`),
  CONSTRAINT `FK_Algo_Post_TO_Likes` FOREIGN KEY (`algo_post_id`) REFERENCES `Algo_Post`(`id`),
  CONSTRAINT `FK_Coding_Post_TO_Likes` FOREIGN KEY (`coding_post_id`) REFERENCES `Coding_Post`(`id`),
  CONSTRAINT `FK_Career_Info_Post_TO_Likes` FOREIGN KEY (`career_info_post_id`) REFERENCES `Career_Info_Post`(`id`)
);
