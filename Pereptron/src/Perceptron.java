import java.util.Arrays;
import java.util.List;

public class Perceptron {


    private double[] weights;
    private double threshold;
    private final double learningConst;

    public Perceptron(double[] weights, double threshold, double learningConst) {
        this.weights = weights;
        this.threshold = threshold;
        this.learningConst = learningConst;
    }


    public int test(double[] inputs) {
        if (inputs.length != weights.length) {
            throw new IllegalArgumentException("you must have the same amount of inputs and weights");
        }
        double sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * inputs[i];
        }
        if (sum >= threshold) {
            return 1;
        } else {
            return 0;
        }
    }

    public Resoult test(List<String[]> list) {
        double counter = 0;
        double correct = 0;
        double counter0 = 0;
        double correct0 = 0;
        double counter1 = 0;
        double correct1 = 0;

        for (int i = 0; i < list.size(); i++) {
            String answ = list.get(i)[list.get(i).length - 1];
            int answCode = answ.equals("Iris-setosa") ? 0 : 1;
            double[] values = new double[list.get(i).length - 1];
            for (int j = 0; j < list.get(i).length - 1; j++) {
                values[j] = Double.parseDouble(list.get(i)[j]);
            }
            int percAnsw = test(values);
            counter++;
            if (answCode == 0) {
                counter0++;
            } else {
                counter1++;
            }
            if (percAnsw == answCode) {
                correct++;
                if (answCode == 0) {
                    correct0++;
                } else {
                    correct1++;
                }
            }
        }

        return  new Resoult(roundToTwo((correct / counter)),roundToTwo((correct0/counter0)),roundToTwo((correct1/counter1)));

    }

    public void learn(List<String[]> list) {

        for (int i = 0; i < list.size(); i++) {
            String answ = list.get(i)[list.get(i).length - 1];
            int answCode = answ.equals("Iris-setosa") ? 0 : 1;
            double[] values = new double[list.get(i).length - 1];
            for (int j = 0; j < list.get(i).length - 1; j++) {
                values[j] = Double.parseDouble(list.get(i)[j]);
            }
            learn(values, answCode);

        }
    }


    public void learn(double[] inputs, int answ) {
        int rec = test(inputs);
        if (rec == answ) {
            return;
        }
        double[] newValues = new double[weights.length + 1];
        double[] tmpWeights = new double[weights.length + 1];
        double[] tmpInputs = new double[inputs.length + 1];
        addToArray(weights, tmpWeights, threshold);
        addToArray(inputs, tmpInputs, -1);
        for (int i = 0; i < tmpWeights.length; i++) {
            newValues[i] = tmpWeights[i] + (answ - rec) * learningConst * tmpInputs[i];
        }

        double[] newWeights = new double[weights.length];
        System.arraycopy(newValues, 0, newWeights, 0, weights.length);
        double newThreshold = newValues[tmpWeights.length - 1];

        weights = newWeights;
        threshold = newThreshold;
    }

    private void addToArray(double[] src, double[] des, double valToAdd) {
        System.arraycopy(src, 0, des, 0, src.length);
        des[des.length - 1] = valToAdd;
    }

    public void info() {
        System.out.println(Arrays.toString(weights) + " " + threshold);
    }


    private double roundToTwo(double num){
       return Math.round(num * 100.0) / 100.0;
    }

}
