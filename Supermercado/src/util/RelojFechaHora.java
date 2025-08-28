package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JMenuItem;

public class RelojFechaHora implements Runnable {
	private JMenuItem mntmFechaHora;
	private JLabel labelFechaHora;
	Thread hilo;
	
	public RelojFechaHora(JMenuItem mntmFechaHora) {
		this.mntmFechaHora = mntmFechaHora;
		hilo = new Thread(this);
		hilo.start();
	}
	
	public RelojFechaHora(JLabel labelFechaHora) {
		this.labelFechaHora = labelFechaHora;
		hilo = new Thread(this);
		hilo.start();
	}

	public void run() {
		Thread ct = Thread.currentThread();
		while (ct == hilo) {
			try {
				actualiza();
				Thread.sleep(1000);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	public void actualiza() {
		Date fechaActual = new Date();
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
		String strFechaActual = df.format(fechaActual);
		
		df = new SimpleDateFormat("hh:mm:ss a");
		String strHoraActual = df.format(fechaActual);
		
		if (mntmFechaHora != null) 
			mntmFechaHora.setText(strFechaActual + " - " + strHoraActual);
		
		if (labelFechaHora != null)
			labelFechaHora.setText(strFechaActual + " - " + strHoraActual);
	}
}
