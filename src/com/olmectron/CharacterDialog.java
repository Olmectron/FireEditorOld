/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron;

import com.olmectron.material.ButtonBox;
import com.olmectron.material.components.ColoredStackPane;
import com.olmectron.material.components.FlatButton;
import com.olmectron.material.components.FontWeight;
import com.olmectron.material.components.MaterialCheckBox;
import com.olmectron.material.components.MaterialCircleImageView;
import com.olmectron.material.components.MaterialDisplayText;
import com.olmectron.material.components.MaterialDropdownMenu;
import com.olmectron.material.components.MaterialLabel;
import com.olmectron.material.components.MaterialRadioButton;
import com.olmectron.material.components.MaterialSelector;
import com.olmectron.material.components.MaterialStandardDialog;
import com.olmectron.material.components.MaterialTabs;
import com.olmectron.material.components.MaterialTextField;
import com.olmectron.material.components.MaterialToast;
import com.olmectron.material.components.RaisedButton;
import com.olmectron.material.components.SimpleSelector;
import com.olmectron.material.components.SimpleStringSelector;
import com.olmectron.material.constants.MaterialColor;
import com.olmectron.material.layouts.MaterialPane;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

/**
 *
 * @author olmec
 */
public class CharacterDialog extends MaterialStandardDialog {
    private MaterialSelector<EmblemClass> classSelector;
    private Unit unit;
    private MaterialRadioButton maleRadio,femaleRadio;
   
