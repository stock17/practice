Practice
===============
**Practice** представляет собой RESTful web-приложение для управления данными работников организаций. 
Приложение позволяет сохранять и извлекать персональные данные о работниках, офисах, где они работают, 
и организациях, которым принадлежат офисы

Взаимодействие с сервером осуществляется через API. Для обращения используется следующий формат сообщений

Организация: {id, name, fullName, inn, kpp, address, phone, isActive} URL = '/api/organization'

Офис:  {id, name, address, phone, isActive} URL = '/api/office'

Работник: {id, firstName, middleName, secondName, position, address, phone, docCode, docName, docNumber, docDate, 
citizenshipName, citizenshipCode, isIdentified} URL = '/api/user'

Запрос конкретного объекта осуществляется добавлением ID к URL ('api/office/1') метод - GET

Запрос нескольких объектов по параметрам осуществляется по относительному пути '/list' ('api/office/list') метод - GET

Сохранение объекта осуществляется по относительному пути '/save' ('api/office/save') метод - POST

Обновление объекта осуществляется по относительному пути '/update' ('api/office/update') метод - POST

### Установка
1. Создать локальный клон репозитория.
2. Собрать jar с помощью Maven, используя команду 'maven install'
3. Запустить приложение, используя команду java -jar practice.jar
### Требования
1. Java development kit не ниже 11 версии
2. Система управления базами данных H2
3. Система сборки Maven
### Автор
Проект разработан в рамках прохождения стажировки Юрием М.
### Контакты
e-mail: edem_garden@mail.ru

