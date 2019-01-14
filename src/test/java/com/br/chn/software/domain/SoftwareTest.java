package com.br.chn.software.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.br.chn.software.domain.model.atividade.Atividade;
import com.br.chn.software.domain.model.atividade.TipoDeAtividade;
import com.br.chn.software.domain.model.software.EspecificacaoTecnica;
import com.br.chn.software.domain.model.software.Funcionalidade;
import com.br.chn.software.domain.model.software.IdFuncionalidade;
import com.br.chn.software.domain.model.software.Software;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SoftwareTest {
	
	private static final String NOME_VALIDO = "NOME UNICO";
	private static final String USECASE_VALIDO = "NOME USE CASE";
	private static final String DOCUMENTACAO_VALIDA = "DOCUMENTACAO VALIDA";
	private static final String ID_FUNCIONALIDADE_NAO_EXISTENTE = "123456";

	@Test(expected = IllegalArgumentException.class)
	public void test_criarSoftware_semNome() {
		Software.criarSoftware(null);
	}

	@Test
	public void test_criarSoftware_valido() {
		criarSoftware_valido();
	}

	private Software criarSoftware_valido() {
		String nomeUnico = "NOME UNICO";
		Software software = Software.criarSoftware(NOME_VALIDO);

		assertEquals("O nome do software deve ser armazenado!", nomeUnico, software.nome());
		assertNotNull("O Id não deve ser null!", software.idSoftware());
		assertFalse("O Id deve ser preenchido!", software.idSoftware().isEmpty());
		assertTrue("Software deve começar sem nenhuma funcionalidade!", software.funcionalidades().isEmpty());
		return software;
	}

	@Test
	public void test_adicionarFuncionalidade_especificacao_Valida() {
		adicionarFuncionalidade_especificacao_Valida();
	}

	private Software adicionarFuncionalidade_especificacao_Valida() {
		Software software = criarSoftware_valido();

		assertTrue("Software deve começar sem nenhuma funcionalidade!", software.funcionalidades().isEmpty());
		software.adicionarFuncionalidade(USECASE_VALIDO, DOCUMENTACAO_VALIDA);
		assertFalse("A funcionalidade deve ser armazenada no software!", software.funcionalidades().isEmpty());
		
		Funcionalidade funcionalidade = software.funcionalidades().get(0);
		assertNotNull("A especificacao tecnica deve ser armazenada na funcionalidade!", funcionalidade.getEspecificacaoTecnica());
		
		assertTrue("Não deve haver historico de atividades para funcionalidades recem criadas.", funcionalidade.getHistoricoEventos().isEmpty());

		EspecificacaoTecnica especificacaoTecnica = funcionalidade.getEspecificacaoTecnica();

		assertSame("A documentacao deve ser armazenada na especificacao tecnica da funcionalidade!",
				DOCUMENTACAO_VALIDA, especificacaoTecnica.getDocumentacao());

		assertSame("O nome do USECASE deve ser armazenado na especificacao tecnica da funcionalidade!",
				USECASE_VALIDO, especificacaoTecnica.getNomeUseCase());
		
		assertNotNull("A atividade da funcionalidade deve ser inicializada!", funcionalidade.getAtividade());
		
		Atividade atividade = funcionalidade.getAtividade();

		TipoDeAtividade tipoDeAtividadeInicial = Atividade.atividadeInicial().getTipo();

		assertSame("Funcionalidades recem criadas devem começar com o tipo de atividade inicial configurado!",
				tipoDeAtividadeInicial, atividade.getTipo());

		return software;
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_adicionarFuncionalidade_especificacao_documentacaoInvalida_Null() {
		Software software = criarSoftware_valido();
		software.adicionarFuncionalidade(USECASE_VALIDO, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_adicionarFuncionalidade_especificacao_documentacaoInvalida_Vazia() {
		Software software = criarSoftware_valido();
		software.adicionarFuncionalidade(USECASE_VALIDO, "");
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_adicionarFuncionalidade_especificacao_useCaseInvalido_Null() {
		Software software = criarSoftware_valido();
		software.adicionarFuncionalidade(null, DOCUMENTACAO_VALIDA);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_adicionarFuncionalidade_especificacao_useCaseInvalido_Vazio() {
		Software software = criarSoftware_valido();
		software.adicionarFuncionalidade("", DOCUMENTACAO_VALIDA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_informaTipoDeAtividadeASerIniciada_funcionalidade_Null() {
		Software software = adicionarFuncionalidade_especificacao_Valida();
		software.proximaAtividadeASerIniciada(new IdFuncionalidade(null));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_informaTipoDeAtividadeASerIniciada_funcionalidade_NaoExiste() {
		Software software = adicionarFuncionalidade_especificacao_Valida();
		software.proximaAtividadeASerIniciada(new IdFuncionalidade(ID_FUNCIONALIDADE_NAO_EXISTENTE));
	}
	
	@Test
	public void test_informaTipoDeAtividadeASerIniciada_funcionalidade_Existente() {
		Software software = adicionarFuncionalidade_especificacao_Valida();
		
		Funcionalidade funcionalidade = software.funcionalidades().get(0);

		TipoDeAtividade tipoDeAtividade = software.proximaAtividadeASerIniciada(funcionalidade.getIdFuncionalidade());

		assertNotNull("Tipo da atividade não deve retornar null!", tipoDeAtividade);
		assertSame("Deve ser retornado o tipo de atividade da funcionalidade informada!", funcionalidade.getAtividade().getTipo(), tipoDeAtividade);
	}

	//TODO Testes de inicio e conclusao de atividade.
}

