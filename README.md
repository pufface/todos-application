# Todos Web App

## Prerequisits
- JRE 8
- Node 6.10
- Npm 4.3.0

## Todos Database
HSQLDB file storage.

jdbc:hsqldb:hsql://localhost:9001/todos  
user: sa  
password: <empty>  

run database: `$gradlew run`

## Todos Service
Restfull stateless service for providing todo data. Secured with Basic authentication.

### Users
user:password  
jozko:mrkvicka

### API
GET http://localhost:8090/todos  
POST http://localhost:8090/todos  
PUT http://localhost:8090/todos  
DELETE http://localhost:8090/todos/{id}

run server: `$gradlew bootRun`  
create fat jar: `$gradlew bootRepackage`  
create eclipse project: `$gradlew eclipse`  

## Todos Frontend
Angular frontend for todos app.

http://locahost:3000/

install: `npm install`  
serve app: `npm run serve`  
run app dev: `npm start`  
to more script see package.json  
