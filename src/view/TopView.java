package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import model.Note;
import model.scale.ScaleType;
import model.scale.collection.DorianScale;
import model.scale.collection.IonianScale;
import model.scale.collection.MajorScale;

import java.util.Arrays;
import java.util.List;

public class TopView extends HBox {

    private Button setScaleButton;
    private ComboBox<Note> key;
    private ComboBox<ScaleType> scale;

    public TopView(){

        scale = new ComboBox<>();
        List<ScaleType> scales = Arrays.asList(ScaleType.values());
        scale.getItems().addAll(scales);
        scale.setPromptText("Scale Type");

        key = new ComboBox<>();
        key.setPromptText("Key");

        scale.valueProperty().addListener(listener -> {
            key.getItems().removeAll(key.getItems());
            switch(scale.getValue()){
                case MAJOR:{
                    key.getItems().addAll(MajorScale.keySignatures);
                    key.setValue(MajorScale.keySignatures.get(0));
                    break;
                }
                case IONIAN:{
                    key.getItems().addAll(IonianScale.keySignatures);
                    key.setValue(IonianScale.keySignatures.get(0));
                    break;
                }
                case DORIAN:{
                    key.getItems().addAll(DorianScale.keySignatures);
                    key.setValue(DorianScale.keySignatures.get(0));
                    break;
                }
            }
        });

        setScaleButton = new Button("Set Scale");

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setSpacing(10);
        this.getChildren().addAll(scale, key, setScaleButton);
    }

    public Button getSetScaleButton(){
        return setScaleButton;
    }

    public ComboBox<Note> getKey(){
        return key;
    }

    public ComboBox<ScaleType> getScale() {
        return scale;
    }
}
