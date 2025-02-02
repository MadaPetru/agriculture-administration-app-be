DECLARE
@Email NVARCHAR(255);
    DECLARE
@UserId INT;
    -- Cursor to iterate through all users
    DECLARE
UserCursor CURSOR FOR
SELECT email, id
FROM user_entity;

OPEN UserCursor;
FETCH NEXT FROM UserCursor INTO @Email, @UserId;

-- Loop through all users
WHILE
@@FETCH_STATUS = 0
BEGIN
        -- Update fm_land table
UPDATE farming_land
SET created_by = @UserId
WHERE created_by = @Email;

UPDATE farming_land_image
SET created_by = @UserId
WHERE created_by = @Email;

UPDATE farming_land_operation_history
SET created_by = @UserId
WHERE created_by = @Email;

UPDATE farming_land_statistics_per_operation_and_year
SET created_by = @UserId
WHERE created_by = @Email;

UPDATE farming_land_statistics_per_year
SET created_by = @UserId
WHERE created_by = @Email;


FETCH NEXT FROM UserCursor INTO @Email, @UserId;
END;

CLOSE UserCursor;
DEALLOCATE
UserCursor;
