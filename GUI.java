import javax.swing.*;
import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Creates a calculator GUI with interactive buttons.
 */
public class GUI {

    /**
     * Builds the calculator GUI.
     */
    public static void buildCalculator() {
        JFrame base = new JFrame();
        base.getContentPane().setLayout(new BoxLayout(base.getContentPane(),1));
        base.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        base.setSize(320,500);
        base.setTitle("Sami's Calculator");
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BoxLayout(outputPanel,0));
        outputPanel.setBorder(BorderFactory.createLineBorder(Color.blue,2));
        base.add(outputPanel);
        JLabel output = new JLabel();
        output.setSize(20,10);
        output.setFont(new Font("Serif", Font.PLAIN, 20));
        outputPanel.add(output);
        output.setText("Enter a calculation.");
        output.updateUI();
        JPanel inputPanel = new JPanel();
        inputPanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
        for (int i = 0; i < 5; i++)
            addButtonWithListener(output, inputPanel,"" + i);
        base.add(inputPanel);
        JPanel inputPanel2 = new JPanel();
        inputPanel2.setBorder(BorderFactory.createLineBorder(Color.blue,2));
        for (int i = 5; i < 10; i++)
            addButtonWithListener(output, inputPanel2,"" + i);
        base.add(inputPanel2);
        JPanel inputPanel3 = new JPanel();
        inputPanel3.setBorder(BorderFactory.createLineBorder(Color.black,2));
        addButtonWithListener(output,inputPanel3,"+");
        addButtonWithListener(output,inputPanel3,"—");
        addButtonWithListener(output,inputPanel3,"*");
        addButtonWithListener(output,inputPanel3,"/");
        addButtonWithListener(output,inputPanel3,"-");
        addButtonWithListener(output,inputPanel3,".");
        base.add(inputPanel3);
        JPanel inputPanel4 = new JPanel();
        addEnterButton(output,inputPanel4,"Enter");
        addClearButton(output,inputPanel4,"Clear");
        base.add(inputPanel4);
        base.setVisible(true);
    }

    /**
     * Adds a button called text to base. When the button is pressed, it updates the output label.
     * @param output The output of the calculator, which is updated on button press.
     * @param base The panel to which the button is added.
     * @param text The text of the button. E.g., "1" or "+".
     */
    private static void addButtonWithListener(JLabel output, JPanel base, String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Serif", Font.PLAIN, 15));
        button.setBorder(BorderFactory.createLineBorder(Color.green,2));
        button.addActionListener(e -> {
            if (output.getText().charAt(0) == 'E')
                output.setText(text);
            else
                output.setText((output.getText() + text));
        });
        base.add(button,BorderLayout.AFTER_LINE_ENDS);
        button.updateUI();
    }

    /**
     * Adds the enter button to the calculator. When the enter button is pressed, it evaluates the
     * expression in output.
     */
    private static void addEnterButton(JLabel output, JPanel base, String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Serif", Font.PLAIN, 15));
        button.setBorder(BorderFactory.createLineBorder(Color.green,2));
        button.addActionListener(e -> {
            if (!(output.getText().charAt(0) == 'E')) {
                try {
                    DecimalFormat df = new DecimalFormat("#.######");
                    df.setRoundingMode(RoundingMode.CEILING);
                    output.setText(df.format(Calculate.calculateStr(output.getText())));
                } catch (SyntaxError error) {
                    output.setText("Enter again, invalid syntax."); }
            }});
        base.add(button,BorderLayout.AFTER_LINE_ENDS);
        button.updateUI();
    }

    /**
     * Adds the clear button to the calculator. When the clear button is pressed, it resets output.
     */
    private static void addClearButton(JLabel output, JPanel base, String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Serif", Font.PLAIN, 15));
        button.setBorder(BorderFactory.createLineBorder(Color.green,2));
        button.addActionListener(e -> {
            if (!(output.getText().charAt(0) == 'E'))
                output.setText("Enter a calculation.");
        });
        base.add(button,BorderLayout.AFTER_LINE_ENDS);
        button.updateUI();
    }
}
