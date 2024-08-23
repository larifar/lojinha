SELECT k.CONSTRAINT_NAME
FROM information_schema.KEY_COLUMN_USAGE k
         JOIN information_schema.TABLE_CONSTRAINTS c
              ON k.CONSTRAINT_NAME = c.CONSTRAINT_NAME
                  AND k.TABLE_NAME = c.TABLE_NAME
WHERE k.TABLE_NAME = 'users_access'
  AND k.COLUMN_NAME = 'access_id'
  AND c.CONSTRAINT_TYPE = 'UNIQUE'
  AND k.CONSTRAINT_NAME != 'unique_user_access';

ALTER TABLE users_access DROP FOREIGN KEY access_fk;
ALTER TABLE users_access DROP INDEX UK334qtcbwdr3q9ah8ocu75pdxn;
ALTER TABLE users_access ADD CONSTRAINT access_fk FOREIGN KEY (access_id) REFERENCES access(id);
