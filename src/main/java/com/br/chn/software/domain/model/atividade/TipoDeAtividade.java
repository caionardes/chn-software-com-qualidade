package com.br.chn.software.domain.model.atividade;

public enum TipoDeAtividade {

	ACEITE(null),    // Quando ACEITE significa que a Funcionalidade está concluida.
	EM_APRESENTACAO(ACEITE),
	APRESENTAR(EM_APRESENTACAO),

	EM_TESTE(APRESENTAR),
	TESTAR(EM_TESTE),

	EM_DESENV(TESTAR),
	DESENVOLVER(EM_DESENV), // Aguardando desenv.
	
	EM_ANALISE(DESENVOLVER), // Em analise
	ANALISAR(EM_ANALISE); // Pendente de analise

	private TipoDeAtividade proximo;

	private TipoDeAtividade(TipoDeAtividade proximo) {
		this.proximo = proximo;
	}
	
	public TipoDeAtividade getProximo() {
		return proximo;
	}
}