# GitHub API Crawler

GitHub API Crawler is REST End point which can scan a user profile and provide followers list.

# Tech Stack Details

* REST Services - JAX-RS (Jersey)
* Bootstrap, packaging , Monitoring - Dropwizard
* Package Http Server - Eclipse Jetty (Embedded by dropwizard)

# Running the Application

* To package the application run 

mvn package

* To the run the inventory service, run below command

  java -jar target/GitCrawler-0.0.1-SNAPSHOT.jar server config.yml server config.yml
  

* Server monitoring can be done through following URL:

http://localhost:8080/admin

* REST End Point Details: 

http://localhost:8081/api/users/abc/followers?level=3

* Note: For a given github user, Crawler fetches details of each follower(not just followers list). Hence response time is noticed to be on higher side. This is done as requirement is ambiguous  and doesn't mention what details of followers are required.
