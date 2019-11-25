package codegen;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CodeGen {

    private static Model m = new Model("");
    private static List<Sourcecode> sourcecodes = new ArrayList<>();
    private static String mainclass = "Car";
    private static String actclass;
    static int i = 0;

    public static String[] declaration(String object) {
        for (Sourcecode source: sourcecodes) {
            List<String[]> src = source.getSource();
            for (String[] line : src) {
                for (String word : line) {
                    if (word.contains(object)) {
                        return line;
                    }
                }
            }
        }
        return null;
    }

    public static void newParameter(String[] line) {
//        String[] element = declaration(object);
        String unit = "";
//        if (element != null) {
//            boolean newclass = false;
//            for (Sourcecode source: sourcecodes) {
//                if(source.getClassname().equals(element[0])) {
//                    newclass = true;
//                }
//            }
//            if(!newclass) {
//                input(element[0]);
//            }
//            actclass = element[0];
//            unit = line();
//        }

        System.out.println(++i);
// m.setParameters(object + "(" + unit + ")" + " = " + "0.0);" + "\n");
    }

    public static String line() {
        for (Sourcecode source: sourcecodes){
            if (source.getClassname() == actclass) {
                List<String[]> src = source.getSource();
                for (String[] line: src) {
                    for (int i = 0; i < line.length; i++) {
                        if(line[i].contains("class") && line[i + 1].contains(mainclass) && m.getName() == "") {
                            m = new Model(mainclass);
                        }
                        if(line[i].contains("new")) {
//                            String list = line[i].substring(0, line[i].indexOf('.')-1);
//                            String object = line[i].substring(line[i].indexOf('(') + 1, line[i].indexOf(')'));
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
                    }
                }
            }
        }
        return null;
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
            List<String[]> src = source.getSource();
            br.close();
            sourcecodes.add(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        input(mainclass);
        actclass = mainclass;
        line();

        System.out.println(m.toString());
    }
}
