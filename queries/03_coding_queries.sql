-- 👤 회원

-- 풀이 게시물 목록 조회
SELECT 
       A.ID AS POST_ID
     , A.TITLE AS POST_TITLE
     , A.CONTENT AS POST_CONTENT
     , B.ID AS MEMBER_ID
     , B.NICKNAME        
     , C.NAME AS MEMBER_RANK
     , D.TITLE AS PROBLEM_TITLE
     , A.COMMENT_COUNT  
     , A.LIKE_COUNT     
     , A.CREATED_AT    
     , A.UPDATED_AT
     , A.VISIBILITY
  FROM CODING_POST A
  JOIN MEMBER B ON A.MEMBER_ID = B.ID
  JOIN MEMBER_RANK C ON B.RANK_ID = C.ID
  JOIN CODING_PROBLEM D ON A.PROBLEM_ID = D.ID
 WHERE A.VISIBILITY = 'Y' AND PROBLEM_ID = 3
 ORDER BY A.ID DESC;

-- ------------------------------------------------------
-- 풀이 게시물 목록 조회(추천순)
 SELECT 
       A.ID AS POST_ID
     , A.TITLE AS POST_TITLE
     , A.CONTENT AS POST_CONTENT
     , B.ID AS MEMBER_ID
     , B.NICKNAME        
     , C.NAME AS MEMBER_RANK
     , D.TITLE AS PROBLEM_TITLE
     , A.COMMENT_COUNT  
     , A.LIKE_COUNT     
     , A.CREATED_AT    
     , A.UPDATED_AT
     , A.VISIBILITY
  FROM CODING_POST A
  JOIN MEMBER B ON A.MEMBER_ID = B.ID
  JOIN MEMBER_RANK C ON B.RANK_ID = C.ID
  JOIN CODING_PROBLEM D ON A.PROBLEM_ID = D.ID
 WHERE A.VISIBILITY = 'Y' AND PROBLEM_ID = 3
 ORDER BY A.LIKE_COUNT DESC;

-- --------------------------------------------------------------
-- 풀이 게시물 상세 조회
SELECT
       A.ID AS POST_ID
     , D.TITLE AS PROBLEM_TITLE
     , D.PLATFORM AS PROBLEM_PLATFORM
     , D.DIFFICULTY AS PROBLEM_DIFFICULTY
     , A.TITLE AS POST_TITLE
     , A.CONTENT AS POST_CONTENT
     , A.AI_BIG_O
     , A.AI_GOOD
     , A.AI_BAD
     , A.AI_PLAN
     , A.COMMENT_COUNT  
     , A.LIKE_COUNT
     , B.ID AS MEMBER_ID
     , B.NICKNAME       
     , C.NAME AS MEMBER_RANK
     , A.CREATED_AT    
     , A.UPDATED_AT
     , A.VISIBILITY
  FROM CODING_POST A
  JOIN MEMBER B ON A.MEMBER_ID = B.ID
  JOIN MEMBER_RANK C ON B.RANK_ID = C.ID
  JOIN CODING_PROBLEM D ON A.PROBLEM_ID = D.ID
 WHERE A.ID = 1;	   	-- #{POSTID}

-- ----------------------------------------------------------
-- 특정 게시물 댓글 목록 조회
SELECT
	     A.POST_ID
     , A.ID AS COMMENT_ID
     , A.PARENT_ID       
     , B.ID AS MEMBER_ID
     , B.NICKNAME 
     , C.NAME AS MEMBER_RANK
     , A.CONTENT       
     , A.CREATED_AT     
     , A.UPDATED_AT     
     , A.VISIBILITY 
  FROM CODING_COMMENT A
  JOIN MEMBER B ON A.MEMBER_ID = B.ID
  JOIN MEMBER_RANK C ON B.RANK_ID = C.ID
  JOIN CODING_POST D ON A.POST_ID = D.ID
 WHERE A.POST_ID = 1 AND A.VISIBILITY = 'Y'
 ORDER BY CASE WHEN A.PARENT_ID IS NULL THEN A.ID
               ELSE A.PARENT_ID END ASC,
                    A.ID ASC;

-- ---------------------------------------------------------------
-- Coding_Problem에 post 수 동기화
UPDATE Coding_Problem p
SET post_count = (
    SELECT COUNT(*)
    FROM Coding_Post c
    WHERE c.problem_id = p.id
      AND c.visibility = 'Y'
)
WHERE p.id IS NOT NULL; 

