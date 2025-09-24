-- [관리자] 전체 신고 이력 조회
SELECT 
       A.MEMBER_ID
     , C.ID
     , C.NAME
     , B.ID
     , B.TYPE
     , A.CONTENT
     , A.TARGET_ID
     , A.CREATED_AT
 FROM REPORT A
 JOIN REPORT_TYPE B ON A.TYPE_ID = B.ID
 JOIN REPORT_CATEGORY C ON A.CATEGORY_ID = C.ID;
 
-- [관리자] 특정 회원의 신고한 이력 조회
SELECT 
       A.ID
     , A.REPORTED_MEMBER_ID
     , C.ID
     , C.NAME
     , B.ID
     , B.TYPE
     , A.CONTENT
     , A.TARGET_ID
     , A.CREATED_AT
 FROM REPORT A
 JOIN REPORT_TYPE B ON A.TYPE_ID = B.ID
 JOIN Report_Category C ON A.CATEGORY_ID = C.ID
WHERE A.MEMBER_ID = 2;

-- [관리자] 특정 회원의 신고 당한 이력 조회
SELECT 
       A.ID
     , A.MEMBER_ID
     , C.ID
     , C.NAME
     , B.ID
     , B.TYPE
     , A.CONTENT
     , A.TARGET_ID
     , A.CREATED_AT
 FROM REPORT A
 JOIN REPORT_TYPE B ON A.TYPE_ID = B.ID
 JOIN Report_Category C ON A.CATEGORY_ID = C.ID
WHERE A.REPORTED_MEMBER_ID = 1;