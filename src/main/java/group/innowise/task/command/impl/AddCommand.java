package group.innowise.task.command.impl;

import group.innowise.task.command.Command;

import group.innowise.task.constant.Constant;
import group.innowise.task.util.UserFieldsParser;
import group.innowise.task.inputOutput.DataReaderWriter;
import group.innowise.task.inputOutput.impl.DefaultDataReaderWriter;
import group.innowise.task.service.UserService;
import group.innowise.task.service.impl.DefaultUserService;

import java.util.List;
import java.util.Map;

public class AddCommand implements Command {

    private final UserService userService = DefaultUserService.getInstance();
    private final DataReaderWriter dataReaderWriter = DefaultDataReaderWriter.getInstance();


    public void execute(String line) {
        Map<String, String> parameters = UserFieldsParser.parseParameters(line);
        List<String> resultOfAdd = userService.add(parameters);
        if (resultOfAdd.isEmpty()) {
            dataReaderWriter.write(Constant.COMPLETED_SUCCESSFULLY);
        } else {
            dataReaderWriter.write(resultOfAdd);
        }
    }
}
