package com.br.chn.software.application.profissional.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.chn.software.application.profissional.ProfissionalService;
import com.br.chn.software.domain.model.atividade.TipoDeAtividade;
import com.br.chn.software.domain.model.profissional.IdProfissional;
import com.br.chn.software.domain.model.profissional.Profissional;
import com.br.chn.software.domain.model.profissional.ProfissionalRepository;

@Service
public class ProfissionalServiceImpl implements ProfissionalService {

	@Autowired
	private ProfissionalRepository profissionalRepository;

	@Override
	public void validarSeProfissionalPodeIniciarAtividade(IdProfissional idProfissional,
			TipoDeAtividade tipoDeAtividade) {
		Profissional profissional = consultaProfissionalExistente(idProfissional);
		if (!profissional.possuiPerfilParaExecutarEsseTipoDeAtividade(tipoDeAtividade)) {
			throw new IllegalArgumentException(String.format("O Profissional [%s] não possui perfil para executar esse tipo de atividade [%s]!",
					profissional.nome(), tipoDeAtividade));
		}
	}

	private Profissional consultaProfissionalExistente(IdProfissional idProfissional) {
		return profissionalRepository.findById(idProfissional).orElseThrow(
				() -> new IllegalArgumentException(String.format("Profissional Id:[%s] não existe!", idProfissional)));
	}
}
