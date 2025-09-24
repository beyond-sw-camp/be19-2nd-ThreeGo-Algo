-- 스터디 모집 게시물 전체 목록 조회 기능

SELECT 
       A.ID AS POST_ID
  	 , B.ID AS MEMBER_ID
  	 , B.NICKNAME AS MEMBER_NICKNAME
  	 , A.TITLE AS POST_TITLE
  	 , A.START_DATE
  	 , A.END_DATE 
  	 , A.EXPIRES_AT 
  	 , A.STATUS
  	 , A.CAPACITY
  	 , COUNT(D.ID) AS PARTICIPANT_COUNT -- 참여자 수 계산
     , A.COMMENT_COUNT
     , A.CREATED_AT
     , A.UPDATED_AT
  	 , A.VISIBILITY
  	 , C.NAME AS RANK_NAME
  FROM STUDY_RECRUIT_POST A
  JOIN MEMBER B ON A.MEMBER_ID = B.ID
  JOIN MEMBER_RANK C ON B.RANK_ID = C.ID
  LEFT JOIN STUDY_RECRUIT_MEMBER D ON A.ID = D.POST_ID AND D.STATUS = 'APPROVED'
 WHERE VISIBILITY = 'Y'
 GROUP BY A.ID
 ORDER BY A.ID DESC;

-- --------------------------------------------------------------------------------------------------------
-- 스터디 모집 게시물 목록 모집 중인 글만 조회 기능

SELECT 
       A.ID AS POST_ID
     , B.ID AS MEMBER_ID
     , B.NICKNAME AS MEMBER_NICKNAME
     , A.TITLE AS POST_TITLE
     , A.START_DATE
     , A.END_DATE
     , A.EXPIRES_AT
     , A.STATUS
     , A.CAPACITY
     , COUNT(D.ID) AS PARTICIPANT_COUNT -- 참여자 수 계산
     , A.COMMENT_COUNT
     , A.CREATED_AT
     , A.UPDATED_AT
     , A.VISIBILITY
     , C.NAME AS RANK_NAME
  FROM STUDY_RECRUIT_POST A
  JOIN MEMBER B ON A.MEMBER_ID = B.ID
  JOIN MEMBER_RANK C ON B.RANK_ID = C.ID
  LEFT JOIN STUDY_RECRUIT_MEMBER D ON A.ID = D.POST_ID AND D.STATUS = 'APPROVED'
 WHERE A.STATUS = 'OPEN'
 GROUP BY A.ID
 ORDER BY A.ID DESC;

-- --------------------------------------------------------------------------------------------------------------------------------------
-- 스터디모집 게시물 상세 조회 기능

SELECT 
       A.ID AS POST_ID
     , B.ID AS MEMBER_ID
     , B.NICKNAME AS MEMBER_NICKNAME
     , A.TITLE AS POST_TITLE
     , A.CONTENT AS POST_CONTENT
     , A.START_DATE
     , A.END_DATE
     , A.EXPIRES_AT
     , A.STATUS
     , A.CAPACITY
     , COUNT(D.ID) AS PARTICIPANT_COUNT -- 참여자 수 계산
     , A.COMMENT_COUNT
     , A.CREATED_AT
     , A.UPDATED_AT
     , A.VISIBILITY
  FROM STUDY_RECRUIT_POST A
  JOIN MEMBER B ON A.MEMBER_ID = B.ID
  LEFT JOIN STUDY_RECRUIT_MEMBER C ON A.ID = C.POST_ID AND C.STATUS = 'APPROVED' -- 승인된 신청자만 세기
 WHERE A.ID = 11 -- 특정 게시물 ID로 필터링 #{postId}
 GROUP BY A.ID;

    
-- -------------------------------------------------------------------------------------------------------------------
-- 스터디댓글 조회 기능

SELECT 
       A.ID AS COMMENT_ID
     , A.PARENT_ID AS PARENT_ID
     , B.ID AS MEMBER_ID
     , B.NICKNAME AS COMMENTER_NICKNAME
     , C.NAME AS MEMBER_RANK
     , A.CONTENT
     , A.CREATED_AT
     , A.UPDATED_AT
     , A.POST_ID
     , A.VISIBILITY
  FROM STUDY_COMMENT A JOIN MEMBER B ON A.MEMBER_ID = B.ID
  JOIN MEMBER_RANK C ON B.RANK_ID = C.ID 
 WHERE A.POST_ID = 1
 ORDER BY CASE WHEN A.PARENT_ID IS NULL THEN A.ID
               ELSE A.PARENT_ID END ASC,
                    A.ID ASC; 								

    
-- -----------------------------------------------------------------------------------------------------------------
-- 스터디 모집 신청 리스트 조회 기능(스터디장만 조회가능)	

SELECT 
       C.MEMBER_ID AS STUDY_LEADER
     , A.ID AS RECRUIT_MEMBER_ID
     , B.ID AS MEMBER_ID
     , B.NICKNAME AS NICKNAME
     , D.NAME AS MEMBER_RANK
     , B.EMAIL
     , C.ID AS POST_ID
     , C.TITLE AS POST_TITLE
     , A.STATUS AS RECRUIT_MEMBER_STATUS
  FROM STUDY_RECRUIT_MEMBER A
  JOIN MEMBER B ON A.MEMBER_ID = B.ID
  JOIN STUDY_RECRUIT_POST C ON A.POST_ID = C.ID
  JOIN MEMBER_RANK D ON B.RANK_ID = D.ID
 WHERE C.ID = 1 AND C.MEMBER_ID		-- 해당 게시물의 작성자만(스터디장만) 조회 가능
 ORDER BY C.START_DATE ASC;
 
