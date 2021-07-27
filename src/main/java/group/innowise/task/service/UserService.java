package group.innowise.task.service;

import group.innowise.task.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<String> add(Map<String, String> data);

    boolean delete(int id);

    List<String> update(Map<String, String> data);

    User findById(int id);

    List<User> findAll();
}
