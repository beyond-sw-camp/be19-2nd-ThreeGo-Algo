DELIMITER $$

CREATE TRIGGER trg_career_info_post_before_insert
BEFORE INSERT ON Career_Info_Post
FOR EACH ROW
BEGIN
  IF NEW.image_url IS NOT NULL THEN
    SET NEW.status = 'PENDING';
  END IF;
END$$

DELIMITER ;