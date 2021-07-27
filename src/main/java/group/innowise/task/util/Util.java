package group.innowise.task.util;

import group.innowise.task.model.Role;
import group.innowise.task.model.User;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import static group.innowise.task.constant.UserFieldConstant.*;

public class Util {

    public static final String DATA_SPLITTER = " +";

    public static void setDataToUser(User user, Map<String, String> parameters) {
        String name = parameters.get(NAME);
        if (name != null) {
            user.setName(name);
        }
        String lastName = parameters.get(LAST_NAME);
        if (lastName != null) {
            user.setLastName(lastName);
        }
        String email = parameters.get(NAME);
        if (email != null) {
            user.setEmail(email);
        }
        String telephoneNumbers = parameters.get(TELEPHONE_NUMBERS);
        if (telephoneNumbers != null) {
            user.setTelephoneNumbers(getTelephoneNumberList(parameters));
        }
        String roles = parameters.get(ROLES);
        if (roles != null) {
            user.setRoles(getRolesSet(parameters));
        }
    }

    public static EnumSet<Role> getRolesSet(Map<String, String> parameters) {
        String[] rolesParameter = parameters.get(ROLES).split(DATA_SPLITTER);
        EnumSet<Role> roles = EnumSet.noneOf(Role.class);
        for (String role : rolesParameter) {
            roles.add(Role.valueOf(role.toUpperCase()));
        }
        return roles;
    }

    public static List<String> getTelephoneNumberList(Map<String, String> parameters) {
        String telephoneNumbers = parameters.get(TELEPHONE_NUMBERS);
        return Arrays.asList(telephoneNumbers.split(DATA_SPLITTER));
    }
}
