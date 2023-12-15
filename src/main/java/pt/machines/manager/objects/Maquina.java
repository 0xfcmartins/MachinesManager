package pt.machines.manager.objects;

/**
 * The Machine class represents a machine with a unique code and a number of working hours.
 */
public class Maquina {
    String code;
    int workingHours;

    public Maquina(String code, int workingHours) {
        this.code = code;
        this.workingHours = workingHours;
    }

    public void defineHorasDeTrabalho(int workingHours) {
        this.workingHours = workingHours;
    }

    public String getCodigo() {
        return this.code;
    }

    public String storingString() {
        return this.code + "," + this.workingHours;
    }

    @Override
    public String toString() {
        return "Machine '" + this.code + "' with a total working hours of " + this.workingHours;
    }
}
