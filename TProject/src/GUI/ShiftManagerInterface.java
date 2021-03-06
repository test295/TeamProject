package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShiftManagerInterface extends MainInterface {

    private String[] colHeads={"Job No.","Priority","Deadline","Status"};
    private String[][]data={{"","","",""}};

    public ShiftManagerInterface(String username,String role) {

        table = new JTable(data,colHeads);
        tPane = new JScrollPane(table);
        add(tPane, BorderLayout.CENTER);
        inTask.setText("FrontStaff Access");
        exTask.setText("Technician Access");
        setVisible(true);

        inTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SOSInterface front = new SOSInterface(username,role);
                front.setVisible(true);
            }
        });

        exTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SOTInterface tech = new SOTInterface(username,role);
                tech.setVisible(true);
            }
        });
    }
}
