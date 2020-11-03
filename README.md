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

`gradlew clean test allureReport`

4. Для просмотра отчета по результатам тестирования выполнить команду:

`gradlew allureServe`

**Важно!**

- В пункте 3 в файле _application.properties_ **по умолчанию** указаны параметры настройки для СУБД MySQL,
для работы с СУБД PostgreSQL в данном файле необходимо указать:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/app
spring.datasource.username=app
spring.datasource.password=pass
```
- Для пользователей Docker Toolbox во всех вышеуказанных настройках и командах вместо _localhost_ необходимо указать:
`192.168.99.100`
