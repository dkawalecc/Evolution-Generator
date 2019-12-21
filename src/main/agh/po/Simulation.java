package agh.po;

import agh.po.Visualizer.ScreenCleaner;
import agh.po.WorldMap.SteppeMap;

public class Simulation {

    private SteppeMap map;
    private int currentDay;

    private int width = 200;
    private int height = 100;
    private int startEnergy = 100;
    private int moveEnergy = 10;
    private int plantEnergy = 40;
    private double jungleRatio = 0.4;
    private int animalsCount = 8;
    private int duration = 40; //days

    public Simulation(){
        this.map = new SteppeMap(
                this.width,
                this.height,
                this.startEnergy,
                this.moveEnergy,
                this.plantEnergy,
                this.jungleRatio,
                this.animalsCount
        );
        this.duration = this.duration;
        //this.delay = parameters.delay;
    }

    public void start() {
        long startTime;
        long endTime;
        long durationTime;
        while (this.currentDay < this.duration) {
            startTime = System.currentTimeMillis();
            this.map.newDay();
            this.currentDay++;
            endTime = System.currentTimeMillis();
            //make all updates take the same time (delay = 100)
            durationTime = endTime - startTime;

            if (100 - durationTime > 0) {
                try {
                    Thread.sleep(100 - durationTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ScreenCleaner.clean();
            this.drawMap();
            //System.out.println();
        }
    }

    private void drawMap() {
        this.map.toString();
    } //System.out.println();
}


//update etc.
//space for the usage of the steppe function (day simulation generator)
