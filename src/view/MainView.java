package view;

import controller.Controller;
import javafx.scene.layout.BorderPane;
import model.ModelInterface;

public class MainView extends BorderPane {

    private ModelInterface model;

    private CenterView centerView;
    private TopView topView;

    public MainView(ModelInterface model){
        this.model = model;

        centerView = new CenterView();
        topView = new TopView();

        this.setCenter(centerView);
        this.setTop(topView);
    }

    public CenterView getCenterView(){
        return centerView;
    }

    public TopView getTopView(){
        return topView;
    }

    public void setEventHandlers(Controller c){
        topView.getSetScaleButton().setOnAction(e -> {
            c.onSetScaleButtonClicked();
        });

        centerView.getExitButton().setOnMouseClicked(e -> {
            c.onExitButtonClicked();
        });

        centerView.getMetronomeButton().setOnMouseClicked(e -> {
            c.onMetronomeButtonClicked();
        });
    }
}
