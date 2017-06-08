/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron;

import com.olmectron.material.MaterialDesign;
import com.olmectron.material.components.ColoredStackPane;
import com.olmectron.material.components.FlatButton;
import com.olmectron.material.components.MaterialCircleImageView;
import com.olmectron.material.components.MaterialDropdownMenu;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author olmec
 */
public class EmblemController {
    //private static BufferedReader txtReader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(MaterialDesign.customPath+"/text/skills.txt")));
    public static String characterHeader=new String("54494E55"); //TINU
    
    public static String characterFooter=new String("FF49464552"); //ÿIFER
    public static String AVATAR_NAME=null;
    public static void setAvatarNameCode(String name){
        AVATAR_NAME=Utils.trimBinaryPair(name);
    }
    
    public static ColoredStackPane setAvatarHair(Unit unit, Pane imagePane, int fitWidth, ImageView characterImage){
        
        imagePane.getChildren().clear();
        imagePane.getChildren().add(characterImage);
        try{
        Image image=new Image(unit.getHairImageURL());
        MaterialCircleImageView hairView=new MaterialCircleImageView(image,fitWidth,true);
         hairView.toSquareImage();         
        //ImageView hairBackView=new ImageView(new Image(unit.getHairImageURL().replace("hair", "back")));
        ColoredStackPane hairBackPane=new ColoredStackPane();
                //hairBackView.setFitWidth(fitWidth);
        //hairBackView.setPreserveRatio(true);
               /* imagePane.widthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                hairBackView.setFitWidth(newValue.intValue());
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });*/
                //hairBackView.setFitWidth(imagePane.getWidth());
       
                //hairBackView.toSquareImage();
               
                ImageView back;
                if(unit.isChild() && unit.isHairEditable()){
                back=new ImageView(new Image(unit.getHairImageURL().replace("hair", "shadow")));
                }
                else{
                     back=new ImageView(new Image(unit.getHairImageURL().replace("hair", "back")));
               
                }
                back.setFitWidth(fitWidth);
                back.setPreserveRatio(true);
                
//back.toSquareImage();
                
                //hairBackView.setClip(back);
                hairBackPane.setClip(back);
                hairBackPane.setPadding(new Insets(20,0,0,0));
             /*   ColorAdjust monochrome = new ColorAdjust();
        //monochrome.setSaturation(-1.0);
                Blend blush = new Blend(
                BlendMode.MULTIPLY,
                monochrome,
                new ColorInput(
                        0,
                        0,
                        fitWidth,//hairBackView.getImage().getWidth(),
                        fitWidth,//hairBackView.getImage().getHeight(),
                        unit.getHairColor()
                )
        );*/
                
               hairBackPane.setColorStyle(unit.getHairColor());
                //hairBackView.setEffect(blush);
               hairBackPane.setPrefWidth(fitWidth);
               hairBackPane.setPrefHeight(fitWidth);
                imagePane.getChildren().addAll(hairBackPane,hairView);
              return hairBackPane;
        }
        catch(NullPointerException cd){
            return null;
        }
               //hairBackView.setStyle(hairBackView.getStyle()+"-fx-effect: innershadow( gaussian , #2196F3 , 7 , 1 , 1 , 1 );");
        
    }
    public static String getAvatarNameCode(){
        return AVATAR_NAME;
    }
    private static ArrayList<EmblemClass> characterClasses=null;
    
