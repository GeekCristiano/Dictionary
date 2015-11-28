import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UI extends JFrame {

    private JFrame frame;
    private JPanel panel;
    private JButton addBtn;
    private JButton delBtn;
    private JProgressBar progressBar;
    private JTextField inTextField;
    private JLabel timeLb;
    private final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");


    public class HintTextField implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            inTextField.setText("");
        }

        @Override
        public void focusLost(FocusEvent e) {
            inTextField.setText("Количество модифицируемых объектов");


        }
    }

    public class AddBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class DelBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


    public class UpdateTime implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Date date = new Date();
            String time = timeFormat.format(date);
            timeLb.setText(time);
        }
    }


    public UI() {
        super("Редактор словаря");

        setLayout(new GridLayout(5, 1, 20, 20));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inTextField = new JTextField();
        inTextField.setText("Количество модифицируемых объектов");
        inTextField.addFocusListener(new HintTextField());
        addBtn = new JButton("добавить");
        addBtn.setSize(30, 100);
        delBtn = new JButton("удалить");
        progressBar = new JProgressBar();
        timeLb = new JLabel();

        add(inTextField);
        add(addBtn);
        add(delBtn);
        add(progressBar);
        add(timeLb);

        Timer timer = new Timer(1000, new UpdateTime());
        timer.setInitialDelay(0);
        timer.start();



        setVisible(true);
        pack();
    }


    public static void main(String[] args) {
        UI plot = new UI();
    }

}
