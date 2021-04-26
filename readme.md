
Hiking Trail Application readMe file
4/18/2021
John Wright

This application is a starter app that will soon document Seattle-area hiking trails 
hiked by the developer.  In its present state the application utilizes Spring Boot 
(version 2.5.0-RC1, Spring 5x), and is a Maven project.  Later the developer will 
evolve the application with a rich data model with modern Rest apis (see the 
Spring guides for details on this: https://spring.io/guides/tutorials/rest/ , read about
Spring HATEOAS applications with HAL representations of each resource).  And it will
incorporate a UI, possibly to provide an Android app as well to be developed using
JavaFX and the Gluon library.  For general assumptions, see the project’s pom.xml 
build file.  The program utilizes a H2 in-memory database.  Also, the application 
utilizes Java 8 apis.  The app supports the following Rest apis. Unit/integration 
tests are incorporated to test the apis.

Web-based starter UI: http://localhost:8080/hikingtrails

H2 in-memory database URL:  http://localhost:8080/h2


// Get (or read) methods:

http://localhost:8080/hikingtrails/getalltrails   	-- returns all hiking trails.

http://localhost:8080/hikingtrails/gettrail/1	-- returns a single trail, say record 1,

      but throws a HikingTrailNotFoundException(id) if record with [id] not found
      in the database.
      

// Post (or create) method:

http://localhost:8080/hikingtrails/newtrail 	-- saves a hiking trail with sample JSON 

      data:

{
    "id": 5,
    "name": "Middle Fork",
    "distance": 10.0,
    "difficulty": "moderate"
}

      but throws a HikingTrailFoundException(id) if record [id] currently is in the 
      database.
      

// Delete method:

http://localhost:8080/hikingtrails/deletetrail/1	-- deletes a single trail, say record 1,
	
      but throws a HikingTrailNotFoundException(id) if no record with [id] is found in 
      the database.


// Put (or update) method:

http://localhost:8080/hikingtrails/replacetrail/1 	-- replaces the data of record specified, 

 	say record 1, with sample JSON data:

{
    "id": 1,
    "name": "Snow Peak",
    "distance": 8.0,
    "difficulty": "difficult"
}

      but throws a HikingTrailNotFoundException(id) if no record with [id] is found 
      in the database.
      
      
