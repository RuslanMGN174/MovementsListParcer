public class Costs implements Comparable <Costs> {
    private String costType;
    private double costSize;

    public Costs(String costType, double costSize) {
        this.costType = costType;
        this.costSize = costSize;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public double getCostSize() {
        return costSize;
    }

    public void setCostSize(double costSize) {
        this.costSize = costSize;
    }


    public String toString() {
        return "Группа расходов: " + costType + " расходы на сумму: " + costSize;
    }

    @Override
    public int compareTo(Costs o) {
        return this.getCostType().equals(o.costType) ? 0 : -1;
    }

}
