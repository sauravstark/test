# Project Name README
## Project Details
Briefly describe your project here.
## Setting up the Database
To run the database, execute the following Docker command:
```
docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=your_database -e MYSQL_USER=your_user -e MYSQL_PASSWORD=your_password -p 3306:3306 -d mysql:8
```
## Populating the Database
After starting the server, populate the database with initial data using the following CURL commands:
### Games
Use the CURL commands below to create sample games in the database:
```
curl -X POST http://localhost:8080/games \
-H "Content-Type: application/json" \
-d '{
  "name": "pacman",
  "displayName": "Pac-Man",
  "description": "A classic arcade game where the player navigates Pac-Man through a maze."
}'

curl -X POST http://localhost:8080/games \
-H "Content-Type: application/json" \
-d '{
  "name": "asteroids",
  "displayName": "Asteroids",
  "description": "An arcade space shooter game."
}'

curl -X POST http://localhost:8080/games \
-H "Content-Type: application/json" \
-d '{
  "name": "tetris",
  "displayName": "Tetris",
  "description": "A tile-matching puzzle video game."
}'

curl -X POST http://localhost:8080/games \
-H "Content-Type: application/json" \
-d '{
  "name": "mariobros",
  "displayName": "Super Mario Bros",
  "description": "A platform game where players control Mario as he journeys through the Mushroom Kingdom."
}'

curl -X POST http://localhost:8080/games \
-H "Content-Type: application/json" \
-d '{
  "name": "spaceinvaders",
  "displayName": "Space Invaders",
  "description": "A classic arcade game where players control a laser cannon to defeat waves of alien invaders."
}'
```
### Users
Use the CURL commands below to create sample users in the database:
```
curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "username": "johndoe",
  "name": "John Doe",
  "email": "johndoe@example.com",
  "dob": "1990-01-01"
}'

curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "username": "janedoe",
  "name": "Jane Doe",
  "email": "janedoe@example.com",
  "dob": "1991-02-02"
}'

curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "username": "alexsmith",
  "name": "Alex Smith",
  "email": "alexsmith@example.com",
  "dob": "1985-03-03"
}'

curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "username": "emilyjohnson",
  "name": "Emily Johnson",
  "email": "emilyjohnson@example.com",
  "dob": "1992-04-04"
}'

curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "username": "michaelbrown",
  "name": "Michael Brown",
  "email": "michaelbrown@example.com",
  "dob": "1988-05-05"
}'

curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "username": "sarawilson",
  "name": "Sara Wilson",
  "email": "sarawilson@example.com",
  "dob": "1990-06-06"
}'

curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "username": "chrisjones",
  "name": "Chris Jones",
  "email": "chrisjones@example.com",
  "dob": "1987-07-07"
}'

curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "username": "amandalewis",
  "name": "Amanda Lewis",
  "email": "amandalewis@example.com",
  "dob": "1993-08-08"
}'

curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "username": "davidmartin",
  "name": "David Martin",
  "email": "davidmartin@example.com",
  "dob": "1986-09-09"
}'

curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "username": "nataliewright",
  "name": "Natalie Wright",
  "email": "nataliewright@example.com",
  "dob": "1994-10-10"
}'

curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "username": "jamesclark",
  "name": "James Clark",
  "email": "jamesclark@example.com",
  "dob": "1989-11-11"
}'

curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "username": "oliviahernandez",
  "name": "Olivia Hernandez",
  "email": "oliviahernandez@example.com",
  "dob": "1991-12-12"
}'

curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "username": "brianmoore",
  "name": "Brian Moore",
  "email": "brianmoore@example.com",
  "dob": "1992-01-13"
}'

curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "username": "meganwalker",
  "name": "Megan Walker",
  "email": "meganwalker@example.com",
  "dob": "1990-02-14"
}'

curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "username": "kevinhall",
  "name": "Kevin Hall",
  "email": "kevinhall@example.com",
  "dob": "1988-03-15"
}'

curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "username": "lisamartinez",
  "name": "Lisa Martinez",
  "email": "lisamartinez@example.com",
  "dob": "1993-04-16"
}'

curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "username": "danielgarcia",
  "name": "Daniel Garcia",
  "email": "danielgarcia@example.com",
  "dob": "1989-05-17"
}'

curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "username": "ashleyrodriguez",
  "name": "Ashley Rodriguez",
  "email": "ashleyrodriguez@example.com",
  "dob": "1991-06-18"
}'

curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "username": "ethanscott",
  "name": "Ethan Scott",
  "email": "ethanscott@example.com",
  "dob": "1992-07-19"
}'

curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "username": "laurenyoung",
  "name": "Lauren Young",
  "email": "laurenyoung@example.com",
  "dob": "1990-08-20"
}'
```
### Records
Use the CURL commands below to create sample records in the database:
```
curl -X POST http://localhost:8080/records/pacman/amandalewis -H "Content-Type: application/json" -d '{
  "score": 1693,
  "recordTime": "2024-06-24T23:38:11"
}'
curl -X POST http://localhost:8080/records/pacman/oliviahernandez -H "Content-Type: application/json" -d '{
  "score": 7367,
  "recordTime": "2024-06-09T14:41:16"
}'
curl -X POST http://localhost:8080/records/pacman/nataliewright -H "Content-Type: application/json" -d '{
  "score": 1274,
  "recordTime": "2024-06-02T19:48:59"
}'
curl -X POST http://localhost:8080/records/pacman/kevinhall -H "Content-Type: application/json" -d '{
  "score": 2918,
  "recordTime": "2024-06-30T12:47:25"
}'
curl -X POST http://localhost:8080/records/pacman/janedoe -H "Content-Type: application/json" -d '{
  "score": 7045,
  "recordTime": "2024-06-29T23:24:22"
}'
curl -X POST http://localhost:8080/records/pacman/ashleyrodriguez -H "Content-Type: application/json" -d '{
  "score": 9693,
  "recordTime": "2024-06-02T00:43:52"
}'
curl -X POST http://localhost:8080/records/pacman/emilyjohnson -H "Content-Type: application/json" -d '{
  "score": 1268,
  "recordTime": "2024-06-18T07:38:02"
}'
curl -X POST http://localhost:8080/records/pacman/sarawilson -H "Content-Type: application/json" -d '{
  "score": 1844,
  "recordTime": "2024-06-05T05:37:43"
}'
curl -X POST http://localhost:8080/records/pacman/alexsmith -H "Content-Type: application/json" -d '{
  "score": 7326,
  "recordTime": "2024-06-01T14:11:11"
}'
curl -X POST http://localhost:8080/records/pacman/lisamartinez -H "Content-Type: application/json" -d '{
  "score": 9888,
  "recordTime": "2024-06-19T06:50:37"
}'
curl -X POST http://localhost:8080/records/asteroids/danielgarcia -H "Content-Type: application/json" -d '{
  "score": 2288,
  "recordTime": "2024-06-29T02:26:24"
}'
curl -X POST http://localhost:8080/records/asteroids/alexsmith -H "Content-Type: application/json" -d '{
  "score": 5654,
  "recordTime": "2024-06-02T15:17:02"
}'
curl -X POST http://localhost:8080/records/asteroids/michaelbrown -H "Content-Type: application/json" -d '{
  "score": 5994,
  "recordTime": "2024-06-26T12:58:58"
}'
curl -X POST http://localhost:8080/records/asteroids/sarawilson -H "Content-Type: application/json" -d '{
  "score": 6762,
  "recordTime": "2024-06-11T05:39:16"
}'
curl -X POST http://localhost:8080/records/asteroids/brianmoore -H "Content-Type: application/json" -d '{
  "score": 2537,
  "recordTime": "2024-06-12T15:38:02"
}'
curl -X POST http://localhost:8080/records/asteroids/janedoe -H "Content-Type: application/json" -d '{
  "score": 5091,
  "recordTime": "2024-06-26T06:12:12"
}'
curl -X POST http://localhost:8080/records/asteroids/chrisjones -H "Content-Type: application/json" -d '{
  "score": 6663,
  "recordTime": "2024-06-08T20:21:30"
}'
curl -X POST http://localhost:8080/records/asteroids/amandalewis -H "Content-Type: application/json" -d '{
  "score": 5260,
  "recordTime": "2024-06-30T03:10:46"
}'
curl -X POST http://localhost:8080/records/asteroids/lisamartinez -H "Content-Type: application/json" -d '{
  "score": 4419,
  "recordTime": "2024-06-12T15:13:07"
}'
curl -X POST http://localhost:8080/records/asteroids/davidmartin -H "Content-Type: application/json" -d '{
  "score": 4038,
  "recordTime": "2024-06-03T22:48:42"
}'
curl -X POST http://localhost:8080/records/tetris/oliviahernandez -H "Content-Type: application/json" -d '{
  "score": 3047,
  "recordTime": "2024-06-03T02:18:27"
}'
curl -X POST http://localhost:8080/records/tetris/emilyjohnson -H "Content-Type: application/json" -d '{
  "score": 1331,
  "recordTime": "2024-06-09T20:58:25"
}'
curl -X POST http://localhost:8080/records/tetris/amandalewis -H "Content-Type: application/json" -d '{
  "score": 5651,
  "recordTime": "2024-06-18T05:13:59"
}'
curl -X POST http://localhost:8080/records/tetris/lisamartinez -H "Content-Type: application/json" -d '{
  "score": 2197,
  "recordTime": "2024-06-24T15:04:40"
}'
curl -X POST http://localhost:8080/records/tetris/sarawilson -H "Content-Type: application/json" -d '{
  "score": 1132,
  "recordTime": "2024-06-18T23:59:54"
}'
curl -X POST http://localhost:8080/records/tetris/jamesclark -H "Content-Type: application/json" -d '{
  "score": 7779,
  "recordTime": "2024-06-15T01:51:45"
}'
curl -X POST http://localhost:8080/records/tetris/meganwalker -H "Content-Type: application/json" -d '{
  "score": 2370,
  "recordTime": "2024-06-13T10:03:23"
}'
curl -X POST http://localhost:8080/records/tetris/danielgarcia -H "Content-Type: application/json" -d '{
  "score": 4617,
  "recordTime": "2024-06-11T12:47:55"
}'
curl -X POST http://localhost:8080/records/tetris/janedoe -H "Content-Type: application/json" -d '{
  "score": 6865,
  "recordTime": "2024-06-13T05:33:25"
}'
curl -X POST http://localhost:8080/records/tetris/laurenyoung -H "Content-Type: application/json" -d '{
  "score": 5372,
  "recordTime": "2024-06-14T17:09:47"
}'
curl -X POST http://localhost:8080/records/mariobros/amandalewis -H "Content-Type: application/json" -d '{
  "score": 1490,
  "recordTime": "2024-06-23T19:59:44"
}'
curl -X POST http://localhost:8080/records/mariobros/lisamartinez -H "Content-Type: application/json" -d '{
  "score": 1780,
  "recordTime": "2024-06-10T18:29:26"
}'
curl -X POST http://localhost:8080/records/mariobros/ashleyrodriguez -H "Content-Type: application/json" -d '{
  "score": 8537,
  "recordTime": "2024-06-08T19:00:18"
}'
curl -X POST http://localhost:8080/records/mariobros/johndoe -H "Content-Type: application/json" -d '{
  "score": 4227,
  "recordTime": "2024-06-14T19:08:26"
}'
curl -X POST http://localhost:8080/records/mariobros/brianmoore -H "Content-Type: application/json" -d '{
  "score": 5681,
  "recordTime": "2024-06-22T22:52:19"
}'
curl -X POST http://localhost:8080/records/mariobros/janedoe -H "Content-Type: application/json" -d '{
  "score": 2922,
  "recordTime": "2024-06-14T12:35:48"
}'
curl -X POST http://localhost:8080/records/mariobros/alexsmith -H "Content-Type: application/json" -d '{
  "score": 6238,
  "recordTime": "2024-06-22T20:19:28"
}'
curl -X POST http://localhost:8080/records/mariobros/ethanscott -H "Content-Type: application/json" -d '{
  "score": 7322,
  "recordTime": "2024-06-20T22:03:10"
}'
curl -X POST http://localhost:8080/records/mariobros/jamesclark -H "Content-Type: application/json" -d '{
  "score": 7429,
  "recordTime": "2024-06-18T19:45:07"
}'
curl -X POST http://localhost:8080/records/mariobros/emilyjohnson -H "Content-Type: application/json" -d '{
  "score": 5374,
  "recordTime": "2024-06-05T12:05:18"
}'
curl -X POST http://localhost:8080/records/spaceinvaders/kevinhall -H "Content-Type: application/json" -d '{
  "score": 1735,
  "recordTime": "2024-06-05T13:15:46"
}'
curl -X POST http://localhost:8080/records/spaceinvaders/johndoe -H "Content-Type: application/json" -d '{
  "score": 2272,
  "recordTime": "2024-06-23T17:39:36"
}'
curl -X POST http://localhost:8080/records/spaceinvaders/chrisjones -H "Content-Type: application/json" -d '{
  "score": 1144,
  "recordTime": "2024-06-11T14:32:49"
}'
curl -X POST http://localhost:8080/records/spaceinvaders/amandalewis -H "Content-Type: application/json" -d '{
  "score": 1641,
  "recordTime": "2024-06-10T20:41:56"
}'
curl -X POST http://localhost:8080/records/spaceinvaders/janedoe -H "Content-Type: application/json" -d '{
  "score": 1103,
  "recordTime": "2024-06-24T12:49:04"
}'
curl -X POST http://localhost:8080/records/spaceinvaders/brianmoore -H "Content-Type: application/json" -d '{
  "score": 1444,
  "recordTime": "2024-06-12T11:09:57"
}'
curl -X POST http://localhost:8080/records/spaceinvaders/laurenyoung -H "Content-Type: application/json" -d '{
  "score": 6503,
  "recordTime": "2024-06-29T05:18:16"
}'
curl -X POST http://localhost:8080/records/spaceinvaders/ashleyrodriguez -H "Content-Type: application/json" -d '{
  "score": 7387,
  "recordTime": "2024-06-01T10:00:57"
}'
curl -X POST http://localhost:8080/records/spaceinvaders/danielgarcia -H "Content-Type: application/json" -d '{
  "score": 7814,
  "recordTime": "2024-06-12T12:30:48"
}'
curl -X POST http://localhost:8080/records/spaceinvaders/davidmartin -H "Content-Type: application/json" -d '{
  "score": 8244,
  "recordTime": "2024-06-10T17:16:38"
}'
```
## Testing the Service
After populating the database, we can test the functionality of the API with this curl:
```
curl --location 'http://localhost:8080/leaderboard/spaceinvaders' \
--header 'Content-Type: application/json'
```
Use these APIs to view the data present in database:
```
curl --location 'http://localhost:8080/games?page=0&count=10' \
--header 'Content-Type: application/json'

curl --location 'http://localhost:8080/users?page=0&count=25' \
--header 'Content-Type: application/json'

curl --location 'http://localhost:8080/records?page=0&count=100' \
--header 'Content-Type: application/json'
```