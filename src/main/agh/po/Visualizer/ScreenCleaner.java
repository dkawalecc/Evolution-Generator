package agh.po.Visualizer;

import java.io.IOException;

public class ScreenCleaner {
    public static void clean() {
        try {
            new ProcessBuilder("cmd", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}