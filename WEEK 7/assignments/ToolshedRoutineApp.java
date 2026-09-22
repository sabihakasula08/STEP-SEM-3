// Level 1: Abstract Base Class[cite: 16]
abstract class GardenTool {
    public abstract String use();
}

// Level 2: Intermediate Concrete Subclass[cite: 16]
class CuttingTool extends GardenTool {
    public CuttingTool() {
        super();
    }

    @Override
    public String use() {
        return "Using the tool in the garden, blade sharpened first";
    }
}

// Level 3: Multilevel Subclass layering additional detail via super.use()[cite: 16]
class Pruner extends CuttingTool {
    public Pruner() {
        super();
    }

    @Override
    public String use() {
        return super.use() + ", then trimming branches precisely";
    }
}

public class ToolshedRoutineApp {
    public static void main(String[] args) {
        CuttingTool c = new CuttingTool();
        System.out.println(c.use());

        Pruner p = new Pruner();
        System.out.println(p.use());
    }
}