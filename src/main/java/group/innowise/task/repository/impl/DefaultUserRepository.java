package group.innowise.task.repository.impl;

import group.innowise.task.constant.Constant;
import group.innowise.task.model.User;
import group.innowise.task.inputOutput.RepositoryReaderWriter;
import group.innowise.task.inputOutput.impl.DefaultRepositoryReaderWriter;
import group.innowise.task.repository.UserRepository;
import group.innowise.task.util.IndexGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultUserRepository implements UserRepository {

    private final IndexGenerator indexGenerator;

    private static UserRepository instance;
    private final RepositoryReaderWriter repositoryReaderWriter = DefaultRepositoryReaderWriter.getInstance();

    private final List<User> users;

    private DefaultUserRepository() {
        File f = new File(Constant.USER_FILE_NAME);
        if(f.exists() && !f.isDirectory()) {
            users = repositoryReaderWriter.readUser();
        }else {
            users = new ArrayList<>();
        }
        indexGenerator = new IndexGenerator(findStartIndex());
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new DefaultUserRepository();
        }
        return instance;
    }

    @Override
    public void save(User user) {
        user.setId(indexGenerator.getId());
        users.add(user);
        repositoryReaderWriter.writeUser(users);
    }

    @Override
    public void delete(int index) {
        users.remove(index);
        repositoryReaderWriter.writeUser(users);
    }

    @Override
    public void update(User user) {
        int listIndex = users.indexOf(user);
        users.set(listIndex, user);
        repositoryReaderWriter.writeUser(users);
    }

    @Override
    public Optional<User> findById(int id) {
        Optional<User> user = users.stream()
                .filter(x -> x.getId() == id)
                .findFirst();
        return user;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> user = users.stream()
                .filter(x -> x.getEmail().equalsIgnoreCase(email))
                .findFirst();
        return user;
    }

    @Override
    public boolean isExistTelephoneNumber(String telephoneNumber) {
        Optional<String> result = users.stream()
                .flatMap(x -> x.getTelephoneNumbers().stream())
                .filter(x -> x.equals(telephoneNumber))
                .findFirst();
        return result.isPresent();
    }

    private int findStartIndex() {
        int startIndex = users.stream()
                .mapToInt(User::getId)
                .max()
                .orElse(-1);
        return ++startIndex;
    }
}
