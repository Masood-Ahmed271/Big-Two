# BigTwo Game (Final) 

____________________________________________________________________________________________________________________________________________________________________


## Introduction;

  This is the complete project named *Big Two.* In this project, you can launch 4 different games for 4 different players to play againts each other. It allows the   game to be multiplayer. This feature of the game uses client-server model so before launching the game, you need to launch the server. Then you can launch the       game and play with your friends.
  
   _________________________________________________________________________________________________________________________________________________________________
  
## Compilation and execution instructions

   To compile and run the code, enter:

```
  1) Go To the terminal
  2) Go to the directory where you have stored the files.
  3) Go to src folder
  4) On the terminal right the following commands:
     javac BigTwo.java
     javac BigTwoServer.java
     java BigTwoServer
     java BigTwo    (This might need to be run on a different terminal)

```

_________________________________________________________________________________________________________________________________________________________________
  
## Pictures:

*Picture of Server*

<img width="485" alt="image" src="https://user-images.githubusercontent.com/63158543/145814408-c333db10-2d08-427e-b076-0753eb1e76d5.png">


*Picture of the Game*

<img width="1197" alt="image" src="https://user-images.githubusercontent.com/63158543/145814449-d7884e31-19ba-4d64-90e6-b7b2a11bb627.png">

 _________________________________________________________________________________________________________________________________________________________

## Important Components in the GUI:

*  Has a panel showing the cards of each player as well as the cards played on the table.

*  The cards shown are in a partially overlapped manner.

*  For each player, the panel shows his/her name and an avatar for him/her.

*  For the active player, the panel shows the faces of his/her cards.

*  For other players, the panel shows only the backs of their cards.

*  For cards played on the table, the panel shows at least (the faces of) the last
   hand of cards played on the table and the name of the player for this hand.
  
*  Allows the active player to select and deselect his/her cards by mouse clicks. The
   selected cards should be drawn in a “raised” position with respect to the rest of the
   cards.

*  Has a “Play” button for the active player to play the selected cards.

*  Has a “Pass” button for the active player to pass his/her turn to the next player.

*  Has a text area to show the current game status as well as end of game messages.

*  Has a text area showing the chat messages sent by the players.

*  Has a text input field for the active player to send out chat messages.

*  Has a “Restart” menu item for restarting the game.
  ________________________________________________________________________________________________________________________________________________________
