package com.br.chn.software.domain.model.software;

import java.time.LocalDateTime;

import com.br.chn.software.domain.model.atividade.Atividade.TipoDeAtividade;
import com.br.chn.software.domain.model.profissional.IdProfissional;
import com.br.chn.software.domain.shared.ValueObject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class HistoricoAtividade implements ValueObject<HistoricoAtividade> {

	private static final long serialVersionUID = -7911483802501889642L;

	private IdProfissional idProfissional;
	private TipoDeAtividade tipoDeAtividade;
	private LocalDateTime dataInicio;
	private LocalDateTime dataConclusao;

	public static HistoricoAtividade iniciaAtividade(IdProfissional idProfissional, TipoDeAtividade tipoDeAtividade) {
		LocalDateTime dataConclusao = null;
		LocalDateTime dataInicio = LocalDateTime.now();
		return new HistoricoAtividade(idProfissional, tipoDeAtividade, dataInicio, dataConclusao);
	}

	public HistoricoAtividade concluiAtividade() {
		LocalDateTime dataConclusao = LocalDateTime.now();
		return new HistoricoAtividade(idProfissional, tipoDeAtividade, dataInicio, dataConclusao);
	}

	public boolean verificaSeFoiIniciadaPor(IdProfissional idProfissional) {
		return this.idProfissional.equals(idProfissional);
	}
	
	public boolean estaConcluida() {
		return dataConclusao != null;
	}
}
