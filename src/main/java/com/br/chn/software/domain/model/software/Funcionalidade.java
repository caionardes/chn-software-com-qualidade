package com.br.chn.software.domain.model.software;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.util.Assert;

import com.br.chn.software.domain.model.atividade.Atividade;
import com.br.chn.software.domain.model.profissional.IdProfissional;
import com.br.chn.software.domain.shared.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Funcionalidade implements Entity<Funcionalidade> {

	private IdFuncionalidade idFuncionalidade;

	private EspecificacaoTecnica especificacaoTecnica;

	private Atividade atividade;

	private List<HistoricoAtividade> historicoEventos = new ArrayList<>();
	private HistoricoAtividade atividadeAtual;

	private Funcionalidade(EspecificacaoTecnica especificacaoTecnica, Atividade atividade) {
		super();
		this.idFuncionalidade = new IdFuncionalidade(UUID.randomUUID().toString());
		this.especificacaoTecnica = especificacaoTecnica;
		this.atividade = atividade;
	}

	public static Funcionalidade criarFuncionalidade(EspecificacaoTecnica especificacaoTecnica) {
		return new Funcionalidade(especificacaoTecnica, Atividade.atividadeInicial());
	}

	public void registraInicioAtividade(IdProfissional idProfissional) {
		validaSeHaAtividadeEmAndamento(idProfissional);

		String msg = "Atividade Iniciada:" + getAtividade() + ", por:" + idProfissional;

		atividadeAtual = HistoricoAtividade.iniciaAtividade(idProfissional, atividade.getTipo());
		atividade = atividade.avancarNoFluxo();

		System.out.println(msg + ", prox atividade: " + getAtividade());
	}

	public void registraConclusaoAtividade(IdProfissional idProfissional) {
		if (atividadeAtual != null && atividadeAtual.verificaSeFoiIniciadaPor(idProfissional)) {
			Assert.isTrue(!atividadeAtual.estaConcluida(), "A atividade atual não deveria estar concluída.");

			String msg = "Atividade concluída:" + getAtividade() + ", por:" + idProfissional;

			historicoEventos.add(atividadeAtual.concluiAtividade());
			atividadeAtual = null;
			atividade = atividade.avancarNoFluxo();

			System.out.println(msg + ", prox atividade: " + getAtividade());
		} else {
			throw new IllegalArgumentException(String.format("O profissional [%s] não iniciou esta atividade!", idProfissional));
		}
	}

	private void validaSeHaAtividadeEmAndamento(IdProfissional idProfissional) {
		if (atividadeAtual != null) {
			if (atividadeAtual.verificaSeFoiIniciadaPor(idProfissional))
				throw new IllegalArgumentException(String.format("O profissional [%s] já iniciou esta atividade anteriormente!", idProfissional));

			if (!atividadeAtual.estaConcluida())
				throw new IllegalArgumentException("A atividade anterior ainda não foi concluída!");
			
		}
	}

}
