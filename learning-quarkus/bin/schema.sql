CREATE TABLE ROOM(
  ROOM_ID BIGSERIAL PRIMARY KEY,
  NAME VARCHAR(16) NOT NULL,
  ROOM_NUMBER CHAR(2) NOT NULL UNIQUE,
  BED_INFO CHAR(2) NOT NULL
);

CREATE TABLE EMPLOYEE(
  EMPLOYEE_ID CHAR(36) PRIMARY KEY,
  FIRST_NAME VARCHAR(64),
  LAST_NAME VARCHAR(64),
  POSITION VARCHAR(64)
);
