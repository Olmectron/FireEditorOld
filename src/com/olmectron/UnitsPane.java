/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron;

import com.olmectron.material.components.ColoredStackPane;
import com.olmectron.material.components.FlatButton;
import com.olmectron.material.components.MaterialCard;
import com.olmectron.material.components.MaterialCircleImageView;
import com.olmectron.material.components.MaterialConfirmDialog;
import com.olmectron.material.components.MaterialDisplayText;
import com.olmectron.material.components.MaterialFlowList;
import com.olmectron.material.components.MaterialIconButton;
import com.olmectron.material.components.MaterialLabel;
import com.olmectron.material.components.MaterialStandardList;
import com.olmectron.material.components.MaterialStandardListItem;
import com.olmectron.material.components.MaterialTextField;
import com.olmectron.material.components.MaterialToast;
import com.olmectron.material.components.MaterialTooltip;
import com.olmectron.material.components.RaisedButton;
import com.olmectron.material.constants.MaterialColor;
import com.olmectron.material.layouts.MaterialPane;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 *
 * @author olmec
 */
public class UnitsPane extends VBox {
    private Main padre;
    public UnitsPane(Main contain){
        super();
        padre=contain;
        
        initAll();
    }
    private MaterialFlowList<Unit> characterList;
    public MaterialFlowList<Unit> getCharacterList(){
        return characterList;
    }
    private void deleteUnits(){
        for(int i=0;i<characterList.size();i++){
            if(characterList.getItemBox(i).isSelected()){
                Unit personaje=characterList.getItem(i);
                personaje.delete();
            
            
            }
                    
                    }
    }
    private ArrayList<Unit> abstractList=null;
    public void setItems(ArrayList<Unit> lista){
        abstractList=lista;
        countText.setText(lista.size()+" / "+EmblemController.getCharacterCount()+" units");
        characterList.clear();
        characterList.addItems(abstractList);
    }
    private MaterialCircleImageView userImage;
    private StackPane imagePane;
    private StackPane getImagePane(){
        return imagePane;
    }
    private void initAll(){
        
        characterList=new MaterialFlowList<Unit>(this) {
            @Override
            public void onLongPressSelection(int selectedItems){
                
            }
            @Override
            public Node itemConverter(Unit item, MaterialStandardListItem<Unit> itemContainer) {
                
                MaterialCard card=new MaterialCard();
                
                card.setId("the_card");
                card.setCardPadding(new Insets(0));
                 card.setPadding(new Insets(3,4,5,4));
                card.setCardWidth(128);
                
                
                card.activateShadow(1);
                imagePane=new StackPane();
                
                        try{
                       userImage= new MaterialCircleImageView(new Image(item.getImageURL()),128,true);
                        }catch(IllegalArgumentException ex){
                            userImage=new MaterialCircleImageView(new Image(EmblemController.PLACEHOLDER_IMAGE_PATH),128,true);
                        }
                       
                        userImage.toSquareImage();/*Circle circle = new Circle();
        circle.setCenterX(70/2);
        circle.setCenterY(70/2);
        circle.setRadius(70/2);
        imagePane.setClip(circle);
        imagePane.setAlignment(Pos.TOP_CENTER);*/
                        
                        
                        
    //userImage.setMySQLImage(item.getImageURL());
//VBox b=new VBox();
                        
                       
                imagePane.getChildren().add(userImage);
                if(item.isAvatar() && item.getFace()<5 && item.hasBirthday()){
                    ColoredStackPane cd=EmblemController.setAvatarHair(item, imagePane, 128,userImage);
                    //cd.setPadding(new Insets(0,0,-5,0));
                }
                if(item.isChild() && item.isHairEditable()){
                    
                    ColoredStackPane cd=EmblemController.setAvatarHair(item, imagePane, 128,userImage);
                }
                if(!item.isSpotpass() && !item.isDLC()){
                    /*Circle circle = new Circle();
        circle.setCenterX(70/2);
        circle.setCenterY(70/2);
        circle.setRadius(70/2);
        imagePane.setClip(circle);*/
        //imagePane.setClip(circle);
                
                if(item.isMainUnit()){
                //imagePane.getStyleClass().add("avatar-background");
                
                }else{
                //imagePane.getStyleClass().add("normal-background");
                }    //userImage.toSquareImage();
                }
                else if(item.isDLC()){
                    /*Circle circle = new Circle();
        circle.setCenterX(70/2);
        circle.setCenterY(70/2);
        circle.setRadius(70/2);
        imagePane.setClip(circle);*/
                    //imagePane.getStyleClass().add("dlc-background");
                }
                MaterialDisplayText nameText=new MaterialDisplayText(item.getName());
                
                nameText.setColor(MaterialColor.BLACK_87);
                
                nameText.setFontSize(16);
                MaterialDisplayText permisosText=new MaterialDisplayText(item.getClassDisplayName());
                
                item.emblemClassProperty().addListener(new  ChangeListener<EmblemClass>() {

                @Override
                public void changed(ObservableValue<? extends EmblemClass> observable, EmblemClass oldValue, EmblemClass newValue) {
                    permisosText.setText(newValue.getName());
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
                permisosText.setColor(MaterialColor.BLACK_54);
                permisosText.setFontSize(13);
                VBox dataBox=new VBox(nameText, permisosText);
                
                dataBox.setAlignment(Pos.CENTER_LEFT);
                
                //b.getChildren().add(nameText);
                HBox userImageBox=new HBox(imagePane);
                userImageBox.setAlignment(Pos.CENTER_LEFT);
                HBox editedBox=new HBox();
                MaterialLabel editedLabel=new MaterialLabel("Updated");
                HBox dummyBox=new HBox();
                //HBox.setHgrow(dummyBox, Priority.ALWAYS);
                dataBox.getChildren().addAll(editedLabel);
                item.editedProperty().addListener(new ChangeListener<Boolean>(){

                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if(newValue){
                            editedLabel.setColor(MaterialColor.RED);
                            editedLabel.setText("Edited, but not saved");
                        }
                        else{
                            editedLabel.setColor(MaterialColor.BLUE);
                            editedLabel.setText("Updated");
                        }
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                editedBox.setAlignment(Pos.CENTER_RIGHT);
                
                VBox box=new VBox(userImageBox,dataBox);
                //userImageBox.setPadding(new Insets(0,0,0,16));
                //dataBox.setPadding(new Insets(0,0,0,112));
                box.setAlignment(Pos.CENTER_LEFT);
                //box.setMinHeight(200);
                box.setMinHeight(78);
                card.addComponent(box);
                return card;
                
             
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onItemClick(Unit item,MouseEvent event) {
                padre.setRootBox(((MaterialPane)new CharacterDialog(item).getMainPane()).getCoreSmartCard());
                
                
                //new CharacterDialog(item).unhide();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        };
        
        characterList.transparentScroll();
        //characterList.setPrefWidth(1000);
        MaterialTextField searchField=new MaterialTextField("Find a unit");
        countText=new MaterialDisplayText("0 units");
        countText.setFontSize(16);
        countText.setColor(MaterialColor.BLACK_87);
        HBox countBox=new HBox();
        countBox.setPadding(new Insets(8,0,0,8));
        countText.setPadding(new Insets(0,16,0,0));
        
        countBox.getChildren().add(countText);
        /*characterListBox.getChildren().addAll(
                //searchField,
                characterList,
                countBox
        );*/
        RaisedButton randomButton=new RaisedButton("Randomize");
        MaterialTooltip randomTooltip=new MaterialTooltip("Shuffles the selected units' skills and class",randomButton);
        randomButton.setOnAction(new EventHandler<ActionEvent>() {

             @Override
             public void handle(ActionEvent event) {
                 if(characterList.size()>0){
                 MaterialConfirmDialog confirm=new MaterialConfirmDialog("Confirm unit randomize",
                 "By confirming this, all of your units' Skills and Class will be randomized. Grima class is excluded. "
                         + "You'll need to press the SAVE button for changes to be written.","Random","Cancel") {
                     @Override
                     public void onPositiveButton(){
                         
                 randomize();
                 dismiss();
                     }
                     @Override
                     public void onDialogShown() {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     }

                     @Override
                     public void onDialogHidden() {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     }

                     @Override
                     public void onDialogKeyReleased(KeyEvent event) {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     }

                     @Override
                     public void onDialogKeyPressed(KeyEvent event) {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     }
                 };
                 confirm.unhide();
                 }
                 else{
                     new MaterialToast("Load a file first").unhide();
                 }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
         });
        FlatButton selectButton=new FlatButton("SELECT ALL");
        selectButton.setOnAction(new EventHandler<ActionEvent>(){

             @Override
             public void handle(ActionEvent event) {
                selectAllUnits(); 
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
         });
        RaisedButton cloneButton=new RaisedButton("CLONE");
       cloneButton.setOnAction(new EventHandler<ActionEvent>() {

             @Override
             public void handle(ActionEvent event) {
                 if(characterList.size()>0){
                /* MaterialConfirmDialog confirm=new MaterialConfirmDialog("Confirm unit clone",
                 "This feature is completely EXPERIMENTAL. Your CONVOY ITEMS and BARRACKS LIST WILL be WIPED OUT ENTIRELY. "
                         + "CRASHES and FREEZES CAN occur in-game. Your selected units will be DUPLICATED. You MUST SAVE to see changes applied.","Understood","Cancel") {
                     @Override
                     public void onPositiveButton(){
                         
                 //cloneUnits();
                 dismiss();
                 MaterialConfirmDialog confirm2=new MaterialConfirmDialog("Are you sure?",
                 "You need to have all of your units OUTSIDE preparation for battle or your SAVE MAY GET CORRUPTED! "
                         + "What do you say? Already done that? Well, your SAVE MAY STILL GET CORRUPTED just because.","UNDERSTOOD, AGAIN","Cancel") {
                     @Override
                     public void onPositiveButton(){
                         
                 
                 dismiss();*/
                 MaterialConfirmDialog confirm3=new MaterialConfirmDialog("Clone selected units",
                 "Your selected units will be cloned. Save in order to see changes. THIS IS EXPERIMENTAL, USE IT AT YOUR OWN RISK."
                         + "","Clone","Cancel") {
                     @Override
                     public void onPositiveButton(){
                         
                 cloneUnits();
                 dismiss();
                     }
                     @Override
                     public void onDialogShown() {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     }

                     @Override
                     public void onDialogHidden() {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     }

                     @Override
                     public void onDialogKeyReleased(KeyEvent event) {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     }

                     @Override
                     public void onDialogKeyPressed(KeyEvent event) {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     }
                 };
                 confirm3.unhide();
                /*     }
                     @Override
                     public void onDialogShown() {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     }

                     @Override
                     public void onDialogHidden() {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     }

                     @Override
                     public void onDialogKeyReleased(KeyEvent event) {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     }

                     @Override
                     public void onDialogKeyPressed(KeyEvent event) {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     }
                 };
                 confirm2.unhide();
                     }
                     @Override
                     public void onDialogShown() {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     }

                     @Override
                     public void onDialogHidden() {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     }

                     @Override
                     public void onDialogKeyReleased(KeyEvent event) {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     }

                     @Override
                     public void onDialogKeyPressed(KeyEvent event) {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     }
                 };
                 confirm.unhide();*/
                 }
                 else{
                     new MaterialToast("Load a file first").unhide();
                 }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
         });
       cloneButton.setColor(MaterialColor.RED);
       MaterialIconButton deleteButton=new MaterialIconButton(MaterialIconButton.DELETE_ICON);
       deleteButton.setColor(MaterialColor.RED);
       deleteButton.setOnAction(new EventHandler<ActionEvent>(){

             @Override
             public void handle(ActionEvent event) {
                 if(characterList.size()>0){
                MaterialConfirmDialog confirm3=new MaterialConfirmDialog("Delete selected units",
                 "Your selected units will be deleted from the save file. Save in order to see changes. THIS IS EXPERIMENTAL, USE IT AT YOUR OWN RISK."
                         + "","Delete","Cancel") {
                     @Override
                     public void onPositiveButton(){
                         
                 deleteUnits(); 
                 dismiss();
                     }
                     @Override
                     public void onDialogShown() {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     }

                     @Override
                     public void onDialogHidden() {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     }

                     @Override
                     public void onDialogKeyReleased(KeyEvent event) {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     }

                     @Override
                     public void onDialogKeyPressed(KeyEvent event) {
                         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                     }
                 };
                 confirm3.unhide();
                 }
                 else{
                     new MaterialToast("Load a file first").unhide();
                 }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
         });
       HBox cloneBox=new HBox(cloneButton,deleteButton);
       System.out.println("Hex characters "+EmblemController.getCharacterCount());
       System.out.println("List characters "+getCharacterList().size());
      EmblemController.characterCountProperty().addListener(new ChangeListener<Number>() {

             @Override
             public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(newValue.intValue()!=getCharacterList().getItemCount()){
                    cloneButton.setDisable(true);
                    deleteButton.setDisable(true);
                } 
                else{ cloneButton.setDisable(false);
                deleteButton.setDisable(false);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
         });
      getCharacterList().itemCountProperty().addListener(new ChangeListener<Number>() {

             @Override
             public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(newValue.intValue()!=EmblemController.getCharacterCount()){
                    cloneButton.setDisable(true);
                    deleteButton.setDisable(true);
                } 
                else{ cloneButton.setDisable(false);
                deleteButton.setDisable(false);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
         });
       cloneBox.setPadding(new Insets(0,0,0,12));
       MaterialTooltip experimentalFeature=new MaterialTooltip("Experimental feature",cloneButton);
        HBox growBox=new  HBox();
        HBox.setHgrow(growBox, Priority.ALWAYS);
        countBox.getChildren().addAll(growBox,selectButton,randomButton,cloneBox);
        
        
         StackPane c=new StackPane(characterList);
        
        c.setPadding(new Insets(24));
       ScrollPane p=new ScrollPane(c);
       p.widthProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                c.setPrefWidth(newValue.doubleValue()-18);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
       /*characterList.setOnScroll(new EventHandler<ScrollEvent>() {
        @Override 
        public void handle(ScrollEvent event) {
            if(event.getEventType().equals(ScrollEvent.SCROLL_STARTED)){
                p.getStyleClass().remove("transparent");
            }
            else if (event.getEventType().equals(ScrollEvent.SCROLL_FINISHED)){
                p.getStyleClass().remove("transparent");
                p.getStyleClass().add("transparent");
            }
            //node.setTranslateX(node.getTranslateX() + event.getDeltaX());
            //node.setTranslateY(node.getTranslateY() + event.getDeltaY());
        }
    });*/
       BorderPane characterBox=new BorderPane();
       //c.setPrefWidth(p.getPrefViewportWidth());
       p.getStyleClass().add("material-scroll");
       //p.getStyleClass().add("transparent");
       
        characterBox.setCenter(p);
        characterList.setContainer(c);
        getChildren().addAll(/*selectBox,*/characterBox);
        setPadding(new Insets(24));
        
    }
    private void selectAllUnits(){
        for(int i=0;i<characterList.size();i++){
            characterList.getItemBox(i).setSelected(true);
        }
    }
    private void cloneUnits(){
         for(int i=0;i<characterList.size();i++){
            if(characterList.getItemBox(i).isSelected()){
                
            Unit unit=characterList.getItemBox(i).getItem();
            unit.cloneUnit();
            }
         
         }
    }
            
    private void randomize(){
        for(int i=0;i<characterList.size();i++){
            if(characterList.getItemBox(i).isSelected()){
            Unit personaje=characterList.getItemBox(i).getItem();
            int skills[]=new int[]{0,0,0,0,0};
            
            for(int j=0;j<5;j++){
                skills[j]=Utils.randomInclusive(0x01, 0x66);
                
                    while(personaje.hasSkill(skills[j])){
                        skills[j]=Utils.randomInclusive(0x01,0x66);
                    }
                    switch(j){
                        case 0:
                            personaje.setSkillOne(EmblemController.getCharacterSkill(skills[j]));
                            break;
                        case 1:
                            personaje.setSkillTwo(EmblemController.getCharacterSkill(skills[j]));
                            break;
                        case 2:
                            personaje.setSkillThree(EmblemController.getCharacterSkill(skills[j]));
                            break;
                        case 3:
                            personaje.setSkillFour(EmblemController.getCharacterSkill(skills[j]));
                            break;
                        case 4:
                            personaje.setSkillFive(EmblemController.getCharacterSkill(skills[j]));
                            break;
                    }
            }
            
            
            
            
            int classCode=Utils.randomInclusive(0x51);
            while(classCode==0x4E){
                classCode=Utils.randomInclusive(0x51);
            }
            int genderCode=Utils.randomInclusive(1);
            if(genderCode==0){
                personaje.setEmblemClass(EmblemController.getCharacterClass(classCode), EmblemClass.MALE);
            }
            else if(genderCode==1){
                                personaje.setEmblemClass(EmblemController.getCharacterClass(classCode), EmblemClass.FEMALE);
            }
            
            
        }
        }
    }
    private MaterialDisplayText countText;
}
