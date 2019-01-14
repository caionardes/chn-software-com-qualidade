package com.br.chn.software.domain.model.software;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

import com.br.chn.software.domain.model.atividade.Atividade.TipoDeAtividade;
import com.br.chn.software.domain.model.profissional.IdProfissional;
import com.br.chn.software.domain.shared.Entity;

import lombok.ToString;

/**
 * O Software. Essa é a classe central do dominio,
 * e é o ROOT de Software-Especificacao-Feature-Profissional-Entrega aggregate.
 * 
 * Um Software é identificado por um nome unico, e possui especificoes de Funcionalidades,
 * Funcionalidades, profissionais para desenvolver as Funcionalidades e o status da entrega.
 * 
 * O ciclo começa com o inicio do projeto, onde um software começa a ser planejado.
 * Durante um curto periodo de tempo, durante as discucoes tecnicas, o software fica sem Funcionalidades.   
 * 
 * Com o inclusao da Funcionalidade e de sua especificação, podem ser inicidas as atividades da Funcionalidade.
 * 
 * O Profissional iniciar ou conclui uma atividade de uma Funcionalidade.
 *
 * @author caio.nardes
 */
@ToString
public class Software implements Entity<Software> {

	@Id
	private IdSoftware idSoftware;
	private String nome;
	private List<Funcionalidade> funcionalidades;

	private Software() {
		super();
	}

	private Software(String nome) {
		this();
		this.idSoftware = new IdSoftware(UUID.randomUUID().toString());
		this.nome = nome;
		this.funcionalidades = new ArrayList<>();
	}

	/* *********************** */
	/* OPERACOES MODIFICADORAS */
	/* *********************** */

	/** Cria o Software */
	public static Software criarSoftware(String nome) {
		Assert.hasText(nome, "O nome do software é obrigatório.");

		return new Software(nome);
	}

	/** Adiciona uma funcionalidade ao Software. */
	public void adicionarFuncionalidade(String nomeUseCase, String documentacao) {
		Assert.hasText(nomeUseCase, "O nome do UseCase é obrigatório para especificação do software.");
		Assert.hasText(documentacao, "A documentação é obrigatória para especificação do software.");
		
		funcionalidades.add(Funcionalidade.criarFuncionalidade(EspecificacaoTecnica.criarEspecificacao(nomeUseCase, documentacao)));
	}

	/** Registra no Software o inicio de uma atividade. */
	public void registraInicioNovaAtividade(IdProfissional idProfissional, IdFuncionalidade idFuncionalidade) {
		Assert.notNull(idFuncionalidade, "É obrigatório informar a funcionalidade da atividade a ser iniciada.");
		Assert.notNull(idFuncionalidade.getId(), "É obrigatório informar a funcionalidade da atividade a ser iniciada.");
		Assert.notNull(idProfissional,   "É obrigatório informar o profissional da atividade a ser iniciada.");
		Assert.notNull(idProfissional.getId(),   "É obrigatório informar o profissional da atividade a ser iniciada.");

		getFuncionalidadePorId(idFuncionalidade).registraInicioAtividade(idProfissional);
	}

	/** Registra no Software a conclusao de uma atividade. */
	public void registraConclusaoAtividade(IdProfissional idProfissional, IdFuncionalidade idFuncionalidade) {
		Assert.notNull(idFuncionalidade, "É obrigatório informar a funcionalidade da atividade a ser iniciada.");
		Assert.notNull(idFuncionalidade.getId(), "É obrigatório informar a funcionalidade da atividade a ser iniciada.");
		Assert.notNull(idProfissional,   "É obrigatório informar o profissional que está concluindo a atividade.");
		Assert.notNull(idProfissional.getId(),   "É obrigatório informar o profissional que está concluindo a atividade.");

		getFuncionalidadePorId(idFuncionalidade).registraConclusaoAtividade(idProfissional);
	}

	/* *************************** */
	/* OPERACOES NAO MODIFICADORAS */
	/* *************************** */

	/** Expoem o tipo atual de atividade em que uma funcionalidade se encontra. */
	public TipoDeAtividade proximaAtividadeASerIniciada(IdFuncionalidade idFuncionalidade) {
		Assert.notNull(idFuncionalidade, "É obrigatório informar a funcionalidade da atividade a ser iniciada.");
		Assert.notNull(idFuncionalidade.getId(), "É obrigatório informar a funcionalidade da atividade a ser iniciada.");

		return getFuncionalidadePorId(idFuncionalidade).getAtividade().getTipo();
	}

	/** Acessa uma funcionalidade atraves do Id. */
	private Funcionalidade getFuncionalidadePorId(IdFuncionalidade idFuncionalidade) {
		return funcionalidades.stream().filter(f -> idFuncionalidade.equals(f.getIdFuncionalidade())).findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Funcionalidade não existente [%s].", idFuncionalidade)));
	}

	/** Acessa a lista das funcionalides do software. */
	public List<Funcionalidade> funcionalidades() {
		return Collections.unmodifiableList(funcionalidades);
	}

	/** Acessa o Id do software. */
	public String idSoftware() {
		return idSoftware.getId();
	}

	/** Acessa o nome do software. */
	public String nome() {
		return nome;
	}
}
