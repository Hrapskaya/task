package group.innowise.task.service.impl;

import group.innowise.task.model.User;
import group.innowise.task.service.UserFactory;
import group.innowise.task.repository.UserRepository;
import group.innowise.task.repository.impl.DefaultUserRepository;
import group.innowise.task.service.UserService;
import group.innowise.task.util.Util;
import group.innowise.task.validator.UserDataValidator;
import group.innowise.task.validator.impl.AllFieldsIsPresentValidator;
import group.innowise.task.validator.impl.EmailValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static group.innowise.task.constant.UserFieldConstant.*;
import static group.innowise.task.constant.Constant.*;

public class DefaultUserService implements UserService {


    private static UserService instance;

    private final UserRepository userRepository = DefaultUserRepository.getInstance();
    private final UserFactory userFactory = UserFactory.getInstance();
    private final UserDataValidator userAddDataValidator = new AllFieldsIsPresentValidator();
    private final UserDataValidator userUpdateDataValidator = new EmailValidator();


    private DefaultUserService() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new DefaultUserService();
        }
        return instance;
    }

    @Override
    public List<String> add(Map<String, String> data) {
        List<String> result = userAddDataValidator.validate(data);
        if (result.isEmpty()) {
            User user = userFactory.create(data);
            result.addAll(checkUniqTelephoneNumbers(user.getTelephoneNumbers()));
            result.addAll(checkEmailUniq(user.getEmail()));
            if (result.isEmpty()) {
                userRepository.save(user);
            }
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        Optional<User> userFromRepository = userRepository.findById(id);
        if (userFromRepository.isPresent()) {
            userRepository.delete(id);
            result = true;
        }
        return result;
    }

    @Override
    public List<String> update(Map<String, String> data) {
        List<String> result = userUpdateDataValidator.validate(data);
        if(result.isEmpty()) {
            String userId = data.get(ID);
            if(userId != null && userId.matches(NUMBER_PATTERN)) {
                Optional<User> userFromRepository = userRepository.findById(Integer.parseInt(userId));
                if (userFromRepository.isPresent()) {
                    User user = userFromRepository.get();
                    if(data.get(TELEPHONE_NUMBERS) != null){
                        result.addAll(checkUniqTelephoneNumbers(Util.getTelephoneNumberList(data)));
                    }
                    if(data.get(EMAIL) != null){
                        result.addAll(checkEmailUniq(data.get(EMAIL)));
                    }
                    if (result.isEmpty()) {
                        Util.setDataToUser(user, data);
                        userRepository.update(user);
                    }
                } else {
                    result.add(NO_ELEMENT_WITH_SUCH_INDEX);
                }
            }else {
                result.add(SPECIFY_THE_USER_ID);
            }
        }
        return result;
    }

    @Override
    public User findById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    private List<String> checkUniqTelephoneNumbers(List<String> telephoneNumbers){
        List<String> result = new ArrayList<>();
        for (String telephoneNumber : telephoneNumbers) {
            if(userRepository.isExistTelephoneNumber(telephoneNumber)){
                result.add(TELEPHONE_NUMBER_IS_ALREADY_EXIST + telephoneNumber);
            }
        }
        return result;
    }

    private List<String> checkEmailUniq(String email){
        List<String> result = new ArrayList<>();
        Optional<User> userFromRepository = userRepository.findByEmail(email);
        if (userFromRepository.isPresent()) {
            result.add(EMAIL_IS_ALREADY_EXIST + email);
        }
        return result;
    }
}
