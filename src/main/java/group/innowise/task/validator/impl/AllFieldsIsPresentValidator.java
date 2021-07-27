package group.innowise.task.validator.impl;

import group.innowise.task.validator.UserDataValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static group.innowise.task.constant.UserFieldConstant.*;
import static group.innowise.task.constant.Constant.*;

public class AllFieldsIsPresentValidator extends UserDataValidator {

    public AllFieldsIsPresentValidator()  {
        super(new EmailValidator());
    }

    @Override
    public List<String> validate(Map<String, String> data) {
        List<String> result = new ArrayList<>();
        String value = data.get(NAME);
        if(value == null || value.isEmpty()){
            result.add(FILL_IN_THE_FIELD + NAME);
        }
        value = data.get(LAST_NAME);
        if(value == null || value.isEmpty()){
            result.add(FILL_IN_THE_FIELD + LAST_NAME);
        }
        value = data.get(EMAIL);
        if(value == null || value.isEmpty()){
            result.add(FILL_IN_THE_FIELD + EMAIL);
        }
        value = data.get(TELEPHONE_NUMBERS);
        if(value == null || value.isEmpty()){
            result.add(FILL_IN_THE_FIELD + TELEPHONE_NUMBERS);
        }
        value = data.get(ROLES);
        if(value == null || value.isEmpty()){
            result.add(FILL_IN_THE_FIELD + ROLES);
        }
        result.addAll(chain(data));
        return result;
    }
}
