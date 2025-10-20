-- Create the database
CREATE DATABASE erp_db;
GO

-- Use the newly created database
USE erp_db;
GO

-- Create the user
CREATE LOGIN erp_db_user WITH PASSWORD = 'secretL0calPassword';
CREATE USER erp_db_user FOR LOGIN erp_db_user;
GO

ALTER ROLE db_owner ADD MEMBER erp_db_user;
GO
