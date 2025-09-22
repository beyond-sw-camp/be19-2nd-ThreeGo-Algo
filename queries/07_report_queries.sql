-- [회원] 기업 정보 공유 게시물 목록 조회
SELECT
      A.ID                    AS postId
    , A.TITLE                 AS title
    , A.MEMBER_ID             AS writerId
    , B.NICKNAME              AS writerNickname
    , A.CREATED_AT            AS createdAt
    , A.STATUS                AS postStatus
    , COUNT(DISTINCT C.ID)    AS likeCount
    , COUNT(DISTINCT D.ID)    AS commentCount
 FROM CAREER_INFO_POST A
 JOIN MEMBER B
   ON A.MEMBER_ID = B.ID
 LEFT JOIN LIKES C
   ON C.CAREER_INFO_POST_ID = A.ID
 LEFT JOIN CAREER_INFO_COMMENT D
   ON D.POST_ID = A.ID
WHERE A.VISIBILITY = 'Y'
GROUP BY A.ID, A.TITLE, A.MEMBER_ID, B.NICKNAME, A.CREATED_AT, A.STATUS
ORDER BY A.CREATED_AT DESC;

-- [회원] 기업별 정보 공유 게시물 상세 조회
SELECT
      A.ID                    AS postId
    , A.TITLE                 AS title
    , A.CONTENT               AS content
    , A.MEMBER_ID             AS writerId
    , B.NICKNAME              AS writerNickname
    , A.CREATED_AT            AS createdAt
    , A.VISIBILITY            AS visibility
    , A.STATUS                AS postStatus   -- 서비스 단에서 내 글일 때만 노출
    , A.REJECT_REASON         AS rejectReason -- 서비스 단에서 내 글일 때만 노출
    , A.IMAGE_URL             AS imageUrl     -- 서비스 단에서 내 글일 때만 노출
    , (SELECT COUNT(*)
         FROM LIKES C
        WHERE C.CAREER_INFO_POST_ID = A.ID) 
                              AS likeCount
    , (SELECT COUNT(*)
         FROM CAREER_INFO_COMMENT D
        WHERE D.POST_ID = A.ID) 
                              AS commentCount
 FROM CAREER_INFO_POST A
 JOIN MEMBER B
   ON A.MEMBER_ID = B.ID
WHERE A.VISIBILITY = 'Y'
  AND A.ID = 1;

-- [회원] 한 게시믈에 댓글
SELECT
       A.ID
     , A.PARENT_ID
     , A.POST_ID
     , A.MEMBER_ID
     , B.NICKNAME
     , A.CONTENT
     , A.CREATED_AT
     , A.UPDATED_AT
     , A.VISIBILITY
  FROM CAREER_INFO_COMMENT A
  JOIN MEMBER B
    ON A.MEMBER_ID = B.ID
 WHERE A.POST_ID = 1
   AND A.VISIBILITY = 'Y'
 ORDER BY CASE WHEN A.PARENT_ID IS NULL THEN A.ID
               ELSE A.PARENT_ID END,
          A.CREATED_AT ASC;

-- [관리자] 인증 대기 상태인 게시물 목록 조회
SELECT
       A.ID
     , A.TITLE
     , A.MEMBER_ID
     , B.NICKNAME
     , A.CREATED_AT
     , A.STATUS
  FROM CAREER_INFO_POST A
  JOIN MEMBER B
    ON A.MEMBER_ID = B.ID
 WHERE A.STATUS = 'PENDING'  -- 주석 처리하면 전체 게시물 조회
 ORDER BY A.CREATED_AT ASC;

-- [관리자] 특정 게시물 상세 조회 (이미지 포함)
SELECT
      A.ID
    , A.TITLE
    , A.CONTENT
    , A.IMAGE_URL
    , A.MEMBER_ID
    , B.NICKNAME
    , A.CREATED_AT
    , A.VISIBILITY
    , A.STATUS
    , A.REJECT_REASON
    , (SELECT COUNT(*)
         FROM LIKES C
        WHERE C.CAREER_INFO_POST_ID = A.ID) 
                              AS likeCount
    , (SELECT COUNT(*)
         FROM CAREER_INFO_COMMENT D
        WHERE D.POST_ID = A.ID) 
                              AS commentCount
 FROM CAREER_INFO_POST A
 JOIN MEMBER B
   ON A.MEMBER_ID = B.ID
WHERE A.ID = 5;

-- [관리자] 전체 댓글 조회
SELECT
       A.ID
     , A.PARENT_ID
     , A.POST_ID
     , A.MEMBER_ID
     , B.NICKNAME
     , A.CONTENT
     , A.CREATED_AT
     , A.UPDATED_AT
     , A.VISIBILITY
  FROM CAREER_INFO_COMMENT A
  JOIN MEMBER B
    ON A.MEMBER_ID = B.ID
 ORDER BY A.CREATED_AT ASC;