-- 스터디 참여자 조회
SELECT 
       sm.id 		  AS 'study_member_id'
     , mem.nickname AS 'study_member_nickname'
     , mr.name      AS 'study_member_rank'
     , sm.role      AS 'stduy_member_role'
  FROM Study_Member sm
  JOIN Member mem      ON sm.member_id = mem.id
  JOIN Member_Rank mr  ON mem.rank_id = mr.id
 WHERE sm.study_id = 1 --  #{studyId}        
ORDER BY 
    CASE WHEN sm.role = 'LEADER' 
	      THEN 0 ELSE 1 END,
    mem.nickname ASC;
    
    
-- 스터디 게시물 전체 조회
SELECT 
       sp.id             AS 'post_id'
     , mem.nickname      AS 'author_nickname'
     , sp.title          AS 'title'
     , sp.created_at     AS 'created_at'
  FROM Study_Post sp
  JOIN Study_Member sm  ON sp.member_id = sm.id
  JOIN Member mem       ON sm.member_id = mem.id
 WHERE sp.study_id = 1        -- #{studyId}
   AND sp.visibility = 'Y'
ORDER BY sp.created_at DESC;

-- 스터디 게시물 상세 조회
SELECT 
       sp.id           AS 'post_id'
     , mem.nickname    AS 'author_nickname'
     , mr.name         AS 'author_rank'
     , sp.title        AS 'title'  
     , sp.created_at   AS 'created_at'
     , sp.updated_at   AS 'updated_at'
     , sp.content      AS 'content' 
     , img.image_url   AS 'image'
  FROM Study_Post sp
  JOIN Study_Member sm ON sp.member_id = sm.id
  JOIN Member mem      ON sm.member_id = mem.id
  JOIN Member_Rank mr   ON mem.rank_id = mr.id
  LEFT JOIN Study_Post_Image img ON sp.id = img.post_id
 WHERE sp.id = 7              -- #{postId}
   AND sp.visibility = 'Y';

-- 스터디 게시물 댓글 조회
SELECT 
    sc.id              AS 'comment_id'
   , mem.nickname      AS 'author_nickname'
   , mr.name           AS 'author_rank'
   , sc.content        AS 'content'
   , sc.created_at     AS 'created_at' 
   , sc.updated_at     AS 'updated_at'
  FROM Study_Comment sc
  JOIN Study_Member sm ON sc.member_id = sm.id
  JOIN Member mem      ON sm.member_id = mem.id
  JOIN Member_Rank mr   ON mem.rank_id = mr.id
 WHERE sc.post_id = 2    -- #{postId}
   AND sc.visibility = 'Y'
ORDER BY sc.created_at ASC;


-- 게시물 로드맵 전체 조회
SELECT 
       sr.title     AS 'roadmap_title'
  FROM Study_Roadmap sr
 WHERE sr.study_id = 1   -- #{studyId}
ORDER BY sr.`order`;


-- 게시물 로드맵 상세 조회
SELECT 
       sr.title         AS 'roadmap_title'
     , sr.description   AS 'roadmap_description'
     , ms.title         AS 'milestone_title'
  FROM Study_Roadmap sr
  LEFT JOIN Study_Milestone ms ON sr.id = ms.roadmap_id
 WHERE sr.id = 1      -- #{roadmapId}
ORDER BY ms.`order`;


-- 로드맵 마일스톤 상세 조회
    SELECT 
           ms.id           AS 'milestone_id'
         , ms.title        AS 'milestone_title'
         , ms.description  AS 'milestone_description'
    FROM Study_Milestone ms
    WHERE ms.id = 2;   -- #{milestoneId}