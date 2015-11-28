import java.io.*;
import java.util.*;

public class Dict implements Serializable {
    private TreeMap<ComplexKey, Double> dict;
    private transient FileOutputStream fos;
    private transient ObjectOutputStream oos;


    public Dict() {
        dict = new TreeMap<>();

        try {
            fos = new FileOutputStream(System.getProperty("user.dir") + "/" + "rez.ser");
            oos = new ObjectOutputStream(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void add(int q) {
        while (q != 0) {
            ComplexKey key = new ComplexKey();
            Double value = Math.random();
            dict.put(key, value);
            q--;
        }
        save(dict);
    }

    public void save(TreeMap<ComplexKey, Double> dict) {
        try {
            fos = new FileOutputStream(System.getProperty("user.dir") + "/" + "rez.ser");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(dict);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void remove(int q) {
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
        save(dict);
    }
}
