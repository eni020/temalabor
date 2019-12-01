package codegen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CodeGen {

    private static ModelGen m = new ModelGen("");
    private static List<Sourcecode> sourcecodes = new ArrayList<>();
    static int i = 0;

    public static void containsNew(String[] line) {
        if (line[3].contains("Parameter")) {
            String unit = line[3].substring(line[3].indexOf('(') + 1, line[3].indexOf(','));
            String value = line[4].substring(0, line[4].indexOf(')'));
            m.setParameters(line[0], unit, value);
        }
        if (line[3].contains("Variable")) {
            String value = "";
            if (line[3].contains("getValue")) {
                value = line[3].substring(line[3].indexOf('(') + 1, line[3].indexOf('.'));
            } else {
                value = line[3].substring(line[3].indexOf('(') + 1, line[3].indexOf(')'));
            }
            m.setVariables(line[0], value);
        }
    }


    public static void containsDer(String[] line) {
        String value = "";
        String var = line[0].substring(line[0].indexOf('(') + 1, line[0].indexOf(','));
        if(line[1].contains("new")) {
            value = line[2].substring(line[2].indexOf('(') + 1, line[2].indexOf(')'));
        }
        else {
            value = line[1].substring(0, line[1].indexOf(')'));
        }
        m.setEquations( "der(" + var + ") = " + value + ";");
    }



    public static void containsIf(String[] line) {
        String when = "";
        String reinit = "";
        if (line[0].contains("getValue")) {
            when = "when (" + line[0].substring(line[0].indexOf('(') + 1, line[0].indexOf('.')) + " " + line[1] + " " + line[2] +  " then ";
        }
        if (line[4].contains("setValue")) {
            reinit = "reinit(" + line[4].substring(0, line[4].indexOf('.')) + ", " + line[4].substring(line[4].indexOf('(') + 1, line[4].length()) +  " end when;";
        }
        m.setEquations(when + reinit);
    }
    public static void line() {
        for (Sourcecode source: sourcecodes){
            List<String[]> src = source.getSource();
            for (String[] line: src) {
                for (int i = 0; i < line.length; i++) {
                    if(line[i].contains("class") && m.getName() == "") {
                        m = new ModelGen(line[i + 1]);
                    }
                    if(line[i].contains("new") && !line[2].contains("Variable")) {
                        containsNew(line);
                    }
                    if(line[i].contains("der")) {
                        containsDer(line);
                    }
                    if(line[i].contains("if")) {
                        containsIf(line);
                    }
                }
            }
        }
    }

    public static void input(String classname) {
        try {
            FileReader fr = new FileReader("src/automata/" + classname + ".java");
            BufferedReader br = new BufferedReader(fr);
            Sourcecode source = new Sourcecode(classname);
            while (true) {
                String actline = "";
                actline = br.readLine();
                String[] line;
                if (actline == null) break;
                if (actline.length() != 0) {
                    String[] rawline = actline.split(" ");
                    Vector<String> linehelper = new Vector<>();
                    for (int i = 0; i < rawline.length; i++) {
                        if (rawline[i].length() != 0) {
                            linehelper.add(rawline[i]);
                        }
                    }
                    line = linehelper.toArray(String[]::new);
                    source.add(line);
                }
            }
            br.close();
            sourcecodes.add(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        input("Car");
        line();
        System.out.println(m.toString());
    }
}
