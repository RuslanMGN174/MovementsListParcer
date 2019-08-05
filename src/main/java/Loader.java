import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Loader {

    private static String staffFile = "data/movementList.csv";
    private static String regex = "\"";
    private static String[] fragments;
    private static ArrayList<Costs> costs = new ArrayList<>();
    private static double costSum = 0;
    private static double profitSum = 0;


    public static void main(String[] args) {
        List<String> chart = loadFromFile();

        System.out.println(costs.size());

//        for (int i = 0; i < chart.size(); i++) {
//            if (costs.size() == 0){
//                costs.add(new Costs(clearCostType(fragments[5]), Double.parseDouble(fragments[7])));
//            } else {
//                for (int j = 0; j < costs.size(); j++) {
//                    if (!costs.get(j).getCostType().equals(clearCostType(fragments[5]))) {
//                        costs.add(new Costs(clearCostType(fragments[5]), Double.parseDouble(fragments[7])));
//                    } else {
//                        costs.get(j).setCostSize(costs.get(j).getCostSize() + Double.parseDouble(fragments[7]));
//                    }
//                }
//            }
//        }

    }

    private static ArrayList<String> loadFromFile() {
        ArrayList<String> movements = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for (int i = 0; i < lines.size(); i++) {
                lines.set(i, replaceComma(lines.get(i), ' '));
            }

            for (int i = 1; i < lines.size(); i++) {
                fragments = lines.get(i).split(",");

                fragments[5] = clearCostType(fragments[5]);
                double profit = Double.parseDouble(fragments[6].trim());
                double cost = Double.parseDouble(fragments[7].trim());
                costSum += cost;
                profitSum += profit;

                if (cost > 0) {
                    if (costs.size() == 0) {
                        costs.add(new Costs(fragments[5], cost));
                    } else {
                        boolean s = costs.stream()
                                .anyMatch(o -> o.getCostType().equals(fragments[5]));

                            if (s = false) {
                                costs.add(new Costs(fragments[5], cost));
                            } else {
                                costs.get(j).setCostSize(costs.get(j).getCostSize() + cost);
                            }
                    }
                }


//                movements.add(fragments[5] + fragments[6].trim() + fragments[7].trim());
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

    public static String clearCostType(String s) {
        return s.substring(20, 60);
    }


}
