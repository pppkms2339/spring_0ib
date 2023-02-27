package task1;

public class StaffEmployee extends Employee {

    protected double oklad;

    public StaffEmployee() {
        super();
        oklad = 0.0;
    }

    public StaffEmployee(String fio, int age, int stage, String position, double oklad) {
        super(fio, age, stage, position);
        this.oklad = oklad;
    }

    @Override
    public double getSalary() {
        return oklad * 1.7;
    }

}
