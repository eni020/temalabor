package codegen;

import java.util.ArrayList;

public class Sourcecode {

    private String classname;
    private static ArrayList<String[]> source;

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public static ArrayList<String[]> getSource() {
        return source;
    }

    public static void setSource(ArrayList<String[]> source) {
        Sourcecode.source = source;
    }

    public Sourcecode(String classname) {
        this.classname = classname;
        source = new ArrayList<>();
    }

    public void add(String[] line) {
        source.add(line);
    }

}
