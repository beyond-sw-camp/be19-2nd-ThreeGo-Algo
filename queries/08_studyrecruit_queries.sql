-- 스터디 모집 게시물 전체 목록 조회 기능

SELECT 
    srp.id AS post_id
  , m.id AS member_id
  , m.nickname AS member_nickname 
  , srp.title AS post_title
  , srp.start_date AS start_date
  , srp.end_date AS end_date
  , srp.expires_at AS expires_at
  , srp.status AS status
  , srp.capacity AS capacity
  , CONCAT(srp.capacity, '/', COUNT(srm.id)) AS 'people_recruited /participant_count' -- 모집인원/참여인원 형식
  , srp.created_at AS created_at
  , srp.updated_at AS updated_at
  , srp.visibility AS visibility
  , mr.name AS rank_name
	FROM Study_Recruit_Post srp
	JOIN Member m ON srp.member_id = m.id
	JOIN Member_Rank mr ON m.rank_id = mr.id
	LEFT JOIN Study_Recruit_Member srm ON srp.id = srm.post_id AND srm.status = 'APPROVED'
	GROUP BY 
    srp.id, m.nickname, srp.title, srp.start_date, srp.end_date, srp.expires_at, 
    srp.status, srp.capacity, srp.created_at, srp.updated_at, srp.visibility, mr.name
	ORDER BY srp.id DESC;

-- --------------------------------------------------------------------------------------------------------
-- 스터디 모집 게시물 목록 모집 중인 글만 조회 기능

SELECT 
    srp.id AS post_id
  , m.id AS member_id
  , m.nickname AS member_nickname 
  , srp.title AS post_title
  , srp.start_date AS start_date
  , srp.end_date AS end_date
  , srp.expires_at AS expires_at
  , srp.status AS status
  , srp.capacity AS capacity
  , CONCAT(srp.capacity, '/', COUNT(srm.id)) AS 'people_recruited /participant_count' -- 모집인원/참여인원 형식
  , srp.created_at AS created_at
  , srp.updated_at AS updated_at
  , srp.visibility AS visibility
  , mr.name AS rank_name
	FROM Study_Recruit_Post srp
	JOIN Member m ON srp.member_id = m.id
	JOIN Member_Rank mr ON m.rank_id = mr.id
	LEFT JOIN Study_Recruit_Member srm ON srp.id = srm.post_id AND srm.status = 'APPROVED'
	WHERE srp.status = 'OPEN'
	GROUP BY 
    srp.id, m.nickname, srp.title, srp.start_date, srp.end_date, srp.expires_at, 
    srp.status, srp.capacity, srp.created_at, srp.updated_at, srp.visibility, mr.name
	ORDER BY srp.id DESC;

-- --------------------------------------------------------------------------------------------------------------------------------------
-- 스터디모집 게시물 상세 조회 기능

SELECT 
    srp.id AS post_id
  , m.id AS member_id
  , m.nickname AS writer
  , srp.title AS post_title
  , srp.content AS post_content
  , srp.start_date AS start_date
  , srp.end_date AS end_date
  , srp.expires_at AS expires_at
  , srp.status AS status
  , srp.capacity AS people_recruited
  , COUNT(srm.id) AS participant_count -- 참여자 수 계산
  , CONCAT(srp.capacity, '/', COUNT(srm.id)) AS `people_recruited / participant_count` -- 모집인원/참여인원 형식
  , srp.created_at 
  , srp.updated_at 
  , srp.visibility
FROM 
    Study_Recruit_Post srp
JOIN 
    Member m ON srp.member_id = m.id
LEFT JOIN 
    Study_Recruit_Member srm ON srp.id = srm.post_id AND srm.status = 'APPROVED' -- 승인된 신청자만 세기
WHERE 
    srp.id = 11 -- 특정 게시물 ID로 필터링 #{postId}
GROUP BY 
    srp.id, m.nickname, srp.title, srp.content, srp.start_date, srp.end_date, 
    srp.expires_at, srp.status, srp.capacity, srp.created_at, srp.updated_at, srp.visibility;

    
-- -------------------------------------------------------------------------------------------------------------------
-- 스터디댓글 조회 기능

SELECT 
	 scm.id AS comment_id
  , scm.parent_id AS parent_id
  , m.id AS member_id
  , m.nickname AS commenter_nickname
  , mr.name AS member_rank
  , scm.content AS comment_content
  , scm.created_at AS created_at
  , scm.updated_at AS updated_at
  , scm.post_id AS post_id
  , scm.visibility AS comment_visibility
	 FROM Study_Comment scm
	 JOIN Member m ON scm.member_id = m.id
	 JOIN Member_Rank mr ON m.rank_id = mr.id  -- 회원 등급 테이블 조인
	 WHERE scm.post_id = 10 -- 특정 스터디 모집글 ID로 필터링 #{postId}
	 ORDER BY scm.created_at ASC;
    
-- -----------------------------------------------------------------------------------------------------------------
-- 스터디 모집 신청 리스트 조회 기능

SELECT 
	 srm.id AS recruit_member_id
  , m.id AS member_id
  , srp.id AS post_id
  , m.nickname AS nickname
  , mr.name AS member_rank     
  , m.email AS member_email          
  , srp.title AS post_title                                
  , srm.status AS recruit_member_status
FROM 
    Study_Recruit_Member srm
JOIN 
    Member m ON srm.member_id = m.id
JOIN  Study_Recruit_Post srp ON srm.post_id = srp.id
JOIN Member_Rank mr ON m.rank_id = mr.id
    WHERE post_id = 1
ORDER BY 
    srp.start_date ASC;
