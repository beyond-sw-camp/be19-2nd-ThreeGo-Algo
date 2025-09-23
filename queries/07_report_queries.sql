-- [회원] 기업 정보 공유 게시물 목록 조회
SELECT
      A.ID
    , A.TITLE
    , A.MEMBER_ID
    , B.NICKNAME
    , A.CREATED_AT
    , A.STATUS
    , A.LIKE_COUNT
    , A.COMMENT_COUNT
 FROM CAREER_INFO_POST A
 JOIN MEMBER B
   ON A.MEMBER_ID = B.ID
WHERE A.VISIBILITY = 'Y'
ORDER BY A.ID DESC;

-- [회원] 기업별 정보 공유 게시물 상세 조회
SELECT
      A.ID
    , A.TITLE
    , A.CONTENT
    , A.MEMBER_ID
    , B.NICKNAME
    , A.CREATED_AT
    , A.VISIBILITY
    , A.STATUS         -- 서비스 단에서 내 글일 때만 노출
    , A.REJECT_REASON  -- 서비스 단에서 내 글일 때만 노출
    , A.IMAGE_URL      -- 서비스 단에서 내 글일 때만 노출
    , A.LIKE_COUNT
    , A.COMMENT_COUNT
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
               ELSE A.PARENT_ID END DESC,
          A.ID ASC;

-- [관리자] 인증 대기 상태인 게시물 목록 조회
SELECT
       A.ID
     , A.TITLE
     , A.MEMBER_ID
     , B.NICKNAME
     , A.CREATED_AT
     , A.STATUS
     , A.LIKE_COUNT
     , A.COMMENT_COUNT
  FROM CAREER_INFO_POST A
  JOIN MEMBER B
    ON A.MEMBER_ID = B.ID
 WHERE A.STATUS = 'PENDING'  -- 주석 처리하면 전체 게시물 조회
 ORDER BY A.ID ASC;

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
    , A.LIKE_COUNT
    , A.COMMENT_COUNT
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
 ORDER BY A.ID DESC;