    public CharacterDialog(Unit unit){
        this.unit=unit;
        this.setDialogWidth(1000);
        //((MaterialPane)this.getMainPane()).getContentCard().setCardHeight(1000);
        initInterface();
        
    }
    private  MaterialDisplayText fd;
    private ColoredStackPane hairBack;
    private MaterialCircleImageView imageView;
    private void initInterface(){
        this.setHideOnClick(false);
        StackPane imagePane=new StackPane();
        //=new MaterialCircleImageView(new Image(unit.getImageURL()),150,true);
        
        try{
                       imageView= new MaterialCircleImageView(new Image(unit.getImageURL()),150,true);
                        }catch(IllegalArgumentException ex){
                            imageView=new MaterialCircleImageView(new Image(EmblemController.PLACEHOLDER_IMAGE_PATH),150,true);
                        }
        imagePane.getChildren().add(imageView);
        if(!unit.isDLC() && !unit.isSpotpass()){
            imageView.toSquareImage();
            
        }
       fd=new MaterialDisplayText(unit.getName());
        
    //MaterialDisplayText fd=new MaterialDisplayText(unit.getName());
        //HBox nameBox=new HBox(fd);
        
        fd.setColor(MaterialColor.BLACK_87);
        fd.getStyleClass().add("medium-font");
        //fd.setFontWeight(FontWeight.MEDIUM);
        HBox nameBox=new HBox(fd);
        if(unit.isChild() && unit.isHairEditable()){
            hairBack=EmblemController.setAvatarHair(unit, imagePane,150,imageView);
                unit.hairProperty().addListener(new ChangeListener<Number>(){

                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                       hairBack=EmblemController.setAvatarHair(unit, imagePane,150,imageView);
                
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                unit.hairColorProperty().addListener(new ChangeListener<Color>(){

                    @Override
                    public void changed(ObservableValue<? extends Color> observable, Color oldValue, Color newValue) {
                       // ColorAdjust monochrome = new ColorAdjust();
        //monochrome.setSaturation(-1.0);
               /* Blend blush = new Blend(
                BlendMode.MULTIPLY,
                monochrome,
                new ColorInput(
                        0,
                        0,
                        150,//hairBackView.getImage().getWidth(),
                        150,//hairBackView.getImage().getHeight(),
                        newValue
                )
        );*/
                //hairBack.setEffect(blush);
                        if(hairBack!=null)
                hairBack.setColorStyle(newValue);
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
        }
        
        if(unit.isAvatar() /*&& unit.getFace()<5 */&& unit.hasBirthday()){
            //fd.setText(fd.getText()+" "+unit.getTestString());
                hairBack=EmblemController.setAvatarHair(unit, imagePane,150,imageView);
                unit.hairProperty().addListener(new ChangeListener<Number>(){

                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                       hairBack=EmblemController.setAvatarHair(unit, imagePane,150,imageView);
                
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                unit.buildProperty().addListener(new ChangeListener<Number>(){

                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        try{
                       imageView.setImage(new Image(unit.getImageURL()));
                        }catch(IllegalArgumentException ex){
                            imageView.setImage(new Image(EmblemController.PLACEHOLDER_IMAGE_PATH));
                        }
                         hairBack=EmblemController.setAvatarHair(unit, imagePane,150,imageView);
                      
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                unit.faceProperty().addListener(new ChangeListener<Number>(){

                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        try{
                       imageView.setImage(new Image(unit.getImageURL()));
                        }catch(IllegalArgumentException ex){
                            imageView.setImage(new Image(EmblemController.PLACEHOLDER_IMAGE_PATH));
                        }
                        hairBack=EmblemController.setAvatarHair(unit, imagePane,150,imageView);
                
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                unit.hairColorProperty().addListener(new ChangeListener<Color>(){

                    @Override
                    public void changed(ObservableValue<? extends Color> observable, Color oldValue, Color newValue) {
                       // ColorAdjust monochrome = new ColorAdjust();
        //monochrome.setSaturation(-1.0);
               /* Blend blush = new Blend(
                BlendMode.MULTIPLY,
                monochrome,
                new ColorInput(
                        0,
                        0,
                        150,//hairBackView.getImage().getWidth(),
                        150,//hairBackView.getImage().getHeight(),
                        newValue
                )
        );*/
                //hairBack.setEffect(blush);
                        if(hairBack!=null)
                hairBack.setColorStyle(newValue);
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                  /*imagePane.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
            MaterialDropdownMenu menu=new MaterialDropdownMenu(imagePane);
            
 FlatButton button=new FlatButton("Listo");
 button.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent event) {
            menu.hideMenu();
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
 HBox hbox=new HBox(button);
 menu.addNodeAsItem(hbox);
 menu.unhide();
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });*/
                
                
                
                
        }
        else if (unit.isSpotpass()){
            //fd.setText("Spotpass: "+Utils.getHexPair(unit.getSpotpassCode())+" "+fd.getText());
        }
        
        
        HBox mainBox=new HBox();
        mainBox.setPadding(new Insets(24));
        VBox formBox=new VBox();
        MaterialLabel classLabel=new MaterialLabel("Class");
        classSelector=new MaterialSelector<EmblemClass>(classLabel){
            @Override
            public void onSelectionChange(EmblemClass valor){
                String gender=""; 
                    if(maleRadio.isSelected()){
                         gender=EmblemClass.MALE;
                     }   
                    else if(femaleRadio.isSelected()){
                       gender=EmblemClass.FEMALE;
                    }
                     unit.setEmblemClass(valor,gender);
                     
            }
        
        };
        formBox.setPadding(new Insets(0,0,0,12));
         maleRadio=new MaterialRadioButton("Male class");
         maleRadio.selectedProperty().addListener(new ChangeListener<Boolean>(){

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                String gender=""; 
                    if(maleRadio.isSelected()){
                         gender=EmblemClass.MALE;
                     }   
                    else if(femaleRadio.isSelected()){
                       gender=EmblemClass.FEMALE;
                    }
                     unit.setEmblemClass(classSelector.getValue(),gender);
                     
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        femaleRadio=new MaterialRadioButton("Female class");
        femaleRadio.selectedProperty().addListener(new ChangeListener<Boolean>(){

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                String gender=""; 
                    if(maleRadio.isSelected()){
                         gender=EmblemClass.MALE;
                     }   
                    else if(femaleRadio.isSelected()){
                       gender=EmblemClass.FEMALE;
                    }
                     unit.setEmblemClass(classSelector.getValue(),gender);
                     
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        maleRadio.setPadding(new Insets(0,0,8,0));
        //maleRadio.setDisable(true);
        VBox radioBox=new VBox(maleRadio,femaleRadio);
        radioBox.setPadding(new Insets(8,8,8,8));
        ToggleGroup radioGroup=new ToggleGroup();
        maleRadio.setToggleGroup(radioGroup);
        femaleRadio.setToggleGroup(radioGroup);
        classSelector.setPrefWidth(500);
        classSelector.setConverter(new StringConverter<EmblemClass>() {
              @Override
              public String toString(EmblemClass user) {
                if (user== null){
                  return null;
                } else {
                  return user.getName();
                }
              }

            @Override
            public EmblemClass fromString(String id) {
                return null;
            }
        });
        classSelector.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EmblemClass>(){

            @Override
            public void changed(ObservableValue<? extends EmblemClass> observable, EmblemClass oldValue, EmblemClass newClass) {
                if(newClass!=null){
                    if(newClass.isExclusive()){
                        switch(newClass.getExclusiveGender()){
                            case EmblemClass.MALE:
                                maleRadio.setDisable(false);
                                maleRadio.setText("Male exclusive");
                                maleRadio.setSelected(true);
                                femaleRadio.setDisable(true);
                                femaleRadio.setText("Female class");
                                
                                break;
                            case EmblemClass.FEMALE:
                                femaleRadio.setDisable(false);
                                femaleRadio.setText("Female exclusive");
                                femaleRadio.setSelected(true);
                                maleRadio.setDisable(true);
                                maleRadio.setText("Male class");                                
                                break;
                            default:
                                maleRadio.setDisable(false);
                                maleRadio.setSelected(true);
                                maleRadio.setText("Enemy exclusive");
                                femaleRadio.setDisable(true);
                                femaleRadio.setText("Female class");
                                break;
                        }
                    }
                    else{
                        maleRadio.setDisable(false);
                                maleRadio.setText("Male class");
                                femaleRadio.setDisable(false);
                                femaleRadio.setText("Female class");
                                if(newClass.getName().equals(unit.getEmblemClass().getName())){
                                    if(unit.getSelectedClassCode()==newClass.getFemaleCode()){
                                   femaleRadio.setSelected(true);
                                    }
                                    else if(unit.getSelectedClassCode()==newClass.getMaleCode()){
                                        maleRadio.setSelected(true);
                                    }
                                }
                                else{
                                    if(unit.getGender().equals(EmblemClass.MALE)){
                                        maleRadio.setSelected(true);
                                    }
                                    else if(unit.getGender().equals(EmblemClass.FEMALE)){
                                        femaleRadio.setSelected(true);
                                    }
                                }
                    }
                }
                else{
                    
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        classSelector.addItems(EmblemController.getCharacterClasses());
        classSelector.getSelectionModel().select(unit.getEmblemClass());
        HBox formBox2=new HBox();
        formBox2.setPadding(new Insets(4,0,8,0));
        
        
        MaterialDisplayText genreText=new MaterialDisplayText("");
        
        genreText.setColor(MaterialColor.BLACK_54);
        genreText.setFontSize(12);
        if(unit.getGender().equals(EmblemClass.MALE)){
            genreText.setText("Male unit");
            
        }
        else if(unit.getGender().equals(EmblemClass.FEMALE)){
            genreText.setText("Female unit");
        }
        if(unit.getFace()>=5){
                genreText.setText(genreText.getText()+" DLC-"+Utils.getHexPair(unit.getFace()));
            }
        if(unit.isSpotpass()){
            genreText.setText(genreText.getText()+" S-"+Utils.getHexPair(unit.getSpotpassCode()));
        }
        RaisedButton doneButton=new RaisedButton("done");
        doneButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                 dismiss();
                    if(R.principal!=null){
                        R.principal.setUnitsPane();
                    }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        VBox imageBox=new VBox(nameBox,imagePane,genreText,doneButton);
        imageBox.setAlignment(Pos.BASELINE_CENTER);
        BorderPane tabPane=new BorderPane();
        tabPane.setCenter(formBox);
        mainBox.getChildren().addAll(imageBox,tabPane);
        
        HBox skillBox=new  HBox();
        
        MaterialLabel label=new  MaterialLabel("Skills");
        label.setColor(MaterialColor.BLACK_87);
        label.setMinWidth(45);
        skillBox.setPrefWidth(500);
        skillBox.setAlignment(Pos.CENTER);
        skillBox.getChildren().addAll(label, new SkillBox(unit.getSkillSet(),unit));
        
       MaterialTextField addedMovementField=new MaterialTextField(unit.getAddedMovement()+"","Added movement");
        addedMovementField.lockLetters();
        addedMovementField.textField().textProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(Utils.currencyToDouble(newValue)>255){
                    addedMovementField.setText("255");
                    addedMovementField.textField().positionCaret("255".length());
                } 
                if(!addedMovementField.getText().trim().equals("") && (int)Utils.currencyToDouble(addedMovementField.getText())!=0){
                    int newAddedMovement=(int)Utils.currencyToDouble(addedMovementField.getText());
                    if(unit.getAddedMovement()!=newAddedMovement){
                        unit.setAddedMovement(newAddedMovement);
                        
                    }
                
                }

    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        HBox classLabelBox=new HBox(classLabel);
        HBox optionsClassBox=new HBox(classSelector);
        formBox.getChildren().addAll(formBox2,classLabelBox,optionsClassBox,addedMovementField,radioBox,skillBox);
        //formBox.setPadding(new Insets(0,0,12,0));
        MaterialTextField nameField=new MaterialTextField(unit.getName(),"Name");
        nameField.setLimite(12);
        nameField.allowDot();
        nameField.allowSpace();
        nameField.textField().textProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.trim().equals("")){
                    if(unit.isMainUnit()){
                        if(newValue.trim().length()>=12){
                            EmblemController.setFileUnitName(Utils.trim(newValue.trim().substring(0,12)));
                        }
                        else{
                            
                            EmblemController.setFileUnitName(Utils.trim(newValue.trim()));
                        }
                    }
                    if(newValue.trim().length()>=12){
                        unit.setDisplayName(Utils.trim(newValue.trim().substring(0,12)));
                    fd.setText(Utils.trim(newValue.trim().substring(0,12)));
                    }
                    else{
                        unit.setDisplayName(Utils.trim(newValue.trim()));
                    fd.setText(Utils.trim(newValue.trim()));
                    }
                    
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        MaterialTextField levelField=new MaterialTextField(unit.getLevel()+"","Level");
        levelField.lockLetters();
        levelField.textField().textProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
if(Utils.currencyToDouble(newValue)>99){
                    levelField.setText("99");
                    levelField.textField().positionCaret("99".length());
                }           
                if(!levelField.getText().trim().equals("") && (int)Utils.currencyToDouble(levelField.getText())!=0){
                    int newLevel=(int)Utils.currencyToDouble(levelField.getText());
                    if(unit.getLevel()!=newLevel){
                        unit.setLevel(newLevel);
                        
                    }
                
                }

//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        MaterialTextField experienceField=new MaterialTextField(unit.getExperience()+"","Experience");
        experienceField.lockLetters();
        experienceField.textField().textProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(Utils.currencyToDouble(newValue)>99){
                    experienceField.setText("99");
                    experienceField.textField().positionCaret("99".length());
                }    
                if(!experienceField.getText().trim().equals("") && (int)Utils.currencyToDouble(experienceField.getText())!=0){
                
                    int newExperience=(int)Utils.currencyToDouble(experienceField.getText());

                    if(unit.getExperience()!=newExperience){
                        unit.setExperience(newExperience);
                        
                    }
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        if(unit.isAvatar()){
            formBox2.getChildren().add(nameField);
        }
        formBox2.getChildren().addAll(levelField,experienceField);
        nameBox.setPadding(new Insets(0,0,16,0));
        nameBox.setAlignment(Pos.CENTER);
        MaterialTabs tabs=new MaterialTabs();
        tabs.addTab("Class and skills");
        tabs.getTabAt(0).select();
        tabs.getTabAt(0).setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                tabPane.setCenter(formBox);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        if((unit.isAvatar() && !unit.isDLC() && !unit.isSpotpass()) || (unit.isChild() && unit.isHairEditable())){
            tabs.addTab("Appearance");
        tabs.getTabAt(1).setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                tabPane.setCenter(getAppearancePane(unit));
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        }
        //addNode();
        addNode(tabs);
        addNode(mainBox);
        
        /*ButtonBox buttonBox=new ButtonBox("Change"){

            @Override
            public void onNegativeButtonClick() {
                dismiss();
                if(R.principal!=null){
                        R.principal.setUnitsPane();
                    }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onPositiveButtonClick() {
                //EmblemClass selectedClass=classSelector.getSelectionModel().getSelectedItem();
                //boolean changed=false;
                //changed=true;
                //if(!unit.getEmblemClass().getName().equals(selectedClass.getName())){
                    
                //     changed=true;
                //}
                
                //if(unit.editedProperty().get()){
                //    changed=true;
                //}
                
                //if(changed){
                    
                   
                //}
                //else {new MaterialToast("But you haven't changed anything").unhide();}
               
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };*/
       
        //addNode(buttonBox);
        setOutsideListeners(this.getMainPane());
    }
    private VBox getAppearancePane(Unit unidad){
        VBox appearancePane=new VBox();
        ArrayList<String> buildList=new ArrayList<String>();
        buildList.add("01");
        buildList.add("02");
        buildList.add("03");
        
        ArrayList<EmblemPortrait> faceList=new ArrayList<EmblemPortrait>();
        
        for (EmblemPortrait dlcPortrait : EmblemPortrait.getDLCPortraits()) {
            faceList.add(dlcPortrait);
        }
        ArrayList<String> hairList=new ArrayList<String>();
        
        hairList.add("01");
        hairList.add("02");
        hairList.add("03");
        hairList.add("04");
        hairList.add("05");
        
        int selectedBuild=unit.getBuild();
        if(selectedBuild==1){
            selectedBuild=0;
        }
        else if(selectedBuild==0){
            selectedBuild=1;
        }
        SimpleStringSelector buildSelector=new SimpleStringSelector("Build",buildList,selectedBuild) {

            @Override
            public void onItemChange(String item, int pos) {
                if(pos==0){
                    unidad.setBuild(1);
                }
                else if(pos==1){
                    unidad.setBuild(0);
                }
                else if(pos==2){
                    unidad.setBuild(2);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        SimpleSelector<EmblemPortrait> faceSelector=new SimpleSelector<EmblemPortrait>("Face",faceList,EmblemPortrait.getDLCUnitPositionFromCode(unidad.getFace())) {

            @Override
            public String getStringValue(EmblemPortrait item) {
                return item.getName();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onItemChange(EmblemPortrait item, int pos) {
                unidad.setFace(Utils.getIntFromHex(item.getHexCode()));
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getItemPosition(ArrayList<EmblemPortrait> portraits, String showText) {
                for(int i=0;i<portraits.size();i++){
                    if(portraits.get(i).getName().equals(showText)){
                        return i;
                    }
                }
                return -1;
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
            
        
        SimpleStringSelector hairSelector=new SimpleStringSelector("Hair",hairList,unit.getHair()) {

            @Override
            public void onItemChange(String item, int pos) {
                unidad.setHair(pos);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        final ColorPicker colorPicker = new ColorPicker();
 colorPicker.setOnAction(new EventHandler() {
     public void handle(Event t) {
         Color c = colorPicker.getValue();
         unit.setHairColor(c);
            //System.out.println("New Color's RGB = "+c.getRed()+" "+c.getGreen()+" "+c.getBlue());
     }
        
 });
 colorPicker.setValue(unit.getHairColor());
 MaterialDisplayText displayColor=new MaterialDisplayText("Hair color");
        displayColor.setFontWeight(FontWeight.MEDIUM);
        displayColor.setMinWidth(60);
        HBox colorBox=new HBox(displayColor,colorPicker);
        displayColor.setPadding(new Insets(0,16,0,0));
        colorPicker.setMaxWidth(125);
        colorBox.setAlignment(Pos.CENTER);
        colorBox.setPrefWidth(700);
        appearancePane.getChildren().addAll(buildSelector,faceSelector,hairSelector, colorBox);
        
        appearancePane.setAlignment(Pos.CENTER);
        return appearancePane;
    }
    private void setOutsideListeners(Pane scene){
            
                scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    
                    @Override
                    public void handle(MouseEvent event) {
                            //System.out.println("juerngviansgvijasndsv");
                            MaterialDropdownMenu m=MaterialDropdownMenu.getLastMenu();
                            if(m!=null){
                                m.hideMenu();
                            }
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
               
    }
    @Override
    public void onPressedKey(KeyEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onReleasedKey(KeyEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onCreate(Pane pane) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void onDialogKeyPressed(KeyEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onDialogKeyReleased(KeyEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
