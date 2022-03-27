Connect Four is a two-player board game where both players pick a colour (Red or Yellow) and take turns dropping their coloured discs into the top of a 7x6 grid. 

The first to connect four discs of the same colour is the winner. 

The challenge this week is to implement a game of Connect Four. 

Running the game against these sample sets of moves should output the correct winner. 

If the game is over before the set of moves ends, the game should ignore the remaining moves and return the rightful winner.

## Rewards:

### :four:  Points are awarded for outputting the correct winner for each sample game

**answer: Yellow - Red - Red (see Connect4GameTest)**

### :three:  Further points are awarded for visualising the game on screen (either a proper UI, or as some kind of console-art)

![visual 2 player](https://github.com/dale-waterworth/ANDCodeChallenges/blob/84290acdc7bacf05802ead979cec7089c8fcbd01/docs/connect4/winner-is-red.png)

### :three:  Further points are awarded for creating an interactive game which can be played against a basic computer player

![interactive](https://github.com/dale-waterworth/ANDCodeChallenges/blob/84290acdc7bacf05802ead979cec7089c8fcbd01/docs/connect4/1or2-player.png)

Submission:

When you’re happy with your solution, submit a link to your source code repo using the /submit command. 

If you encounter an error with either command, try again in 10 seconds - I’m aware of this issue, it's on my "to-fix" list. 

Submission closes noon 30th March (edited) 


https://pastebin.com/JDe8z7Np

`connectFour([
"A_Red",
"B_Yellow",
"A_Red",
"B_Yellow",
"A_Red",
"B_Yellow",
"G_Red",
"B_Yellow"
]);`

connectFour([
"A_Red",
"G_Yellow",
"B_Red",
"F_Yellow",
"C_Red",
"E_Yellow",
"D_Red"
]);

connectFour([
"B_Yellow",
"C_Red",
"C_Yellow",
"D_Red",
"A_Yellow",
"D_Red",
"D_Yellow",
"E_Red",
"E_Yellow",
"F_Red",
"E_Yellow
]);
