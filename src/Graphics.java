import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Graphics extends JFrame {

    private JFrame frame;
    private JPanel panel;
    private JButton addBtn;
    private JButton delBtn;
    private JProgressBar progressBar;
    private JTextField inTextField;
    private JLabel timeLb;
    private Dictionary dictionary;
    private final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private ExecutorService executor = Executors.newFixedThreadPool(1);

    public Graphics() {
        super("Редактор словаря");
        System.out.println("");
        setLayout(new GridBagLayout());
        setLayout(new GridLayout(0, 1, 20, 20));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    executor.shutdown();
                    executor.awaitTermination(60, TimeUnit.SECONDS);
                } catch (InterruptedException ex) {
                    System.err.println("tasks interrupted");
                } finally {
                    if (!executor.isTerminated()) {
                        System.err.println("cancel non-finished tasks");
                    }
//                    executor.shutdownNow();
                    System.out.println("shutdown finished");
                }
                e.getWindow().dispose();
            }
        });


        dictionary = new Dictionary();
        inTextField = new JTextField();
        inTextField.setText("Количество модифицируемых объектов");
        inTextField.setToolTipText("Количество модифицируемых объектов");
        inTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                try{
                JTextField inTF = (JTextField) e.getSource();
                try {
                    float in = Float.parseFloat(inTF.getText());
                    if (in < 0) {
                        addBtn.setEnabled(false);
                        delBtn.setEnabled(false);
                        inTF.setText("Некорректный ввод!");
                    } else {
                        addBtn.setEnabled(true);
                        delBtn.setEnabled(true);
                    }


                } catch (NumberFormatException | NullPointerException ex) {
                    inTF.setText("Некорректный ввод!");
                    addBtn.setEnabled(false);
                    delBtn.setEnabled(false);
                }
            }
        });

        addBtn = new JButton("добавить");
        addBtn.setSize(30, 100);
        addBtn.setEnabled(false);
        delBtn = new JButton("удалить");
        delBtn.setEnabled(false);
        progressBar = new JProgressBar();
        timeLb = new JLabel();

        add(inTextField);
        add(addBtn);
        add(delBtn);
        add(progressBar);
        add(timeLb);

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                executor.submit(() -> {
                    dictionary.add(Long.parseLong(inTextField.getText()));
//                });
            }
        });

        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                executor.submit(() -> {
                    dictionary.remove(Long.parseLong(inTextField.getText()));
//                });
            }
        });

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                String time = timeFormat.format(date);
                timeLb.setText(time);
            }
        });
        timer.setInitialDelay(0);
        timer.start();


        pack();
        setVisible(true);


    }


    public static void main(String[] args) {
        Graphics plot = new Graphics();
    }

}