package com.br.chn.software.application.software;

import com.br.chn.software.domain.model.profissional.IdProfissional;
import com.br.chn.software.domain.model.software.IdFuncionalidade;
import com.br.chn.software.domain.model.software.IdSoftware;

public interface SoftwareService {

	String criarSoftware(String nome);

	void adicionarFuncionalidade(IdSoftware idSoftware, String nomeUseCase, String descricao);

	void registraInicioNovaAtividade(IdSoftware idSoftware, IdProfissional idProfissional, IdFuncionalidade idFuncionalidade);

	void registraConclusaoAtividade(IdSoftware idSoftware, IdProfissional idProfissional, IdFuncionalidade idFuncionalidade);
}
