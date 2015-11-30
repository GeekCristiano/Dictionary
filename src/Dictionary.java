import javax.swing.*;
import java.io.*;
import java.util.*;

public class Dictionary implements Serializable {

    private TreeMap<ComplexKey, Double> dict;
    private Graphics application = null;


    public Dictionary(Graphics application) {
        dict = new TreeMap<>();
        this.application = application;
    }

    public void add(long q) {
        while (q != 0) {

            ComplexKey key = new ComplexKey();
            Double value = Math.random();
            dict.put(key, value);
            q--;
        }
        autoSaveSerialization(dict);
        autoSaveToFile(dict);

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
            autoSaveSerialization(dict);
            autoSaveToFile(dict);

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

            int count = 0;
            JProgressBar pbBar = this.application.getProgressBar();
            while (itr.hasNext()) {
                Map.Entry entry = (Map.Entry) itr.next();
                ComplexKey k = (ComplexKey) entry.getKey();
                Double value = (Double) entry.getValue();

                String key = k.getNum() + " " + new String(k.getStr().getString());

                bw.write(key + " = " + value);
                bw.newLine();
                bw.flush();
                int progress = ++count;

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        pbBar.setValue(progress);
                    }
                });
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
