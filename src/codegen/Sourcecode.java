package codegen;

import java.util.ArrayList;
import java.util.List;

public class Sourcecode {

    private String classname;
    private static List<String[]> source;

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public static List<String[]> getSource() {
        return source;
    }

    public static void setSource(List<String[]> source) {
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
