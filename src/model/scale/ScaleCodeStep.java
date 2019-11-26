package model.scale;

public enum ScaleCodeStep {

    HALF(1),
    WHOLE(2),
    WHOLEHALF(3);

    private final int stepSize;

    private ScaleCodeStep(int stepSize){
        this.stepSize = stepSize;
    }

    public int getStepSize(){
        return this.stepSize;
    }
}
