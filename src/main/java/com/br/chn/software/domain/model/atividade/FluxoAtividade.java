package com.br.chn.software.domain.model.atividade;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.br.chn.software.domain.shared.Entity;

import lombok.Getter;

@Getter
public class FluxoAtividade implements Entity<FluxoAtividade> {

	@Id
	private TipoDeAtividade tipoAtividade;
	private List<TipoDeAtividade> fluxosAlterativos;

	public FluxoAtividade(TipoDeAtividade tipoAtividade, TipoDeAtividade... fluxosAlterativos) {
		super();
		this.tipoAtividade = tipoAtividade;
		this.fluxosAlterativos = Arrays.asList(fluxosAlterativos);
	}
}
