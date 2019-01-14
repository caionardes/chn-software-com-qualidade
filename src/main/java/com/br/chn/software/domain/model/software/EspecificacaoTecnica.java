package com.br.chn.software.domain.model.software;

import com.br.chn.software.domain.shared.ValueObject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class EspecificacaoTecnica implements ValueObject<EspecificacaoTecnica> {

	private static final long serialVersionUID = -2288158991731955764L;

	private String nomeUseCase;
	private String documentacao;

	public static EspecificacaoTecnica criarEspecificacao(String nomeUseCase, String documentacao) {
		return new EspecificacaoTecnica(nomeUseCase, documentacao);
	}
}
