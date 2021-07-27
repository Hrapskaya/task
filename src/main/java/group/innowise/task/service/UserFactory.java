package group.innowise.task.service;

import group.innowise.task.model.User;
import group.innowise.task.util.Util;

import java.util.Map;

import static group.innowise.task.constant.UserFieldConstant.*;

public class UserFactory {

    public static UserFactory instance;

    private UserFactory() {
    }

    public static UserFactory getInstance() {
        if (instance == null) {
            instance = new UserFactory();
        }
        return instance;
    }

    public User create(Map<String, String> parameters){
        User user = new User.Builder()
                .setName(parameters.get(NAME))
                .setLastName(parameters.get(LAST_NAME))
                .setEmail(parameters.get(EMAIL))
                .setTelephoneNumbers(Util.getTelephoneNumberList(parameters))
                .setRoles(Util.getRolesSet(parameters))
                .build();
        return user;
    }

}
