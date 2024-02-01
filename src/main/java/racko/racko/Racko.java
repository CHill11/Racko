/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package racko.racko;

import java.io.IOException;
import java.util.*;

/**
 *
 * @author Chris Hill
 */
public class Racko {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
         
        Player player1;
        Player player2;
        Player player3;
        Player player4;
        Player players[];
        int[] playersScoresForSort;
        boolean winner = false;//Continue game if a winner is not detected
        boolean isSetup = true;//Assume that the drawpile and discard was created correctly
        
        do{
            
            //Setup the game
            int[] deck = null;
            int numberOfPlayers = 0;
            
            
            boolean hasMadePlayers = false;//Flag to see if the game should start
            boolean wantCPU = false;
            boolean cont = true;
            
            //Get the number of players
            do{//Continue until a valid number of players is entered
                System.out.println("Enter the number of players (1-4)");//Ask for the number of players
                String playersInput = scanner.next().toLowerCase();
                switch(playersInput){
                    case "1": numberOfPlayers = 1;
                        cont = false;
                        break;
                    case "2": numberOfPlayers = 2;
                        cont = false;
                        break;
                    case "3": numberOfPlayers = 3;
                        cont = false;
                        break;
                    case "4": numberOfPlayers = 4;
                        cont = false;
                        break;
                    default: System.out.println("Invalid number of players. Try again.");
                }
            }while(cont);
            
            cont = true;//Reset the loop flag
            
            
            //Get the number of computer players
            do{//Continue until a valid entry is entered
                if(numberOfPlayers == 1){
                    break;
                }
                if(numberOfPlayers < 4 && numberOfPlayers != 0){//Check to see if cpu players are wanted
                    System.out.println("Do you want computer players? (y/n)");
                    String wantCPUinput = scanner.next().toLowerCase();
                    switch(wantCPUinput){
                        case "y":
                        case "yes": wantCPU = true;
                                    cont = false;
                                    break;
                        case "n":
                        case "no":  wantCPU = false;
                                    cont = false;
                                    break;
                        default:    System.out.println("Invalid entry. Try again.");
                    }
                }
            }while(cont);
            
            switch(numberOfPlayers){
                case 1: System.out.println("Enter the name of the first player");
                        player1 = new Player(scanner.next());
                        player2 = new Cpu_Player(2);
                        player3 = new Cpu_Player(3);
                        player4 = new Cpu_Player(4);
                        players = new Player[4];//Add the players to an array for easy terversial including CPU
                        players[0] = player1;
                        players[1] = player2;
                        players[2] = player3;
                        players[3] = player4;

                        deck = Create_deck.deckCreate(4);//Create the deck for the proper number of players

                        hasMadePlayers = true;
                        break;
                case 2: System.out.println("Enter the name of the first player");
                        player1 = new Player(scanner.next());
                        System.out.println("Enter the name of the second player");
                        player2 = new Player(scanner.next());
                        if(wantCPU){
                            player3 = new Cpu_Player(3);
                            player4 = new Cpu_Player(4);
                            players = new Player[4];//Add the players to an array for easy terversial including CPU
                            players[0] = player1;
                            players[1] = player2;
                            players[2] = player3;
                            players[3] = player4;
                            
                            deck = Create_deck.deckCreate(4);//Create the deck for the proper number of players
                        }else{
                            players = new Player[2];//Add the players to an array for easy terversial excluding CPU
                            players[0] = player1;
                            players[1] = player2;
                            
                            deck = Create_deck.deckCreate(2);//Create the deck for the proper number of players
                        }
                        hasMadePlayers = true;
                        break;

                case 3: System.out.println("Enter the name of the first player");
                        player1 = new Player(scanner.next());
                        System.out.println("Enter the name of the second player");
                        player2 = new Player(scanner.next());
                        System.out.println("Enter the name of the third player");
                        player3 = new Player(scanner.next());
                        if(wantCPU){
                            player4 = new Cpu_Player(4);
                            players = new Player[4];//Add the players to an array for easy terversial including CPU
                            players[0] = player1;
                            players[1] = player2;
                            players[2] = player3;
                            players[3] = player4;
                            
                            deck = Create_deck.deckCreate(4);//Create the deck for the proper number of players
                        }else{
                            players = new Player[3];//Add the players to an array for easy terversial excluding CPU
                            players[0] = player1;
                            players[1] = player2;
                            players[2] = player3;
                            
                            deck = Create_deck.deckCreate(3);//Create the deck for the proper number of players
                        }
                        hasMadePlayers = true;
                        break;

                case 4: System.out.println("Enter the name of the first player");
                        player1 = new Player(scanner.next());
                        System.out.println("Enter the name of the second player");
                        player2 = new Player(scanner.next());
                        System.out.println("Enter the name of the third player");
                        player3 = new Player(scanner.next());
                        System.out.println("Enter the name of the forth player");
                        player4 = new Player(scanner.next());
                        players = new Player[4];//Add the players to an array for easy terversial excluding CPU
                        players[0] = player1;
                        players[1] = player2;
                        players[2] = player3;
                        players[3] = player4;
                        
                        deck = Create_deck.deckCreate(4);//Create the deck for the proper number of players
                        hasMadePlayers = true;
                        break;   

                default: System.out.println("You didn't enter a valid number of players");
                players = new Player[0];
            }
            
            Stack drawPile = Create_deck.makedrawPile(deck);//Create the drawPile stack
            
            for(Player p: players) {//Create the rack for the players
                p.createRack(drawPile);
            }
           /* for(Player player: players) { //Create the racks for the players
                int[] playerRack = player.getRack();
                for(int i = 0; i < 10; i++){
                    playerRack[i] = (int) drawPile.pop();
                }
                player.setRack(playerRack);
            }*/
            
            Stack discard = new Stack();//Create the discard stack
            discard.push(drawPile.pop());//Setup the discard with one card
            if(discard.isEmpty() || drawPile.isEmpty()){//Make sure the stack got populated.
                isSetup = false;
                scanner.next();
            }
            
            while(hasMadePlayers && isSetup){//Start of the game loop
                System.out.print("Welcome to racko!\n");
                while(!winner){                    
                    for(Player player: players){
                        
                        for (int i = 0;i < 50; i++){//Clear the preivious players rack from the screen
                            System.out.println();
                        }
                        
                        System.out.println(player.getPlayer() + " it is your turn! Make sure you are the only person looking at the screen and press enter.");//Delay the start of the turn to make sure nobody see other peoples racks
                        try{
                            System.in.read();
                        }catch(IOException e){
                            System.out.print(e);
                        }
                        if(player.takeTurn(discard, drawPile, player)){//Start of turn and check to see if the player has won the round
                            System.out.println(player.getPlayer() + " Racko!!");
                            System.out.print("Press enter to continue...");
                            try{
                                System.in.read();//Pause so players can see
                            }catch(IOException e){
                                System.out.print(e);
                            }
                            
                            System.out.println("Here are all the players racks.");

                            //Display the racks of all the player
                            for (Player p : players) {
                                System.out.println(p.toString(""));
                            }
                            
                            System.out.print("Press enter to continue...");
                            
                            try{
                                System.in.read();//Pause so players can see
                            }catch(IOException e){
                                System.out.print(e);
                            }
                            
                            System.out.println("The scores for the players are: ");

                            //Display the scores of the players
                            for(int i = 0; i < players.length;i++){
                                players[i].calculateScore();//Add up the scores of all the players
                                players[i].setIsWinner(false);//Reset the players winner status
                                System.out.println(players[i].getPlayer() + " " + players[i].getScore());//Display the player name and score
                            }
                            
                            for(int i = 0; i < players.length; i++){
                                //Check to see it the player is over 500 points
                                if(players[i].scoreWinner()){//If a player has gone over 500 points
                                    playersScoresForSort = new int[players.length];//Create the appropriate sized array for the number of players
                                    for(int j = 0; j < playersScoresForSort.length;j++){//Add all the players scores into the array
                                        playersScoresForSort[j] = players[j].getScore();
                                    }
                                    
                                    //Sort the array of score to find the largest
                                    for(int k = 0;k < playersScoresForSort.length;k++){
                                        for(int j = 0; j < playersScoresForSort.length - 1;j++){
                                            if(playersScoresForSort[j] < playersScoresForSort[j + 1]){
                                                int temp = playersScoresForSort[j];
                                                playersScoresForSort[j] = playersScoresForSort[j + 1];
                                                playersScoresForSort[j + 1] = temp;
                                            }
                                        }
                                    }
                                    
                                    //Get the player that matches the largest sorted score
                                    for(int j = 0;j < playersScoresForSort.length; j++){
                                        if(players[j].getScore() == playersScoresForSort[0]){
                                            System.out.println(players[j].getPlayer() + " has won!!! With a score of " + players[j].getScore());
                                            isSetup = false;
                                            winner = true;
                                            System.out.print("Press enter to exit.");
                                            try{
                                                System.in.read();
                                            }catch(IOException e){
                                                System.out.print(e);
                                            }
                                        }
                                    }
                                }
                            }
                            if(isSetup && !winner){//Check to see if a winner has been found and if it hasn't continue
                                System.out.print("Press enter to continue...");
                                try{
                                    System.in.read();
                                }catch(IOException e){
                                    System.out.print(e);
                                }
                            }
                                //Create a new deck and fill the racks

                                deck = Create_deck.deckCreate(players.length);//Create a new deck for the next game

                                drawPile.clear();//Clear the draw pile for the next game
                                discard.clear();//Clear the discard pile for the next game
                                drawPile = Create_deck.makedrawPile(deck);//Create a new draw pile

                                for(Player p: players) {//Create the rack for the players
                                    p.createRack(drawPile);
                                }

                                discard = Create_deck.makeDiscardPile(drawPile);//Create a new discard pile
                                break;
                            
                        }
                    }
                }
            }
        }while(isSetup);
    }
}