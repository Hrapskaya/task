package group.innowise.task.inputOutput.impl;

import group.innowise.task.inputOutput.DataReaderWriter;

import java.util.List;
import java.util.Scanner;

public class DefaultDataReaderWriter implements DataReaderWriter {


    public static final Scanner scanner = new Scanner(System.in);

    private static DataReaderWriter instance;

    private DefaultDataReaderWriter() {
    }

    public static DataReaderWriter getInstance() {
        if (instance == null) {
            instance = new DefaultDataReaderWriter();
        }
        return instance;
    }

    @Override
    public void write(String text) {
        System.out.println(text);
    }

    @Override
    public void write(List<String> list) {
        for(String element: list){
            System.out.println(element);
        }
    }

    @Override
    public String read() {
        String text = scanner.nextLine();
        return text;
    }

}
