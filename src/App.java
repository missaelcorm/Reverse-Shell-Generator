import com.iteso.ReverseShellGenerator.ReverseShell;
import com.iteso.ReverseShellGenerator.ReverseShell.LISTENER;
import com.iteso.ReverseShellGenerator.ReverseShell.REVERSE_TYPE;
import com.iteso.ReverseShellGenerator.ReverseShell.SHELLS;

import javax.swing.*;
import java.awt.event.*;

public class App extends JFrame implements ActionListener{

    ReverseShell shell;

    private JLabel revLabel;
    private JComboBox<REVERSE_TYPE> comboRevBox;
    private JLabel listLabel;
    private JComboBox<LISTENER> comboListBox;
    private JLabel shellLabel;
    private JComboBox<SHELLS> comboShellBox;
    private JLabel ipLabel;
    private JTextField ipField;
    private JLabel portLabel;
    private JTextField portField;
    private JButton submitButton;
    private JTextArea outpuTextArea;
    private JScrollPane scrollPane;

    private REVERSE_TYPE revTypes[] = REVERSE_TYPE.values();
    private LISTENER LisTypes[] = LISTENER.values();
    private SHELLS shellTypes[] = SHELLS.values();

    private String localIP = "10.10.10.10";
    private int localPort = 80;
    private REVERSE_TYPE revSelected = REVERSE_TYPE.BASH_i;
    private LISTENER listSelected = LISTENER.NC;
    private SHELLS shellSelected = SHELLS.SH;

    public App(){
        setLayout(null);

        ipLabel = new JLabel("IP:");
        ipLabel.setBounds(10, 10, 100, 20);
        add(ipLabel);

        ipField = new JTextField();
        ipField.setBounds(210, 10, 100, 20);
        add(ipField);
        ipField.setText(localIP);

        portLabel = new JLabel("Port:");
        portLabel.setBounds(10, 30, 100, 20);
        add(portLabel);

        portField = new JTextField();
        portField.setBounds(210, 30, 100, 20);
        add(portField);
        portField.setText(String.valueOf(localPort));

        listLabel = new JLabel("Listener Type:");
        listLabel.setBounds(10, 50, 100, 20);
        add(listLabel);
        
        comboListBox = new JComboBox<LISTENER>();
        comboListBox.setBounds(210, 50, 200, 20);
        add(comboListBox);

        revLabel = new JLabel("Reverse Type:");
        revLabel.setBounds(10, 70, 100, 20);
        add(revLabel);

        comboRevBox = new JComboBox<REVERSE_TYPE>();
        comboRevBox.setBounds(210, 70, 200, 20);
        add(comboRevBox);

        shellLabel = new JLabel("Shell Type:");
        shellLabel.setBounds(10, 90, 100, 20);
        add(shellLabel);

        comboShellBox = new JComboBox<SHELLS>();
        comboShellBox.setBounds(210, 90, 200, 20);
        add(comboShellBox);

        for (REVERSE_TYPE type : revTypes) {
            comboRevBox.addItem(type);
        }

        for (LISTENER type : LisTypes) {
            comboListBox.addItem(type);
        }

        for (SHELLS type : shellTypes) {
            comboShellBox.addItem(type);
        }

        submitButton = new JButton("Submit");
        submitButton.setBounds(110, 120, 100, 20);
        add(submitButton);
        submitButton.addActionListener(this);

        outpuTextArea = new JTextArea();
        outpuTextArea.setBounds(20, 150, 1100, 500);
        add(outpuTextArea);

        scrollPane = new JScrollPane(outpuTextArea);
        scrollPane.setBounds(20, 150, 1100, 500);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==submitButton){
            localIP = ipField.getText();
            localPort = Integer.parseInt(portField.getText());

            listSelected = (LISTENER) comboListBox.getSelectedItem();
            revSelected = (REVERSE_TYPE) comboRevBox.getSelectedItem();
            shellSelected = (SHELLS) comboShellBox.getSelectedItem();

            shell = ReverseShell.getShellInstance(localIP, localPort, listSelected, revSelected, shellSelected);
            
            outpuTextArea.setText(shell.toString());
        }
    }

    public static void main(String[] args) throws Exception {

        App app = new App();
        app.setTitle("Reverse Shell Generator");
        app.setExtendedState(app.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}