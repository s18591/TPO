package com.company;
import Iris.Iris;
import java.io.*;
import java.util.*;

public class KNN implements Comparator<Iris> {
    public List<Iris> readTraining(String training) throws IOException {

        List<Iris> irises = new ArrayList<>();
        String s;
        File file = new File(training);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while ((s = bufferedReader.readLine()) != null) {
            s = s.replaceAll(",", ".");
            String[] strings = s.trim().split("\\s+");
            double[] doubles = new double[strings.length - 1];
            for (int i = 0; i < strings.length - 1; i++) {
                doubles[i] = Double.parseDouble(strings[i]);
            }
            irises.add(new Iris(doubles, strings[strings.length - 1]));
        }
        bufferedReader.close();
        return irises;
    }

    public List<Iris> readTest(String test) throws IOException {
        List<Iris> irises = new ArrayList<>();
        String s;
        File file = new File(test);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while ((s = bufferedReader.readLine()) != null) {
            s = s.replaceAll(",", ".");
            String[] strings = s.trim().split("\\s+");
            double[] doubles = new double[strings.length - 1];
            for (int i = 0; i < strings.length - 1; i++) {
                doubles[i] = Double.parseDouble(strings[i]);
            }
            irises.add(new Iris(doubles, strings[strings.length - 1]));
        }
        bufferedReader.close();
        return irises;
    }

    public boolean[] calculating(List<Iris> irisesTest, List<Iris> irisesTraining, int key) {
        boolean[] res = new boolean[irisesTest.size()];
        for (int i = 0; i < irisesTest.size(); i++) {

            for (int j = 0; j < irisesTraining.size(); j++) {

                double dist = 0;
                for (int k = 0; k < irisesTest.get(i).doubles.length; k++) {
                    dist += Math.pow(irisesTraining.get(j).doubles[k] - irisesTest.get(i).doubles[k], 2);
                }
                dist = Math.sqrt(dist);
                irisesTraining.get(j).setDistance(dist);
            }
            irisesTraining.sort(this);
            HashMap<String, Integer> hm = new HashMap<String, Integer>();
            for (int n = 0; n < key; n++) {
                if (hm.containsKey(irisesTraining.get(n).getString())) {
                    hm.put(irisesTraining.get(n).getString(), hm.get(irisesTraining.get(n).getString()) + 1);
                } else {
                    hm.put(irisesTraining.get(n).getString(), 1);
                }
            }
            int max = 0;
            String str = null;
            for (String s : hm.keySet()) {
                if (max < hm.get(s)) {
                    max = hm.get(s);
                    str = s;
                }
            }

            if (str.equals(irisesTest.get(i).getString())) {
                res[i] = true;
            } else
                res[i] = false;
        }

        System.out.println(Arrays.toString(res));
        return res;
    }

    public String clacOne(List<Iris> test, List<Iris> training, int key) {
        String str = null;
        for (int i = 0; i < test.size(); i++) {
            for (int j = 0; j < training.size(); j++) {
                double dist = 0;
                for (int k = 0; k < test.get(i).doubles.length; k++) {
                    dist += Math.pow(training.get(j).doubles[k] - test.get(i).doubles[k], 2);
                }
                dist = Math.sqrt(dist);
                training.get(j).setDistance(dist);
            }
            training.sort(this);
            HashMap<String, Integer> hm = new HashMap<String, Integer>();
            for (int n = 0; n < key; n++) {
                if (hm.containsKey(training.get(n).getString())) {
                    hm.put(training.get(n).getString(), hm.get(training.get(n).getString()) + 1);
                } else {
                    hm.put(training.get(n).getString(), 1);
                }
            }
            int max = 0;
            for (String s : hm.keySet()) {
                if (max < hm.get(s)) {
                    max = hm.get(s);
                    str = s;
                }
            }
            }
        return str;
        }




    @Override
    public int compare(Iris iris, Iris t1) {
        if (iris.getDistance() > t1.getDistance())
            return 1;

        if (iris.getDistance() < t1.getDistance())
            return -1;
        return 0;
    }
}
