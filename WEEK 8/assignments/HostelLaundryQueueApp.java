// Wash type interface enabling open-ended addition of new wash programs[cite: 70]
interface LaundryWashType {
    String getName();
    int getDurationMinutes();
    double getCharge();
}

class QuickWash implements LaundryWashType {
    @Override public String getName() { return "Quick"; }
    @Override public int getDurationMinutes() { return 30; }
    @Override public double getCharge() { return 20.00; }
}

class NormalWash implements LaundryWashType {
    @Override public String getName() { return "Normal"; }
    @Override public int getDurationMinutes() { return 45; }
    @Override public double getCharge() { return 30.00; }
}

class HeavyWash implements LaundryWashType {
    @Override public String getName() { return "Heavy"; }
    @Override public int getDurationMinutes() { return 60; }
    @Override public double getCharge() { return 45.00; }
}

class LaundryMachine {
    private final String machineId;
    private boolean isBusy;

    public LaundryMachine(String machineId) {
        this.machineId = machineId;
        this.isBusy = false;
    }

    public String getMachineId() { return machineId; }
    public boolean isBusy() { return isBusy; }

    public void markBusy() { this.isBusy = true; }
    public void markFree() { this.isBusy = false; }
}

class LaundryStudent {
    private final String name;

    public LaundryStudent(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class LaundryService {
    // Manages booking workflow and ensures machine availability[cite: 70]
    public void startWash(LaundryStudent student, LaundryMachine machine, LaundryWashType washType) {
        if (machine.isBusy()) {
            System.out.printf("Machine %s is currently busy.\n", machine.getMachineId());
            return;
        }

        machine.markBusy();
        System.out.printf("%s wash started on %s for %s (%d min). Charge: %.2f.\n",
                washType.getName(), machine.getMachineId(), student.getName(),
                washType.getDurationMinutes(), washType.getCharge());
    }

    public void completeWash(LaundryMachine machine) {
        machine.markFree();
        System.out.printf("%s cycle completed. %s is now free.\n",
                machine.getMachineId(), machine.getMachineId());
    }
}

public class HostelLaundryQueueApp {
    public static void main(String[] args) {
        LaundryService service = new LaundryService();

        LaundryMachine m1 = new LaundryMachine("M1");
        LaundryMachine m2 = new LaundryMachine("M2");

        LaundryStudent asha = new LaundryStudent("Asha");
        LaundryStudent ravi = new LaundryStudent("Ravi");
        LaundryStudent neha = new LaundryStudent("Neha");

        // Asha starts Quick wash on M1[cite: 70]
        service.startWash(asha, m1, new QuickWash());

        // Ravi attempts to start Heavy wash on M1 (busy)[cite: 70]
        service.startWash(ravi, m1, new HeavyWash());

        // Ravi starts Heavy wash on M2[cite: 70]
        service.startWash(ravi, m2, new HeavyWash());

        // Machine M1 completes cycle[cite: 70]
        service.completeWash(m1);

        // Neha starts Normal wash on M1[cite: 70]
        service.startWash(neha, m1, new NormalWash());
    }
}