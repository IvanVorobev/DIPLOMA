### Документация

**Процедура настройки, установки и запуска ПО и авто-тестов**

1. Для запуска СУБД (MySQL и PostgreSQL) и симулятора банковских сервисов gate-simulator выполнить команду:

`docker-compose up`

2. Для запуска SUT выполнить команду:

- для MySQL: 

`java -Dspring.datasource.url=jdbc:mysql://localhost/app -jar artifacts/aqa-shop.jar`

- для PostgreSQL:

`java -Dspring.datasource.url=jdbc:postgresql://localhost/app -jar artifacts/aqa-shop.jar`

3. Для запуска авто-тестов выполнить команду:

- для MySQL: 

`gradlew clean test allureReport -Durl=jdbc:mysql://localhost:3306/app`

- для PostgreSQL:

`gradlew clean test allureReport -Durl=jdbc:postgresql://localhost:5432/app`


4. Для просмотра отчета по результатам тестирования выполнить команду:

`gradlew allureServe`

5. Для завершения работы allureServe выполнить команду:

`Ctrl + С` далее выбрать `Y`

6. Для завершения работы контейнеров выполнить команду:

`Ctrl + С` далее `docker-compose down`


**Важно!**

Для пользователей Docker Toolbox во всех вышеуказанных настройках и командах вместо _localhost_ необходимо указать:
`192.168.99.100`