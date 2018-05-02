import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Frame extends JFrame {

	final String[] PRODUCER_NAMES = { "Mercedes-Benz", "Lexus", "Jaguar", "Toyota", "McLaren" };
	final String[] CONSUMER_NAMES = { "CIA", "MI6", "MOSSAD", "MSS", "BND", "FSB", "DGSE", "ISI", "RAW", "ASIS" };
	
	private JPanel contentPane;
	private JTextField txtField;

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

	public Frame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		
		scroll.setBounds(20, 20, 560, 300);
		
		contentPane.add(scroll);
		
		txtField = new JTextField();
		txtField.setBounds(464, 336, 120, 20);
		contentPane.add(txtField);
		txtField.setColumns(10);
		
		Market m = new Market();
		Screen s = new Screen(textArea);
		Timer timer = new Timer();
		
		timer.schedule(new TimerTask() {
		  @Override
		  public void run() {
			  txtField.setText(m.getStatus()); 
		  }
		}, 0, 1000/24);
		
		int i;
		
		for (i = 0; i < PRODUCER_NAMES.length; i++) {
			Producer producer = new Producer(PRODUCER_NAMES[i], m, s);
			producer.startProduction();
		}
		
		for (i = 0; i < CONSUMER_NAMES.length; i++) {
			Consumer consumer = new Consumer(CONSUMER_NAMES[i], m, s);	
			consumer.startBuying();
		}		
	}
	
	
}
