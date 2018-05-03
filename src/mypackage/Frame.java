package mypackage;
import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// constants
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
		txtField.setEditable(false);
		
		Market m = new Market();
		// create a wrapper that hides operations on textArea 
		Screen s = new Screen(textArea);
		// timer
		Timer timer = new Timer();
		
		// 24 frames per second
		timer.schedule(new TimerTask() {
		  public void run() {
			  txtField.setText(m.getStatus()); 
		  }
		}, 0, 1000/24);
		
		int i;
		
		for (i = 0; i < PRODUCER_NAMES.length; i++) {
			// new thread
			Producer producer = new Producer(PRODUCER_NAMES[i], m, s);
			// start 
			producer.startProduction();
		}
		
		for (i = 0; i < CONSUMER_NAMES.length; i++) {
			// new thread
			Consumer consumer = new Consumer(CONSUMER_NAMES[i], m, s);
			// start
			consumer.startBuying();
		}		
	}
	
	
}
