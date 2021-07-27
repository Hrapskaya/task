package group.innowise.task.validator.impl;

import group.innowise.task.model.Role;
import group.innowise.task.validator.UserDataValidator;

import java.util.*;
import java.util.stream.Collectors;

import static group.innowise.task.constant.UserFieldConstant.*;

public class RolesValidator extends UserDataValidator {

    public static final String DATA_SPLITTER = " +";
    public static final int MAX_ROLES_NUMBER = 2;
    public static final String INCORRECT_USER_ROLES_MESSAGE = "Roles options:\n" +
            "lvl.1: USER, CUSTOMER;\n" +
            "lvl.2: ADMIN, PROVIDER;\nlvl.3: Super_ADMIN;\n" +
            "At the same time, a user can have 1 role from each level.\n" +
            "If the user has the SUPER_ADMIN role, it is forbidden to select other roles";


    private final Set<String> roles;

    {
        roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
    }

    public RolesValidator() {
        super(new TelephoneNumbersValidator());
    }

    @Override
    public List<String> validate(Map<String, String> data) {
        List<String> result = new ArrayList<>();
        String rolesData = data.get(ROLES);
        if (rolesData != null && !rolesData.isEmpty()) {
            List<String> rolesFromData = Arrays.asList(rolesData.split(DATA_SPLITTER));
           if(!isAllDataRoles(rolesFromData) || !isDataMatchOptions(rolesFromData)){
               result.add(INCORRECT_USER_ROLES_MESSAGE);
           }
        }
        result.addAll(chain(data));
        return result;
    }

    private boolean isAllDataRoles(List<String> rolesFromData) {
        boolean result = true;
        for (String role : rolesFromData) {
            if (!roles.contains(role.toUpperCase())) {
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean isDataMatchOptions(List<String> rolesFromData) {
        boolean result = false;
        int rolesNumber = rolesFromData.size();
        if (rolesNumber == 1) {
            result = true;
        }
        if (rolesNumber == MAX_ROLES_NUMBER) {
            int variable = rolesFromData.stream()
                    .map(String::toUpperCase)
                    .map(Role::valueOf)
                    .mapToInt(Role::getLevel)
                    .reduce(1, (a, b) -> a * b);
            if(variable == 2){
                result = true;
            }
        }
        return result;
    }

}
