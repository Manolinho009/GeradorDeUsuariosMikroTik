package br.com.thiago_rocha.modals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import br.com.thiago_rocha.Logs;

public class Configuracoes {

	//Variaveis de configuração
	private static String documento;
	private static String senha;	
	private static String login;
	private static String ip;
	private static String profile;
	private static String server;
	private static String log;


	
	public static String getLog() {
		return log;
	}
	public static void setLog(String log) {
		Configuracoes.log = log;
	}
	public static String getDocumento() {
		return documento;
	}
	public static void setDocumento(String documento) {
		Configuracoes.documento = documento;
	}
	public static String getSenha() {
		return senha;
	}
	public static void setSenha(String senha) {
		Configuracoes.senha = senha;
	}
	public static String getLogin() {
		return login;
	}
	public static void setLogin(String login) {
		Configuracoes.login = login;
	}
	public static String getIp() {
		return ip;
	}
	public static void setIp(String ip) {
		Configuracoes.ip = ip;
	}
	public static String getProfile() {
		return profile;
	}
	public static void setProfile(String profile) {
		Configuracoes.profile = profile;
	}
	public static String getServer() {
		return server;
	}
	public static void setServer(String server) {
		Configuracoes.server = server;
	}

	//Metodo responsavel pela leitura e efetivação das configurações.
		public void setConfig() throws IOException {

			//Criando um objeto do tipo Properties que auxilia na leitura das configurações
			Properties prop = new Properties();

			//Lendo o arquivo de configurações
			prop.load(new FileInputStream(new File("config.properties")));

			System.out.println("--- CONFIGURAÇÕES SETADAS ---");
			System.out.println(prop.getProperty("documento"));
			System.out.println(prop.getProperty("login"));
			System.out.println(prop.getProperty("senha"));
			System.out.println(prop.getProperty("ip"));
			System.out.println(prop.getProperty("profile"));
			System.out.println(prop.getProperty("server"));
			System.out.println(prop.getProperty("log"));
			System.out.println("--- --------------------- ---");

			
			//Setando os campos de configuração de acordo com o arquivo de configuração.
			setDocumento(prop.getProperty("documento"));
			setIp(prop.getProperty("ip"));
			setLogin(prop.getProperty("login"));
			setSenha(prop.getProperty("senha"));
			setProfile(prop.getProperty("profile"));
			setServer(prop.getProperty("server"));
			setLog(prop.getProperty("log"));

		}
}
