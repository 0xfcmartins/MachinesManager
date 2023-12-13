package pt.machines.manager.objects;

public class Machine {
    String code;
    int workingHours;

    public Machine(String code, int workingHours) {
        this.code = code;
        this.workingHours = workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    public String getCode() {
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
