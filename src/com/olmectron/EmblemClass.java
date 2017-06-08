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
public class EmblemClass {
    public static final String MALE="M";
    
   public static final String FEMALE="F";
    
   public static final String MONSTER="X";
   public static final String UNDEFINED="U";
    private String name, gender=null;
    
    public EmblemClass(String name, int maleCode, int femaleCode){
        this.name=name;
        this.maleCode=maleCode;
        this.femaleCode=femaleCode;
    }
    
    
    private boolean exclusive=false;
    public boolean isExclusive(){
        return exclusive;
    }
    public EmblemClass(int code,String name, String gender){
        //this.code=code;
        this.name=name;
        this.gender=gender;
        this.exclusive=true;
        this.exclusiveCode=code;
       
    }
    private int exclusiveCode=-1, femaleCode=-1, maleCode=-1;
    public String getExclusiveGender(){
        return gender;
    }
    public int getMaleCode(){
        if(exclusive){
            throw new IllegalStateException("This unit is exclusive gender: "+getExclusiveGender());
        }
        return maleCode;
    }
    public int getFemaleCode(){
        if(exclusive){
            throw new IllegalStateException("This unit is exclusive gender: "+getExclusiveGender());
        }
        return femaleCode;
    }
    public int getExclusiveCode(){
        if(!exclusive){
            throw new IllegalStateException("This unit isn't exclusive gender");
        }
        return exclusiveCode;
    }
    public String getName(){
        return name;
    }
   
}
