package com.mezase.client.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import com.mezase.client.controllers.Controller;
import com.mezase.client.data.MessageQueue;

public class ClientGUI implements ActionListener {

	private static final String TEXT_SUBMIT = "text-submit";
	private static final String INSERT_BREAK = "insert-break";

	private Controller controller;

	private KeyListener kl;

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
		jframe.setTitle("Kotoba");
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
		btnSend.setEnabled(true);
		contentPane.add(btnSend);

		txtMessageOutput = new JTextArea("");
		txtMessageOutput.setFont(new Font("Arial", Font.PLAIN, 13));
		txtMessageOutput.setEditable(false);
		txtMessageOutput.setLineWrap(true);
		txtMessageOutput.setBounds(0, 0, 493, 215);

		txtMessageInput = new JTextArea("");
		txtMessageInput.setLineWrap(true);
		txtMessageInput.setFont(new Font("Arial", Font.PLAIN, 13));

		messageOutputScroll = new JScrollPane();
		messageOutputScroll.setBounds(2, 2, 490, 213);
		messageOutputScroll.setViewportView(txtMessageOutput);
		contentPane.add(messageOutputScroll);

		messageInputScroll = new JScrollPane();
		messageInputScroll.setBounds(2, 218, 440, 72);
		messageInputScroll.setViewportView(txtMessageInput);
		contentPane.add(messageInputScroll);

		keyListenerInit();

		jframe.setVisible(true);

		jframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				controller.disconnect();
				System.exit(0);
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSend) {
			sendMessage();
		}
	}

	public void keyListenerInit() {
		InputMap input = txtMessageInput.getInputMap();
		KeyStroke enter = KeyStroke.getKeyStroke("ENTER");
		KeyStroke shiftEnter = KeyStroke.getKeyStroke("shift ENTER");
		input.put(shiftEnter, INSERT_BREAK); // input.get(enter)) = "insert-break"
		input.put(enter, TEXT_SUBMIT);

		ActionMap actions = txtMessageInput.getActionMap();
		actions.put(TEXT_SUBMIT, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
		kl = new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (txtMessageInput.getText().length() > 0) {
					btnSend.setEnabled(true);
				}
				else {
					btnSend.setEnabled(false);
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		};
		txtMessageInput.addKeyListener(kl);
	}

	public void sendMessage() {
		txtMessageInput.setText(txtMessageInput.getText().trim());
		if (txtMessageInput.getText().length() > 0) {
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
