import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Loader {

    private static String staffFile = "data/movementList.csv";
    private static String regex = "\"";
    private static String[] fragments;
    private static double totalCost = 0;
    private static double totalProfit = 0;


    public static void main(String[] args) {
        Map<String, Double> costs = loadFromFile();
        System.out.printf("\nИтого расходы: %.2f\nИтого приходы: %.2f\n\n", totalCost, totalProfit);
        for (String key : costs.keySet()) {
            System.out.printf("Группа расходов: %40s\tРасходы на сумму: %.2f\n", key, costs.get(key));
        }
    }

    private static Map<String, Double> loadFromFile() {
        HashMap<String, Double> movementsCost = new HashMap<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for (int i = 0; i < lines.size(); i++) {
                lines.set(i, replaceComma(lines.get(i), ' '));
            }

            for (int i = 1; i < lines.size(); i++) {
                fragments = lines.get(i).split(",");

                double profit = Double.parseDouble(fragments[6].trim());
                double cost = Double.parseDouble(fragments[7].trim());

                fragments[5] = clearCostType(fragments[5]).trim();
                totalCost += cost;
                totalProfit += profit;

                if (movementsCost.containsKey(fragments[5])) {
                    movementsCost.put(fragments[5], movementsCost.get(fragments[5]) + cost);
                } else {
                    movementsCost.put(fragments[5], cost);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return movementsCost;
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
