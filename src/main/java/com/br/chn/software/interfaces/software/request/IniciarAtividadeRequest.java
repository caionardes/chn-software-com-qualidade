package com.br.chn.software.interfaces.software.request;

import lombok.Data;

@Data
public class IniciarAtividadeRequest {

	private String idSoftware;
	private String idProfissional;
	private String idFuncionalidade;
}
