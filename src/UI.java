import javax.swing.*;
import java.awt.*;

public class UI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Редактор словаря");
        frame.setLayout(new FlowLayout());

      frame.setSize(new Dimension(300,301));

        System.out.println(frame.getSize().getWidth() + frame.getSize().getHeight());
        frame.addComponentListener(new MyListener());


        JTextField in = new JTextField();
        JButton addBtn = new JButton("добавить");
        JButton delBtn = new JButton("удалить");
        JProgressBar pb = new JProgressBar();


//        frame.getContentPane().add(in);
        frame.getContentPane().add(addBtn);
        frame.getContentPane().add(delBtn);
//        frame.getContentPane().add(pb);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(300, 100);
        frame.setVisible(true);
    }
}
