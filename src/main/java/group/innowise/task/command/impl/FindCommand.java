package group.innowise.task.command.impl;

import group.innowise.task.command.Command;
import group.innowise.task.constant.Constant;
import group.innowise.task.model.User;
import group.innowise.task.inputOutput.DataReaderWriter;
import group.innowise.task.inputOutput.impl.DefaultDataReaderWriter;
import group.innowise.task.service.UserService;
import group.innowise.task.service.impl.DefaultUserService;

public class FindCommand implements Command {

    private final UserService userService = DefaultUserService.getInstance();
    private final DataReaderWriter dataReaderWriter = DefaultDataReaderWriter.getInstance();

    @Override
    public void execute(String line) {
        String userIndex = line.trim();
        if (userIndex.matches(Constant.NUMBER_PATTERN)) {
            User user = userService.findById(Integer.parseInt(userIndex));
            if (user == null) {
                dataReaderWriter.write(Constant.NO_ELEMENT_WITH_SUCH_INDEX);
            } else {
                dataReaderWriter.write(user.toString());
            }
        }else {
            dataReaderWriter.write(Constant.SPECIFY_THE_USER_ID);
        }

    }
}
