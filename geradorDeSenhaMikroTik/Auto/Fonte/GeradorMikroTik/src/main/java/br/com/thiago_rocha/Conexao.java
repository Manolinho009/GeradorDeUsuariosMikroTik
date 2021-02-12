package br.com.thiago_rocha;

import br.com.thiago_rocha.modals.Configuracoes;
import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.MikrotikApiException;

public class Conexao {

	private ApiConnection con;
	
	public ApiConnection conectar(Configuracoes config) {
		try {
			
			//Iniciando com a conexão ao MikroTik.
				con = ApiConnection.connect(config.getIp());
			
			
			//Efetuando o login no Mikrotik
				con.login(config.getLogin(),config.getSenha());	
			
				return con;
		} catch (MikrotikApiException e) {
			// TODO Auto-generated catch block
				
				Logs.gerarLog(config, "Erro de Conexão + "+e);
				
				System.err.println(e);
				e.printStackTrace();
				return null;
		}

	}
	
	
}
