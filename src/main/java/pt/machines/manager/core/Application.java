package pt.machines.manager.core;

import pt.machines.manager.core.storage.Memory;
import pt.machines.manager.exceptions.FileLoadException;
import pt.machines.manager.files.ResourceFileManager;
import pt.machines.manager.objects.Machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import static java.util.Objects.isNull;

/**
 * The Application class represents an application that manages the machines.
 */
public class Application {

    @SuppressWarnings("SpellCheckingInspection")
    private final static String MAIN_CONFIG = "maquinas.txt";

    /**
     * Initializes the application by loading configuration from a resource file,
     * storing the data in memory, and printing the loaded machines.
     *
     * @throws RuntimeException If there is an error while initializing.
     */
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
     * </p>
     * Prints the information of a machine.
     *
     * @param code The code of the machine.
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
     * </p>
     * Updates the working hours of a machine.
     *
     * @param code         The code of the machine.
     * @param workingHours The new working hours of the machine.
     */
    private void updateMachineWorkingHours(String code, int workingHours) {
        Machine machine = Memory.select(code);

        if (machine != null)
            machine.setWorkingHours(workingHours);

        System.out.println("Updated " + machine);
    }

    /**
     * 4. Student 3
     * </p>
     * Removes a machine from memory based on its code.
     *
     * @param code The code of the machine.
     */
    private void removeMachine(String code) {
        boolean result = Memory.remove(code);

        if (result)
            System.out.println("Machine " + code + " has been removed!");

        System.out.println("Machine not found!");
    }

    /**
     * 4. Student 4
     * </p>
     * Saves the state of all machines in memory by storing their information in a resource file.
     * Each machine's information is converted to a string representation and stored in the file.
     * If there are any errors while saving the state, a RuntimeException is thrown.
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

    /**
     * This method displays a menu of options for managing machines.
     */
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
                        System.out.print("Code > ");
                        code = reader.readLine();
                        this.removeMachine(code);

                        break;
                    case 4:
                        this.saveState();
                }

                if (option != 4) {
                    System.out.println("> Press some key to continue");
                    reader.readLine();
                }
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
