INSERT INTO Likes (member_id, algo_post_id, coding_post_id, career_info_post_id) 
VALUES
(1, 3, NULL, NULL),
(1, NULL, 12, NULL),
(1, NULL, NULL, 7),
(2, NULL, NULL, 5),
(2, 7, NULL, NULL),
(2, NULL, 20, NULL),
(3, 1, NULL, NULL),
(3, NULL, 4, NULL),
(3, NULL, NULL, 15),
(4, NULL, NULL, 10),
(4, 12, NULL, NULL),
(4, NULL, 18, NULL),
(6, NULL, 1, NULL),
(6, NULL, NULL, 2),
(6, 8, NULL, NULL),
(7, 5, NULL, NULL),
(7, NULL, 3, NULL),
(7, NULL, NULL, 22),
(9, NULL, NULL, 8),
(9, 2, NULL, NULL),
(9, NULL, 25, NULL),
(10, NULL, 5, NULL),
(10, NULL, NULL, 1),
(10, 9, NULL, NULL),
(12, 8, NULL, NULL),
(12, NULL, NULL, 12),
(12, NULL, 27, NULL),
(14, NULL, 6, NULL),
(14, 2, NULL, NULL),
(14, NULL, NULL, 14),
(16, NULL, NULL, 7),
(16, 3, NULL, NULL),
(17, NULL, 4, NULL),
(17, NULL, NULL, 9),
(17, 15, NULL, NULL),
(19, 1, NULL, NULL),
(19, NULL, 3, NULL),
(19, NULL, NULL, 11),
(21, NULL, NULL, 11),
(21, 7, NULL, NULL),
(23, NULL, 2, NULL),
(23, NULL, NULL, 15),
(26, 5, NULL, NULL),
(26, NULL, NULL, 20),
(26, NULL, 30, NULL),
(27, NULL, 1, NULL);

-- Career_Info_Post 좋아요 반영
UPDATE Career_Info_Post p
SET like_count = (
    SELECT COUNT(*)
    FROM Likes l
    WHERE l.career_info_post_id = p.id
)
WHERE p.id IS NOT NULL;

-- Algo_Post 좋아요 반영
UPDATE Algo_Post a
SET like_count = (
    SELECT COUNT(*)
    FROM Likes l
    WHERE l.algo_post_id = a.id
)
WHERE a.id IS NOT NULL;

-- Coding_Post 좋아요 반영
UPDATE Coding_Post c
SET like_count = (
    SELECT COUNT(*)
    FROM Likes l
    WHERE l.coding_post_id = c.id
)
WHERE c.id IS NOT NULL;