package group.innowise.task.command;

import group.innowise.task.command.impl.*;

public enum Action {

    ADD(new AddCommand()),
    FIND(new FindCommand()),
    FIND_ALL(new FindAllCommand()),
    DELETE(new DeleteCommand()),
    UPDATE(new UpdateCommand());

    private Command command;

    Action(Command command) {
        this.command = command;
    }

    public Command getCommand(){
        return this.command;
    }
}
