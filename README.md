# Webservices
REST booking system

This is thr firdt pzrt of a two part project. It consosts of a soap webservice that calls out to a rest xebservice. 
The idea is to create a simple train booking system.. 
This is the REST webservice where we find most of the database requests. 


# Prerequisites 
  This has been developped using Java 17 but can be ran with Java 8 or later if the version is changed in the pom file, along with the other dependencies.
  Intellij Idea 
  PostgreSQL for the database 
  Maven 
  
# Step 1: 
Clone the project: 
git clone https://github.com/Nour0306/Webservices.git

# Step 2:
Import project as a maven project on Intellij 

# Step 3:
Go to the "Application.properties" in m=the src/main/ressources to configure the database properties
On the project you'll find my properties but once cloned you might need to change them to yours of ypur username and password are different 

spring.datasource.username=postgres
spring.datasource.password=admin

# Step 4 :
In the ressource file, you will be able to find an Init.sql file that is meant to poppulate the database to make testing easier. 
To do so, I am assuming Postgres is already installed and configured in you PATH. 
If so, you can go to a regular terminal and run this command: 
psql -h localhost -U postgres -p 5434 -f {Path}\BookingSystemSQL\init.sql

** You need to make sure that your postgres is running on he port 5434, but it most likely it is if you are running Postgres 15.
** Preferable step and highly recommended: create the database on postgres before running the script (it  needs to be named booking)

# Step 5:
Build the project using Maven with:
  mvn clean install

# Step 6 :
Run theSpring Boot app 

# Step 7: TESTING 
Using postman, we can test all of our app's endpoint. I'll be providing some exemple that you may run : 

Get ALL trains : http://localhost:8080/trains
Get One-way trains while mentionning a set of criterias (Follow the scenario):
http://localhost:8080/trains/from/Paris/to/London/outboundDate/2023-01-01 12:00:00/tickets/2/travelClass/Standard
Get round trips trains while mentionning a set of criterias (Follow the scenario):
http://localhost:8080/trains/from/Paris/to/London/outboundDate/2023-01-01 12:00:00/returnDate/2023-01-03 08:00:00/tickets/2/travelClass/Standard
Get trains with only specification the start and the destination: 
http://localhost:8080/trains/from/Paris/to/London/


Get ALL Reservations: http://localhost:8080/reservations
Book a one-way trip or a round trip (depends on what you put in the body) - outcome true or false: (POST)
http://localhost:8080/reservations/book
  Exemple of JSON body:
  {
    "travelClass": "First",
    "ticketType": "flexible",
    "numberOfTickets":7,
    "outboundTrainId": 1,
    "returnTrainId": 2
}

There are Gets and posts to all other modals. We created them assuming that at a future we could add roles like admin that would be able to add trains. 

# Step 8 : SWAGGER
Once the application is ran, swagger is accessible on: 
http://localhost:8080/swagger-restBookingSystem.html
