package view;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import model.Note;
import model.scale.Scale;

import java.util.List;

public class FretboardView extends StackPane {

    private static final double CIRCLE_RADIUS = 10;
    private static final int NO_OF_STRINGS = 6;
    private static final int NO_OF_FRETS = 25;
    private static final Color FRETBOARD_COLOR = Color.GRAY;
    private static final double FRETBOARD_NUMBER_SIZE = 24;
    private static final Color BASE_NOTE_COLOR = Color.RED;
    private static final Color NOTE_COLOR = Color.ORANGE;

    private Circle[][] c;
    private Label[] fn = new Label[10];

    private ImageView imageView;

    public FretboardView(){
        imageView = new ImageView(new Image("fretboard.png"));
        imageView.setFitWidth(CenterView.CENTER_VIEW_WIDTH * 0.95);
        imageView.setFitHeight(CenterView.CENTER_VIEW_HEIGHT * 0.25);
        Rectangle fretboardColor = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight(), FRETBOARD_COLOR);
        fretboardColor.setOpacity(0.2);
        init();
        initFretNumbers();
        this.getChildren().addAll(imageView, fretboardColor);
        this.getChildren().addAll(fn);
        for(int string = 0; string < NO_OF_STRINGS; string++){
            for(int fret = 0; fret < NO_OF_FRETS; fret++){
                this.getChildren().add(c[string][fret]);
            }
        }
    }

    private void initFretNumbers() {
        fn[0] = new Label("3");
        fn[1] = new Label("5");
        fn[2] = new Label("7");
        fn[3] = new Label("9");
        fn[4] = new Label("12");
        fn[5] = new Label("15");
        fn[6] = new Label("17");
        fn[7] = new Label("19");
        fn[8] = new Label("21");
        fn[9] = new Label("24");

        for(Label l : fn){
            l.setTextFill(Color.WHITE);
            l.setFont(Font.font("Serif", FontWeight.BOLD, FRETBOARD_NUMBER_SIZE));
        }

        double translateXChange = -imageView.getFitWidth() * 0.455;
        double translateXMultiplier = 1;
        double translateXChangeValue = imageView.getFitWidth() * 0.0708;

        for(int fret = 1, label = 0; fret < NO_OF_FRETS; fret++){
            if(fret == 3 || fret == 5 || fret == 7 || fret == 9 || fret == 12 || fret == 15 || fret == 17 || fret == 19 || fret == 21 || fret == 24){
                fn[label].setTranslateX(translateXChange);
                fn[label].setTranslateY(imageView.getFitHeight() * 0.57);
                label++;
            }
            translateXChange = translateXChange + translateXChangeValue * translateXMultiplier;
            translateXMultiplier = translateXMultiplier * 0.94404;

        }
    }

    private void init(){

        double translateYchange = imageView.getFitHeight() * 0.418;
        double translateXchange = -imageView.getFitWidth() * 0.455;
        double translateXmultiplier = 1;
        double translateXChangeValue = imageView.getFitWidth() * 0.0708;
        c = new FretCircle[NO_OF_STRINGS][NO_OF_FRETS];
        for(int string = 0; string < NO_OF_STRINGS; string++){
            int noteValue = 0;
            int octave = 0;
            switch(string){
                case 0:{
                    noteValue = Note.E.getNumVal();
                    octave = 2;
                    break;
                }
                case 1:{
                    noteValue = Note.A.getNumVal();
                    octave = 2;
                    break;
                }
                case 2:{
                    noteValue = Note.D.getNumVal();
                    octave = 3;
                    break;
                }
                case 3:{
                    noteValue = Note.G.getNumVal();
                    octave = 3;
                    break;
                }
                case 4:{
                    noteValue = Note.B.getNumVal();
                    octave = 3;
                    break;
                }
                case 5:{
                    noteValue = Note.E.getNumVal();
                    octave = 4;
                    break;
                }
            }
            for(int fret = 0; fret < NO_OF_FRETS; fret++){
                c[string][fret] = new FretCircle(CIRCLE_RADIUS, noteValue, octave);
                c[string][fret].setFill(NOTE_COLOR);
                c[string][fret].setOpacity(0);

                if(fret == 0){ //For the first frets
                    c[string][fret].setTranslateX(-imageView.getFitWidth() * 0.495); //0.507
                    c[string][fret].setTranslateY(translateYchange);
                }
                else{
                    c[string][fret].setTranslateX(translateXchange);
                    c[string][fret].setTranslateY(translateYchange);
                    translateXchange = translateXchange + translateXChangeValue * translateXmultiplier;
                    translateXmultiplier = translateXmultiplier * 0.94404;
                }


                if(noteValue == Note.B.getNumVal()){
                    octave++;
                }
                noteValue = (noteValue + 1) % 12;
            }
            translateYchange = translateYchange - imageView.getFitHeight() * 0.164;
            translateXchange = -imageView.getFitWidth() * 0.455;
            translateXmultiplier = 1;
            translateXChangeValue = imageView.getFitWidth() * 0.0708;
        }

    }

    public void setScale(Scale scale){
        List<Note> notes = scale.getNotes();

        FadeTransition[][] fadeTransitions = new FadeTransition[NO_OF_STRINGS][NO_OF_FRETS];
        for(int string = 0; string < NO_OF_STRINGS; string++){
            for(int fret = 0; fret < NO_OF_FRETS; fret++){
                fadeTransitions[string][fret] = new FadeTransition(Duration.millis(1000), c[string][fret]);
                fadeTransitions[string][fret].setFromValue(0);
                fadeTransitions[string][fret].setToValue(0.9);
            }
        }

        for(Circle circle[] : c){
            for(Circle ci : circle){
                ci.setOpacity(0);
                ((FretCircle)ci).setColor(NOTE_COLOR);
            }
        }

        for(int note = 0; note < notes.size(); note++){
            for(int string = 0; string < NO_OF_STRINGS; string++){
                for(int fret = 0; fret < NO_OF_FRETS; fret++){
                    if(((FretCircle)(c[string][fret])).getNoteValue() == notes.get(note).getNumVal()) {
                        fadeTransitions[string][fret].play();
                        if(((FretCircle)(c[string][fret])).getNoteValue() == notes.get(0).getNumVal()){
                            ((FretCircle)c[string][fret]).setColor(BASE_NOTE_COLOR);
                        }
                        else{
                            ((FretCircle)c[string][fret]).setColor(NOTE_COLOR);
                        }
                    }
                }
            }
        }
        System.out.printf("***********************************\n%s\n", scale.toString());
    }

    public Circle[][] getFretCircles(){
        return c;
    }
}
