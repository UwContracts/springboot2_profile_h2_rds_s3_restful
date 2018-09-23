# springboot2_profile_h2_rds_restful
Demo of Springboot2, H2, RDS, JPA, ORM, Thymeleaf, and RESTful API

This is a standard Springboot application, please run the Application.java as a normal Java Application and an embedded Tomcat server will be running at port 5000.
1. mvn clean package -Plocal
2. cd aircraft-queue-web
2. mvn spring-boot:run -Plocal
Once it is up and running, please use below URLs for test run,

UI end points:
http://localhost:5000/boot<br>
This API will populate sample aircraft into table and return current aircraft enqueue list.

http://localhost:5000/enqueue?name=test&type=VIP&size=small<br>
This API will enqueue an aircraft with provided type and size parameters, both are required parameters.

http://localhost:5000/dequeue<br>
This API will dequeue an aircraft, and return current aircraft dequeue list.

API end points:
http://localhost:5000/api/boot<br>
This API will populate sample aircraft into table and return current aircraft enqueue list.

http://localhost:5000/api/enqueuedAircraftList<br>
This API will return current aircraft enqueue list.

http://localhost:5000/api/enqueue?name=test&type=VIP&size=SMALL<br>
This API will enqueue an aircraft with provided type and size parameters, both are required parameters.

http://localhost:5000/api/dequeue<br>
This API will dequeue an aircraft.

http://localhost:5000/api/dequeuedAircraftList<br>
This API will return current aircraft dequeue list.

Please see detail implementation of dequeuing logic in DequeueAircraftServiceImpl.java, the basic steps are,

1. Find the first enqueued aircraft based on its type, size and time.

2. Convert enqueued aircraft to dequeued aircraft and save into dequeued AC table, only 1 thread will success as PK is unique.

3. Remove the enqueued AC so that it won't be picked again.

4. In case concurrent conflict occurred, pause to let another thread to finish.

5. Repeat above steps to dequeue an AC until configurated threshold is reached.
 
