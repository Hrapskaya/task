package group.innowise.task.validator;

import java.util.*;


public abstract class UserDataValidator {


    private UserDataValidator successor;

    public UserDataValidator() {
    }

    public UserDataValidator(UserDataValidator successor) {
        this.successor = successor;
    }

    abstract public List<String> validate(Map<String, String> data);

    protected List<String> chain(Map<String, String> data){
        if(successor == null){
            return new ArrayList<>();
        }
        return successor.validate(data);
    }
}
