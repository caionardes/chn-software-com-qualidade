package com.br.chn.software.interfaces.software.view;

import java.util.ArrayList;
import java.util.List;

import com.br.chn.software.domain.model.software.Funcionalidade;
import com.br.chn.software.domain.model.software.Software;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SoftwareViewAdapter {

	private String idSoftware;
	private String nome;
	private List<FuncinalidadeView> funcionalidades = new ArrayList<>();

	public SoftwareViewAdapter(Software software) {
		super();
		this.idSoftware = software.idSoftware();
		this.nome = software.nome();
		software.funcionalidades().forEach(this::add);
	}

	public void add(Funcionalidade fun) {
		funcionalidades.add(new FuncinalidadeView(
				fun.getIdFuncionalidade().getId(),
				fun.getEspecificacaoTecnica().getNomeUseCase(),
				fun.getEspecificacaoTecnica().getDocumentacao()));
	}

	@Data
	@AllArgsConstructor
	public static class FuncinalidadeView {
		private String idFuncionalidade;
		private String nomeUseCase;
		private String documentacao;
	}
}
