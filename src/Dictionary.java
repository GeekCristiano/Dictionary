import java.io.*;
import java.util.*;

public class Dictionary implements Serializable {

    private TreeMap<ComplexKey, Double> dict;
    private ComplexKey key;
    private Double value;

    public Dictionary() {
        dict = new TreeMap<>();
    }

    public Double getValue() {
        return this.value;
    }

    public ComplexKey getKey() {
        return this.key;
    }

    public void add(long q) {
        while (q != 0) {

            key = new ComplexKey();
            value = Math.random();
            System.out.println(value);
            dict.put(key, value);
            q--;
        }
        autoSaveToFile(dict);
        autoSaveSerialization(dict);
    }

    public void remove(long q) {

        if (q == 0) return;
        else if (q > dict.size()) dict.clear();
        else {
            while (q != 0) {
                Iterator itr = dict.entrySet().iterator();
                Random r = new Random();
                Map.Entry entry = (Map.Entry) itr.next();
                int ind = r.nextInt(dict.size());
                for (int i = 0; i < ind; i++) {
                    entry = (Map.Entry) itr.next();
                }
                dict.remove(entry.getKey());
                q--;
            }
            autoSaveToFile(dict);
            autoSaveSerialization(dict);
        }
    }

    private void autoSaveSerialization(TreeMap<ComplexKey, Double> dict) {

        try {
            FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "\\" + "log.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dict);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void autoSaveToFile(TreeMap<ComplexKey, Double> dict) {

        Iterator itr = dict.entrySet().iterator();

        try {

            FileWriter fw = new FileWriter(System.getProperty("user.dir") + "\\" + "log.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            while (itr.hasNext()) {
                Map.Entry entry = (Map.Entry) itr.next();
                ComplexKey k = (ComplexKey) entry.getKey();
                Double value = (Double) entry.getValue();

                String key = k.getNum() + "" + new String(k.getStr().getString());

                String record = key + "=" + value;
                bw.write(record);
                bw.newLine();
                bw.flush();
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
