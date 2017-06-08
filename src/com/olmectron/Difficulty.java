/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron;

/**
 *
 * @author olmec
 */
public class Difficulty {
    public static final int NORMAL=0;
    public static final int HARD=1;
    public static final int LUNATIC=2;
    public static final int LUNATIC_PLUS=3;
    private int difficulty;
    private String difString=null;
    public String getDifficulty(){
        return difString;
    }
    public int getIntDifficulty(){
        return difficulty;
    }
    private void setDifficulty(String dif){
        this.difString=dif;
    }
    public Difficulty(int dif){
        difficulty=dif;
        switch(dif){
            case NORMAL:
               setDifficulty("Normal");
                break;
            case HARD:
                setDifficulty("Hard");
                break;
            case LUNATIC:
                setDifficulty("Lunatic");
                break;
            case LUNATIC_PLUS:
                setDifficulty("Lunatic +");
                break;
            default:
                throw new IllegalStateException("Invalid game difficulty");
                
        }
    }
}
