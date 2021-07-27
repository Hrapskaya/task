package group.innowise.task.inputOutput.impl;

import group.innowise.task.constant.Constant;
import group.innowise.task.model.User;
import group.innowise.task.inputOutput.RepositoryReaderWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultRepositoryReaderWriter implements RepositoryReaderWriter {


    private static RepositoryReaderWriter instance;

    private DefaultRepositoryReaderWriter() {
    }

    public static RepositoryReaderWriter getInstance() {
        if (instance == null) {
            instance = new DefaultRepositoryReaderWriter();
        }
        return instance;
    }

    @Override
    public void writeUser(List<User> users) {
        try (FileOutputStream fs = new FileOutputStream(Constant.USER_FILE_NAME);
             ObjectOutputStream os = new ObjectOutputStream(fs)) {
            os.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> readUser() {
        List<User> users = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(Constant.USER_FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            users = (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }
}
