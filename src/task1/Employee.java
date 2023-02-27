package task1;

public abstract class Employee extends Person {

    protected int stage;
    protected String position;

    public Employee() {
        super();
        stage = 0;
        position = "no position";
    }

    public Employee(String fio, int age, int stage, String position) {
        super(fio, age);
        this.stage = stage;
        this.position = position;
    }

    public abstract double getSalary();

}
