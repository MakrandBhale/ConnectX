# ConnectX
A java implementation for the popular game Connect 4.

## Descriptions 

### Class Description

1. `ArgOptions` class validates all the command-line arguments and creates an instance of the `Options` class. 
2. `Board` class creates a matrix of `CellNode` of dimension `row*col`. 
3. `CellNode` class contains all the neighbours of current cell. Kind of like in a graph.
4. `Player` class has information about the player and the piece they've taken
5. `Referee` class manages the game, takes and validates input, decides winner and restarts the game if users want.

## Screenshots

### Starting the game
![AskingForChoice](https://github.com/MakrandBhale/ConnectX/blob/master/screenshots/choice.png)

### Player win
![PlayerWin](https://github.com/MakrandBhale/ConnectX/blob/master/screenshots/win.png)

