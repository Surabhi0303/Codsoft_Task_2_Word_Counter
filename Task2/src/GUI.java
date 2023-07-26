import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class GUI extends JFrame implements ActionListener
{

    private JTextField inputField,fileInput;
    private JTextArea resultArea;
    private JButton countButton;
    String filePath=" ";
    Vector<Integer> v=new Vector<>();
    static int newlinecount=0;


  boolean isFile=false;


    GUI() {
        setTitle("Word Counter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inputField = new JTextField(30);

        JButton browseButton = new JButton("Browse");
        browseButton.addActionListener(this);




        countButton = new JButton("Count Words ");



        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Hi");
                String inputText="";

                if(!isFile)
                {
                    inputText= inputField.getText();
                }



                else {


                    try {
                        inputText=readFileContent(filePath);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }


                System.out.println(inputText);


               v= Word_Counter.Word_Counter(inputText);








                displayResult(v.get(0),v.get(1),v.get(2),v.get(3));

                isFile=false;








            }
        });








        JPanel inputPanel = new JPanel();
        inputPanel.add(inputField);



        inputPanel.add(browseButton);


        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                // Open the file chooser dialog
                isFile=true;
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(GUI.this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    filePath = selectedFile.getAbsolutePath();
                    inputField.setText(selectedFile.getAbsolutePath());
                }

            }
        });





        inputPanel.add(countButton);


        add(inputPanel, BorderLayout.CENTER);

    }



    private String readFileContent(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append('\n');
                newlinecount++;

            }
        }
        return content.toString();
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setVisible(true);
    }


    private void displayResult(int wordCount, int punctuationCount, int unique_words,int repeated_words) {
        JFrame resultFrame = new JFrame();
        resultFrame.setTitle("Result");
        resultFrame.setSize(300, 200);
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultFrame.setLayout(new BorderLayout());

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        System.out.println(newlinecount);

        wordCount=wordCount-newlinecount;

        resultArea.append("The count of words is: " + wordCount+ "\n");
        resultArea.append("The count of punctuation marks is: " + punctuationCount + "\n");
        resultArea.append("The count of unique words is: " + unique_words + "\n");
        resultArea.append("The count of repeated words is: " + repeated_words + "\n");
        // Append other result information here if needed

        // Update the resultArea with the counts

        JScrollPane scrollPane = new JScrollPane(resultArea);
        resultFrame.add(scrollPane, BorderLayout.CENTER);

        resultFrame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
