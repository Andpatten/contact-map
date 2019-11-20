## Introduction

Contact Map is an app that helps the frequent traveler manage their contacts. 

## Description

Contact Map accesses the user’s contact list and sort the contact list based on location, sorting preferences, and filter preferences. Location can be broken down into states or proximity. 
The app will also save the preferred sorting and filtering methods for each contact for future use. The app will be utilizing google maps to retrieve the x and y coordinates of each contact with an address.  

## Intended Users

Content developers who travel and need help keeping track of past and future employers from a given area. 
Comedians who want to conveniently see their past friends/co-comedians from a particular show. 
Salespeople who want help keeping track of past companies they have pitched to.

## User Instructions

After opening the app choose to allow the app access to your contacts and location when prompted. 
You can then search your contacts and the app will sort your contacts based on proximity. 
The seek bar allows you to set a maximum distance up to 100 miles. 
You can change your contacts by clicking on them. 
The app will soon save your previous queries for next time. 


#### [User stories](docs/user-stories.md) 
#### [Entity Relationship Diagram](docs/erd.md)
#### [Wireframe Diagram](docs/wireframe.md)
#### [DDL](docs/ddl.md)
#### [License Info](docs/license-info.md)
#### [Current State](docs/current-state.md)
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
### Activity
#### [MainActivity](https://github.com/Andpatten/contact-map/blob/master/app/src/main/java/com/andpatten/contactmap/controller/MainActivity.java)
### Services
#### [GeoService](https://github.com/Andpatten/contact-map/blob/master/app/src/main/java/com/andpatten/contactmap/service/GeoService.java)
#### [ContactService](https://github.com/Andpatten/contact-map/blob/master/app/src/main/java/com/andpatten/contactmap/service/ContactService.java)
### View Model
#### [MainViewModel](https://github.com/Andpatten/contact-map/blob/master/app/src/main/java/com/andpatten/contactmap/viewmodel/MainViewModel.java)
### Pojos
#### [Contact](https://github.com/Andpatten/contact-map/blob/master/app/src/main/java/com/andpatten/contactmap/viewmodel/MainViewModel.java)
#### [Coordinates](https://github.com/Andpatten/contact-map/blob/master/app/src/main/java/com/andpatten/contactmap/model/pojo/Coordinates.java)
#### [CoordinatesResponse](https://github.com/Andpatten/contact-map/blob/master/app/src/main/java/com/andpatten/contactmap/model/pojo/CoordinatesResponse.java)