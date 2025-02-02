SELECT name
FROM sys.key_constraints
WHERE type = 'PK'
  AND OBJECT_NAME(parent_object_id) = 'farming_land_statistics_per_operation_and_year';


DECLARE
@ConstraintName NVARCHAR(255);
DECLARE
@ConstraintType NVARCHAR(255);
DECLARE
@SQL NVARCHAR(MAX);

-- Cursor to iterate through all constraints
DECLARE
ConstraintCursor CURSOR FOR
SELECT name, type
FROM sys.objects
WHERE type IN ('PK', 'UQ', 'FK') -- Primary Key, Unique Constraint, Foreign Key
  AND parent_object_id = OBJECT_ID('farming_land_statistics_per_operation_and_year');

OPEN ConstraintCursor;
FETCH NEXT FROM ConstraintCursor INTO @ConstraintName, @ConstraintType;

-- Loop through all constraints
WHILE
@@FETCH_STATUS = 0
BEGIN
        SET
@SQL = 'ALTER TABLE farming_land_statistics_per_operation_and_year DROP CONSTRAINT ' + QUOTENAME(@ConstraintName);
EXEC sp_executesql @SQL;
        PRINT
'Dropped constraint: ' + @ConstraintName + ' (Type: ' + @ConstraintType + ')';

FETCH NEXT FROM ConstraintCursor INTO @ConstraintName, @ConstraintType;
END;

CLOSE ConstraintCursor;
DEALLOCATE
ConstraintCursor;