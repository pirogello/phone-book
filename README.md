# Телефонная книга

## Оглавление
0. [Описание](#Описание)
1. [Запуск](#Запуск)
## Описание
Backend реализован на **Spring boot**. В качестве базы данных используется **PostgreSQL**.

Frontend реализован на **Vue.js** по принципу одностраничное приложение.

Все модули запускаются в Docker.

Ответ на теоритический вопрос (после того как пользователь произвел удаление записи из таблицы – 
как можно реализовать отмену удаления?) 

     Для сущности создано специальное поле status c первоначальным значением 'ACTIVE'. 
     При удалении записи это поле изменяет свое значение на 'DELETED'. 
     При всех READ запросах из базы данных не возвращаются строки у которых значение в поле status равно 'DELETED'.
 
 Дополнительное задание (Ассинхронная обработка)
 
    По нажатию на кнопку 'Обновить все' обновляется все записи в таблице. 
    Поле для обновления - Последнее обновление (Дата последнего обновления). 
    После обновления всех записей приходит уведомление (Появляется сверху справа).
    
    На сервере в ассинхронном методе поток принудительно отправяется в спящий режим
    на 3 секунды для более наглядного применения ассинхронности.
## Запуск
Склонируйте или скачайте файлы из ветки **backend** и ветки **frontend**.

Для запуска приложения потребуется **Docker** и **Docker compose**.

1. Backend: 
    
    В главном каталоге откройте командную строку.
    
    Запустите в командной строке следующие команды: 
    ```
    mvn clean package
    docker compose up -d
    ```
   Для остановки в той же консоли введите следующую команду: 
    ```
    docker compose down
    ```
2. Frontend: 

    В главном каталоге откройте командную строку.
    
    Запустите в командной строке следующие команды: 
    ```
    docker build -t phone-book-frontend .
    docker run -it -p 8080:8080 --rm --name phone-book-frontend
    ```
