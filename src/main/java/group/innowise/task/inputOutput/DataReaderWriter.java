package group.innowise.task.inputOutput;

import java.util.List;

public interface DataReaderWriter {

    void write(String text);

    void write(List<String> list);

    String read();

}
