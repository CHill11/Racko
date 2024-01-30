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
 * @author Chris Hill
 */
public class Player {
    String player = "CPU";
    int[] rack = new int[10];
    int currentCard;
    int score = 0;//The players score
    boolean isScoreWinner = false;//Won the over all game because they scored 500 points
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
            System.out.println("If you want the discard pile card press y or press n to pick up a new card from the draw pile. Type score for your score. \nCards in the draw pile: " + drawPile.size());
            String pickup = scanner.next().toLowerCase();
            switch(pickup){
                case "y":
                    player.drawCardFromDiscard(discard, player, drawPile);//If player decided to pick up from the discard pile 
                    turnFinished = true;
                    break;
                case "n":
                    player.drawCardFromDeck(drawPile, discard, player, false);//If player decided to pick up from the draw pile
                    turnFinished = true;
                    break;
                case "score": System.out.println("Here is your score " + player.getPlayer() + ":" + player.getScore());//Player want to know their score
                    System.out.print("Press enter to continue...");
                    try{
                        System.in.read();
                    }catch(IOException e){
                        System.out.print(e);
                    }
                    return player.takeTurn(discard, drawPile, player);
                default:
                    System.out.println("You enterd an invalid entery.");//If player enterd a bad input
            }
        }
        return player.isWinner(player);//See if the player has won
    }
    
    public void drawCardFromDeck(Stack<Integer> drawPile, Stack<Integer> discard, Player player,boolean cameFromDiscard){//Get the top card from the deck
        boolean drawPileEmpty = drawPile.isEmpty();
        if(!drawPileEmpty){
            boolean cont = true;
            currentCard = drawPile.pop();
                     
            while(cont){
                cont= false;
                System.out.println("Here is your picked up card: " + currentCard);
                System.out.println("Enter the slot on the rack that you want to exchange or 0 to discard it.");
                String temp = scanner.next(); 
            
                switch(temp){//Put the card into the proper slot int the rack
                    case "0": discard.push(currentCard);
                            break;
                    case "1": discard.push(rack[0]);
                            rack[0] = currentCard;
                            break;
                    case "2": discard.push(rack[1]);
                            rack[1] = currentCard;
                            break;
                    case "3": discard.push(rack[2]);
                            rack[2] = currentCard;
                            break;
                    case "4": discard.push(rack[3]);
                            rack[3] = currentCard;
                            break;
                    case "5": discard.push(rack[4]);
                            rack[4] = currentCard;
                            break;
                    case "6": discard.push(rack[5]);
                            rack[5] = currentCard;
                            break;
                    case "7": discard.push(rack[6]);
                            rack[6] = currentCard;
                            break;
                    case "8": discard.push(rack[7]);
                            rack[7] = currentCard;
                            break;
                    case "9": discard.push(rack[8]);
                            rack[8] = currentCard;
                            break;
                    case "10": discard.push(rack[9]);
                            rack[9] = currentCard;
                            break;
                    default: System.out.println("Input invalid. Try again.");
                            cont = true;
                }
            }
            if(!cameFromDiscard){
                if(!cont){
                    System.out.println(player + "\nPress enter to continue.");
                    try{
                    System.in.read();
                    }catch(IOException e){
                        System.out.print(e);
                    }
                }
            }
        }else {//Draw pile empty. 
            while(!discard.isEmpty()){
                drawPile.push(discard.pop());
            }
            
            discard.push(drawPile.pop()); //Flip over the top card of the draw pile for the top card for the discard pile
            this.drawCardFromDeck(drawPile, discard, player, false);//
        }
    }
    
    public void drawCardFromDiscard(Stack<Integer> discard, Player player, Stack<Integer> drawPile){//Get the top card from the discard pile
        if(!discard.isEmpty()){
            boolean cont = true;
        
            currentCard = discard.pop();//Get the top card from the draw pile to show the player
            
            while(cont){
                cont = false;
                System.out.println("Here is your card from the discard pile: " + currentCard);
                System.out.println("Enter the slot on the rack that you want to exchange or 0 to draw a new card it.");
                //System.out.print(player);//Display the rack
                String temp = scanner.next();

                switch(temp){//Put the card into the proper slot int the rack
                    case "0": discard.push(currentCard);//Put the card back on the discard pile if user wants to pick up a new card
                            drawCardFromDeck(drawPile, discard, player, true);
                            break;
                    case "1": discard.push(rack[0]);
                            rack[0] = currentCard;
                            break;
                    case "2": discard.push(rack[1]);
                            rack[1] = currentCard;
                            break;
                    case "3": discard.push(rack[2]);
                            rack[2] = currentCard;
                            break;
                    case "4": discard.push(rack[3]);
                            rack[3] = currentCard;
                            break;
                    case "5": discard.push(rack[4]);
                            rack[4] = currentCard;
                            break;
                    case "6": discard.push(rack[5]);
                            rack[5] = currentCard;
                            break;
                    case "7": discard.push(rack[6]);
                            rack[6] = currentCard;
                            break;
                    case "8": discard.push(rack[7]);
                            rack[7] = currentCard;
                            break;
                    case "9": discard.push(rack[8]);
                            rack[8] = currentCard;
                            break;
                    case "10":discard.push(rack[9]);
                            rack[9] = currentCard;
                            break;
                    default: System.out.println("Input invalid. Try again.");
                            cont = true;
                }
                if(!cont){
                    System.out.println(player + "\nPress enter to continue.");
                    try{
                    System.in.read();
                    }catch(IOException e){
                        System.out.print(e);
                    }
                }
            }
        }else {
            System.out.println("An error has occured the program will now exit.");
            System.exit(200);
        }
    }

    public void createRack(Stack<Integer> drawPile) {
        //Create the rack for the player
        for(int i = 0; i < 10; i++){
            rack[i] = (int) drawPile.pop();
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
        return player.isWinner;//Pass the flag to see if the player has won
    }
    
    public boolean scoreWinner(){
        if(score >= 500){
            isScoreWinner = true;
        }
        return isScoreWinner;
    }
    
    public int calculateScore(){//calculate score for the round
        if(isWinner){
            score += 75;
            
             //Check for a run
            int longestRun = 0;
            int[] longestRunArray = new int[rack.length];//The runs length
            for(int j = 0;j < rack.length;j++){
                longestRunArray[j]++;
                for(int i = j;i < rack.length - 1; i++){
                    if(rack[i] + 1 == rack[i + 1]){
                        longestRunArray[j]++;
                    }else break;
                }
            }

            //Sort through the diffrent runs to find the longest
            for(int i = 0;i < longestRunArray.length;i++){
                if(longestRun < longestRunArray[i]) {
                    longestRun = longestRunArray[i];
                }
            }

            switch(longestRun){//Give the corect ammount of points
                case 3: score += 50;
                        break;
                case 4: score += 100;
                        break;
                case 5: score += 200;
                        break;
                case 6:;
                case 7:;
                case 8:;
                case 9:;
                case 10:score += 400;
                        break;
                default:
            }
        }else{
            score += 5;
            for(int i = 0; i <= 8; i++){
                if(rack[i] < rack[i + 1]){
                    score += 5;
                }else break;
            }
        }
        
       
        return score;
    }

    public boolean isIsWinner() {
        return isWinner;
    }

    public void setIsWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }
    
    public int getScore(){
        return score;
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

    public String toString(String endGame) {//Display the rack in an easier to read format for the end of a game
        return player + "'s rack: " + "\n10: " + rack[9] + "\n9 : " + rack[8] + "\n8 : " + rack[7] + "\n7 : " + rack[6] + "\n6 : " + rack[5] + "\n5 : " 
                + rack[4] + "\n4 : " + rack[3] + "\n3 : " + rack[2] + "\n2 : " + rack[1] + "\n1 : " + rack[0];
    }
    
    @Override
    public String toString() {//Display the rack in an easier to read format
        return "Here is your rack: " + "\n10: " + rack[9] + "\n9 : " + rack[8] + "\n8 : " + rack[7] + "\n7 : " + rack[6] + "\n6 : " + rack[5] + "\n5 : " 
                + rack[4] + "\n4 : " + rack[3] + "\n3 : " + rack[2] + "\n2 : " + rack[1] + "\n1 : " + rack[0];
    }
}
