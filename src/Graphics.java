import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Graphics extends JFrame {

    private JButton addBtn;
    private JButton delBtn;
    private JProgressBar progressBar;
    private JTextField inTextField;
    private JLabel timeLb;
    private Dictionary dictionary;
    private final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public Graphics() {
        super("Редактор словаря");
        setLayout(new GridBagLayout());
        setLayout(new GridLayout(0, 1, 20, 20));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    executor.shutdown();
                    executor.awaitTermination(3, TimeUnit.SECONDS);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } finally {
                    if (!executor.isTerminated()) {
                        System.err.println("Задача не завершена!");
                    }
                    executor.shutdownNow();
                    System.out.println("Принудительная остановка!");
                }
                e.getWindow().dispose();
            }
        });

        dictionary = new Dictionary(this);
        inTextField = new JTextField();
        inTextField.setText("Количество модифицируемых объектов");
        inTextField.setToolTipText("После ввода нажмите <Enter>");
        inTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JTextField inTF = (JTextField) e.getSource();
                try {
                    float in = Long.parseLong(inTF.getText());
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
                executor.submit(() -> {
                    progressBar.setValue(0);
                    dictionary.add(Long.parseLong(inTextField.getText()));
                });
            }
        });

        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executor.submit(() -> {
                    progressBar.setValue(0);
                    dictionary.remove(Long.parseLong(inTextField.getText()));
                });

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

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public static void main(String[] args) {

        Graphics prepareGUI = new Graphics();

    }

}