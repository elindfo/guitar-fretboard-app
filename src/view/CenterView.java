package view;

import controller.Controller;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CenterView extends StackPane {

    public static final double CENTER_VIEW_WIDTH = Controller.WINDOW_WIDTH;
    public static final double CENTER_VIEW_HEIGHT = Controller.WINDOW_HEIGHT;

    private FretboardView fretboardView;
    private ImageView background;
    private CenterViewButton exitButton, metronomeButton;
    private ScaleNoteBox scaleNoteBox;


    public CenterView(){
        fretboardView = new FretboardView();

        background = new ImageView(new Image("backgroundblue.jpg"));
        background.setFitWidth(CENTER_VIEW_WIDTH);
        background.setFitHeight(CENTER_VIEW_HEIGHT);

        exitButton = new CenterViewButton("Exit");

        metronomeButton = new CenterViewButton("Met");

        HBox buttonBox = new HBox();
        buttonBox.setTranslateX(CENTER_VIEW_WIDTH * 0.01);
        buttonBox.setTranslateY(CENTER_VIEW_HEIGHT * 0.4); //0.4
        buttonBox.setSpacing(10);
        buttonBox.getChildren().addAll(exitButton, metronomeButton);

        scaleNoteBox = new ScaleNoteBox();
        scaleNoteBox.setTranslateX(CENTER_VIEW_WIDTH * 0.03);
        scaleNoteBox.setTranslateY(-CENTER_VIEW_HEIGHT * 0.3);
        scaleNoteBox.setMaxHeight(ScaleNoteBox.NOTE_TEXT_BOX_HEIGHT);

        this.getChildren().addAll(background, fretboardView, buttonBox, scaleNoteBox);
    }

    public FretboardView getFretboardView(){
        return fretboardView;
    }

    public CenterViewButton getExitButton(){
        return exitButton;
    }

    public CenterViewButton getMetronomeButton() {
        return metronomeButton;
    }

    public ScaleNoteBox getScaleNoteBox() {
        return scaleNoteBox;
    }
}
