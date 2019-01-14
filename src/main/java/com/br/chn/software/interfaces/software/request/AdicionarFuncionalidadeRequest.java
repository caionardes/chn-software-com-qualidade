package com.br.chn.software.interfaces.software.request;

import lombok.Data;

@Data
public class AdicionarFuncionalidadeRequest {

	private String idSoftware;
	private EspecificacaoTecnicaDTO especificacaoTecnicaDTO;

	@Data
	public static class EspecificacaoTecnicaDTO {
		private String nomeUseCase;
		private String descricao;
	}
}
