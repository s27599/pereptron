import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*
        IN CODE
        Iris-setosa = 0
        Iris-versicolor/other = 1
        */


        final String TrainSetFileName = "TrainSet.csv";
        final String TestSetFileName = "TestSet.csv";
        //start Data
        final double[] weights = {1, 2, 3, 4};
        final double threshold = 0.5;
        final double learningConst = 0.25;
        final double leastSatisfactoryAccuracy = 0.9;

        Perceptron perceptron = new Perceptron(weights, threshold, learningConst);

        try (Scanner scanner = new Scanner(new File(TrainSetFileName))) {
            List<String[]> list = ReadData(scanner);
            int counter = 0;
            do {
                perceptron.learn(list);
//                System.out.println(perceptron.test(list));
                counter++;
            } while (perceptron.test(list).getAccuracy() < leastSatisfactoryAccuracy && counter < 100);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        Scanner sysScanner = new Scanner(System.in);
        String input;



        do {
            System.out.println("menu:\n" +
                    "1-Start\n" +
                    "2-Own data\n" +
                    "q-exit\n");
             input = sysScanner.nextLine();

             if(input.equals("1")){
                 try (Scanner scanner = new Scanner(new File(TestSetFileName))) {
                     List<String[]> list = ReadData(scanner);
                   Resoult res =  perceptron.test(list);
                     System.out.println(res);


                 } catch (FileNotFoundException e) {
                     throw new RuntimeException(e);
                 }
             } else if (input.equals("2")) {
                 System.out.println("insert data devided by coma");
                 String data = sysScanner.nextLine();
                 String[] splitedData = data.split(",");
                 double[] dArr= new double[splitedData.length];
                 for (int i = 0; i < splitedData.length; i++) {
                     try {
                         dArr[i] = Double.parseDouble(splitedData[i]);
                     }catch(NumberFormatException e){
                         System.out.println("Wrong format");
                     }
                 }
                if (perceptron.test(dArr)==0){
                    System.out.println("Iris-setosa");
                }else{
                    System.out.println("Iris-versicolor");
                }


             }

        } while (!input.equals("q"));





    }

    public static ArrayList<String[]> ReadData(Scanner scanner) {
        ArrayList<String[]> tmp = new ArrayList<>();
        while (scanner.hasNext()) {
            String[] data = scanner.next().split(",");
            tmp.add(data);
        }
        return tmp;
    }


}

