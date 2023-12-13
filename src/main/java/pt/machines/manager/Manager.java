package pt.machines.manager;

import pt.machines.manager.core.Application;

public class Manager {

    private static final Application application;

    static {
        application = new Application();
    }

    public static void main(String[] args) {
        Manager.application.start();
    }

}