# Simple ORM Project

#### _A light weight ORM for simplifying database calls using Java mavin projects_
#### By _**John Blalock**_

## Description

_This project uses Java Reflection to add functionality to a Java project similar to Hibernate by abstracting database the process of writing SQL calls.  Currently works with PostgreSQL.  _

## Technologies Used

* _Java 8_
* _Mavin_
* _Java Reflect_
* _Log4j_
* _PostgreSQL_

## Setup/Installation

_To use this ORM in an existing repo follow the steps bellow in your project:_

* _Download this repository and install in your terminal_
  *  `git clone git@github.com:simpledimplejohn/simple_orm_p1.git`
  * Navigate to the root folder
  * Run:   `mvn install`

* _Create an application.properties file in /src/main/resources for database information_

* _This file should have these fields:_
```
url=jdbc:postgresql://localhost:5432/postgres?currentSchema=example_database
username=example_user_name
password=example_pass_word
```

* _Your project should include the following dependency in the pom.xml file_
```
		<dependency>
			<groupId>com.revature</groupId>
			<artifactId>SimpleORM</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
```
* _To add a Class to the the database use the following fields use the following annotations_

  * @Table(tableName="your_table_name") place this at the top of your class
  * @Column(columName="your_column_name) place this above each field you would like to include.

* _To add a this table to the database do the following in your Main Method:_
  * `MakeDatabaseCalls DB = new MakeDatabaseCalls();`
  * `DB.createSqlString(ExampleClass.class)`

* _An example repo can be found here showing the implementation: [SimpleORM_Impl](https://github.com/simpledimplejohn/simple_orm_impl)_

## Limitations
_This project is in process and has the following limitations_

* Can only add a table to the Database does not include CRUD function yet.
* Can only add three types of fields:
  * Integers
  * Strings
  * Booleans 

## Known Bugs

## Future Changes

## License

_[MIT](https://opensource.org/licenses/MIT)_

_Copyright (c) 2022 John Blalock_

## Contact Information

_simpledimplejohn@gmail.com_
