/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racko.racko;

import java.util.Arrays;
import java.util.Stack;

/**
 *
 * @author paper
 */
public class Cpu_Player extends Player{ 
    
    public Cpu_Player(int cpuNum) {
        super(cpuNum);
    }
        
    @Override
    public boolean takeTurn(Stack discard, Stack drawPile,Player player){
        int card = (int) discard.peek();
        
        System.out.println( "Before turn:\n" + player.toString());//temp
        
        
        if(shouldPickUpDiscard(card)){
            card = (int)discard.pop();
            swap(rack, discard, card);
        }else{
            card = (int)drawPile.pop();
            swap(rack,discard,card);
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
            if(card >= j && card <= j + 5){//Test if discard would be a good fit
                discard.push(rack[i]);
                rack[i] = card;
            }
            j += 6;
        }
        return discard;
    }
}
