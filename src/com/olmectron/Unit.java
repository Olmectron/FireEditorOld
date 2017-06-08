/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron;

import static com.olmectron.EmblemController.fBlockStarter;
import com.olmectron.material.MaterialDesign;
import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;

/**
 *
 * @author olmec
 */
//Hex for SpotPass and  DLC character names FF FF 00 00 00 00 FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF
public class Unit {
    private String name, hex, hexBlock, gender;
    private int intCode=-1;
    private String imageURL;
    private String hiddenName;
    public String getHiddenName(){
        return hiddenName;
    }
   
    public Unit(String name, String gender, int hexCode){
        this.name=name;
        this.hiddenName=name;
        
        this.gender=gender;
        String imageName=name.replace("'", "").toLowerCase();
        
        imageURL=MaterialDesign.customPath+"/character_images/"+imageName+".png";
        
        this.hex=Integer.toHexString(hexCode);
        this.intCode=hexCode;
    }
    public Unit copyOriginalInstance(){
        Unit copy=new Unit(name,gender,intCode);
        copy.setChild(child);
        copy.setHairEditable(hairEditable);
        return copy;
    }
    private boolean hairEditable=false;
    public void setHairEditable(boolean hairEditable){
        this.hairEditable=hairEditable;
    }
    public boolean isHairEditable(){
        return hairEditable;
    }
    
    private boolean child=false;
    public void setChild(boolean child){
        this.child=child;
    }
    public boolean isChild(){
        return child;
    }
    
