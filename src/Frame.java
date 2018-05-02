import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Frame extends JFrame {

	private JPanel contentPane;
	private JTextField txtDummy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtrDummy = new JTextArea();
		txtrDummy.setText("dummy");
		txtrDummy.setBounds(20, 18, 404, 202);
		contentPane.add(txtrDummy);
		
		txtDummy = new JTextField();
		txtDummy.setText("dummy");
		txtDummy.setBounds(436, 15, 130, 26);
		contentPane.add(txtDummy);
		txtDummy.setColumns(10);
	}
}
