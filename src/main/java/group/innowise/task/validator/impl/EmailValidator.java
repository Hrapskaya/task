package group.innowise.task.validator.impl;

import group.innowise.task.validator.UserDataValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static group.innowise.task.constant.Constant.EMAIL_PATTERN;
import static group.innowise.task.constant.UserFieldConstant.EMAIL;

public class EmailValidator extends UserDataValidator {

    public static final String INVALID_EMAIL = "Please enter user email in the format: *****@*****.*.";

    public EmailValidator() {
        super(new RolesValidator());
    }

    @Override
    public List<String> validate(Map<String, String> data) {
        List<String> result = new ArrayList<>();
        String email = data.get(EMAIL);
        if (email != null && !email.isEmpty()){
            if(!email.matches(EMAIL_PATTERN)) {
                result.add(INVALID_EMAIL);
            }
        }
        result.addAll(chain(data));
        return result;
    }
}