   /* public static String[] characterGenders=new String[]{
        EmblemClass.MALE,
        EmblemClass.FEMALE,
        EmblemClass.MALE, //Este es UNKNOWN EN REALIDAD
    EmblemClass.MALE,
EmblemClass.FEMALE,
EmblemClass.MALE,
EmblemClass.MALE,
EmblemClass.FEMALE,
EmblemClass.MALE,
EmblemClass.MALE,
EmblemClass.FEMALE,
EmblemClass.MALE,
EmblemClass.FEMALE,
EmblemClass.MALE,

EmblemClass.MALE,
EmblemClass.FEMALE,
EmblemClass.FEMALE,
EmblemClass.MALE,
EmblemClass.FEMALE,
EmblemClass.MALE,
EmblemClass.FEMALE,
EmblemClass.MALE,
EmblemClass.FEMALE,
EmblemClass.FEMALE,
EmblemClass.FEMALE,
EmblemClass.MALE,
EmblemClass.FEMALE,
EmblemClass.FEMALE,
EmblemClass.MALE,
EmblemClass.FEMALE,
EmblemClass.MALE,
EmblemClass.FEMALE,
EmblemClass.MALE,
EmblemClass.MALE,
EmblemClass.MALE,
EmblemClass.FEMALE,
EmblemClass.FEMALE,
EmblemClass.FEMALE,
EmblemClass.MALE,
EmblemClass.MALE,
EmblemClass.FEMALE,
EmblemClass.MALE,
EmblemClass.MALE,
EmblemClass.FEMALE,
EmblemClass.FEMALE,
EmblemClass.FEMALE,
EmblemClass.MALE,
EmblemClass.MALE,
EmblemClass.FEMALE,
EmblemClass.MALE,
EmblemClass.FEMALE,
EmblemClass.MALE,
EmblemClass.MALE
    
    };*/
    /*public static String[] characters=new String[]{
        "Avatar_M",
        "Avatar_F",
        "DLCCharacter",
    "Chrom",
"Lissa",
"Frederick",
"Virion",
"Sully",
"Vaike",
"Stahl",
"Miriel",
"Kellam",
"Sumia",
"Lon'qu",

"Ricken",
"Maribelle",
"Panne",
"Gaius",
"Cordelia",
"Gregor",
"Nowi",
"Libra",
"Tharja",
"Olivia",
"Cherche",
"Henry",
"Lucina",
"Say'ri",
"Basilio",
"Flavia",
"Donnel",
"Anna",
"Owain",
"Inigo",
"Brady",
"Kjelle",
"Cynthia",
"Severa",
"Gerome",
"Morgan_M",
"Morgan_F",
"Yarne",
"Laurent",
"Noire",
"Nah",
"Tiki",
"Gangrel",
"Walhart",
"Emmeryn",
"Yen'fay",
"Aversa",
"Priam",
"Marth"
    
    };*/
    /*public static int[] characterCodes=new int[]{
    0x00,
        0x01,
        0x02,
0x03,
0x04,
0x05,
0x06,
0x07,
0x08,
0x09,
0x0A,
0x0B,
0x0C,
0x0D,
0x0E,
0x0F,
0x10,
0x11,
0x12,
0x13,
0x14,
0x15,
0x16,
0x17,
0x18,
0x19,
0x1A,
0x1B,
0x1C,
0x1D,
0x1E,
0x1F,
0x20,
0x21,
0x22,
0x23,
0x24,
0x25,
0x26,
0x27,
0x28,
0x29,
0x2A,
0x2B,
0x2C,
0x2D,
0x2E,
0x2F,
0x30,
0x31,
0x32,
0x33,
0x34
    
    
    };*/
    public static EmblemSkill getCharacterSkill(int code){
        for (EmblemSkill characterSkill : getCharacterSkills()) {
            if(characterSkill.getCode()==code){
                return characterSkill;
            }
        }
        return null;
    }
    public static EmblemClass getCharacterClass(int code){
        for (EmblemClass characterClass : getCharacterClasses()) {
            if(characterClass.isExclusive()){
                if(characterClass.getExclusiveCode()==code){
                    return characterClass;
                }
            }
            else{
                
                if(characterClass.getFemaleCode()==code || characterClass.getMaleCode()==code){
                    return characterClass;
                }
            }
        }
        return null;
    }
    private static ArrayList<EmblemSkill> characterSkills=null;
    
