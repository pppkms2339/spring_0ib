package task1;

public class Student extends Person implements HasGrant {

    protected String group;

    public Student() {
        super();
        group = "default group";
    }

    public Student(String fio, int age, String group) {
        super(fio, age);
        this.group = group;
    }

    @Override
    public double grant() {
        return 20.0;
    }

}
