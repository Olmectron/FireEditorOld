/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron;

import com.olmectron.material.components.MaterialConfirmDialog;
import com.olmectron.material.components.MaterialLabel;
import com.olmectron.material.components.MaterialSelector;
import com.olmectron.material.components.MaterialTextField;
import com.olmectron.material.components.MaterialTooltip;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

/**
 *
 * @author olmec
 */
public class MiscPane extends VBox {
    private Main padre;
    public MiscPane(Main contain){
        super();
        padre=contain;
        initAll();
    }
    private CheckBox fullItems;
    private int quantityConvoy=0;
    private boolean includeForge=false;
    public int getConvoyQuantity(){
        return quantityConvoy;
    }
    public boolean includesForge(){
        return includeForge;
    }
    public CheckBox getFullItemsCheckBox(){
        return fullItems;
    }
    private MaterialSelector<Difficulty> difficultySelector;
    public MaterialSelector<Difficulty> getDifficultySelector(){
        return difficultySelector;
    }
    private void initAll(){
        
        MaterialLabel difficultyLabel=new MaterialLabel("Difficulty");
        difficultySelector=new MaterialSelector<Difficulty>(difficultyLabel){
            @Override
            public void onSelectionChange(Difficulty valor){
                
            }
        
        };
        
        difficultySelector.setPrefWidth(500);
        difficultySelector.setConverter(new StringConverter<Difficulty>() {
              @Override
              public String toString(Difficulty user) {
                if (user== null){
                  return null;
                } else {
                  return user.getDifficulty();
                }
              }

            @Override
            public Difficulty fromString(String id) {
                return null;
            }
        });
        
        difficultySelector.addItem(new Difficulty(0));
        
        difficultySelector.addItem(new Difficulty(1));
        
        difficultySelector.addItem(new Difficulty(2));
        
        difficultySelector.addItem(new Difficulty(3));
        
        VBox difficultyBox=new VBox(difficultyLabel,difficultySelector);
        
        
        
            fullItems=new CheckBox("Full Convoy");
        
        fullItems.selectedProperty().addListener(new ChangeListener<Boolean>(){
            private MaterialTextField itemsField;
            private CheckBox forgeBox;
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                MaterialConfirmDialog itemsDialog=new MaterialConfirmDialog("Quantity per slot","Write a number between 0 and 65,535 to fill your convoy.","DONE","CANCEL") {
                    @Override
                    public void onPositiveButton(){
                        quantityConvoy=(int)Utils.currencyToDouble(itemsField.getText());
                        includeForge=forgeBox.isSelected();
                        if(forgeBox.isSelected()){
                        fullItems.setText("Full convoy: "+quantityConvoy+" Weapon uses/Items per slot. Forged included.");    
                        }
                        else{
                        fullItems.setText("Full convoy: "+quantityConvoy+" Weapon uses/Items per slot. ");    
                        }
                        dismiss();
                    }
                    @Override
                    public void onNegativeButton(){
                        fullItems.setSelected(false);
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
                };
                VBox extraBox=new VBox();
                itemsField=new MaterialTextField("99","Weapon uses/Items per slot");
                itemsField.lockLetters();
                itemsField.setPadding(new Insets(0,0,8,0));
                itemsField.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                    itemsDialog.onPositiveButton();    
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                itemsField.textField().textProperty().addListener(new ChangeListener<String>(){

                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        if(Utils.currencyToDouble(newValue)>65535){
                            itemsField.textField().textProperty().removeListener(this);
                            itemsField.setText("65535");
                            
                            itemsField.textField().textProperty().addListener(this);
                            itemsField.textField().positionCaret(itemsField.getText().length());
                        }
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                forgeBox=new CheckBox("Include forged items"){
                @Override
                public void requestFocus(){
                    
                }
                };
                forgeBox.setSelected(false);
                
                MaterialTooltip tooltip=new MaterialTooltip("There are around 106 empty slots for forged weapons. If you don't have near that quantity, and active this option, as many dummy slots will fill your items section in the convoy.",forgeBox);
                
                extraBox.getChildren().addAll(itemsField,forgeBox);
                itemsDialog.setCustomContent(extraBox);
                itemsDialog.unhide();
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        
        
        HBox checkBox=new HBox(fullItems);
       difficultyBox.setPadding(new Insets(12,0,12,0));
      
        setPadding(new Insets(24));
       
        
       getChildren().addAll(checkBox,difficultyBox); 
       
    }
    
}
