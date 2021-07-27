package group.innowise.task.constant;

public class Constant {

    public static final String TELEPHONE_NUMBER_PATTERN = "375[\\d]{9}";
    public static final String EMAIL_PATTERN = ".+@.+\\..+";
    public static final String NUMBER_PATTERN = "[\\d]+";

    public static final String USER_FILE_NAME = "user.txt";
    public static final String EXIT_COMMAND = "q!";

    public static final String COMPLETED_SUCCESSFULLY = "Operation completed successfully.";

    public static final String NO_ELEMENT_WITH_SUCH_INDEX = "No element with such index.";
    public static final String SPECIFY_THE_USER_ID = "To execute the command, you must specify the user id.";
    public static final String FILL_IN_THE_FIELD = "Please fill in the field: ";
    public static final String EMAIL_IS_ALREADY_EXIST = "The user with this email is already in the storage: ";
    public static final String TELEPHONE_NUMBER_IS_ALREADY_EXIST = "The user with this telephone number is already in the storage: ";

    public static final String UNKNOWN_COMMAND = "Unknown command.";
    private Constant() {
    }
}
