package br.com.thiago_rocha.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.thiago_rocha.Conexao;
import br.com.thiago_rocha.Usuarios;
import br.com.thiago_rocha.modals.Configuracoes;

class UsuariosTest {

	public static Configuracoes config = new Configuracoes();
	
	public Conexao con = new Conexao();
	
	public static ArrayList<String> comandos = new Usuarios().seletor(config);
	
	@BeforeAll
	private static void iniciar() {
		
	}
	
	@Test
	void test() {
		assertEquals("", comandos.get(0));
	}

	@Test
	void test1() {
		assertEquals("", comandos.get(1));
	}
	
	@Test
	void test2() {
		assertEquals("", comandos.get(2));
	}
	@Test
	void test3() {
		assertEquals("", comandos.get(3));
	}
}
