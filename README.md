# Сервис-ориентированная архитектура

## Лабораторная работа № 2

Для запуска тестов нужно задать значение переменной окружения `SPRING_PROFILES_ACTIVE` равное `test` - в этом профиле используется БД поднимаемая `testcontainers`

Для запуска в обычном режиме значение переменной окружения `SPRING_PROFILES_ACTIVE` равное `dev`

Файл `.env` должен находится в директории `resources`

Пример `.env` файла находится в `env.example`

Подключение к серверу:<br>
`ssh s33xxxx@se.ifmo.ru -p 2222`

Проброс порта для helios:<br>
`ssh -L 8080:localhost:33xxxx s33xxxx@se.ifmo.ru -p 2222`

Url подключения к БД<br>
`jdbc:postgresql://localhost:5432/studs`

Сбор jar файла с пропуском тестов<br>
`mvn package -DskipTests`

Ссылка на фронтенд - https://github.com/stoneshik/second-lab-soa-frontend

Ссылка на второй вызываемый сервис реализованный на JAX-RS - https://github.com/stoneshik/second-lab-soa-frontend

Писалось все по TDD, можно глянуть в гите (*≧ω≦*)