-- --------------------------------------------------------------------
-- 알고리즘 문제 목록 조회
SELECT
       A.ID AS PROBLEM_ID
     , B.ID AS MEMBER_ID
     , B.NICKNAME        
     , A.TITLE AS PROBLEM_TITLE
     , A.PLATFORM AS PLATFORM
     , A.DIFFICULTY AS DIFFICULTY
     , A.POST_COUNT AS POST_COUNT   -- DB에 저장된 값 그대로 사용
     , A.CREATED_AT AS CREATED_AT
     , A.VISIBILITY
  FROM CODING_PROBLEM A
  JOIN MEMBER B ON A.MEMBER_ID = B.ID
 WHERE A.VISIBILITY = 'Y'
 ORDER BY A.ID ASC;

-- ---------------------------------------------------------------------
-- 난이도 순(플랫폼별 정렬 후 난이도 쉬운것부터 정렬)
SELECT
       A.ID AS PROBLEM_ID
     , B.ID AS MEMBER_ID
     , B.NICKNAME
     , A.TITLE AS PROBLEM_TITLE
     , A.PLATFORM
     , A.DIFFICULTY
     , A.POST_COUNT
     , A.CREATED_AT
     , A.VISIBILITY
  FROM CODING_PROBLEM A
  JOIN MEMBER B ON A.MEMBER_ID = B.ID
 WHERE A.VISIBILITY = 'Y'
 ORDER BY
  /* 1) 플랫폼 우선순위: PROGRAMMERS < BAEKJOON < LEETCODE */
  CASE A.PLATFORM
    WHEN 'PGS' THEN 1
    WHEN 'BOJ' THEN 2
    WHEN 'ETC' THEN 3
    ELSE 9
  END,
  /* 2) 플랫폼 내 난이도 순서(세부) */
  CASE
    /* PROGRAMMERS: LV.0 < LV.1 < ... < LV.5 */
    WHEN A.PLATFORM = 'PGS' THEN
      CASE REPLACE(A.DIFFICULTY,' ','')
        WHEN 'LV.0' THEN 0
        WHEN 'LV.1' THEN 1
        WHEN 'LV.2' THEN 2
        WHEN 'LV.3' THEN 3
        WHEN 'LV.4' THEN 4
        WHEN 'LV.5' THEN 5
        ELSE 99
      END

    /* BAEKJOON: BRONZE 5 → BRONZE 1 → SILVER 5 → … → RUBY 1 */
    WHEN A.PLATFORM = 'BOJ' THEN
      CASE
        WHEN A.DIFFICULTY LIKE '브론즈%'   THEN 10 + (6 - CAST(REGEXP_SUBSTR(A.DIFFICULTY,'[0-9]+') AS UNSIGNED))
        WHEN A.DIFFICULTY LIKE '실버%'     THEN 20 + (6 - CAST(REGEXP_SUBSTR(A.DIFFICULTY,'[0-9]+') AS UNSIGNED))
        WHEN A.DIFFICULTY LIKE '골드%'     THEN 30 + (6 - CAST(REGEXP_SUBSTR(A.DIFFICULTY,'[0-9]+') AS UNSIGNED))
        WHEN A.DIFFICULTY LIKE '플래티넘%' THEN 40 + (6 - CAST(REGEXP_SUBSTR(A.DIFFICULTY,'[0-9]+') AS UNSIGNED))
        WHEN A.DIFFICULTY LIKE '다이아%'  THEN 50 + (6 - CAST(REGEXP_SUBSTR(A.DIFFICULTY,'[0-9]+') AS UNSIGNED))
        WHEN A.DIFFICULTY LIKE '루비%'     THEN 60 + (6 - CAST(REGEXP_SUBSTR(A.DIFFICULTY,'[0-9]+') AS UNSIGNED))
        ELSE 999
      END

    /* LEETCODE: EASY < MEDIUM < HARD */
    WHEN A.PLATFORM = 'ETC' THEN
      CASE A.DIFFICULTY
        WHEN 'EASY'   THEN 1
        WHEN 'MEDIUM' THEN 2
        WHEN 'HARD'   THEN 3
        ELSE 9
      END

    ELSE 999
  END,
  /* 3) 동일 우선순위 내에서 제목으로 안정화 */
  A.TITLE ASC;
