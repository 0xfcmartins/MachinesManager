package pt.machines.manager.core.storage;

import pt.machines.manager.objects.Machine;

import java.util.LinkedList;

/**
 * The Memory class provides methods for storing, selecting, retrieving, and removing Machine objects in memory.
 */
public class Memory {

    /**
     * The machines variable is a constant LinkedList that stores Machine objects.
     * <p>
     * It is part of the Memory class, which provides methods for storing, selecting, retrieving, and removing Machine
     * objects in memory.
     */
    public static final LinkedList<Machine> machines;

    static {
        machines = new LinkedList<>();
    }

    /**
     * Adds a new Machine object to the machines list in memory.
     *
     * @param code         The code of the machine.
     * @param workingHours The working hours of the machine.
     */
    public static void store(String code, int workingHours) {
        machines.add(new Machine(code, workingHours));
    }

    /**
     * Selects a Machine object from the machines list in memory based on its code.
     *
     * @param code The code of the machine.
     * @return The selected Machine object, or null if no match is found.
     */
    public static Machine select(String code) {
        for (Machine machine : machines) {
            if (machine.getCode().equals(code))
                return machine;
        }

        return null;
    }

    /**
     * Retrieves all the Machine objects stored in memory.
     *
     * @return An array of Machine objects. If there are no machines stored in memory, an empty array is returned.
     */
    public static Machine[] getAll() {
        return machines.toArray(new Machine[0]);
    }

    /**
     * Removes a machine from memory based on its code.
     *
     * @param code The code of the machine to be removed.
     * @return true if the machine was found and removed, false otherwise.
     */
    public static boolean remove(String code) {
        for (Machine machine : machines) {
            if (machine.getCode().equals(code))
                return machines.remove(machine);
        }

        return false;
    }
}
