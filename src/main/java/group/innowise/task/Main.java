package group.innowise.task;

import group.innowise.task.command.Action;
import group.innowise.task.command.Command;
import group.innowise.task.constant.Constant;
import group.innowise.task.inputOutput.DataReaderWriter;
import group.innowise.task.inputOutput.impl.DefaultDataReaderWriter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static final String FIST_WORD_PATTERN = ".*:";
    public static final String EXCEPT_FIRST_WORD_PATTERN = ":.*";

    private static final Set<String> actions;
    private final static DataReaderWriter dataReaderWriter = DefaultDataReaderWriter.getInstance();

    static {
        actions = Arrays.stream(Action.values())
                .map(Action::name)
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        String line = dataReaderWriter.read().trim();
        String action = line.replaceAll( EXCEPT_FIRST_WORD_PATTERN, "").trim().toUpperCase();
        while (!action.equals(Constant.EXIT_COMMAND)) {
            line = line.replaceAll(FIST_WORD_PATTERN, "").trim();
            if (actions.contains(action)) {
                Command command = Action.valueOf(action).getCommand();
                command.execute(line);
            }else {
                dataReaderWriter.write(Constant.UNKNOWN_COMMAND);
            }
            line = dataReaderWriter.read().trim();
            action =line.replaceAll( EXCEPT_FIRST_WORD_PATTERN, "").trim().toUpperCase();
        }
    }
}
