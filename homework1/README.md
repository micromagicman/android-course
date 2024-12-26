# Домашнее задание №1

## Сборка проекта

```bash
./gradlew clean :homework1:build --refresh-dependencies
```

## Запуск

Программа принимает один аргумент - количество животных в зоопарке.
Значение по умолчанию - `20`. Максимальное количество животных - `500`.

```bash
java -jar homework1/build/libs/homework1-1.0.0-SNAPSHOT.jar 26
```