package Thiago_Rocha.geradorDeSenhaMikroTik;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.MikrotikApiException;

/**
 * Autor: Thiago Rocha
 * 
 * GitHub:https://github.com/Manolinho009
 *
 */

public class App 
{


	//Variaveis de configuração
	private static String documento = "frontint.txt";
	private static String senha = "isgi@infoservi";	
	private static String login = "admin";
	private static String ip = "192.168.1.224";
	private static String profile = "hospedes";

	//Array para armazenar as Strings de comandos para o Mikrotik
	private static ArrayList<String> arrayFinal;



	//Metodos responsaveis pela leitura e alteração das variaveis de configuração 
	public static String getDocumento() {
		return documento;
	}
	public static void setDocumento(String documento) {
		App.documento = documento;
	}
	public static String getSenha() {
		return senha;
	}
	public static void setSenha(String senha) {
		App.senha = senha;
	}
	public static String getLogin() {
		return login;
	}
	public static void setLogin(String login) {
		App.login = login;
	}
	public static String getIp() {
		return ip;
	}
	public static void setIp(String ip) {
		App.ip = ip;
	}
	public static String getProfile() {
		return profile;
	}
	public static void setProfile(String profile) {
		App.profile = profile;
	}


	//Metodo main Executa assim que o programa abre.
	public static void main(String[] args) throws MikrotikApiException, IOException {	

		try {

			//Chamada do metodo que faz a leitura do arquivo de configuração
			lerConfig();

			//Chamada do metodo que faz a separação e montagem do "Documento" em um array de Strings.
			seletor();

			//Iniciando com a conexão ao MikroTik.
			ApiConnection con = ApiConnection.connect(ip);

			//Efetuando o login no Mikrotik
			con.login(login,senha);	

			//Executando cada comando registrado no Array formano no seletor()
			for(String s : arrayFinal){
				con.execute(s);
			}

			//Fechando a conexão
			con.close();

			//Deletando o "Documento"
			new File(documento).delete();

		} catch (MikrotikApiException e) {
			e.printStackTrace();
		} 


	}

	//Metodo responsavel pela separação dos items no "Documento"
	public static void seletor() {

		//array temporario que armazena cada campo do "Documento"
		ArrayList<String> listaTemp = new ArrayList<String>();

		//Array que contera as Strings de comando prontas
		arrayFinal = new ArrayList<String>();

		try {

			//Abrindo o "Documento"
			File myObj = new File(documento);
			Scanner myReader = new Scanner(myObj);

			//Lendo cada linha do "Documento"
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();

				//Separando os campos lidos por " " ("Espaços em branco").
				for(String s : data.split(" ")){

					//Ignorando os " " ("Espaços em branco")
					if(s.isEmpty() != true) {

						//Adicionando o campo na Array Temporario
						listaTemp.add(s);
					}
				}

				//Armazenando a operação junto com a senha.
				String operacaoESenha = listaTemp.get(0).toString();

				//Separando a operação da senha.
				String operacao = operacaoESenha.substring(0, 3);
				String senha = operacaoESenha.substring(3);

				//Verificando se devemos deletar ou adicionar um usuario.

				//Se a operação for de deletar.
				if(operacao.equals("CKO")) {

					//Criando uma String de comando com o nome do usuario no campo .id
					String result = "/ip/hotspot/user/remove .id="+listaTemp.get(2);

					System.out.println(result);

					//Apagando lista temporaria 
					listaTemp.clear();

					//Adicionando a String de resultado no Array final com todos os comandos 
					arrayFinal.add(result);	
				}

				//Se a operação for se adicionar 
				else if(operacao.equals("CKI")){

					//Criando a String se comando usando os campos registrados no Array temporario
					String result = "/ip/hotspot/user/add name="+listaTemp.get(2)+" password="+senha+" profile="+profile;

					System.out.println(result);

					//Apagando a lista temporaria
					listaTemp.clear();

					//Adicionando a String de resultado no Array final com todos os comandos 
					arrayFinal.add(result);					
				}
			}

			//Fechando o "Documento"
			myReader.close();

		}catch (Exception e) {
			System.out.println("Erro :"+ e);
		}
	}

	//Metodo responsavel pela leitura e efetivação das configurações.
	public static void lerConfig() throws IOException {

		//Criando um objeto do tipo Properties que auxilia na leitura das configurações
		Properties prop = new Properties();

		//Lendo o arquivo de configurações
		prop.load(new FileInputStream(new File("config.properties")));


		System.out.println(prop.getProperty("documento"));
		System.out.println(prop.getProperty("login"));
		System.out.println(prop.getProperty("senha"));
		System.out.println(prop.getProperty("ip"));
		System.out.println(prop.getProperty("profile"));


		//Setando os campos de configuração de acordo com o arquivo de configuração.
		setDocumento(prop.getProperty("documento"));
		setIp(prop.getProperty("ip"));
		setLogin(prop.getProperty("login"));
		setSenha(prop.getProperty("senha"));
		setProfile(prop.getProperty("profile"));


	}
}
