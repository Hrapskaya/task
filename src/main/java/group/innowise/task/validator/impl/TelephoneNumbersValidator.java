package group.innowise.task.validator.impl;

import group.innowise.task.validator.UserDataValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static group.innowise.task.constant.Constant.TELEPHONE_NUMBER_PATTERN;
import static group.innowise.task.constant.UserFieldConstant.TELEPHONE_NUMBERS;

public class TelephoneNumbersValidator extends UserDataValidator {

    public static final String DATA_SPLITTER = " +";
    public static final int MAX_TELEPHONE_NUMBER = 3;
    public static final String INCORRECT_NUMBER_TELEPHONE_MESSAGE = "Please enter user telephone numbers from 1 to 3 separated by a space in the format: 375** *******.";

    public TelephoneNumbersValidator() {

    }

    @Override
    public List<String> validate(Map<String, String> data) {
        List<String> result = new ArrayList<>();
        String telephoneNumbersData = data.get(TELEPHONE_NUMBERS);
        if (telephoneNumbersData != null && !telephoneNumbersData.isEmpty()) {
            String[] telephoneNumbers = telephoneNumbersData.split(DATA_SPLITTER);
            boolean isMatches = true;
            for (String telephoneNumber : telephoneNumbers) {
                if(!telephoneNumber.matches(TELEPHONE_NUMBER_PATTERN)){
                    isMatches = false;
                    break;
                }
            }
            int number = telephoneNumbers.length;
            if (!isMatches || number > MAX_TELEPHONE_NUMBER || number < 1) {
                result.add(INCORRECT_NUMBER_TELEPHONE_MESSAGE);
            }
        }
        return result;
    }
}
