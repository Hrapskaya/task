package group.innowise.task.inputOutput;

import group.innowise.task.model.User;

import java.util.List;

public interface RepositoryReaderWriter {

    void writeUser(List<User> users);

    List<User> readUser();
}
