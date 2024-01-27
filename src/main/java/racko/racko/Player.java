/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racko.racko;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
/**
 *
 * @author paper
 */
public class Player {
    String player = "CPU";
    int[] rack = new int[10];
    int currentCard;
    boolean isWinner = false;
    Scanner scanner = new Scanner(System.in);

    public Player(int cpuNum) {
    this.player = this.player + cpuNum;
    
    }
    
    public Player(String player) {
        this.player = player;
    }
    
    public Player(){
        
    }
    
    public boolean takeTurn(Stack discard, Stack drawPile,Player player){
        boolean turnFinished = false;
        while(!turnFinished){
            System.out.println(player);//Display the rack
            System.out.println("Here is the discard pile card " + discard.peek());
            System.out.print("If you want the discard pile card press y or press n to pick up a new card from the draw pile.");
            String pickup = scanner.next().toLowerCase();
            switch(pickup.charAt(0)){
                case 'y':
                    player.drawCardFromDiscard(discard, player);//If player decided to pick up from the discard pile 
                    turnFinished = true;
                    break;
                case 'n':
                    player.drawCardFromDeck(drawPile, discard, player);//If player decided to pick up from the draw pile
                    turnFinished = true;
                    break;
                default:
                    System.out.println("You enterd an invalid entery.");//If player enterd a bad input
            }
            
        }
        return player.isWinner(player);//See if the player has won
    }
    
    public void drawCardFromDeck(Stack<Integer> deck, Stack<Integer> discard, Player player){//Get the top card from the deck
        if(!deck.isEmpty()){
            currentCard = deck.pop();
            System.out.println("Here is your picked up card: " + currentCard);
            System.out.println("Enter the slot on the rack that you want to exchange or 0 to discard it.");
            //System.out.println(player.toString());//Display the rack
            int temp = scanner.nextInt();

            switch(temp){//Put the card into the proper slot int the rack
                case 0: discard.push(currentCard);
                        break;
                case 1: discard.push(rack[0]);
                        rack[0] = currentCard;
                        break;
                case 2: discard.push(rack[1]);
                        rack[1] = currentCard;
                        break;
                case 3: discard.push(rack[2]);
                        rack[2] = currentCard;
                        break;
                case 4: discard.push(rack[3]);
                        rack[3] = currentCard;
                        break;
                case 5: discard.push(rack[4]);
                        rack[4] = currentCard;
                        break;
                case 6: discard.push(rack[5]);
                        rack[5] = currentCard;
                        break;
                case 7: discard.push(rack[6]);
                        rack[6] = currentCard;
                        break;
                case 8: discard.push(rack[7]);
                        rack[7] = currentCard;
                        break;
                case 9: discard.push(rack[8]);
                        rack[8] = currentCard;
                        break;
                case 10: discard.push(rack[9]);
                        rack[9] = currentCard;
                        break;
                default: System.out.print("Input invalid");
                        drawCardFromDeck(deck, discard, player);
            }
            System.out.println(player + "\nPress enter to continue.");
            try{
            System.in.read();
            }catch(IOException e){
                System.out.print(e);
            }
        }else {//Draw pile empty. 
            int temp = discard.pop();
            while(!discard.isEmpty()) {//Flip over the discard pile
                deck.push(discard.pop());
            }
            discard.push(temp); //Keep the top card of the discard pile
            this.drawCardFromDeck(deck, discard, player);
        }
    }
    
    public void drawCardFromDiscard(Stack<Integer> discard, Player player){//Get the top card from the discard pile
        if(!discard.isEmpty()){
            currentCard = discard.pop();
            System.out.println("Here is your picked up card from the discard pile: " + currentCard);
            System.out.println("Enter the slot on the rack that you want to exchange or 0 to discard it.");
            //System.out.print(player);//Display the rack
            int temp = scanner.nextInt();

            switch(temp){//Put the card into the proper slot int the rack
                case 0: discard.push(currentCard);
                        break;
                case 1: discard.push(rack[0]);
                        rack[0] = currentCard;
                        break;
                case 2: discard.push(rack[1]);
                        rack[1] = currentCard;
                        break;
                case 3: discard.push(rack[2]);
                        rack[2] = currentCard;
                        break;
                case 4: discard.push(rack[3]);
                        rack[3] = currentCard;
                        break;
                case 5: discard.push(rack[4]);
                        rack[4] = currentCard;
                        break;
                case 6: discard.push(rack[5]);
                        rack[5] = currentCard;
                        break;
                case 7: discard.push(rack[6]);
                        rack[6] = currentCard;
                        break;
                case 8: discard.push(rack[7]);
                        rack[7] = currentCard;
                        break;
                case 9: discard.push(rack[8]);
                        rack[8] = currentCard;
                        break;
                case 10:discard.push(rack[9]);
                        rack[9] = currentCard;
                        break;
                default: System.out.print("Input invalid");
                        drawCardFromDiscard(discard, player);
            }
            System.out.println(player + "\nPress enter to continue.");
            try{
            System.in.read();
            }catch(IOException e){
                System.out.print(e);
            }
        }else {
            System.out.println("An error has occured the program will now exit.");
            System.exit(200);
        }
    }

    public boolean isWinner(Player player) {//See if the player has won
        if(rack[0] < rack[1]) {
            if(rack[1] < rack[2]) {
                if(rack[2] < rack[3]) {
                    if(rack[3] < rack[4]) {
                        if(rack[4] < rack[5]) {
                            if(rack[5] < rack[6]) {
                                if(rack[6] < rack[7]) {
                                    if(rack[7] < rack[8]) {
                                        if(rack[8] < rack[9]) {
                                            player.isWinner = true;//Set to ture if player has won
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return player.isWinner;//Pass the flag to seee if the player has won
    }
    
    public String getPlayer() {
        return player;
    }

    public int[] getRack() {
        return rack;
    }

    public void setRack(int[] rack) {
        this.rack = rack;
    }

    @Override
    public String toString() {//Display the rack in an easier to read format
        return "Here is your rack: " + "\n10: " + rack[9] + "\n9 : " + rack[8] + "\n8 : " + rack[7] + "\n7 : " + rack[6] + "\n6 : " + rack[5] + "\n5 : " 
                + rack[4] + "\n4 : " + rack[3] + "\n3 : " + rack[2] + "\n2 : " + rack[1] + "\n1 : " + rack[0];
    }
}
