package pt.machines.manager;

import pt.machines.manager.core.Aplicacao;

public class Main {

    private static final Aplicacao app;

    static {
        app = new Aplicacao();
    }

    public static void main(String[] args) {
        Main.app.iniciar();
    }

}