package br.com.thiago_rocha;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import br.com.thiago_rocha.modals.Configuracoes;

public class Usuarios {

	public ArrayList<String> seletor(Configuracoes config) {

		//array temporario que armazena cada campo do "Documento"
		ArrayList<String> listaTemp = new ArrayList<String>();
		
		//Array com os comandos prontos para execução		
		ArrayList<String> comandos = new ArrayList<String>();
		
		try {

			//Abrindo o "Documento"
			File myObj = new File(config.getDocumento());
			Scanner myReader = new Scanner(myObj);

			//Lendo cada linha do "Documento"
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();

				//Separando os campos lidos por " " ("Espaços em branco").
				for(String s : data.split(" ")){

					//Ignorando os " " ("Espaços em branco")
					if(!s.isEmpty()) {

						//Adicionando o campo na Array Temporario
						listaTemp.add(s);
					}
				}

				//Armazenando a operação junto com o usuario
				String operacaoEUsuario = listaTemp.get(0).toString();

				//Separando a operação da senha.
				String operacao = operacaoEUsuario.substring(0, 3);
				String usuario = operacaoEUsuario.substring(3);

				//Verificando se devemos deletar ou adicionar um usuario.

				//Se a operação for de deletar.
				System.out.println("usuario --"+usuario+"--");
				if(operacao.equals("CKO")) {

					if(!listaTemp.isEmpty()) {
						
						//Criando uma String de comando com o nome do usuario no campo .id
						//wr
						//String result = "/ip/hotspot/user/remove .id=\""+usuario+"\"";
						String result = "/ip/hotspot/user/remove user= \""+usuario+"\"";
                        //usuario ativo 	
						//ip hotspot active remove [/ip hotspot active find user="diego"]
						System.out.println(result);
						
						//Apagando lista temporaria 
						listaTemp.clear();
						
						//Adicionando a String de resultado no Array final com todos os comandos 
						comandos.add(result);							
					}
				}

				//Se a operação for se adicionar 
				else if(operacao.equals("CKI")){

					//Criando a String se comando usando os campos registrados no Array temporario
					//System.out.println("usuario --"+"/ip/hotspot/user/add password=\""+listaTemp.get(2).toLowerCase()+"\" name=\""+usuario+"\" profile=\""+config.getProfile()+"\" server=\""+config.getServer()+"\"");

					String result = "/ip/hotspot/user/add password=\""+listaTemp.get(2).toLowerCase()+"\" name=\""+usuario+"\" profile=\""+config.getProfile()+"\" server=\""+config.getServer()+"\"";

					System.out.println(result);

					//Apagando a lista temporaria
					listaTemp.clear();

					//Adicionando a String de resultado no Array final com todos os comandos 
					comandos.add(result);					
				}
			}

			//Fechando o "Documento"
			myReader.close();
			return comandos;
			
		}catch (Exception e) {
			Logs.gerarLog(config, "Erro na busca de parametros + "+e);
			System.out.println("Erro :"+ e);
			return null;
		}
	}

}
