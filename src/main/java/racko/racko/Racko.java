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
        boolean winner = false;//Continue game if a winner is not detected
        boolean isSetup = true;//Assume that the drawpile and discard was created correctly
        
        do{
            
            //Setup the game
            int[] deck = null;
            boolean hasMadePlayers = false;//Flag to see if the game should start
            boolean wantCPU = false;
            
            System.out.println("Enter the number of players (2-4)");//Ask for the number of players
            int numberOfPlayers = scanner.nextInt();
           /* if(numberOfPlayers < 4){//Check to see if cpu players are wanted
                System.out.println("Do you want computer players? (y/n)");
                String wantCPUinput = scanner.next();
                if(wantCPUinput.charAt(0) == 'y') {
                    wantCPU = true;
                }
            }*/
            switch(numberOfPlayers){
                case 2: System.out.println("Enter the name of the first player");
                        player1 = new Player(scanner.next());
                        System.out.println("Enter the name of the second player");
                        player2 = new Player(scanner.next());
                        if(wantCPU){
                            player3 = new Player(3);
                            player4 = new Player(4);
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
                            player4 = new Player(4);
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
            
            if(players.length == 0){
                System.out.print("The system could not create the game and will now exit.");
                try {
                    System.in.read();
                } catch (IOException e) {
                    System.out.print(e);
                }
                System.exit(200);
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
                            
                            System.out.println("Here are all the players racks.");//Display the racks of all the player
                            for(int i = 0; i < players.length; i++){
                                System.out.println(players[i].toString(""));
                            }
                            System.out.print("Press enter to continue...");
                            try{
                                System.in.read();//Pause so players can see
                            }catch(IOException e){
                                System.out.print(e);
                            }
                            
                            System.out.println("The scores for the players are: ");//Display the scores of the players
                            for(int i = 0; i < players.length;i++){
                                players[i].calculateScore();//Add up the scores of all the players
                                players[i].setIsWinner(false);//Reset the players winner status
                                System.out.println(players[i].getPlayer() + " " + players[i].getScore());//Display the player name and score
                                if(players[i].scoreWinner()){//Check the score of the players to see if anyone has won
                                    System.out.println(players[i].getPlayer() + " has reached the 500 point goal and has won!!!");
                                    try{
                                        System.in.read();
                                    }catch(IOException e){
                                        System.out.print(e);
                                    }
                                    winner = true;
                                    isSetup = false;
                                    break;
                                }
                            }
                            System.out.print("Press enter to continue...");
                            try{
                                System.in.read();
                            }catch(IOException e){
                                System.out.print(e);
                            }
                            
                            //Create a new deck and fill the racks
                            deck = Create_deck.swap(deck);
                            drawPile = Create_deck.makedrawPile(deck);//Create a new draw pile
                            
                            for(Player p: players) {//Create the rack for the players
                                p.createRack(drawPile);
                            }
                            
                            Create_deck.makeDiscardPile(drawPile);//Create a new discard pile
                            break;
                        }
                    }
                }
            }
        }while(isSetup);
    }
}
