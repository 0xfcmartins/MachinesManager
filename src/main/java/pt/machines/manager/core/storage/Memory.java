package pt.machines.manager.core.storage;

import pt.machines.manager.objects.Machine;

import java.util.LinkedList;

public class Memory {

    public static final LinkedList<Machine> machines;

    static {
        machines = new LinkedList<>();
    }

    public static void store(String code, int workingHours) {
        machines.add(new Machine(code, workingHours));
    }

    public static Machine select(String code) {
        for (Machine machine : machines) {
            if (machine.getCode().equals(code))
                return machine;
        }

        return null;
    }

    public static Machine[] getAll() {
        return machines.toArray(new Machine[0]);
    }

    public static boolean remove(String code) {
        for (Machine machine : machines) {
            if (machine.getCode().equals(code))
                return machines.remove(machine);
        }

        return false;
    }
}
