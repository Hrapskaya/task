package group.innowise.task.util;

public class IndexGenerator {

    private int idCount;

    public IndexGenerator(int idCount) {
        this.idCount = idCount;
    }

    public int getId() {
        return idCount++;
    }

}
