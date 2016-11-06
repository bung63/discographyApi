# discographyApi

# a simple rest API for a discography application

## I have set this up on a Glassfish server which has a problem with "ClassNotFoundException: com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector" on the first call after deploy after that it works fine 

# A few CRUD example below

# Create 
## curl -X POST -d '{"name":"New Artist","albums":[]}' http://localhost:8080/discography-rest/rest/artist/ -H "Content-Type:application/json"
## curl -X POST -d '{"name":"New Artist 2","albums":[{"title":"Album 1","release":"2001","tracks":[{"track":"New Track 1"}]}]}' http://localhost:8080/discography-rest/rest/artist/ -H "Content-Type:application/json"

# Read
## curl http://localhost:8080/discography-rest/rest/artist/				Get All
## curl http://localhost:8080/discography-rest/rest/artist/1				Get Artist by id
## curl http://localhost:8080/discography-rest/rest/artist/name/Queen		Get Artist by name

# Update
## curl -X PUT -d '{"id":"1","name":"New Name","albums":[]}' http://localhost:8080/discography-rest/rest/artist/ -H "Content-Type:application/json"

# Delete
## curl -X DELETE http://localhost:8080/discography-rest/rest/artist/1  		Delete Artist by id
## curl -X DELETE http://localhost:8080/discography-rest/rest/artist/name/Queen  Delete Artist by name
