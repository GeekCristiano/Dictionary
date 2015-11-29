import java.io.*;
import java.util.*;

public class Dictionary implements Serializable {

    private TreeMap<ComplexKey, Double> dict;
    private ComplexKey key;
    private Double value;

    public ComplexKey getKey() {
        return this.key;
    }

    public Double getValue() {
        return this.value;
    }

    public Dictionary() {
        dict = new TreeMap<>();
    }

    public void add(long q) {
        while (q != 0) {

            key = new ComplexKey();
            value = Math.random();
            System.out.println(value);
            dict.put(key, value);
            q--;
        }
        autoSave(dict);
    }

    public void autoSave(TreeMap<ComplexKey, Double> dict) {

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
                bw.write("\r\n");
                bw.flush();
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void remove(long q) {
        if (q == 0) return;
        else if (q > dict.size()) dict.clear();
        else {
            List keyList = new ArrayList(dict.keySet());
            Random r = new Random();
            while (q != 0) {
                dict.remove(keyList.get(r.nextInt(dict.size())));
                q--;
            }
        }
        autoSave(dict);
    }
}
