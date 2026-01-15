package org.example.model;

import java.io.*;
import java.util.List;

public class FileHandler {

    public static void save(List<Expense> list) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("expenses.dat"));
        out.writeObject(list);
        out.close();
    }
}
