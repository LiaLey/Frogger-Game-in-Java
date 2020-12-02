### COMP2042_CW_CeliaLimErnYin

### Screenshots of Game 
---
![Image test](src/main/resources/images/FroggerMain.png)
![Image test](src/main/resources/images/FroggerGame.png)
![Image test](src/main/resources/images/FroggerPause.png)


### Refactoring 
---
- <h4>Packaging</h4>

Related classes were packaged together.
1. **actors** - Contains classes that define objects visible on the stage.
   
   - **actors.active** - Contains classes of actors that shows movement. Eg. Animal, Car
   - **actors.passive** - Contains classes of actors that are static. Eg. Background Image, Lives

2. **world** - Contains classes that defines the stage settings and display. Eg. LevelType1, World

3. **menu** - Contains classes used to define construction of Menus

4. **sound** - Contains the MediaPlay class that plays background music

5. **application** - Contains the Main class that runs the game.

- <h4>Breaking up of Classes</h4>  

To improve encapsulation and promote single responsibility, large classes like Animal was broken down. An additional AnimationIterator class separately controls the animal animations 
The Obstacle class was also broken down. A MediaPlay class was also separately added for playing music.  

- <h4> Design Patterns</h4> 

Included an MVC pattern to control the MainMenu and show the Info window using scene builder. 

- <h4>Naming Convention Changes</h4>
Some naming conventions were changed to avoid confusion and disorientation that was present in the orginal code. 
Eg. MyStage was changed to MyLevel to avoid confusion with the JavaFX Stage class.


### Code Maintenance
---

- Scoring System

Points will now be added based on travel distance (along the vertical plane) by the player in each round in addition to the 
points earned after winning each level.

- Winning System

Changes were made so that frog holes can no longer be activated for a second time. 

- Display of scores

Made changes on the display of scores so that the images of scores would not overlay each other.

- Animation of Frog

A separate class was created to handle the animation of the movements of the frog.
Images of the frog are now stored in an array to be looped every frame to form an animation.

- Movement on Obstacles

Instead of fixing the speed of the frog, we will get the speed of the obstacles and assigned it to the frog so it will move in time with the obstacle.


### Additions
---

- Main Menu 

Added a Main Menu  with options to start the game, exit the game, access the leaderboard or the info Menu.

- LeaderBoard

Added a permanent Leaderboard displaying all the scores of the players in order.
The scores are stored in a file and loaded each time the game application starts.

- Information Menu

Added an Info Menu using that shows the simple operations of the game. A controller class controls the functions of the Info Menu.


- Pause Menu

Added a mid-game pause Menu so players can pause, resume, restart the game, exit to the main menu or exit the game entirely.

- Additional Levels

Added additional levels with different levels of difficulty.
The levels are repeated in an infinite loop and the game only ends once the player loses.

- Lives

Lives are added and the player only loses once all lives are lost. Lives will be visibly removed each time the frog dies.
The lives are displayed at the bottom of the window for the players.

- Sound effects

Added a few more additional sound effect to indicate the change in levels, wins and Game-Overs.

- Junit Testings

Added additional Junit tests for a few classes

- Build Files

Added Gradle build files for the project 

