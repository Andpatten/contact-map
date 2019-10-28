## Introduction

Contact Map is an app that helps the traveling worker manage their contacts. 

## Description

Contact  Map accesses the user’s contact list and sorts the contact list based on location and sorting preference. Location can be broken down into Countries, States, Cities, Counties or distance from user. 
The app will also save the location and preferred sorting methods for each contact for future sorting. The app will be utilizing google maps to retrieve the x and y coordinates of each contact with an address. The app will also let you add places you like to visit like coffee shops and restaurants to your contact list.  

## Intended Users

People who travel a lot for work. 

#### [User stories](docs/user-stories.md) 
#### [Entity Relationship Diagram](docs/erd.md)
#### [Wireframe Diagram](docs/wireframe.md)
#### [DDL](docs/ddl.md)
### Entity Classes
#### [Filter Class](https://github.com/Andpatten/contact-map/blob/master/app/src/main/java/com/andpatten/contactmap/model/entity/Filter.java)
#### [Query Class](https://github.com/Andpatten/contact-map/blob/master/app/src/main/java/com/andpatten/contactmap/model/entity/Query.java)
#### [Sort Class](https://github.com/Andpatten/contact-map/blob/master/app/src/main/java/com/andpatten/contactmap/model/entity/Sort.java)
### Dao Classes
#### [Filter Dao](https://github.com/Andpatten/contact-map/blob/master/app/src/main/java/com/andpatten/contactmap/model/dao/FilterDao.java)
#### [Query Dao](https://github.com/Andpatten/contact-map/blob/master/app/src/main/java/com/andpatten/contactmap/model/dao/QueryDao.java)
#### [Sort Dao](https://github.com/Andpatten/contact-map/blob/master/app/src/main/java/com/andpatten/contactmap/model/dao/SortDao.java)
### Database
#### [Contact Map Database](https://github.com/Andpatten/contact-map/blob/master/app/src/main/java/com/andpatten/contactmap/service/ContactMapDatabase.java)