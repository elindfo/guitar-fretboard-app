package view;

import javafx.animation.ScaleTransition;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model.Note;

public class FretCircle extends Circle {

    private int noteValue;
    private int octave;
    private Color color;

    public FretCircle(double radius, int noteValue, int octave){
        super(radius);
        this.noteValue = noteValue;
        this.octave = octave;
        this.color = null;
    }

    public int getNoteValue() {
        return noteValue;
    }

    public int getOctave() {
        return octave;
    }

    public void setColor(Color c){
        this.setFill(c);
    }

    public void marked(){
        color = (Color)this.getFill();
        ScaleTransition st = new ScaleTransition(Duration.millis(150),this);
        st.setToX(1.4);
        st.setToY(1.4);
        DropShadow ds = new DropShadow(10, 4, 4, Color.BLACK);
        this.setEffect(ds);
        this.setFill(((Color) this.getFill()).saturate());
        st.play();
    }

    public void unMarked(){
        ScaleTransition st = new ScaleTransition(Duration.millis(150),this);
        st.setToX(1);
        st.setToY(1);
        this.setStroke(null);
        this.setEffect(null);
        this.setFill(color);
        st.play();
    }
}
