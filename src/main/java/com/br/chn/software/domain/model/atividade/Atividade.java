package com.br.chn.software.domain.model.atividade;

import com.br.chn.software.domain.shared.ValueObject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Atividade implements ValueObject<Atividade> {

	private static final long serialVersionUID = -7867547387488807221L;

	private TipoDeAtividade tipo;

	public static Atividade atividadeInicial() {
		return new Atividade(TipoDeAtividade.ANALISAR);
	}

	public static Atividade novaAtividade(TipoDeAtividade tipo) {
		return new Atividade(tipo);
	}

	public Atividade avancarNoFluxo() {
		return new Atividade(tipo.getProximo());
	}

	public static enum TipoDeAtividade {
		ACEITE(null),    // Atividade aceite está Concluida.
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
}
