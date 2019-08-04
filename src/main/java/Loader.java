import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    private static String staffFile = "data/movementList.csv";
    private static String regex = "\"";
    private static String[] fragments;
    private static List<Costs> costs = new ArrayList<>();


    public static void main(String[] args) {
        List<String> chart = loadFromFile();
//        for (int i = 0; i < chart.size(); i++) {
//            if (costs.size() == 0){
//                costs.add(new Costs(costType(fragments[5]), Double.parseDouble(fragments[7])));
//            } else {
//                for (int j = 0; j < costs.size(); j++) {
//                    if (!costs.get(j).getCostType().equals(costType(fragments[5]))) {
//                        costs.add(new Costs(costType(fragments[5]), Double.parseDouble(fragments[7])));
//                    } else {
//                        costs.get(j).setCostSize(costs.get(j).getCostSize() + Double.parseDouble(fragments[7]));
//                    }
//                }
//            }
//        }

        System.out.println(chart.size());
        System.out.println(costs.size());
        costs.forEach(c -> System.out.println(c.getCostType() + " " + c.getCostSize()));
    }

    private static ArrayList<String> loadFromFile() {
        ArrayList<String> movements = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for (int i = 0; i < lines.size(); i++) {
                lines.set(i, replaceComma(lines.get(i), ' '));
            }

            for (String line : lines) {
                fragments = line.split(",");

                if (fragments.length != 8) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }

                movements.add(fragments[5] + fragments[6].trim() + fragments[7].trim());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return movements;
    }

    public static String replaceComma(String s, char replaceRegex) {
        if (!s.contains(regex)) {
            return s;
        }
        int first = s.indexOf(regex);
        int last = s.lastIndexOf(regex);
        String substring = s.substring(first, last);
        int comma = substring.indexOf(",");
        char[] chars = s.toCharArray();
        chars[first] = replaceRegex;
        chars[last] = replaceRegex;
        chars[first + comma] = '.';
        return String.valueOf(chars);
    }

    public static String costType(String s) {
        return s.substring(20, 60);
    }


}
