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
public class Cpu_Player extends Player{ 
    
    public Cpu_Player(int cpuNum) {
        super(cpuNum);
    }
        
    @Override
    public boolean takeTurn(Stack discard, Stack drawPile,Player player){
        if(discard.isEmpty()){//Make sure the discard pile is not empty
            if(drawPile.isEmpty()){//Make sure that the draw pile is not empty
                System.out.println("A CPU player tried to get a card from the drawpile and it was empty and the discard ple is also empty.");
            }
            discard.push(drawPile.pop());//Add a card to the discard pile if it is empty
        }
        
        int card = (int) discard.peek();
        
        System.out.println( "Before turn:\n" + player.toString());//temp
        
        
        if(shouldPickUpDiscard(card)){//See if the discard card is usefull
            card = (int)discard.pop();//Get the card from the discard pile
            swap(rack, discard, card);//Put the discard where it is usefull
        }else{
            if(drawPile.isEmpty()){//See if the draw pile is empty
                Create_deck.flipDiscardPile(drawPile, discard);//flip the discard pile back into the draw pile
            }else{
                card = (int)drawPile.pop();//Draw a new card from the draw pile 
                swap(rack,discard,card);//Find out where to put the card in the rack
            }
        }
        
        System.out.println( "\nAfter turn:\n" + player.toString());//temp
        
        System.out.println("Cpu player " + player.getPlayer() + " has taken their turn.");
        return player.isWinner(player);//See if the cpu has won
    }
    
    private boolean shouldPickUpDiscard(int discard) {
        int j = 1;
        for(int i = 0;i < 10;i++){
            if(discard >= j && discard <= j + 5){//Test if discard would be a good fit
                if(rack[i] >= j && rack[i] <= j + 5){//See if the card that is already in the rack should stay
                    System.out.print("The discard is: " + discard + " and the rack was: " + rack[i]);//Temp
                    return false;
                }else {
                    System.out.print("The discard is: " + discard + " and the rack was: " + rack[i]);//Temp
                    return true;
                }
            }
            j += 6;
        }
        return false;
    }
    
    private Stack swap(int[] rack, Stack discard,int card){
        int j = 1;
        for(int i = 0;i < 10;i++){
            if(card >= j && card <= j + 5){//Test if card would be a good fit
                discard.push(rack[i]);
                rack[i] = card;
            }
            j += 6;
        }
        return discard;
    }
}