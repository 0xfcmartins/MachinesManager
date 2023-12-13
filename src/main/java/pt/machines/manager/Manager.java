package pt.machines.manager;

import pt.machines.manager.core.Application;

/**
 * The Manager class represents a manager for the application. It is responsible for
 * starting and managing the application.
 */
public class Manager {

    private static final Application application;

    static {
        application = new Application();
    }

    public static void main(String[] args) {
        Manager.application.start();
    }

}