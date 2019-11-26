package controller;

import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.ModelInterface;
import model.Note;
import model.scale.Scale;
import model.scale.collection.DorianScale;
import model.scale.collection.IonianScale;
import model.scale.collection.MajorScale;
import view.FretCircle;
import view.MainView;
import view.ScaleNoteBox;

public class Controller {

    private static final Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    public static final double WINDOW_WIDTH = primaryScreenBounds.getWidth() * 0.80; //0.8
    public static final double WINDOW_HEIGHT = primaryScreenBounds.getHeight() * 0.7; //0.7

    private ModelInterface model;
    private MainView view;

    private Scene scene;

    public Controller(Stage stage){
        model = new ModelInterface();
        view = new MainView(model);
        view.setEventHandlers(this);

        scene = new Scene(view, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("GuitarFretboardApp");
        stage.show();
    }

    public void onSetScaleButtonClicked() {
        if(view.getTopView().getScale().getValue() != null && view.getTopView().getKey().getValue() != null){
            Scale scale = null;
            Note key = view.getTopView().getKey().getValue();
            switch(view.getTopView().getScale().getValue()){
                case MAJOR:{
                    scale = new MajorScale(key);
                    break;
                }
                case IONIAN:{
                    scale = new IonianScale(key);
                    break;
                }
                case DORIAN:{
                    scale = new DorianScale(key);
                    break;
                }
            }

            view.getCenterView().getFretboardView().setScale(scale);
            view.getCenterView().getScaleNoteBox().setScale(scale);

            for(ScaleNoteBox.NoteTextBox ntb : view.getCenterView().getScaleNoteBox().getNoteTextBoxes()){
                ntb.setOnMouseEntered(e -> {
                    System.out.println(ntb.getNote().getDesciption() + " " + ntb.getNote().getNumVal());
                    for(Circle cArr[] : view.getCenterView().getFretboardView().getFretCircles()){
                        for(Circle c : cArr){
                            if(ntb.getNote().getNumVal() == ((FretCircle)c).getNoteValue()){
                                ((FretCircle)c).marked();
                            }
                        }
                    }
                });
                ntb.setOnMouseExited(e -> {
                    for(Circle cArr[] : view.getCenterView().getFretboardView().getFretCircles()){
                        for(Circle c : cArr){
                            if(ntb.getNote().getNumVal() == ((FretCircle)c).getNoteValue()){
                                ((FretCircle)c).unMarked();
                            }
                        }
                    }
                });
            }
        }
    }

    public void onExitButtonClicked() {
        if(model.getMetronome().isRunning()){
            model.getMetronome().stop();
        }
        Platform.exit();
    }

    public void onMetronomeButtonClicked() {
        if(model.getMetronome().isRunning()){
            model.getMetronome().stop();
        }
        else{
            model.getMetronome().start();
        }
    }
}
