package view;

import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CenterViewButton extends StackPane {

    private static final double CENTER_VIEW_BUTTON_WIDTH = CenterView.CENTER_VIEW_WIDTH * 0.07;
    private static final double CENTER_VIEW_BUTTON_HEIGHT = CenterView.CENTER_VIEW_HEIGHT * 0.05;

    private static final Color BUTTON_COLOR = Color.LIGHTGRAY;

    private Rectangle button;
    private Label text;

    public CenterViewButton(String text){
        button = new Rectangle(CENTER_VIEW_BUTTON_WIDTH, CENTER_VIEW_BUTTON_HEIGHT);
        button.setArcHeight(10);
        button.setArcWidth(10);
        button.setFill(BUTTON_COLOR);
        button.setStroke(BUTTON_COLOR.darker());
        button.setStrokeWidth(3);

        this.text = new Label(text);
        this.text.setFont(Font.font("Serif", FontWeight.BOLD, 25));

        setEventHandlers();

        this.getChildren().addAll(button, this.text);
    }

    private void setEventHandlers() {
        button.setOnMouseEntered(e -> {
            button.setEffect(new Glow(1));
        });

        button.setOnMouseExited(e -> {
            button.setEffect(null);
        });

        text.setOnMouseEntered(e -> {
            button.setEffect(new Glow(1));
        });

        text.setOnMouseExited(e -> {
            button.setEffect(null);
        });
    }
}
