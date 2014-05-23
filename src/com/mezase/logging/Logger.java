package com.mezase.logging;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

	private static PrintWriter cwriter;
	private static PrintWriter swriter;

	public static void init(String file) {
		try {
			cwriter = new PrintWriter(new FileWriter(file + " - client.txt", true));
			swriter = new PrintWriter(new FileWriter(file + " - server.txt", true));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void clog(String str, String type) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss - dd/MM/yy");
		Date date = new Date();
		cwriter.append(type + " - " + dateFormat.format(date) + "\r\n");
		cwriter.append(str + "\r\n******************************************\r\n");
		cwriter.flush();
	}

	public static void clog(Throwable aThrowable, String type) {
		aThrowable.printStackTrace();
		Writer result = new StringWriter();
		PrintWriter printWriter = new PrintWriter(result);
		aThrowable.printStackTrace(printWriter);
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss - dd/MM/yy");
		Date date = new Date();
		cwriter.append(type + " - " + dateFormat.format(date) + "\r\n");
		cwriter.append(result.toString() + "\r\n******************************************\r\n");
		cwriter.flush();
	}

	public static void slog(String str, String type) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss - dd/MM/yy");
		Date date = new Date();
		swriter.append(type + " - " + dateFormat.format(date) + "\r\n");
		swriter.append(str + "\r\n******************************************\r\n");
		swriter.flush();
	}

	public static void slog(Throwable aThrowable, String type) {
		aThrowable.printStackTrace();
		Writer result = new StringWriter();
		PrintWriter printWriter = new PrintWriter(result);
		aThrowable.printStackTrace(printWriter);
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss - dd/MM/yy");
		Date date = new Date();
		swriter.append(type + " - " + dateFormat.format(date) + "\r\n");
		swriter.append(result.toString() + "\r\n******************************************\r\n");
		swriter.flush();
	}
}
