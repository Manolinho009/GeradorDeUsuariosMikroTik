package br.com.thiago_rocha;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Icone {

	private Image imagem;
	
	public Icone(String image) {

		TrayIcon trayIcon = null;
	     if (SystemTray.isSupported()) {
	        
	    	 
	         SystemTray tray = SystemTray.getSystemTray();
	         
	         
	         this.imagem = Toolkit.getDefaultToolkit().getImage(image);

	         PopupMenu popup = new PopupMenu();
	         
	         MenuItem defaultItem = new MenuItem("Sair");
	         ActionListener listener = new ActionListener() {
	             public void actionPerformed(ActionEvent e) {
	            	 System.exit(0);
	             }
	         };
	         defaultItem.addActionListener(listener);
	         popup.add(defaultItem);

	         
	         trayIcon = new TrayIcon(imagem, "Gerador de Senhas para Mikrotik", popup);
	         
	         try {
	             tray.add(trayIcon);
	         } catch (AWTException e) {
	             System.err.println(e);
	         }
	         
	     }
	     if (trayIcon != null) {
	         trayIcon.setImage(imagem);
	     }
	}
}