-- ---------------------------------------------------------------------------------------------
-- 난이도 순(난이도 별로 가중치 두어 쉬운 것부터 정렬)
SELECT
       A.ID AS PROBLEM_ID
     , B.ID AS MEMBER_ID
     , B.NICKNAME
     , A.TITLE AS PROBLEM_TITLE
     , A.PLATFORM
     , A.DIFFICULTY
     , A.POST_COUNT
     , A.CREATED_AT
     , A.VISIBILITY
     , CASE
         /* Programmers (PGS): Lv.0 < Lv.1 < ... < Lv.5 */
         WHEN A.PLATFORM = 'PGS' THEN
           CASE REPLACE(UPPER(A.DIFFICULTY),' ','')
             WHEN 'LV.0' THEN  0
             WHEN 'LV.1' THEN  8
             WHEN 'LV.2' THEN 16
             WHEN 'LV.3' THEN 24
             WHEN 'LV.4' THEN 32
             WHEN 'LV.5' THEN 40
             ELSE 999
           END

         /* Baekjoon (BOJ): Bronze5(가장 쉬움) → Bronze1 → Silver5 → … → Ruby1(가장 어려움) */
         WHEN A.PLATFORM = 'BOJ' THEN
           CASE
             WHEN A.DIFFICULTY LIKE '브론즈%'   THEN  5 + (5 - CAST(REGEXP_SUBSTR(A.DIFFICULTY,'[0-9]+') AS UNSIGNED))
             WHEN A.DIFFICULTY LIKE '실버%'     THEN 15 + (5 - CAST(REGEXP_SUBSTR(A.DIFFICULTY,'[0-9]+') AS UNSIGNED))
             WHEN A.DIFFICULTY LIKE '골드%'     THEN 25 + (5 - CAST(REGEXP_SUBSTR(A.DIFFICULTY,'[0-9]+') AS UNSIGNED))
             WHEN A.DIFFICULTY LIKE '플래티넘%' THEN 35 + (5 - CAST(REGEXP_SUBSTR(A.DIFFICULTY,'[0-9]+') AS UNSIGNED))
             WHEN A.DIFFICULTY LIKE '다이아%'   THEN 45 + (5 - CAST(REGEXP_SUBSTR(A.DIFFICULTY,'[0-9]+') AS UNSIGNED))
             WHEN A.DIFFICULTY LIKE '루비%'     THEN 55 + (5 - CAST(REGEXP_SUBSTR(A.DIFFICULTY,'[0-9]+') AS UNSIGNED))
             ELSE 999
           END

         /* LeetCode (ETC): Easy < Medium < Hard */
         WHEN A.PLATFORM = 'ETC' THEN
           CASE UPPER(A.DIFFICULTY)
             WHEN 'EASY'   THEN 12   -- (Lv.1 ~ Lv.2 사이 정도로 가정)
             WHEN 'MEDIUM' THEN 28   -- (Lv.3 ~ Lv.4 사이)
             WHEN 'HARD'   THEN 44   -- (Lv.5 급)
             ELSE 999
           END

         ELSE 999
       END AS NORM_SCORE
  FROM CODING_PROBLEM A
  JOIN MEMBER B ON A.MEMBER_ID = B.ID
 WHERE A.VISIBILITY = 'Y'
 ORDER BY NORM_SCORE ASC, A.TITLE ASC;
-- --------------------------------------------------------
-- 알고리즘 문제 상세 조회
SELECT
       A.ID AS PROBLEM_ID
     , A.TITLE AS PROBLEM_TITLE
     , A.CONTENT AS PROBLEM_CONTENT
     , A.PLATFORM
     , A.DIFFICULTY 
     , A.INPUT          
     , A.OUTPUT          
     , A.PROBLEM_URL     
     , A.CONSTRAINTS     
     , A.POST_COUNT      
     , A.CREATED_AT
     , B.ID AS MEMBER_ID     
     , B.NICKNAME
  FROM CODING_PROBLEM A
  JOIN MEMBER B ON A.MEMBER_ID = B.ID
 WHERE A.ID = 2;

-- ------------------------------------------------
-- 🛠 관리자

-- 전체 풀이 게시물 목록 조회 (VISIBILITY 조건 무시)
SELECT 
       A.ID AS POST_ID
     , A.TITLE AS POST_TITLE
     , A.CONTENT AS POST_CONTENT
     , B.ID AS MEMBER_ID
     , B.NICKNAME        
     , C.NAME AS MEMBER_RANK
     , D.TITLE AS PROBLEM_TITLE
     , A.COMMENT_COUNT  
     , A.LIKE_COUNT     
     , A.CREATED_AT    
     , A.UPDATED_AT
     , A.VISIBILITY
  FROM CODING_POST A
  JOIN MEMBER B ON A.MEMBER_ID = B.ID
  JOIN MEMBER_RANK C ON B.RANK_ID = C.ID
  JOIN CODING_PROBLEM D ON A.PROBLEM_ID = D.ID
 WHERE PROBLEM_ID = 3
 ORDER BY A.ID DESC;

