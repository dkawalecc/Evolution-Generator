package agh.po;

public class Parameters {
    public int width;
    public int height;
    public int startEnergy;
    public int moveEnergy;
    public int plantEnergy;
    public double jungleRatio;
    public int animalsCount;
    public int duration;

    public Parameters(int width, int height, int startEnergy, int moveEnergy, int plantEnergy, double jungleRatio, int randomAnimals, int days){
        this.width = width;
        this.height = height;
        this.startEnergy = startEnergy;
        this.moveEnergy = moveEnergy;
        this.plantEnergy = plantEnergy;
        this.jungleRatio = jungleRatio;
        this.animalsCount = randomAnimals;
        this.duration = days;
    }
}
