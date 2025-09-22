-- [회원] 로드맵 대분류 전체 목록 조회
SELECT
       A.id
	 , A.title
	 , A.description
	 , A.`order`
	 , A.created_at
	 , A.updated_at
	 , A.question_count
	 , IFNULL(C.count, 0) AS solved_count
	FROM Algo_Roadmap A
	LEFT JOIN (SELECT
					 B.roadmap_id
				   , B.count
				FROM Algo_Member_Progress B
			   WHERE B.member_id = 1) C ON C.roadmap_id = A.id
	ORDER BY A.`order`;

-- [회원] 특정 로드맵 대분류의 게시글 전체 목록 조회
SELECT
       A.id
     , B.nickname
     , A.content
     , A.created_at
     , A.updated_at
     , A.likes
     , D.comments
     , CASE WHEN E.id IS NOT NULL THEN TRUE ELSE FALSE END AS isLiked
  FROM Algo_Post A
  LEFT JOIN Member B ON A.member_id = B.id
  LEFT JOIN (SELECT
                    C.post_id
                  , count(C.post_id) AS comments
               FROM Algo_Comment C
			  GROUP BY C.post_id) D ON A.id = D.post_id 
  LEFT JOIN Likes E ON A.id = E.algo_post_id AND E.member_id = 1
 WHERE A.visibility = 'Y' AND A.roadmap_id = 1
 ORDER BY A.id;

-- [회원] 알고리즘 학습 게시물 상세 조회
SELECT
       A.id
     , A.title
     , A.member_id
     , B.nickname
     , C.title AS roadmap_title
     , A.content
     , A.created_at
     , A.updated_at
     , A.likes
     , CASE WHEN D.id IS NOT NULL THEN TRUE ELSE FALSE END AS isLiked
  FROM Algo_Post A
  LEFT JOIN Member B ON A.member_id = B.id
  LEFT JOIN Algo_Roadmap C ON A.roadmap_id = C.id
  LEFT JOIN Likes D ON A.id = D.algo_post_id AND D.member_id = 1
 WHERE A.visibility = 'Y' AND A.id = 1;
 
-- 학습 게시물의 문제 목록 조회
SELECT
       A.id
     , A.question
     , A.type
  FROM Algo_Quiz_Question A
 WHERE A.algo_post_id = 1;

-- 학습 게시물의 문제에 대한 보기 목록 조회
SELECT
       A.question_id
     , A.option_text
     , A.is_correct
  FROM Algo_Quiz_Option A
 WHERE A.question_id = 1
 ORDER BY A.question_id; 

-- 알고리즘 학습 게시물 이미지 조회
SELECT 
       A.image_url
  FROM Algo_Post_Image A
 WHERE A.post_id = 1
 ORDER BY A.id;

-- 알고리즘 학습 게시물 댓글 조회
SELECT 
	   A.id
     , A.parent_id
     , A.member_id
     , B.nickname
     , A.content
     , A.created_at
     , A.updated_at
     , A.visibility
  FROM Algo_Comment A
  LEFT JOIN Member B ON A.member_id = B.id
 WHERE A.post_id = 1;
 
-- [관리자] 로드맵 대분류 전체 목록 조회
SELECT
       A.id
     , A.title
     , A.description
     , A.`order`
     , A.created_at
     , A.updated_at
     , A.question_count
  FROM Algo_Roadmap A
 ORDER BY A.`order`;

-- [관리자] 특정 로드맵 대분류의 게시글 전체 목록 조회
SELECT
       A.id
     , B.nickname
     , A.content
     , A.created_at
     , A.updated_at
     , A.likes
     , D.comments
  FROM Algo_Post A
  LEFT JOIN Member B ON A.member_id = B.id
  LEFT JOIN (SELECT
                    C.post_id
                  , count(C.post_id) AS comments
               FROM Algo_Comment C
			  GROUP BY C.post_id) D ON A.id = D.post_id 
 WHERE A.roadmap_id = 1
 ORDER BY A.id;
 
-- [관리자] 알고리즘 학습 게시물 상세 조회
SELECT
       A.id
     , A.title
     , A.member_id
     , B.nickname
     , C.title AS roadmap_title
     , A.content
     , A.created_at
     , A.updated_at
     , A.likes
  FROM Algo_Post A
  LEFT JOIN Member B ON A.member_id = B.id
  LEFT JOIN Algo_Roadmap C ON A.roadmap_id = C.id
 WHERE A.id = 1;