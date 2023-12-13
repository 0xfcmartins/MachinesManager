package pt.machines.manager.core;

import pt.machines.manager.core.storage.Memory;
import pt.machines.manager.core.ui.Menu;
import pt.machines.manager.exceptions.FileLoadException;
import pt.machines.manager.files.ResourceFileManager;
import pt.machines.manager.objects.Machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import static java.util.Objects.isNull;

public class Application {

    @SuppressWarnings("SpellCheckingInspection")
    private final static String MAIN_CONFIG = "maquinas.txt";
    private final Menu menu = new Menu();

    private void init() {
        try {
            String[] lines = ResourceFileManager.loadResource(MAIN_CONFIG);

            for (String line : lines) {
                String[] split = line.trim().split(",");
                Memory.store(split[0], Integer.parseInt(split[1]));
            }

            System.out.println("Loaded machines:");

            for (Machine machine : Memory.getAll())
                System.out.println(machine);

            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 4. Student 1
     */
    private void printMachineInformation(String code) {
        Machine machine = Memory.select(code);
        if (isNull(machine)) {
            System.out.println("Machine not found!");

            return;
        }

        System.out.println(machine);
    }

    /**
     * 4. Student 2
     */
    private void updateMachineWorkingHours(String code, int workingHours) {
        Machine machine = Memory.select(code);

        if (machine != null)
            machine.setWorkingHours(workingHours);

        System.out.println("Updated " + machine);
    }

    /**
     * 4. Student 3
     */
    private String removeMachine(String code) {
        boolean result = Memory.remove(code);

        if (result)
            return "Machine " + code + " has been removed!";

        return "Machine not found!";
    }

    /**
     * 4. Student 4
     */
    private void saveState() {
        Machine[] machines = Memory.getAll();
        String[] machinesToStore = new String[machines.length];

        for (int i = 0; i < machines.length; i++) {
            machinesToStore[i] = machines[i].storingString();
        }

        try {
            ResourceFileManager.store(MAIN_CONFIG, machinesToStore);
        } catch (IOException | FileLoadException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void menu() {
        int option = 0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (option != 4) {
            System.out.println("1. Show the information of a machine");
            System.out.println("2. Update a machine working capacity");
            System.out.println("3. Remove machine");
            System.out.println("4. Exit");
            System.out.print("> ");

            try {
                option = Integer.parseInt(reader.readLine());
                String code;
                int workingHours;

                switch (option) {
                    case 1:
                        System.out.print("Code > ");
                        code = reader.readLine();
                        this.printMachineInformation(code);

                        break;
                    case 2:
                        System.out.print("Code > ");
                        code = reader.readLine();

                        System.out.print("New working hours > ");
                        workingHours = Integer.parseInt(reader.readLine());
                        this.updateMachineWorkingHours(code, workingHours);

                        break;
                    case 3:

                        break;
                    case 4:
                }

                System.out.println("> Press some key to continue");
                reader.readLine();
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public void start() {
        this.init();
        this.menu();
    }

}
