/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racko.racko;

import java.util.Stack;

/**
 *
 * @author Chris Hill
 */
public class Create_deck {
    static public int[] deckCreate(int numOfPlayers){
        int[] deck;
        switch(numOfPlayers){
            case 2 -> {
                deck = new int[40];//Create the deck
                for(int i = 1; i <= 40; i++){//Add the numbers to the deck
                    deck[i - 1] = i;
                }
                deck = swap(deck);
                return deck;
            }
                    
                
            case 3 -> {
                deck = new int[50];//Create the deck
                for(int i = 1; i <= 50; i++){//Add the numbers to the deck
                    deck[i - 1] = i;
                }
                deck = swap(deck);
                return deck;
            }
                    
              
            case 4 -> {
                deck = new int[60];//Create the deck
                for(int i = 1; i <= 60; i++){//Add the numbers to the deck
                    deck[i - 1] = i;
                }
                deck = swap(deck);
                return deck;
            }
             
            default -> {
                deck = new int[0];
                return deck;
            }
        }        
    }


    static private int[] swap(int[] deck){//Shuffles the deck 500 times
        int temp;
        
        for(int i = 0;i < 500;i++){
            int seed = (int)(Math.random() * deck.length); //Gets a number between 0 and the max number need for the ammount of players used to select a card in the pile
            int seed1 = (int)(Math.random() * deck.length);//Gets a number between 0 and the max number need for the ammount of players used to select a card in the pile
            temp = deck[seed];//Store the number that is to be switched in the deck
            deck[seed] = deck[seed1];//Move the number that is at deck[seed1] to the location deck[seed]
            deck[seed1] = temp;//Move the number that was at deck[seed] to deck[seed1]
        }
        return deck;
    }
    
    static public Stack makedrawPile(int[] deck){
        Stack drawPile = new Stack();
        for(int card: deck){
            drawPile.push(card);
        }
        //System.out.println("Draw pile " + drawPile.toString());
        return drawPile;
    }
    
    static public Stack makeDiscardPile(Stack drawPile){
        Stack discard = new Stack();
        discard.push(drawPile.pop());
        return discard;
    }
    
    static public Stack flipDiscardPile(Stack drawPile, Stack discard){
        
        if(drawPile.isEmpty()){
            while(!discard.isEmpty()){//Keep fliping cards over till the discard pile is empty
                drawPile.push(discard.pop());
            }
        }
        System.out.println("The draw pile has been refreshed.");
        return drawPile;
    }
    
    static public int[] sort(int[] deck){
        int[] sortedDeck = deck;
        
        for(int i = 0;i < deck.length;i++){
            for(int j = i + 1;j < deck.length; j++) {
                if(deck[i] < deck[j]){
                    int temp = deck[j];
                    deck[j] = deck[i];
                    deck[i] = temp;
                }
            }
        }
        
        return sortedDeck;
    }
}