# MusicApp
MusicApp is a music player project developed by me for my graduation thesis. It provides users with basic functions such as searching for songs, listening to songs, creating playlists, adding songs to lists, and the most typical function of the project is searching for suitable songs based on the listener's feelings.
#### Score: 9/10


## Features
* Search for songs by title, artist, or genre.
* Listen to songs online.
* Create and manage playlists.
* Add songs to lists.
* Search for suitable songs based on the listener's feelings.


## 💻Tech Stack
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white) ![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)


## How to Use
To install and run the app, you can follow these steps:
1. Clone the repository from GitHub.
2. Open MusicServer in your IDE.
3. Immport the file mysql.sql into mysql workbench
4. Open file AppMusic/MusicServer/src/main/resources/application.properties and edit:
   * spring.datasource.url=jdbc: database url
   * spring.datasource.username= username
   * spring.datasource.password= password
5. Run the `main` method in the `MusicServerApplication.java` class.
6. The website will be hosted on your local machine at `http://localhost:8080`.
7. Open SpringMusicApp in AndroidStudio
8. Open file AppMusic/SpringMusicApp/app/src/main/java/com/example/springmusicapp/Service/APIService.java and edit:
   * private static String url="your localhost/server/";
9. Run App

