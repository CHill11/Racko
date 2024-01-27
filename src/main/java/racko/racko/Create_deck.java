/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racko.racko;

import java.util.Stack;
/**
 *
 * @author paper
 */
public class Create_deck {
    static public int[] deckCreate(){
    int[] deck = new int[60];//Create the deck
        for(int i = 1;i <= deck.length;i++){//Add the numbers to the deck
            deck[i - 1] = i;
        }
        
        deck = swap(deck);
        return deck;
    }


    static public int[] swap(int[] deck){//Shuffles the deck 500 times
        int temp;
        
        for(int i = 0;i < 500;i++){
            int seed = (int)(Math.random() * 60); //Gets a number between 0 and 59 to select a card in the pile
            int seed1 = (int)(Math.random() * 60);//Gets a number between 0 and 59 to select a card in the pile
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
        return drawPile;
    }
    
    static public Stack makeDiscardPile(Stack drawPile){
        Stack discard = new Stack();
        discard.push(drawPile.pop());
        return discard;
    }
}