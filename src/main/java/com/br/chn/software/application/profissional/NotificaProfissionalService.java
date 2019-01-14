package com.br.chn.software.application.profissional;

import com.br.chn.software.domain.model.atividade.TipoDeAtividade;
import com.br.chn.software.domain.model.software.IdFuncionalidade;
import com.br.chn.software.domain.model.software.IdSoftware;

public interface NotificaProfissionalService {

	void notificaAtividadeDisponivel(IdSoftware idSoftware, IdFuncionalidade idFuncionalidade, TipoDeAtividade tipoDeAtividade);
}
