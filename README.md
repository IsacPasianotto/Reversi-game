# Reversi-game
This repo was made for the "Software Development Methods" exam at the University of Trieste that I took during my studies in Data Science and Scientific Computing.

The project consists in implementing a game, practicing Continuous integration and using automated tests and Test Driven Development to grow the code.


## TODO List 

- [x] Define the tests for TTD
  - [x] test the board 
    - [x] **TODO**: add a test to be sure that the board is able to flip lines in more than one direction if it's necessary 
  - [x] Test the aspects related to player: 
    - [x] Is the program handling correctly a human player?
    - [x] Is the program handling correctly the computer player?
  - [x] test the mechanics of the game  
- [x] Implement the base game in terminal
  - [x] Define the rules
  - [x] Define the board
  - [x] Define the player and the computer mechanics
  - [x] Check the end of the game
  - [x] Check the validity of the moves
  - [x] Update the score ad each move (and print maybe?)
- [ ] **_IMPORTANT_**: Since I had some external libraries, I had to check credits and licenses for them.
  - [ ]  JUnit 5 --> Eclipse Public License 2.0
  - [ ]  Mockito --> MIT License
  - [ ]  Swing  --> GNU General Public License v2.0
  - [ ]  openJDK --> GNU General Public License v2.0
- [ ] Add the README
- [ ] (Optional) Implement the game in a GUI




## Terminal Game

### How to run the game: 

1. Clone the repo
2. Open the terminal and go to the folder where you cloned the repo
3. Run the command: 
  ```
  ./gradlew terminal:run --console=plain
  ```

Otherwise you can obtain the `.jar`  file with: 

```
./gradlew terminal:dist
```

which will create the `.jar` file in the folder `/readyToDistribute`. Then you can cd into the folder and run the command: 

```
java -jar terminal-1.0.jar
```

Note that the `./gradlew build` command will take care of destroying the `.jar` file and the folder `/readyToDistribute`. 

