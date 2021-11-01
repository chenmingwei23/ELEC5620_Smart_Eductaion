# Nine yards software

Project management software

## Add Files
[All materials](https://gitlab.com/cs40-1/nine-yards-software/-/tree/main/src/main/resources/Material)


## Package structure
![Package structure1](./src/main/resources/static/img/pkg_struc1.png) ![Package structure2](./src/main/resources/static/img/pkg_struc2.png)

## Technology selection
- JDK 11

- SpringBoot 2.5.4
    - SpringMVC
    - Thymeleaf

- MySQL 8.0.21


## Getting started

1. Load Project.
2. Install MySQL 8.0.21.
3. Run schema_ddl.sql in MySQL to create database and schema.
4. Change 'spring.datasource.username' and 'spring.datasource.password' to yours in MySQL
5. Build before run (make sure you get all .jar)
    - mvn clean install -Dmaven.test.skip=true
      ![Maven build](./src/main/resources/static/img/build.png)
6. Run the main method:
   ![run](./src/main/resources/static/img/run.png)

### Commit message style
1. fix: fix bug (eg: fix: can't find user error).
2. feat: add new function (eg: feat: add userMapper)