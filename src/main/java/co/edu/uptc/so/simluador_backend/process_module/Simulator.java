// Clase Simulator
public class Simulator {
    private int delay;
    private int maxNextProcessTime;
    private int maxNextIOTime;
    private int maxIOExecutionTime;
    private int quantum;
    private int clock;
    private Scheduler scheduler;
    private CPU cpu;

    public Simulator(int delay, int maxNextProcessTime, int maxNextIOTime, int maxIOExecutionTime, int quantum) {
        this.delay = delay;
        this.maxNextProcessTime = maxNextProcessTime;
        this.maxNextIOTime = maxNextIOTime;
        this.maxIOExecutionTime = maxIOExecutionTime;
        this.quantum = quantum;
        this.clock = 0;
        this.scheduler = new Scheduler();
        this.cpu = new CPU();
    }

    // Implementar los métodos y lógica requeridos en el diagrama de clases
}