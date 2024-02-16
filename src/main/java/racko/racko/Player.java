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
    boolean isWinner = false;//Won the round and got a racko
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
        if(discard.isEmpty()){//Make sure discard pile is not empty after a draw pile refresh
            Create_deck.makeDiscardPile(drawPile);
        }
        while(!turnFinished){
            System.out.println(player);//Display the rack
            System.out.println("Here is the discard pile card " + discard.peek());
            System.out.println("If you want the discard pile card press y or press n to pick up a new card from the draw pile. Type help for more in.");// \nCards in the draw pile: " + drawPile.size());
            String pickup = scanner.nextLine().trim().toLowerCase();
            switch(pickup){
                case "y" -> {
                    player.drawCardFromDiscard(discard, player, drawPile);//If player decided to pick up from the discard pile 
                    turnFinished = true;
                }
                case "n" -> {
                    player.drawCardFromDeck(drawPile, discard, player, false);//If player decided to pick up from the draw pile
                    turnFinished = true;
                }
                case "help" -> {
                        help(player);//Display the options to the player
                }
                case "" -> {}
                default -> System.out.println("You enterd an invalid entery.");//If player enterd a bad input
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
                System.out.println("Enter the slot on the rack that you want to exchange or 0 to discard it. Type help for other options.");
                String temp = scanner.nextLine().toLowerCase(); 
            
                switch(temp){//Put the card into the proper slot int the rack
                    case "0" -> discard.push(currentCard);
                    case "1" -> {
                        discard.push(rack[0]);
                        rack[0] = currentCard;
                    }
                    case "2" -> {
                        discard.push(rack[1]);
                        rack[1] = currentCard;
                    }
                    case "3" -> {
                        discard.push(rack[2]);
                        rack[2] = currentCard;
                    }
                    case "4" -> {
                        discard.push(rack[3]);
                        rack[3] = currentCard;
                    }
                    case "5" -> {
                        discard.push(rack[4]);
                        rack[4] = currentCard;
                    }
                    case "6" -> {
                        discard.push(rack[5]);
                        rack[5] = currentCard;
                    }
                    case "7" -> {
                        discard.push(rack[6]);
                        rack[6] = currentCard;
                    }
                    case "8" -> {
                        discard.push(rack[7]);
                        rack[7] = currentCard;
                    }
                    case "9" -> {
                        discard.push(rack[8]);
                        rack[8] = currentCard;
                    }
                    case "10" -> {
                        discard.push(rack[9]);
                        rack[9] = currentCard;
                    }
                    case "help" -> {
                        help(player);
                        cont = true;
                    }
                    case "" -> {}
                    default -> {
                        System.out.println("Input invalid. Try again.");
                        cont = true;
                    }
                }
                //Put the card into the proper slot int the rack
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
            drawPile = Create_deck.flipDiscardPile(drawPile,discard);
            
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
                System.out.println("Enter the slot on the rack that you want to exchange or 0 to draw a new card it. Type help for other options.");
                //System.out.print(player);//Display the rack
                String temp = scanner.nextLine();

                switch(temp){//Put the card into the proper slot int the rack
                    case "0" -> {
                        discard.push(currentCard);//Put the card back on the discard pile if user wants to pick up a new card
                        drawCardFromDeck(drawPile, discard, player, true);
                    }
                    case "1" -> {
                        discard.push(rack[0]);
                        rack[0] = currentCard;
                    }
                    case "2" -> {
                        discard.push(rack[1]);
                        rack[1] = currentCard;
                    }
                    case "3" -> {
                        discard.push(rack[2]);
                        rack[2] = currentCard;
                    }
                    case "4" -> {
                        discard.push(rack[3]);
                        rack[3] = currentCard;
                    }
                    case "5" -> {
                        discard.push(rack[4]);
                        rack[4] = currentCard;
                    }
                    case "6" -> {
                        discard.push(rack[5]);
                        rack[5] = currentCard;
                    }
                    case "7" -> {
                        discard.push(rack[6]);
                        rack[6] = currentCard;
                    }
                    case "8" -> {
                        discard.push(rack[7]);
                        rack[7] = currentCard;
                    }
                    case "9" -> {
                        discard.push(rack[8]);
                        rack[8] = currentCard;
                    }
                    case "10" -> {
                        discard.push(rack[9]);
                        rack[9] = currentCard;
                    }
                    case "help" -> {
                        help(player);
                        cont = true;
                    }
                    case "" -> {}
                    default -> {
                        System.out.println("Input invalid. Try again.");
                        cont = true;
                    }
                }
                //Put the card into the proper slot int the rack
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
    
    private void help(Player player){     
        boolean exit = false;
        
        while(!exit){
            System.out.println("Type \"rack, rules, or score\" for more information or exit to continue the game.");
            String temp = scanner.nextLine().toLowerCase().trim();
            
            switch(temp){
                case "score" ->  {
                    player.displayScore(player);
                }
                case "rack", "display", "display rack", "displayrack" ->  {
                    System.out.println(player);
                }
                case "rules" ->  {
                    Racko.rules();
                }
                case "exit" ->  {
                    exit = true;
                }
                case "" -> {}
                default -> System.out.println("You enterd an invalid entery.");//If player enterd a bad input
            }
        }
    }
    
    private void displayScore(Player player){
        System.out.println(player.getPlayer() + " score is: " + player.getScore());
    }
    
    public boolean getIsWinner() {
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

    public boolean isScoreWinner() {
        return isScoreWinner;
    }
    
    public void endGameWinner(Player player){
        System.out.print(player.getPlayer() + " has won!!! With a score of " + player.getScore());
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