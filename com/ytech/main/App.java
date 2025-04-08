package com.ytech.main;

import com.ytech.graphics.Window;
import com.ytech.graphics.layouts.Orphelinats;

public class App {
    public static Window window;
    public static Orphelinats orphelinats = new Orphelinats();

    public static void main(String[] args) {
        window = new Window(800, 680);
    }
}