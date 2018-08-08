package view;

/**
 * This is a server control page
 * function: setup server, shutdown server
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controlor.ServerSetup;

public class ServerWindow extends JFrame implements ActionListener {

	JPanel jp1;
	JButton jb1, jb2;
	
	final Thread serverThread = new Thread(new Runnable() {
        @Override
        public void run() {
        	new ServerSetup();
        }
    });

	public static void main(String[] args) {
		ServerWindow mysf = new ServerWindow();
	}

	public ServerWindow() {
		
		jp1 = new JPanel();
		jb1 = new JButton("Start server");
		jb1.addActionListener(this);
		jb2 = new JButton("Close server");
		jb2.addActionListener(this);
		jp1.add(jb1);
		
		this.add(jp1);
		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == jb1) {
			jb1.setVisible(false);
			jp1.add(jb2);
			serverThread.start();
		}
		if (arg0.getSource() == jb2) {
			System.exit(0);
		}
	}

}
