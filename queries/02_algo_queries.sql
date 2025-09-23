-- [회원] 로드맵 대분류 전체 목록 조회
SELECT
       A.ID
	 , A.TITLE
     , A.DESCRIPTION
     , A.`ORDER`
     , A.CREATED_AT
     , A.UPDATED_AT
     , A.QUESTION_COUNT
     , IFNULL(B.COUNT, 0) AS SOLVED_COUNT
  FROM ALGO_ROADMAP A
  LEFT JOIN ALGO_MEMBER_PROGRESS B
    ON B.ROADMAP_ID = A.ID
   AND B.MEMBER_ID = 1
 ORDER BY A.`ORDER`;

-- [회원] 특정 로드맵 대분류의 게시글 전체 목록 조회
SELECT
       A.ID
     , A.MEMBER_ID
     , A.ROADMAP_ID
     , A.TITLE
     , A.CREATED_AT
     , A.UPDATED_AT
     , A.LIKE_COUNT
     , A.COMMENT_COUNT
	 , EXISTS (SELECT 1
                 FROM LIKES B
                WHERE B.ALGO_POST_ID = A.ID
                  AND B.MEMBER_ID = 1
              ) AS IS_LIKED                               -- 해당 회원이 좋아요를 눌렀는지 여부
     , (SELECT COUNT(C.ID)
          FROM ALGO_QUIZ_QUESTION C
         WHERE C.ALGO_POST_ID = A.ID) AS QUESTION_COUNT   -- 각 게시물의 총 문제 개수  
  FROM ALGO_POST A
 WHERE A.VISIBILITY = 'Y' 
   AND A.ROADMAP_ID = 2
 ORDER BY A.ID;

 -- [회원] 특정 회원이 맞힌 문제의 게시물 ID 및 문제 인덱스 조회
SELECT 
       B.ALGO_POST_ID
     , A.ALGO_QUIZ_QUESTION_ID
  FROM MEMBER_ALGO_CORRECT_QUIZ_HISTORY A
  LEFT JOIN ALGO_QUIZ_QUESTION B ON B.ID = A.ALGO_QUIZ_QUESTION_ID
 WHERE A.MEMBER_ID = 2
 ORDER BY B.ALGO_POST_ID; 

-- [회원] 알고리즘 학습 게시물 상세 조회
SELECT
       A.ID
     , A.MEMBER_ID
     , A.TITLE
     , A.CONTENT
     , A.CREATED_AT
     , A.UPDATED_AT
     , A.COMMENT_COUNT
     , A.LIKE_COUNT
     , C.TITLE AS ROADMAP_TITLE
	 , EXISTS (SELECT 1
                 FROM LIKES B
                WHERE B.ALGO_POST_ID = A.ID
                  AND B.MEMBER_ID = 1
              ) AS IS_LIKED                       -- 해당 회원이 좋아요를 눌렀는지 여부
  FROM ALGO_POST A
  LEFT JOIN ALGO_ROADMAP C ON A.ROADMAP_ID = C.ID
 WHERE A.VISIBILITY = 'Y' 
   AND A.ID = 1;

-- [공용] 특정 학습 게시물의 문제 및 보기 목록 조회
SELECT
       A.ID AS QUESTION_ID
     , A.QUESTION
     , A.TYPE
     , B.ID AS OPTION_ID
     , B.OPTION_TEXT
     , B.IS_CORRECT
  FROM ALGO_QUIZ_QUESTION A 
  LEFT JOIN ALGO_QUIZ_OPTION B ON A.ID = B.QUESTION_ID
 WHERE A.ALGO_POST_ID = 1
 ORDER BY A.ID;

-- [공용] 알고리즘 학습 게시물 이미지 조회
SELECT 
       A.IMAGE_URL
  FROM ALGO_POST_IMAGE A
 WHERE A.POST_ID = 1
 ORDER BY A.ID;

-- [공용] 알고리즘 학습 게시물 댓글 조회
SELECT 
	   A.ID AS COMMENT_ID
     , A.PARENT_ID AS PARENT_COMMENT_ID
     , A.MEMBER_ID
     , B.NICKNAME
     , C.NAME AS MEMBER_RANK
     , A.CONTENT
     , A.CREATED_AT
     , A.UPDATED_AT
     , A.VISIBILITY
  FROM ALGO_COMMENT A
  LEFT JOIN MEMBER B ON A.MEMBER_ID = B.ID
  LEFT JOIN MEMBER_RANK C ON B.RANK_ID = C.ID
 WHERE A.POST_ID = 1
 ORDER BY CASE WHEN A.PARENT_ID IS NULL THEN 0 ELSE 1 END, A.ID;

-- [관리자] 로드맵 대분류 전체 목록 조회
SELECT
       A.ID
	 , A.TITLE
     , A.DESCRIPTION
     , A.`ORDER`
     , A.CREATED_AT
     , A.UPDATED_AT
     , A.QUESTION_COUNT
  FROM ALGO_ROADMAP A
 ORDER BY A.`ORDER`;

-- [관리자] 특정 로드맵 대분류의 게시글 전체 목록 조회
SELECT
       A.ID
     , A.MEMBER_ID
     , B.NICKNAME
     , A.TITLE
     , A.CREATED_AT
     , A.UPDATED_AT
     , A.LIKE_COUNT
     , A.COMMENT_COUNT
  FROM ALGO_POST A
  LEFT JOIN MEMBER B ON A.MEMBER_ID = B.ID
 WHERE A.ROADMAP_ID = 1
 ORDER BY A.ID;

-- [관리자] 알고리즘 학습 게시물 상세 조회
SELECT
       A.ID
     , A.MEMBER_ID
     , B.NICKNAME
     , A.TITLE
     , A.CONTENT
     , A.CREATED_AT
     , A.UPDATED_AT
     , A.COMMENT_COUNT
     , A.LIKE_COUNT
     , C.TITLE AS ROADMAP_TITLE
  FROM ALGO_POST A
  LEFT JOIN MEMBER B ON A.MEMBER_ID = B.ID
  LEFT JOIN ALGO_ROADMAP C ON A.ROADMAP_ID = C.ID
 WHERE A.ID = 1;