-- --------------------------------------------------
-- 특정 풀이 게시물 상세 조회
SELECT
       A.ID AS POST_ID
     , D.TITLE AS PROBLEM_TITLE
     , D.PLATFORM AS PROBLEM_PLATFORM
     , D.DIFFICULTY AS PROBLEM_DIFFICULTY
     , A.TITLE AS POST_TITLE
     , A.CONTENT AS POST_CONTENT
     , A.AI_BIG_O
     , A.AI_GOOD
     , A.AI_BAD
     , A.AI_PLAN
     , A.COMMENT_COUNT  
     , A.LIKE_COUNT
     , B.ID AS MEMBER_ID    
     , B.NICKNAME       
     , C.NAME AS MEMBER_RANK
     , A.CREATED_AT    
     , A.UPDATED_AT
     , A.VISIBILITY
  FROM CODING_POST A
  JOIN MEMBER B ON A.MEMBER_ID = B.ID
  JOIN MEMBER_RANK C ON B.RANK_ID = C.ID
  JOIN CODING_PROBLEM D ON A.PROBLEM_ID = D.ID
 WHERE A.ID = 1;	   	-- #{POSTID}

-- ------------------------------------------------------------
-- 전체 풀이 게시물 댓글 목록 조회
SELECT
	     A.POST_ID
     , A.ID AS COMMENT_ID
     , A.PARENT_ID       
     , B.ID AS MEMBER_ID
     , B.NICKNAME 
     , C.NAME AS MEMBER_RANK
     , A.CONTENT       
     , A.CREATED_AT     
     , A.UPDATED_AT     
     , A.VISIBILITY 
  FROM CODING_COMMENT A
  JOIN MEMBER B ON A.MEMBER_ID = B.ID
  JOIN MEMBER_RANK C ON B.RANK_ID = C.ID
  JOIN CODING_POST D ON A.POST_ID = D.ID
 ORDER BY CASE WHEN A.PARENT_ID IS NULL THEN A.ID
              ELSE A.PARENT_ID END ASC,
                   A.ID ASC;
                  
-- ------------------------------------------------------------
-- 해당 풀이 게시물 댓글 목록 조회
SELECT
	     A.POST_ID
     , A.ID AS COMMENT_ID
     , A.PARENT_ID       
     , B.ID AS MEMBER_ID
     , B.NICKNAME 
     , C.NAME AS MEMBER_RANK
     , A.CONTENT       
     , A.CREATED_AT     
     , A.UPDATED_AT     
     , A.VISIBILITY 
  FROM CODING_COMMENT A
  JOIN MEMBER B ON A.MEMBER_ID = B.ID
  JOIN MEMBER_RANK C ON B.RANK_ID = C.ID
  JOIN CODING_POST D ON A.POST_ID = D.ID
 WHERE A.POST_ID = 1
 ORDER BY CASE WHEN A.PARENT_ID IS NULL THEN A.ID
              ELSE A.PARENT_ID END ASC,
                   A.ID ASC;
-- ---------------------------------------------------------
-- 알고리즘 문제 목록 조회 (VISIBILITY 제외)
SELECT
       A.ID AS PROBLEM_ID
     , B.ID AS MEMBER_ID
     , B.NICKNAME        
     , A.TITLE AS PROBLEM_TITLE
     , A.PLATFORM AS PLATFORM
     , A.DIFFICULTY AS DIFFICULTY
     , A.POST_COUNT AS POST_COUNT   -- DB에 저장된 값 그대로 사용
     , A.CREATED_AT AS CREATED_AT
     , A.VISIBILITY
  FROM CODING_PROBLEM A
  JOIN MEMBER B ON A.MEMBER_ID = B.ID
 ORDER BY A.ID ASC;

-- ----------------------------------------------------------
-- 알고리즘 문제 상세 조회 (VISIBILITY 제외)
SELECT
       A.ID AS PROBLEM_ID
     , A.TITLE AS PROBLEM_TITLE
     , A.CONTENT AS PROBLEM_CONTENT
     , A.PLATFORM
     , A.DIFFICULTY 
     , A.INPUT          
     , A.OUTPUT          
     , A.PROBLEM_URL     
     , A.CONSTRAINTS     
     , A.POST_COUNT      
     , A.CREATED_AT
     , B.ID AS MEMBER_ID      
     , B.NICKNAME      
  FROM CODING_PROBLEM A
  JOIN MEMBER B ON A.MEMBER_ID = B.ID
 WHERE A.ID = 2;
