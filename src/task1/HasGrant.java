package task1;

public interface HasGrant {

    default double grant() {
        return 10.0;
    }

}
