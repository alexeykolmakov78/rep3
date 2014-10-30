
  
  CREATE TABLE MESSAGES
  (
     id INT PRIMARY KEY AUTO_INCREMENT,
     theme VARCHAR(30),
     text BLOB,
     sender VARCHAR(30),
     receiver VARCHAR(30)
  );
  
  CREATE TABLE USERS
  (
     id INT PRIMARY KEY AUTO_INCREMENT,
     firstname VARCHAR(30),
     lastname VARCHAR(30),
     nickname VARCHAR(30),
     password VARCHAR(30),
     role VARCHAR(30)
  );
  
 CREATE TABLE USERS_MESSAGES
  (
     MESSAGES_ID INT,
     USERS_ID INT
  );
  
  Это второе тестовое сообщение. Оно отправлено участнику Петру Петрову, никнейм user участником Иваном Ивановым, позывной admin. Оно не удалялось и присутствует у обоих.