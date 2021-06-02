-- CREATE TRIGGER trigger_after_insert_student
-- AFTER
-- INSERT ON student FOR EACH ROW BEGIN
-- INSERT INTO records
-- VALUES (NEW.id, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
-- END

CREATE TRIGGER trigger_before_delete_student
BEFORE
DELETE ON STUDENT FOR EACH ROW BEGIN
DELETE FROM records WHERE records.id=OLD.id;
END