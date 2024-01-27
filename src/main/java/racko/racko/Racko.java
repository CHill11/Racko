/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package racko.racko;

import java.io.IOException;
import java.util.*;
/**
 *
 * @author paper
 */
public class Racko {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
         
        Player player1 = null;
        Player player2 = null;
        Player player3 = null;
        Player player4 = null;
        boolean winner = false;//Continue game if a winner is not detected
        
        do{
            
            //Setup the game
            int[] deck = Create_deck.deckCreate();
            System.out.println(Arrays.toString(deck));//Delete after finish testing
            boolean hasMadePlayers = false;//Flag to see if the game should start
            boolean isSetup = true;//Assume that the drawpile and discard was created correctly
            boolean wantCPU = false;
            
            System.out.println("Enter the number of players (2-4)");//Ask for the number of players
            int numberOfPlayers = scanner.nextInt();
            if(numberOfPlayers < 4){
                System.out.println("Do you want computer players? (y/n)");
                String wantCPUinput = scanner.next();
                if(wantCPUinput.charAt(0) == 'y') {
                    wantCPU = true;
                }
            }
            switch(numberOfPlayers){
                case 2: System.out.println("Enter the name of the first player");
                        player1 = new Player(scanner.next());
                        System.out.println("Enter the name of the second player");
                        player2 = new Player(scanner.next());
                        player3 = new Player(3);
                        player4 = new Player(4);
                        hasMadePlayers = true;
                        break;

                case 3: System.out.println("Enter the name of the first player");
                        player1 = new Player(scanner.next());
                        System.out.println("Enter the name of the second player");
                        player2 = new Player(scanner.next());
                        System.out.println("Enter the name of the third player");
                        player3 = new Player(scanner.next());
                        player4 = new Player(4);
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
                        hasMadePlayers = true;
                        break;   

                default: System.out.println("You didn't enter a valid number of players");
            }
            Player[] players = {player1,player2,player3,player4};//Add the players to an array for easy terversial
            Stack drawPile = Create_deck.makedrawPile(deck);//Create the drawPile stack
            
            for(Player player: players) { //Create the racks for the players
                int[] playerRack = player.getRack();
                for(int i = 0; i < 10; i++){
                    playerRack[i] = (int) drawPile.pop();
                }
                player.setRack(playerRack);
            }
            
            Stack discard = new Stack();//Create the discard stack
            discard.push(drawPile.pop());//Setup the discard with one card
            if(discard.isEmpty() || drawPile.isEmpty()){//Make sure the stack got populated.
                isSetup = false;
                scanner.next();
            }
            
            while(hasMadePlayers && isSetup){//Start of the game loop
                System.out.print("Welcome to racko!\n");
                while(!winner){
                    
                    System.out.println(players[0].getPlayer() + " it is your turn! Make sure you are the only person looking at the screen and press enter.");//Delay the start of the turn to make sure nobody see other peoples racks
                    try{
                        System.in.read();
                    }catch(IOException e){
                        System.out.print(e);
                    }
                    
                    if(player1.takeTurn(discard, drawPile, player1)){//Start of player 1 turn
                        System.out.println(players[0].getPlayer() + " has Won!!!");
                        System.out.println("Here are all the players racks.");
                        for(int i = 0; i < 4; i++){
                            players[i].toString();
                        }
                        winner = true;
                        break;
                    }
                    
                    for (int i = 0;i < 20; i++){//Clear the preivious players rack from the screen
                        System.out.println();
                    }
                    
                    System.out.println(players[1].getPlayer() + " it is your turn! Make sure you are the only person looking at the screen and press enter.");//Delay the start of the turn to make sure nobody see other peoples racks
                    try{
                        System.in.read();
                    }catch(IOException e){
                        System.out.print(e);
                    }
                    
                    if(player2.takeTurn(discard, drawPile, player2)){//Start of player two turn
                        System.out.println(players[1].getPlayer() + " has Won!!!");
                        System.out.println("Here are all the players racks.");
                        for(int i = 0; i < 4; i++){
                            players[i].toString();
                        }
                        winner = true;
                        break;
                    }
                    
                    for (int i = 0;i < 20; i++){//Clear the preivious players rack from the screen
                        System.out.println();
                    }
                    
                    if(numberOfPlayers == 3 || wantCPU) {
                        
                        System.out.println(players[2].getPlayer() + " it is your turn! Make sure you are the only person looking at the screen and press enter.");//Delay the start of the turn to make sure nobody see other peoples racks
                        try{
                            System.in.read();
                        }catch(IOException e){
                            System.out.print(e);
                        }
                        
                        if(player3.takeTurn(discard, drawPile, player3)){//Start of players 3 turn
                            System.out.println(players[2].getPlayer() + " has Won!!!");
                            System.out.println("Here are all the players racks.");
                            for(int i = 0; i < 4; i++){
                                players[i].toString();
                            }
                            winner = true;
                            break;
                        }
                    }
                    
                    if(numberOfPlayers == 3) {
                        for (int i = 0;i < 20; i++){//Clear the preivious players rack from the screen
                            System.out.println();
                        }
                    }
                    
                    if(numberOfPlayers == 4 || wantCPU) {
                        
                        System.out.println(players[3].getPlayer() + " it is your turn! Make sure you are the only person looking at the screen and press enter.");//Delay the start of the turn to make sure nobody see other peoples racks
                        try{
                            System.in.read();
                        }catch(IOException e){
                            System.out.print(e);
                        }
                        
                        if(player4.takeTurn(discard, drawPile, player4)){//Start of players 4 turn
                            System.out.println(players[3].getPlayer() + " has Won!!!");
                            System.out.println("Here are all the players racks.");
                            for(int i = 0; i < 4; i++){
                                players[i].toString();
                            }
                            winner = true;
                            break;
                        }
                    }
                }
            }
        }while(true);
    }
}
