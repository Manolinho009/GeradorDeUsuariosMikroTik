package br.com.thiago_rocha;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import br.com.thiago_rocha.modals.Configuracoes;

public class Logs {

	public static void gerarLog(Configuracoes config , String texto) {
		
		try {
			FileWriter log = new FileWriter(config.getLog(),true); 
			
			log.append(texto+"\n");
			
			System.out.println("Registrado?");
			log.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			e.printStackTrace();
		}
		
		
		
		
	}
}
