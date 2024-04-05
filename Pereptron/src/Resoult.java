public class Resoult {

    private final double accuracy;
    private final double accuracy1;
    private final double accuracy2;

    public Resoult(double accuracy, double accuracy0, double accuracy1) {
        this.accuracy = accuracy;
        this.accuracy1 = accuracy0;
        this.accuracy2 = accuracy1;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public double getAccuracy1() {
        return accuracy1;
    }

    public double getAccuracy2() {
        return accuracy2;
    }

    @Override
    public String toString() {
        return "Resoult{" +
                "accuracy=" + accuracy +
                ", accuracy0=" + accuracy1 +
                ", accuracy1=" + accuracy2 +
                '}';
    }
}
