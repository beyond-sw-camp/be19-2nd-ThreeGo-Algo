-- 스터디 참여자 조회
SELECT 
       A.STUDY_ID     AS STUDY_ID   
     , A.ID           AS STUDY_MEMBER_ID
     , B.NICKNAME     AS STUDY_MEMBER_NICKNAME
     , C.`NAME`       AS STUDY_MEMBER_RANK
     , A.`ROLE`       AS STUDY_MEMBER_ROLE
  FROM STUDY_MEMBER A
  JOIN MEMBER B       ON A.MEMBER_ID = B.ID
  JOIN MEMBER_RANK C  ON B.RANK_ID   = C.ID
 WHERE A.STUDY_ID = 1 --  #{studyId}        
 ORDER BY 
  CASE WHEN A.`ROLE` = 'LEADER' 
       THEN 0 ELSE 1 END,
       B.NICKNAME ASC;
    
 -- 스터디 게시물 전체 조회
SELECT 
       A.STUDY_ID       AS STUDY_ID 
     , A.ID             AS POST_ID
     , B.MEMBER_ID      AS MEMBER_ID 
     , C.NICKNAME       AS AUTHOR_NICKNAME
     , A.TITLE          AS TITLE
     , A.COMMENT_COUNT  AS COMMENT_COUNT
     , A.CREATED_AT     AS CREATED_AT
     , A.UPDATED_AT     AS UPDATED_AT
  FROM STUDY_POST A
  JOIN STUDY_MEMBER B   ON A.MEMBER_ID = B.ID
  JOIN MEMBER C         ON B.MEMBER_ID = C.ID
 WHERE A.STUDY_ID = 1        -- #{studyId}
   AND A.VISIBILITY = 'Y'
 ORDER BY A.ID DESC;


-- 스터디 게시물 상세 조회
SELECT 
       A.ID                                    AS POST_ID
     , B.MEMBER_ID                             AS MEMBER_ID
     , C.NICKNAME                              AS AUTHOR_NICKNAME
     , D.`NAME`                                AS AUTHOR_RANK
     , A.TITLE                                 AS TITLE  
     , A.CREATED_AT                            AS CREATED_AT
     , A.UPDATED_AT                            AS UPDATED_AT
     , A.CONTENT                               AS CONTENT 
     , GROUP_CONCAT(E.IMAGE_URL SEPARATOR ',') AS IMAGES
     , A.COMMENT_COUNT                         AS COMMENT_COUNT
  FROM STUDY_POST A
  JOIN STUDY_MEMBER B                          ON A.MEMBER_ID = B.ID
  JOIN `MEMBER` C                              ON B.MEMBER_ID = C.ID
  JOIN MEMBER_RANK D                           ON C.RANK_ID   = D.ID
  LEFT JOIN STUDY_POST_IMAGE E                 ON A.ID = E.POST_ID
 WHERE A.ID = 5          -- #{postId}
   AND A.VISIBILITY = 'Y'
 GROUP BY A.ID;


-- 스터디 게시물 댓글 조회 (게시글 댓글 수 포함)
SELECT 
       A.POST_ID            AS POST_ID
     , A.ID                 AS COMMENT_ID    
     , A.PARENT_ID          AS PARENT_ID
     , C.NICKNAME           AS AUTHOR_NICKNAME
     , D.`NAME`             AS AUTHOR_RANK
     , A.CONTENT            AS CONTENT
     , A.CREATED_AT         AS CREATED_AT 
     , A.UPDATED_AT         AS UPDATED_AT
  FROM STUDY_COMMENT A
  JOIN STUDY_MEMBER B       ON A.MEMBER_ID = B.ID
  JOIN `MEMBER` C           ON B.MEMBER_ID = C.ID
  JOIN MEMBER_RANK D        ON C.RANK_ID   = D.ID
 WHERE A.POST_ID = 2    -- #{postId}
   AND A.VISIBILITY = 'Y'
 ORDER BY CASE WHEN A.PARENT_ID IS NULL THEN A.ID
               ELSE A.PARENT_ID END ASC,
                    A.ID ASC;


-- 게시물 로드맵 전체 조회
SELECT 
       A.STUDY_ID  AS STUDY_ID
     , A.ID        AS ROADMAP_ID  
     , A.TITLE     AS ROADMAP_TITLE
  FROM STUDY_ROADMAP A
 WHERE A.STUDY_ID = 1   -- #{studyId}
 ORDER BY A.`ORDER`;


-- 게시물 로드맵 상세 조회
SELECT 
       A.ID                   AS ROADMAP_ID
     , B.ID                   AS MILESTONE_ID  
     , A.TITLE                AS ROADMAP_TITLE
     , A.`DESCRIPTION`        AS ROADMAP_DESCRIPTION
     , B.TITLE                AS MILESTONE_TITLE
  FROM STUDY_ROADMAP A
  LEFT JOIN STUDY_MILESTONE B ON A.ID = B.ROADMAP_ID
 WHERE A.ID = 1      -- #{roadmapId}
 ORDER BY B.`ORDER`;


-- 로드맵 마일스톤 상세 조회
SELECT 
       A.ROADMAP_ID    AS ROADMAP_ID
     , A.ID            AS MILESTONE_ID
     , A.TITLE         AS MILESTONE_TITLE
     , A.`DESCRIPTION` AS MILESTONE_DESCRIPTION
  FROM STUDY_MILESTONE A
 WHERE A.ID = 2;   -- #{milestoneId}
