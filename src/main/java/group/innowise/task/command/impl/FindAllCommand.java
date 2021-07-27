package group.innowise.task.command.impl;

import group.innowise.task.command.Command;
import group.innowise.task.model.User;
import group.innowise.task.inputOutput.DataReaderWriter;
import group.innowise.task.inputOutput.impl.DefaultDataReaderWriter;
import group.innowise.task.service.UserService;
import group.innowise.task.service.impl.DefaultUserService;

import java.util.List;

public class FindAllCommand implements Command {

    private final UserService userService = DefaultUserService.getInstance();
    private final DataReaderWriter dataReaderWriter = DefaultDataReaderWriter.getInstance();

    @Override
    public void execute(String line) {
        List<User> users = userService.findAll();
        for (User user : users) {
            dataReaderWriter.write(user.toString());
        }
    }
}