    public int getAddedMovement(){
        return addedMovementProperty().get();
    }
    public void setGender(String gender){
        this.gender=gender;
    }
    public void cloneUnit(){
        this.editedBlock=this.editedBlock+this.editedBlock;
        checkEdited();
    }
    public void delete(){
        this.editedBlock="";
        checkEdited();
        
    }
    public String getGender(){
        return this.gender;
    }
    public boolean hasBirthday(){
        return getDia()!=0 && getMes()!=0;
    }
    public boolean isAvatar(){
        return this.getHexCode()==0 || this.getHexCode()==1 || this.getHexCode()==2 || this.getHexCode()==0x34;
    }
    private boolean mainUnit=false;
    public boolean isMainUnit(){
        return mainUnit && (getHexCode()==0 || getHexCode()==1);
    }
    public void setFileUnit(boolean mainUnit){
        this.mainUnit=mainUnit;
    }
    private StringProperty displayName;
    public StringProperty displayNameProperty(){
        if(displayName==null){
            displayName=new SimpleStringProperty(this,"displayName");
            displayName.set(null);
            displayName.addListener(new ChangeListener<String>(){

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    try{
                    int start=editedBlock.indexOf(EmblemController.fIdentifier)+
                            EmblemController.fIdentifier.length()+30;
                         //String nameHex=Utils.trimBinaryPair(editedBlock.substring(start,start+48));
                         //personaje.setDisplayName(Utils.getStringFromHex(nameHex));
                    String nameHex=Utils.get48HexFromTextSpaced(newValue);
                    editedBlock=editedBlock.substring(0,start)
                            +nameHex+editedBlock.substring(start+48);
                    
                    checkEdited();
                    }
                    catch(NullPointerException ex){
                        System.out.println("Unique character");
                    }
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        return displayName;
    }
    public void setDisplayName(String name){
        displayNameProperty().set(name);
        
        System.out.println(displayName);
    }
    public String getDisplayName(){
        return displayNameProperty().get();
    }
    private final static int SKILL_SET_POS=102;
    private EmblemSkill[] skillSet=new EmblemSkill[5];
    
    public EmblemSkill[] getSkillSet(){
        return skillSet;
    }
    public boolean hasSkill(int number){
        for(int i=0;i<skillSet.length;i++){
            if(skillSet[i].getCode()==number){
                return true;
            }
        }
        return false;
    }
    private void setSkills(String skillBlock){
        String skillOne=skillBlock.substring(0,2);
        String skillTwo=skillBlock.substring(4,6);
        String skillThree=skillBlock.substring(8,10);
        String skillFour=skillBlock.substring(12,14);
        String skillFive=skillBlock.substring(16,18);
        
        skillSet[0]=EmblemController.getCharacterSkill(Utils.getIntFromHex(skillOne));
        skillSet[1]=EmblemController.getCharacterSkill(Utils.getIntFromHex(skillTwo));
        skillSet[2]=EmblemController.getCharacterSkill(Utils.getIntFromHex(skillThree));
        skillSet[3]=EmblemController.getCharacterSkill(Utils.getIntFromHex(skillFour));
        skillSet[4]=EmblemController.getCharacterSkill(Utils.getIntFromHex(skillFive));
        //editedBlock=editedBlock.substring(0,38)+hexExperience+editedBlock.substring(40);
        
        
    }
    public void setSkillOne(EmblemSkill skill){
        emblemSkillOneProperty().set(skill);
        
    }public void setSkillTwo(EmblemSkill skill){
        emblemSkillTwoProperty().set(skill);
        
    }public void setSkillThree(EmblemSkill skill){
        emblemSkillThreeProperty().set(skill);
        
    }public void setSkillFour(EmblemSkill skill){
        emblemSkillFourProperty().set(skill);
        
    }
    public void setSkillFive(EmblemSkill skill){
        emblemSkillFiveProperty().set(skill);
        
    }
    private String clase;
    public String getFireClass(){
        return clase;
    }
    public boolean isSpotpass(){
        return (this.getHexCode()==2 || this.getHexCode()==0x34) && !hasBirthday() && getFace()<5;
    }
     public boolean isDLC(){
        return (this.getHexCode()==2 || this.getHexCode()==0x34) && !hasBirthday() && getFace()>5;
    }
    public String getImageURL(){
        if(this.isAvatar() && getFace()<5 && hasBirthday()){
            String nombre="";
                String gender=this.getGender();
                switch(gender){
                    case EmblemClass.FEMALE:
                        nombre="avatar_f";
                        break;
                    case EmblemClass.MALE:
                        nombre="avatar_m";
                        break;
        }
                
                String imageName="build_"+Utils.getHexPair(getBuild())+"_"+Utils.getHexPair(getFace());
                return MaterialDesign.customPath+"/character_images/"+nombre+"/"+imageName+".png";
            
            
        }
        else if(this.isAvatar() && getFace()>5 && hasBirthday()){
             String imageName=Utils.getHexPair(getFace());
             return EmblemPortrait.getPortraitURL(imageName,true);
             
            
        }
        else if(this.isSpotpass()){
            String imageName=Utils.getHexPair(getSpotpassCode());
            return MaterialDesign.customPath+"/character_images/spotpass/"+imageName+".png";
            
        }
        else if(this.isDLC()){
            String imageName=Utils.getHexPair(getFace());
            return MaterialDesign.customPath+"/character_images/spotpass/dlc"+imageName+".png";
            
        }
        else if(this.isChild() && this.isHairEditable()){
            String portraitName=MaterialDesign.customPath+"/character_images/children/"+
			getHiddenName().replace("'","").replace(" ","").toLowerCase()+".png";
            return portraitName;
						
        }
        return imageURL;
    }
    public String getHairImageURL(){
        if(this.isAvatar() && getFace()<5 && hasBirthday()){
            String nombre="";
                String gender=this.getGender();
                switch(gender){
                    case EmblemClass.FEMALE:
                        nombre="avatar_f";
                        break;
                    case EmblemClass.MALE:
                        nombre="avatar_m";
                        break;
        }
                
                String imageName="hair_"+Utils.getHexPair(getBuild())+"_"+Utils.getHexPair(getHair());
                return MaterialDesign.customPath+"/character_images/"+nombre+"/"+imageName+".png";
            
            
        }
        if(this.isChild() && this.isHairEditable()){
					int start;
					//var companionBlock=emblemCharacter.hexBlock;
					
						start=getHexBlock().indexOf(fBlockStarter)+96;
						
						
						
					
					//var start=personaje.hexBlock.indexOf("FFFFFFFFFF")+"FFFFFFFFFF".length+18;
					
                                        String hairColorString=getHexBlock().substring(start,start+6);
					setHairColor(Color.web("#"+hairColorString));
					//console.log("child hair "+hairColorString);
					String hairImageName=MaterialDesign.customPath+"/character_images/children/"+
										getHiddenName().replace("'","").replace(" ","").toLowerCase()+"_hair.png";
										//return MaterialDesign.customPath+"/character_images/"+nombre+"/"+imageName+".png";
							/*String backImageName=MaterialDesign.customPath+"/character_images/portraits/children/"+
										getName().replace("'","").replace(" ","").toLowerCase()+"_shadow.png";
							String portraitName=MaterialDesign.customPath+"/character_images/portraits/children/"+
										getName().replace("'","").replace(" ","").toLowerCase()+".png";
							*/	
                                                      return hairImageName;
				
                
        }
        return null;
    }
    
    public int getHexPosition(){
        return position;
    }
    public String getHexBlock(){
        return hexBlock;
    }
    public int getHexCode(){
        return intCode;
    }
    
    //private EmblemClass emblemClass;
    private IntegerProperty level;
    private IntegerProperty experience;
    public IntegerProperty levelProperty(){
        if(level==null){
            level=new SimpleIntegerProperty(Unit.class,"level");
            level.addListener(new ChangeListener<Number>(){

                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    hexLevel=Utils.getHexPair(newValue.intValue());
                    editedBlock=editedBlock.substring(0,36)+hexLevel+editedBlock.substring(38);
                    checkEdited();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        return level;
    }
    public IntegerProperty experienceProperty(){
        if(experience==null){
            experience=new SimpleIntegerProperty(Unit.class,"experience");
            experience.addListener(new ChangeListener<Number>(){

                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    hexExperience=Utils.getHexPair(newValue.intValue());
                    editedBlock=editedBlock.substring(0,38)+hexExperience+editedBlock.substring(40);
                    checkEdited();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        return experience;
    }
    private IntegerProperty addedMovement;
    public IntegerProperty addedMovementProperty(){
        if(addedMovement==null){
            addedMovement=new SimpleIntegerProperty(Unit.class,"addedMovement");
            addedMovement.addListener(new ChangeListener<Number>(){

                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    hexExperience=Utils.getHexPair(newValue.intValue());
                    editedBlock=editedBlock.substring(0,42)+hexExperience+editedBlock.substring(44);
                    checkEdited();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        return addedMovement;
    }
    public int getExperience(){
        return experienceProperty().get();
    }
    public void setExperience(int lev){
        experienceProperty().set(lev);
    }
    public int getLevel(){
        return levelProperty().get();
    }
    public void setLevel(int lev){
        levelProperty().set(lev);
    }
    private ObjectProperty<EmblemSkill> emblemSkillOne;
    public ObjectProperty<EmblemSkill> emblemSkillOneProperty(){
        if(emblemSkillOne==null){
            emblemSkillOne=new SimpleObjectProperty<EmblemSkill>(Unit.class,"emblemSkillOne");
            emblemSkillOne.addListener(new  ChangeListener<EmblemSkill>() {

                @Override
                public void changed(ObservableValue<? extends EmblemSkill> observable, EmblemSkill oldValue, EmblemSkill newValue) {
                   skillSet[0]=newValue;
                    String skillHex=Utils.getHexPair(newValue.getCode());
                    
                    editedBlock=editedBlock.substring(0,SKILL_SET_POS+0)+skillHex+editedBlock.substring(SKILL_SET_POS+0+2);
                    checkEdited();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            
        }
        return emblemSkillOne;
    }private ObjectProperty<EmblemSkill> emblemSkillTwo;
    public ObjectProperty<EmblemSkill> emblemSkillTwoProperty(){
        if(emblemSkillTwo==null){
            emblemSkillTwo=new SimpleObjectProperty<EmblemSkill>(Unit.class,"emblemSkillTwo");
            emblemSkillTwo.addListener(new  ChangeListener<EmblemSkill>() {

                @Override
                public void changed(ObservableValue<? extends EmblemSkill> observable, EmblemSkill oldValue, EmblemSkill newValue) {
                    skillSet[1]=newValue;
                    String skillHex=Utils.getHexPair(newValue.getCode());
                  
                    
                    editedBlock=editedBlock.substring(0,SKILL_SET_POS+4)+skillHex+editedBlock.substring(SKILL_SET_POS+4+2);
                    checkEdited();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            
        }
        return emblemSkillTwo;
    }private ObjectProperty<EmblemSkill> emblemSkillThree;
    public ObjectProperty<EmblemSkill> emblemSkillThreeProperty(){
        if(emblemSkillThree==null){
            emblemSkillThree=new SimpleObjectProperty<EmblemSkill>(Unit.class,"emblemSkillThree");
            emblemSkillThree.addListener(new  ChangeListener<EmblemSkill>() {

                @Override
                public void changed(ObservableValue<? extends EmblemSkill> observable, EmblemSkill oldValue, EmblemSkill newValue) {
                   skillSet[2]=newValue;
                    
                    String skillHex=Utils.getHexPair(newValue.getCode());
                  
                    
                    editedBlock=editedBlock.substring(0,SKILL_SET_POS+8)+skillHex+editedBlock.substring(SKILL_SET_POS+8+2);
                    checkEdited();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            
        }
        return emblemSkillThree;
    }private ObjectProperty<EmblemSkill> emblemSkillFour;
    public ObjectProperty<EmblemSkill> emblemSkillFourProperty(){
        if(emblemSkillFour==null){
            
            emblemSkillFour=new SimpleObjectProperty<EmblemSkill>(Unit.class,"emblemSkillFour");
            emblemSkillFour.addListener(new  ChangeListener<EmblemSkill>() {

                @Override
                public void changed(ObservableValue<? extends EmblemSkill> observable, EmblemSkill oldValue, EmblemSkill newValue) {
                   
                    skillSet[3]=newValue;
                    String skillHex=Utils.getHexPair(newValue.getCode());
                  
                    
                    editedBlock=editedBlock.substring(0,SKILL_SET_POS+12)+skillHex+editedBlock.substring(SKILL_SET_POS+12+2);
                    checkEdited();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            
        }
        return emblemSkillFour;
    }
    private ObjectProperty<EmblemSkill> emblemSkillFive;
    public ObjectProperty<EmblemSkill> emblemSkillFiveProperty(){
        if(emblemSkillFive==null){
            emblemSkillFive=new SimpleObjectProperty<EmblemSkill>(Unit.class,"emblemSkillFive");
            emblemSkillFive.addListener(new  ChangeListener<EmblemSkill>() {

                @Override
                public void changed(ObservableValue<? extends EmblemSkill> observable, EmblemSkill oldValue, EmblemSkill newValue) {
                   
                    skillSet[4]=newValue;
                    String skillHex=Utils.getHexPair(newValue.getCode());
                  
                    
                    editedBlock=editedBlock.substring(0,SKILL_SET_POS+16)+skillHex+editedBlock.substring(SKILL_SET_POS+16+2);
                    checkEdited();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        return emblemSkillFive;
    }
    private String classGender=null;
    public void setClassGender(String classGender){
        this.classGender=classGender;
    }
    public String getClassGender(){
        if(classGender!=null){
            return classGender;
        }
        return gender;
    }
    private ObjectProperty<EmblemClass> emblemClass;
    public ObjectProperty<EmblemClass> emblemClassProperty(){
        if(emblemClass==null){
            emblemClass=new SimpleObjectProperty<EmblemClass>(Unit.class,"emblemClass");
            emblemClass.addListener(new  ChangeListener<EmblemClass>() {

                @Override
                public void changed(ObservableValue<? extends EmblemClass> observable, EmblemClass oldValue, EmblemClass newValue) {
                    //System.out.println("Clase original "+clase);
                    if(newValue.isExclusive()){
                        clase=Utils.getHexPair(newValue.getExclusiveCode());
                      //  System.out.println("Exclusive class "+clase);
                    }
                    else{
                        if(oldValue!=null){
                            if(getClassGender().equals(EmblemClass.FEMALE)){
                                clase=Utils.getHexPair(newValue.getFemaleCode());
                            //    System.out.println("Female class "+clase);
                            }
                            else if(getClassGender().equals(EmblemClass.MALE)){
                                clase=Utils.getHexPair(newValue.getMaleCode());
                              //  System.out.println("Male class "+clase);
                            }
                        }
                        else{
                            
                        }
                    }
                    
                    editedBlock=editedBlock.substring(0,6)+clase+editedBlock.substring(8);
                    checkEdited();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        return emblemClass;
    }
    private BooleanProperty edited;
    public BooleanProperty editedProperty(){
        if(edited==null){
            edited=new SimpleBooleanProperty(Unit.class,"edited");
            edited.set(false);
        }
        return edited;
    }
    public void setEdited(boolean edit){
        editedProperty().set(edit);
    }
    public boolean getEdited(){
        return editedProperty().get();
    }
    
    public void checkEdited(){
        if(getOriginalHexBlock().equals(getEditedHexBlock())){
            setEdited(false);
            //System.out.println("Still same "+getEditedHexBlock().length());
        }
        else{
            
            setEdited(true);
            //System.out.println("Changed "+getEditedHexBlock().length());
        }
    }
    public String getClassDisplayName(){
        String g="";
        if(!getEmblemClass().isExclusive()){
            if(this.getSelectedClassCode()==this.getEmblemClass().getFemaleCode()){
                g="(F)";

            }
            else if(this.getSelectedClassCode()==this.getEmblemClass().getMaleCode()){
                g="(M)";
            }
        }
        return getEmblemClass().getName()+" "+g;
    }
    public void setEmblemClass(EmblemClass emblemClass){
        setEmblemClass(emblemClass,null);
    }
    public void setEmblemClass(EmblemClass emblemClass, String genero){
        setClassGender(genero);
        
        if(getEmblemClass()!=null && emblemClass.getName().equals(this.getEmblemClass().getName())){
                
            
               if(getEmblemClass().isExclusive()){
                        clase=Utils.getHexPair(getEmblemClass().getExclusiveCode());
                      //  System.out.println("Exclusive class "+clase);
                    }
                    else{
                        
                        if(getClassGender().equals(EmblemClass.FEMALE)){
                            clase=Utils.getHexPair(getEmblemClass().getFemaleCode());
                        //    System.out.println("Female class "+clase);
                        }
                        else if(getClassGender().equals(EmblemClass.MALE)){
                            clase=Utils.getHexPair(getEmblemClass().getMaleCode());
                          //  System.out.println("Male class "+clase);
                        }
                        
                    }
                    
                    editedBlock=editedBlock.substring(0,6)+clase+editedBlock.substring(8);
                    checkEdited();
        }

        else{
             emblemClassProperty().set(emblemClass);
        }
       
    }
    public String getEditedHexBlock(){
        return editedBlock;
    }
    public String getOriginalHexBlock(){
        return getHexBlock();
    }
    public EmblemClass getEmblemClass(){
        
        return emblemClassProperty().get();
    }
   
    public int getEmblemClassCode(){
        if(getEmblemClass().isExclusive()){
            return getEmblemClass().getExclusiveCode();
        }else{
            if(this.gender.equals(EmblemClass.MALE)){
                return getEmblemClass().getMaleCode();
            }
            else if(this.gender.equals(EmblemClass.FEMALE)){
                return getEmblemClass().getFemaleCode();
            }
        }
        return -1;
    }
    public int getSelectedClassCode(){
        return Utils.getIntFromHex(clase);
    }
    
    private String editedBlock;
    private String hexLevel=null, hexExperience=null;
    public void setHexBlock(String hexBlock){
        this.hexBlock=hexBlock;
        this.editedBlock=hexBlock;
        this.clase=hexBlock.substring(6,8);
        setLevel(Utils.getIntFromHex(hexBlock.substring(36,38)));
        setSkills(hexBlock.substring(SKILL_SET_POS, SKILL_SET_POS+20));
        setExperience(Utils.getIntFromHex(hexBlock.substring(38,40)));
        setAddedMovement(Utils.getIntFromHex(hexBlock.substring(42,44)));
        //this.hexLevel=hexBlock.substring(36,38);
        //this.hexExperience=hexBlock.substring(38,40);
       
        setEmblemClass(EmblemController.getCharacterClass(Utils.getIntFromHex(clase)));
        if(this.isAvatar()){
            parseAvatar();
        }
    }
    public void setAddedMovement(int move){
        addedMovementProperty().set(move);
    }
    private ObjectProperty<Color> hairColor;
    public ObjectProperty<Color> hairColorProperty(){
        if(hairColor==null){
            hairColor=new SimpleObjectProperty<Color>(this,"hairColor");
            hairColor.addListener(new ChangeListener<Color>(){

                @Override
                public void changed(ObservableValue<? extends Color> observable, Color oldValue, Color newValue) {
                    int startColor=editedBlock.indexOf(EmblemController.fIdentifier)
                +EmblemController.fIdentifier.length()+18;

                    int startGender=editedBlock.indexOf(EmblemController.fIdentifier)
                +EmblemController.fIdentifier.length()+82+4;
                //String gender=charBlock.substring(startGender,startGender+2);
                //String build=charBlock.substring(startGender+2,startGender+4);
                //String face=charBlock.substring(startGender+4,startGender+6);
                //String hair=charBlock.substring(startGender+6,startGender+8);
                //String hairColorString=charBlock.substring(startGender+8,startGender+14);
                    String colorString=newValue.toString().substring(2);
                    colorString=colorString.substring(0, colorString.length()-2).toUpperCase();
                    System.out.println("String color: "+colorString);
                    editedBlock=editedBlock.substring(0,startGender+8)+
                            colorString+editedBlock.substring(startGender+14);
                    editedBlock=editedBlock.substring(0,startColor)+
                            colorString+editedBlock.substring(startColor+6);
                    checkEdited();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        return hairColor;
    }
    public void setHairColor(Color hairColor){
        hairColorProperty().set(hairColor);
        
    }
    public Color getHairColor(){
        return hairColorProperty().get();
    }
    private IntegerProperty hair, face, build;
    public IntegerProperty hairProperty(){
        if(hair==null){
            hair=new SimpleIntegerProperty(this,"hair");
            hair.addListener(new ChangeListener<Number>(){

                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    
                  
                    int startGender=editedBlock.indexOf(EmblemController.fIdentifier)
                +EmblemController.fIdentifier.length()+82+4;
                //String gender=charBlock.substring(startGender,startGender+2);
                //String build=charBlock.substring(startGender+2,startGender+4);
                //String face=charBlock.substring(startGender+4,startGender+6);
                //String hair=editedBlock.substring(startGender+6,startGender+8);
                //String hairColorString=charBlock.substring(startGender+8,startGender+14);
                    String hairString=Utils.getHexPair(newValue.intValue());
                    editedBlock=editedBlock.substring(0,startGender+6)+
                            hairString+editedBlock.substring(startGender+8);
                    checkEdited();
                    

//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        return hair;
    }
    public IntegerProperty buildProperty(){
        if(build==null){
            build=new SimpleIntegerProperty(this,"build");
            build.addListener(new ChangeListener<Number>(){

                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    
                  
                    int startGender=editedBlock.indexOf(EmblemController.fIdentifier)
                +EmblemController.fIdentifier.length()+82+4;
                //String gender=charBlock.substring(startGender,startGender+2);
                //String build=charBlock.substring(startGender+2,startGender+4);
                //String face=charBlock.substring(startGender+4,startGender+6);
                //String hair=editedBlock.substring(startGender+6,startGender+8);
                //String hairColorString=charBlock.substring(startGender+8,startGender+14);
                    String buildString=Utils.getHexPair(newValue.intValue());
                    editedBlock=editedBlock.substring(0,startGender+2)+
                            buildString+editedBlock.substring(startGender+4);
                    checkEdited();
                    

//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        return build;
    }
    public IntegerProperty faceProperty(){
        if(face==null){
            face=new SimpleIntegerProperty(this,"face");
            face.addListener(new ChangeListener<Number>(){

                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    
                  
                    int startGender=editedBlock.indexOf(EmblemController.fIdentifier)
                +EmblemController.fIdentifier.length()+82+4;
                //String gender=charBlock.substring(startGender,startGender+2);
                //String build=charBlock.substring(startGender+2,startGender+4);
                //String face=charBlock.substring(startGender+4,startGender+6);
                //String hair=editedBlock.substring(startGender+6,startGender+8);
                //String hairColorString=charBlock.substring(startGender+8,startGender+14);
                    String faceString=Utils.getHexPair(newValue.intValue());
                    editedBlock=editedBlock.substring(0,startGender+4)+
                            faceString+editedBlock.substring(startGender+6);
                    checkEdited();
                    

//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        return face;
    }
    public void setFace(int code){
        faceProperty().set(code);
    }
    public int getFace(){
        return faceProperty().get();
    }
    public void setBuild(int code){
        buildProperty().set(code);
    }
    public int getBuild(){
        return buildProperty().get();
    }
    
    public void setHair(int code){
        hairProperty().set(code);
    }
    public int getHair(){
        return hairProperty().get();
    }
    private String testParseAvatar=null;
    public String getTestString(){
        if(testParseAvatar==null){
            return "";
        }
        return testParseAvatar;
    }
    private int diaCumple=0;
    private int mesCumple=0;
    public void setDia(int dia){
        diaCumple=dia;
    }
    public void setMes(int mes){
        mesCumple=mes;
    }
    public int getDia(){
        return diaCumple;
    }
    public int getMes(){
        return mesCumple;
    }
    public int spotpassCode=-1;
    public int getSpotpassCode(){
        return spotpassCode;
    }
    public void setSpotpassCode(int code){
        this.spotpassCode=code;
    }
    
    private void parseAvatar(){
        String charBlock=this.getOriginalHexBlock();
        int startGender=charBlock.indexOf(EmblemController.fIdentifier)
                +EmblemController.fIdentifier.length()+82+4;
        if(getHexCode()==2){
            startGender=charBlock.indexOf(EmblemController.dlcIdentifier)
                +EmblemController.dlcIdentifier.length()+82+4;
        
        }
        
                String gender=charBlock.substring(startGender,startGender+2);
                String build=charBlock.substring(startGender+2,startGender+4);
                String face=charBlock.substring(startGender+4,startGender+6);
                String hair=charBlock.substring(startGender+6,startGender+8);
                String hairColorString=charBlock.substring(startGender+8,startGender+14);
                setHairColor(Color.web("#"+hairColorString));
                setBuild(Utils.getIntFromHex(build));
                setFace(Utils.getIntFromHex(face));
                setHair(Utils.getIntFromHex(hair));
                //testParseAvatar=(charBlock.substring(startGender, startGender+14));
                setDia(Utils.getIntFromHex(charBlock.substring(startGender+18, startGender+20)));
                setMes(Utils.getIntFromHex(charBlock.substring(startGender+20, startGender+22)));
                setSpotpassCode(Utils.getIntFromHex(charBlock.substring(startGender+22, startGender+24)));
                testParseAvatar=""+diaCumple+" "+mesCumple;
                /*int genderInt=Utils.getIntFromHex(gender);
                if(genderInt==0){
                    person.setGender(EmblemClass.MALE);
                }
                else if(genderInt==1){
                    person.setGender(EmblemClass.FEMALE);
                }
                else{
                    System.out.println("Character without gender");
                }*/
    }
    
    
    private int position;
    public void setHexPosition(int pos){
        this.position=pos;
    }
    public String getName(){
        if(getDisplayName()!=null){
            return getDisplayName();
        }
        return name;
    }
    
}
