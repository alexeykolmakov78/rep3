﻿
   Система обмена cообщениями (MessagingSystem)
   --------------------------------------------
   
   Впапке Тестовый проект содержится:
   
   1. MessagingSystem - проект Eclipse
   2. messagingsystem-1.0.0-BUILD-SNAPSHOT - WAR файл для развертывания в контейнере сервлетов.
   
   Для работы приложения необходимо:
   --------------------------------- 
1. Tomcat 7.0 или выше (http://tomcat.apache.org).
2. JDK 1.6.0 или выше (http://java.sun.com).
3. Maven 3.2.1 или выше (http://maven.apache.org) для развертывания. 

   Для запуска приложения в Eclipse IDE 
   ------------------------------------
1. Импортировать проект в Eclipse
2. Необходимо отредактировать файл MessagingSystem/src/main/webapp/WEB-INF/db/jdbc.properties:
   установить значение свойства jdbc.databaseurl = jdbc:h2:Полный_путь_к_файлу/messagingsystem;IFEXISTS=TRUE;AUTO_SERVER=TRUE 
   где Полный_путь_к_файлу = полный путь к файлу messagingsystem.h2.db .
3. Запустить проект: Run > Run As > Run On Server > Выбрать сервер Tomcat v7.0 > Next > Add MessagingSystem (если нужно) > Finish.

   Для запуска приложения в контейнере сервлетов Tomcat v7.0 (без использеования IDE):
   -----------------------------------------------------------------------------------
1. Разместить файл MessagingSystem/target/messagingsystem-1.0.0-BUILD-SNAPSHOT.war
   в каталоге /Tomcat/webapps/, запустить Tomcat.
2. Перезапустить Tomcat.
3. Если Tomcat установлен на локальнои компьютере, в поисковой строке браузера набрать:
   http://localhost:8080/messagingsystem-1.0.0-BUILD-SNAPSHOT

   Для входа в систему:
   --------------------
Пользователи по умолчанию:
Никнейм: admin,  пароль: admin (администратор).
Никнейм: user,  пароль: user (участник).

   ОГРАНИЧЕНИЯ:
   ------------
 Для поля Никнейм используйте ТОЛЬКО латинский алфавит, цифры и др. символы.   
 
    В проекте использовались технологии:
    ------------------------------------   
Spring MVC, Spring Security, Hibernate,
JSP, JSTL,
база данных H2 (embeded).

   Тестирование проводилось вручную.
   
   
 
   

   