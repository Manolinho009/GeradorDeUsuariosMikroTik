package br.com.thiago_rocha;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.thiago_rocha.modals.Configuracoes;
import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.ApiConnectionException;

public class Main {

	private static String icone = "icone.png";

	public static Configuracoes config = new Configuracoes();

	public Conexao con = new Conexao();

	public static ArrayList<String> comandos;

	public static ApiConnection conApi;
	
	public static void main(String[] args) throws IOException, InterruptedException, ApiConnectionException {

		
		config.setConfig();
		
		new Icone(icone);
		
	
		Conexao con = new Conexao();
//wr
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//		
		while(true) {
						
			try {
				//conApi = con.conectar(config);	
				//Iniciando com a conexão ao MikroTik.
				conApi = ApiConnection.connect(config.getIp());
				
				
				//Efetuando o login no Mikrotik
				//conApi.login(config.getLogin(),config.getSenha());
				

				System.out.println(formatter.format(date));
				conApi.login(config.getLogin(), config.getSenha());
				
				
				Logs.gerarLog(config, " <-- Teste de conexão --> ");
				
				if(new File(config.getDocumento()).canRead() && conApi != null) {

					System.out.println(formatter.format(date));
					Logs.gerarLog(config, "<-- Conectou -->");
					
					comandos = new Usuarios().seletor(config);
					
					//Logs.gerarLog(config, "Teste de leitura ");

					for(String s : comandos){
						try {
						//wr
						//	Date date = new Date();
						//	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
							System.out.println(formatter.format(date));

							conApi.execute(s);

							//Logs.gerarLog(config, "leituras - "+s);
							Logs.gerarLog(config, formatter.format(date)+ "\n" + s);

						}catch (Exception e) {
							System.err.println(s);
						}
					}

					comandos.clear();
					//Deletando o "Documento"
					new File(config.getDocumento()).delete();
				}
				
			}catch (Exception e) {
				Logs.gerarLog(config, "Erro no metodo de execução main"+"\n" +e);
				System.err.println("Erro == "+e);

				
			}finally {
				if(conApi != null) {
					//Fechando a conexão
					conApi.close();
				}
				Thread.sleep(30000);					
			}
		}	
	}
}
