package task1;

import java.util.Date;

public class HireEmployee extends Employee {

    protected Date hireDate;
    protected int workHoursCount;
    protected double taxPerHour;

    public HireEmployee() {
        super();
        hireDate = new Date();
        workHoursCount = 0;
        taxPerHour = 0.0;
    }

    public HireEmployee(
            String fio,
            int age,
            int stage,
            String position,
            Date hireDate,
            int workHoursCount,
            double taxPerHour
    ) {
        super(fio, age, stage, position);
        this.hireDate = hireDate;
        this.workHoursCount = workHoursCount;
        this.taxPerHour = taxPerHour;
    }

    @Override
    public double getSalary() {
        return taxPerHour * workHoursCount;
    }

}
