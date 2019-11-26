package view;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import model.Note;
import model.scale.Scale;

import java.util.ArrayList;
import java.util.List;

public class ScaleNoteBox extends HBox {

    public static final double NOTE_TEXT_BOX_HEIGHT = CenterView.CENTER_VIEW_HEIGHT * 0.1;
    public static final double NOTE_TEXT_BOX_WIDTH = CenterView.CENTER_VIEW_WIDTH * 0.04;

    private List<NoteTextBox> notes;

    public ScaleNoteBox() {
        notes = new ArrayList<>();

    }

    public void setScale(Scale scale) {
        FadeTransition[] fadeTransitions = new FadeTransition[scale.getNotes().size()];
        this.getChildren().removeAll(this.getChildren());
        notes.clear();
        for(int i = 0; i < scale.getNotes().size(); i++) {
            NoteTextBox newBox;
            if (i == 0) {
                notes.add(newBox = new NoteTextBox(Color.RED, scale.getNotes().get(i)));
            } else {
                notes.add(newBox = new NoteTextBox(Color.ORANGE, scale.getNotes().get(i)));
            }
            fadeTransitions[i] = new FadeTransition(Duration.millis(500), newBox);
            fadeTransitions[i].setFromValue(0);
            fadeTransitions[i].setToValue(1);
            fadeTransitions[i].play();
        }
        this.getChildren().addAll(notes);
    }

    public List<NoteTextBox> getNoteTextBoxes(){
        return notes;
    }

    public class NoteTextBox extends StackPane {

        private Rectangle background;
        private Label text;
        private Note n;
        private Color backgroundColor;

        public NoteTextBox(Color backgroundColor, Note n) {
            background = new Rectangle(NOTE_TEXT_BOX_WIDTH, NOTE_TEXT_BOX_HEIGHT, backgroundColor);
            background.setStroke(Color.WHITE);
            background.setStrokeWidth(2);
            background.setArcHeight(20);
            background.setArcWidth(20);

            this.backgroundColor = backgroundColor;

            this.n = n;

            this.text = new Label(n.getDesciption());
            this.text.setTextFill(Color.BLACK);
            this.text.setFont(Font.font("Serif", FontWeight.BOLD, 20));

            setEventHandlers();

            this.getChildren().addAll(background, this.text);
        }

        public void setNote(Note note){
            text.setOpacity(0);
            text.setText(note.getDesciption());
            FadeTransition ft = new FadeTransition(Duration.millis(500), text);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();
        }

        public Note getNote(){
            return n;
        }

        private void setEventHandlers(){
            background.setOnMouseEntered(e -> {
                background.setFill(Color.LIGHTGRAY);
            });
            text.setOnMouseEntered(e -> {
                background.setFill(Color.LIGHTGRAY);
            });
            background.setOnMouseExited(e -> {
                background.setFill(backgroundColor);
            });
            text.setOnMouseExited(e -> {
                background.setFill(backgroundColor);
            });
        }
    }
}


