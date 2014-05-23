package com.mezase.client.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import com.mezase.client.controllers.Controller;
import com.mezase.client.data.MessageQueue;

public class ClientGUI implements ActionListener {

	private Controller controller;

	private JFrame jframe;
	private JPanel contentPane;
	private JTextArea txtMessageOutput;
	private JTextArea txtMessageInput;
	private JScrollPane messageOutputScroll;
	private JScrollPane messageInputScroll;
	private JButton btnSend;

	private ImageIcon icon;

	public ClientGUI(Controller controller) {
		this.controller = controller;
		initialize();
	}

	public void initialize() {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
		}

		icon = new ImageIcon(this.getClass().getResource("/icon.png"));

		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(null);

		jframe = new JFrame();
		jframe.setTitle("Exportador McDoc");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setBounds(0, 0, 500, 320);
		jframe.setContentPane(contentPane);
		jframe.setLocationRelativeTo(null);
		jframe.setResizable(false);
		jframe.setIconImage(icon.getImage());

		btnSend = new JButton("Send");
		btnSend.setBorder(null);
		btnSend.setFont(new Font("Arial", Font.PLAIN, 13));
		btnSend.setBounds(443, 218, 49, 72);
		btnSend.addActionListener(this);
		contentPane.add(btnSend);

		txtMessageOutput = new JTextArea("");
		txtMessageOutput.setFont(new Font("Arial", Font.PLAIN, 13));
		txtMessageOutput.setEditable(false);
		txtMessageOutput.setBounds(0, 0, 493, 215);

		txtMessageInput = new JTextArea("");
		txtMessageInput.setFont(new Font("Arial", Font.PLAIN, 13));

		messageOutputScroll = new JScrollPane();
		messageOutputScroll.setBounds(2, 2, 490, 213);
		messageOutputScroll.setViewportView(txtMessageOutput);
		contentPane.add(messageOutputScroll);

		messageInputScroll = new JScrollPane();
		messageInputScroll.setBounds(2, 218, 440, 72);
		messageInputScroll.setViewportView(txtMessageInput);
		contentPane.add(messageInputScroll);

		jframe.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSend) {
			String message = txtMessageInput.getText();
			controller.writeBroadcastMessage(message);
			txtMessageInput.setText("");
		}
	}

	public void updateOutput(String message) {
		txtMessageOutput.append(message + "\n");
		txtMessageOutput.setCaretPosition(txtMessageOutput.getText().length());
	}
}
