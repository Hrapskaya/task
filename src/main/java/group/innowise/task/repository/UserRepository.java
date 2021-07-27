package group.innowise.task.repository;

import group.innowise.task.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    void delete(int index);

    void update(User user);

    Optional<User> findById(int id);

    List<User> findAll();

    Optional<User> findByEmail(String email);

    boolean isExistTelephoneNumber(String telephoneNumber);
}
