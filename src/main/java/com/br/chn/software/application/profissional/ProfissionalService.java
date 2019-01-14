package com.br.chn.software.application.profissional;

import com.br.chn.software.domain.model.atividade.TipoDeAtividade;
import com.br.chn.software.domain.model.profissional.IdProfissional;

public interface ProfissionalService {

	void validarSeProfissionalPodeIniciarAtividade(IdProfissional idProfissional, TipoDeAtividade tipoDeAtividade);
}
