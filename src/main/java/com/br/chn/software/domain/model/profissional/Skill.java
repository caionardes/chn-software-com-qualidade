package com.br.chn.software.domain.model.profissional;

import java.util.stream.Stream;

import com.br.chn.software.domain.model.atividade.TipoDeAtividade;

public enum Skill {

	ANALISTA, DESENVOLVEDOR, TESTADOR, APRESENTADOR;

	public boolean possuiPerfilParaExecutar(TipoDeAtividade tipo) {
		return aptidoes().anyMatch(e -> e.equals(tipo));
	}

	private Stream<TipoDeAtividade> aptidoes() {
		switch (this) {
			case APRESENTADOR:
				return Stream.of(TipoDeAtividade.APRESENTAR);
			case ANALISTA:
				return Stream.of(TipoDeAtividade.ANALISAR);
			case DESENVOLVEDOR:
				return Stream.of(TipoDeAtividade.DESENVOLVER);
			case TESTADOR:
				return Stream.of(TipoDeAtividade.TESTAR);
			default:
				return Stream.empty();
		}
	}
}