    public static ArrayList<EmblemSkill> getCharacterSkills(){
        if(characterSkills==null){
            characterSkills=new ArrayList<EmblemSkill>();
        InputStream in = EmblemController.class.getResourceAsStream(MaterialDesign.customPath+"/text/skills.txt");
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
                input.lines().forEach(new Consumer<String>(){

                        @Override
                        public void accept(String t) {
                            StringTokenizer emblemLine=new StringTokenizer(t,"&&");
                             int code=Utils.getIntFromHex(emblemLine.nextToken().trim());
                            
                            String eName=Utils.trim(emblemLine.nextToken().trim());
                            String eDesc=Utils.trim(emblemLine.nextToken());
                           characterSkills.add(new EmblemSkill(eName, eDesc, code));
                            //System.out.println("Linea "+t);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
        }
       /* try {
            File f=new File(MaterialDesign.customPath+"/text/skills.txt");
            Files.lines(f.toPath()).forEach(new Consumer<String>(){
                
                @Override
                public void accept(String t) {
                    System.out.println("Linea "+t);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
             try {
            if(txtReader.ready()){
            System.out.println("JAMON: "+txtReader.readLine());
            }
            } catch (IOException ex) {
            Logger.getLogger(EmblemController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return null;
        } catch (IOException ex) {
            Logger.getLogger(EmblemController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return characterSkills;
    }
    private static ArrayList<Unit> characters;
    public static ArrayList<Unit> getCharacters(){
        if(characters==null){
            characters=new ArrayList<Unit>();
            InputStream in = EmblemController.class.getResourceAsStream(MaterialDesign.customPath+"/text/units.txt");
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
                input.lines().forEach(new Consumer<String>(){

                        @Override
                        public void accept(String t) {
                            StringTokenizer emblemLine=new StringTokenizer(t,",");
                             int code=Utils.getIntFromHex(emblemLine.nextToken().trim());
                            
                            String name=Utils.trim(emblemLine.nextToken().trim());
                            String genero=Utils.trim(emblemLine.nextToken());
                            String japaneseName=Utils.trim(emblemLine.nextToken());
                            boolean child=Boolean.parseBoolean(Utils.trim(emblemLine.nextToken()));
                            boolean hairEditable=Boolean.parseBoolean(Utils.trim(emblemLine.nextToken()));
                            Unit character=new Unit(name, genero, code);
                            character.setChild(child);
                            character.setHairEditable(hairEditable);
                            characters.add(character);
                            
                            //System.out.println("Linea "+t);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
        }
        return characters;
    }
    public static ArrayList<EmblemClass> getCharacterClasses(){
        //getCharacterSkills();
        if(characterClasses==null){
            characterClasses=new ArrayList<EmblemClass>();
            
            characterClasses.add(new EmblemClass("Lord",0x00,0x01));
            characterClasses.add(new EmblemClass("Great Lord",0x02,0x03));
            
            characterClasses.add(new EmblemClass("Tactician",0x04,0x05));
            characterClasses.add(new EmblemClass("Grandmaster",0x06,0x07));
            characterClasses.add(new EmblemClass("Cavalier",0x08,0x09));
            characterClasses.add(new EmblemClass("Knight",0x0A,0x0B));
            characterClasses.add(new EmblemClass("Paladin",0x0C,0x0D));
            characterClasses.add(new EmblemClass("Great Knight",0x0E,0x0F));
            characterClasses.add(new EmblemClass("General",0x10,0x11));
            
            
            characterClasses.add(new EmblemClass(0x12,"Barbarian",EmblemClass.MALE));
            characterClasses.add(new EmblemClass(0x13,"Fighter",EmblemClass.MALE));
            
            characterClasses.add(new EmblemClass("Mercenary",0x14,0x15));
            characterClasses.add(new EmblemClass("Archer",0x16,0x17));
            
            characterClasses.add(new EmblemClass(0x18,"Berserker",EmblemClass.MALE));
            characterClasses.add(new EmblemClass(0x19,"Warrior",EmblemClass.MALE));
            
            characterClasses.add(new EmblemClass("Hero",0x1A,0x1B));
            characterClasses.add(new EmblemClass("Bow Knight",0x1C,0x1D));
            characterClasses.add(new EmblemClass("Sniper",0x1E,0x1F));
            characterClasses.add(new EmblemClass("Myrmidon",0x20,0x21));
            characterClasses.add(new EmblemClass("Thief",0x22,0x23));
            characterClasses.add(new EmblemClass("Swordmaster",0x24,0x25));
            characterClasses.add(new EmblemClass("Assassin",0x26,0x27));
            characterClasses.add(new EmblemClass("Trickster",0x28,0x29));
            
            
            characterClasses.add(new EmblemClass(0x2A,"Pegasus Knight",EmblemClass.FEMALE));
            characterClasses.add(new EmblemClass(0x2B,"Falcon Knight",EmblemClass.FEMALE));
            characterClasses.add(new EmblemClass(0x2C,"Dark Flier",EmblemClass.FEMALE));
            
            characterClasses.add(new EmblemClass("Wyvern Rider",0x2D,0x2E));
            characterClasses.add(new EmblemClass("Wyvern Lord",0x2F,0x30));
            characterClasses.add(new EmblemClass("Griffon Rider",0x31,0x32));
            
            characterClasses.add(new EmblemClass(0x33,"Troubadour",EmblemClass.FEMALE));
            characterClasses.add(new EmblemClass(0x34,"Priest",EmblemClass.MALE));
            characterClasses.add(new EmblemClass(0x35,"Cleric",EmblemClass.FEMALE));
            
            characterClasses.add(new EmblemClass("Mage",0x36,0x37));
            characterClasses.add(new EmblemClass("Dark Mage",0x38,0x39));
            
            characterClasses.add(new EmblemClass(0x3A,"Valkyrie",EmblemClass.FEMALE));
            characterClasses.add(new EmblemClass(0x3B,"War Monk",EmblemClass.MALE));
            characterClasses.add(new EmblemClass(0x3C,"War Cleric",EmblemClass.FEMALE));
            
            characterClasses.add(new EmblemClass("Sage",0x3D,0x3E));
            characterClasses.add(new EmblemClass("Dark Knight",0x3F,0x40));
            characterClasses.add(new EmblemClass("Sorcerer",0x41,0x42));

            characterClasses.add(new EmblemClass(0x43,"Dancer",EmblemClass.FEMALE));
            characterClasses.add(new EmblemClass(0x44,"Manakete",EmblemClass.FEMALE));
            
            characterClasses.add(new EmblemClass("Taguel",0x45,0x46));
               
            characterClasses.add(new EmblemClass(0x47,"Soldier",EmblemClass.MALE));
            characterClasses.add(new EmblemClass(0x48,"Villager",EmblemClass.MALE));
            characterClasses.add(new EmblemClass(0x49,"Merchant",EmblemClass.FEMALE));
            characterClasses.add(new EmblemClass(0x4A,"Revenant",EmblemClass.MONSTER));
            characterClasses.add(new EmblemClass(0x4B,"Entombed",EmblemClass.MONSTER));
            characterClasses.add(new EmblemClass(0x4C,"Conqueror",EmblemClass.MALE));
            characterClasses.add(new EmblemClass(0x4D,"Lodestar",EmblemClass.MALE));
            characterClasses.add(new EmblemClass(0x4E,"Grima",EmblemClass.MONSTER));
            characterClasses.add(new EmblemClass(0x4F,"Mirage",EmblemClass.MONSTER));
            
            characterClasses.add(new EmblemClass(0x50,"Dread Fighter",EmblemClass.MALE));
            characterClasses.add(new EmblemClass(0x51,"Bride",EmblemClass.FEMALE));
            
        }
        
        
        return characterClasses;
    }
     public static Unit getCharacter(int code){
        
        for(Unit character : getCharacters()){
            if(character.getHexCode()==code){
                return character;
            }
        }
        return null;
        
    }
    public static Unit getCharacterInstance(int code){
        for(Unit character: getCharacters()){
            if(character.getHexCode()==code){
                Unit copia=character.copyOriginalInstance();
                
                return copia;
            }
        }
        return null;
    }
    public static String getHexPair(int hex){
        String returnHex;
        if(hex<=0x0F){
        returnHex=("0"+Integer.toHexString(hex));
        
        }
        else{
            returnHex=(Integer.toHexString(hex));
        }
        return returnHex.toUpperCase();
    }
    public static String getCharacterBlock(String fullHex){
        int start=fullHex.indexOf(characterHeader)+characterHeader.length();
        int end=fullHex.indexOf(characterFooter);
        return fullHex.substring(start,end);
    }
    private static String characterIdentifier="FFFF04";
    private static ArrayList<Integer> nadaExample(String hexBlock){
        String hexCopy=hexBlock;
        int startIndex=hexCopy.indexOf(characterIdentifier)-48;
        
        //System.out.println("INICIO "+startIndex);
        int charactersRemoved=0;
        ArrayList<Integer> positionList=new ArrayList<Integer>();
        
        while(startIndex>=0){
            System.out.println("Start: "+startIndex);
            //try{
           //if(hexCopy.substring(startIndex+charactersRemoved+0,startIndex+charactersRemoved+2).equals("07") 
            //       && hexCopy.substring(startIndex+charactersRemoved+4,startIndex+charactersRemoved+6).equals("00")){
                positionList.add(startIndex+charactersRemoved);
           /*}}
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }*/
           String toReplace=hexCopy.substring(startIndex, 54+startIndex);
           charactersRemoved=charactersRemoved+toReplace.length();
           hexCopy=hexCopy.replace(toReplace,"");
           startIndex=hexCopy.indexOf(characterIdentifier)-48;
            
        }
        return positionList;
        
    }
    private static ArrayList<Integer> getCharacterPositions(String hexBlock){
        ArrayList<Integer> positionList=new ArrayList<Integer>();
        for (int index = hexBlock.indexOf(characterIdentifier);
        index >= 0;
        index = hexBlock.indexOf(characterIdentifier, index + 1))
    {   
        String hocBlock=hexBlock.substring(index-48);
        if(hocBlock.startsWith("07") && hocBlock.substring(4,6).equals(("00"))){
            positionList.add(index-48);
            System.out.println("Índice: "+ index);
        }
        
    }
        return positionList;
        
    }
    //54 goes till first 04 weapon start block
    //122 gets until skill holes
    private static ArrayList<String> getCharacterHexBlocks(String hexBlock, ArrayList<Integer> posiciones){
        //ArrayList<Integer> posiciones=getCharacterPositions(hexBlock);
        ArrayList<String> hexBlocks=new ArrayList<String>();
        for (int i=0; i<posiciones.size();i++) {
            Integer posicion=posiciones.get(i);
            if(i+1<posiciones.size()){
                Integer posicionFinal=posiciones.get(i+1);
            
                hexBlocks.add(hexBlock.substring(posicion, posicionFinal));
        
            }
            else{
                hexBlocks.add(hexBlock.substring(posicion));
        
            }
            
            
        }
        return hexBlocks;
    }
    private static int getCodeFromBlock(String charBlock){
        int charCode=Integer.decode("0x"+charBlock.substring(2,4));
        return charCode;
    }
    private static boolean isCharacter(String charBlock){
        if(charBlock.startsWith("07") && charBlock.substring(4, 6).equals("00") && charBlock.substring(48, 54).equals(characterIdentifier)){
            return true;
        }
        return false;
    }
    
    private static boolean isCharacterValid(String charBlock){
        int charCode=Integer.decode("0x"+charBlock.substring(2,4));
        for(int i=0;i<getCharacters().size();i++){
            if(charCode==getCharacters().get(i).getHexCode()){
                
                return true;
            }
        }
        return false;
    }
    private static boolean isDLCCharacter(Unit person,String charBlock){
        
        if(charBlock.contains(dlcIdentifier) && (person.getHexCode()<3 || person.getHexCode()==0x34)
                
                ){
            
            if(charBlock.indexOf(dlcIdentifier)+dlcIdentifier.length()+78<charBlock.length()){
                int startGender=charBlock.indexOf(dlcIdentifier)+dlcIdentifier.length()+82+4;
                String gender=charBlock.substring(startGender,startGender+2);
                int genderInt=Utils.getIntFromHex(gender);
                if(genderInt==0){
                    person.setGender(EmblemClass.MALE);
                }
                else if(genderInt==1){
                    person.setGender(EmblemClass.FEMALE);
                }
                else{
                    System.out.println("Character without gender");
                }
                return true;
            }
            
            //return true;
        }
        return false;
    }
    private static String fullHex="";
    public static void setFullHex(String fHex){
        fullHex=fHex;
    }
    public static String getFullHex(){
        return fullHex;
    }
    public static final String dlcIdentifier="FFFF00000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
    public static final String fIdentifier="FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
    public static final String fBlockStarter="32FFFF";	
    private static boolean isYourUnit(Unit emblemCharacter,String charBlock){
      
        if(!charBlock.contains(dlcIdentifier) && charBlock.contains(fIdentifier) &&
                emblemCharacter.getHexCode()<3
                ){
            
            if(charBlock.indexOf(fIdentifier)+fIdentifier.length()+78<charBlock.length()){
            
                
                int start=charBlock.indexOf(fIdentifier)+fIdentifier.length()+30;
                String nameHex=Utils.trimBinaryPair(charBlock.substring(start,start+48));
                if(nameHex.equals(getAvatarNameCode())){
                    emblemCharacter.setFileUnit(true);
                    return true;
                }
            }
        }
        /*if(!charBlock.contains("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF")){
            
            //System.out.println("-----"+charBlock);
            
        return true;
        }*/
        return false;
    }
    public static void setFileUnitName(String name){
        
                    int start=32;
                         //String nameHex=Utils.trimBinaryPair(editedBlock.substring(start,start+48));
                         //personaje.setDisplayName(Utils.getStringFromHex(nameHex));
                    String nameHex=Utils.get48HexFromTextSpaced(name);
                    setFullHex(getFullHex().substring(0,start)
                            +nameHex+getFullHex().substring(start+48));
                    
                    
                   
    }
    
    public static String PLACEHOLDER_IMAGE_PATH=
            MaterialDesign.customPath+"/character_images/dlccharacter.png";
    public static ArrayList<Unit> parseCharacterBlock(String hexFull){
        int totalCount=getCharacterCount(hexFull);
        System.out.println("Total characters: "+totalCount);
        ArrayList<Unit> charactersArray=new ArrayList<Unit>();
        ArrayList<Integer> position=getCharacterPositions(hexFull);
        ArrayList<String> characterBlocks=getCharacterHexBlocks(hexFull,position);
        int c=0;
        System.out.println(characterBlocks.size()+" character blocks");
        
        for (String characterBlock : characterBlocks) {
            System.out.println(characterBlock.substring(0,10)+" block start "+position.get(c)+" pos start");
        
            if(isCharacter(characterBlock) && isCharacterValid(characterBlock)){
                Unit personaje=EmblemController.getCharacterInstance(getCodeFromBlock(characterBlock));
                isMorgan(personaje);
                
                if(isYourUnit(personaje,characterBlock) || isDLCCharacter(personaje,characterBlock)){
                    int start=characterBlock.indexOf(fIdentifier)+fIdentifier.length()+30;
                         String nameHex=Utils.trimBinaryPair(characterBlock.substring(start,start+48));
                         personaje.setDisplayName(Utils.getStringFromHex(nameHex));
                         
                }
                
                
                
                personaje.setHexPosition(position.get(c));
                personaje.setHexBlock(characterBlock);
                
                
                
                charactersArray.add(personaje);
            }
            c++;
            
            
        }
        //ArrayList<Integer> positions=new ArrayList<Integer>();
        
        
        /*for(int i=0;i<characterCodes.length;i++){
           
            int startIndex=hexBlock.indexOf("FFFFFFFF04")-2;
            
            
            
            //int startIndex=hexBlock.indexOf("07"+getHexPair(characterCodes[i])+"00");
            if(startIndex!=-1){
               if(positions.size()>0){
                   if(startIndex<positions.get(0)){
                       positions.add(0, startIndex);
                       charactersArray.add(0,getCharacter(characterCodes[i]));
                   }
                   else if(startIndex>positions.get(positions.size()-1)){
                       positions.add(startIndex);
                       
                       charactersArray.add(getCharacter(characterCodes[i]));
                   }
                   else{
                       //boolean continuar=true;
                       int count=positions.size()-2;
                       while(count>=0){
                           if(positions.get(count)<startIndex || positions.get(count)==startIndex){
                               positions.add(count+1,startIndex);
                               charactersArray.add(count+1,getCharacter(characterCodes[i]));
                               break;
                           }
                               count--;
                       }
                   }
                   
               }
               else{
                   positions.add(startIndex);
                   charactersArray.add(getCharacter(characterCodes[i]));
               }
                System.out.println(getCharacter(characterCodes[i]).getName());
            
            }
            
            //hexBlock.indexOf(hexBlock)
        }*/
        System.out.println("Parsed characters: "+charactersArray.size());
        for(int k=0;k<charactersArray.size();k++){
                //System.out.println(positions.get(k)+"");
                System.out.println(charactersArray.get(k).getImageURL()+", Pos: "+charactersArray.get(k).getHexPosition());
         }
        return charactersArray;
    }
    private static boolean isMorgan(Unit unit){
        if(unit.getHexCode()==0x27 || unit.getHexCode()==0x28){
            unit.setDisplayName("Morgan");
        }
        return true;
    }
    public static int getCharacterCount(String hexBlock){
        int count=Integer.decode("0x"+hexBlock.substring(4, 6));
        setCharacterCount(count);
        return count;
    }
    private static IntegerProperty characterCount;
    public static IntegerProperty characterCountProperty(){
        if(characterCount==null){
            characterCount=new SimpleIntegerProperty(EmblemController.class,"characterCount");
        }
        return characterCount;
    }
    public static void setCharacterCount(int valor){
        characterCountProperty().set(valor);
    }
    public static int getCharacterCount(){
        return characterCountProperty().get();
    }
    public static int getCharacterCountFromHex(String characterBlock){
        return  EmblemController.getCharacterPositions(characterBlock).size();
    }
    
}
