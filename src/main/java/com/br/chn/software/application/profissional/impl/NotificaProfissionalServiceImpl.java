package com.br.chn.software.application.profissional.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.chn.software.application.profissional.NotificaProfissionalService;
import com.br.chn.software.domain.model.atividade.TipoDeAtividade;
import com.br.chn.software.domain.model.profissional.ProfissionalRepository;
import com.br.chn.software.domain.model.software.IdFuncionalidade;
import com.br.chn.software.domain.model.software.IdSoftware;

@Service
public class NotificaProfissionalServiceImpl implements NotificaProfissionalService {

	@Autowired
	private ProfissionalRepository profissionalRepository;

	@Override
	public void notificaAtividadeDisponivel(IdSoftware idSoftware, IdFuncionalidade idFuncionalidade, TipoDeAtividade tipoDeAtividade) {
		profissionalRepository.findAll().stream()
				.filter(p -> p.possuiPerfilParaExecutarEsseTipoDeAtividade(tipoDeAtividade)).forEach(p -> {
				System.out.println("::::::::::::::: ENVIANDO EMAIL :::::::::::::::");
				System.out.println("::::::::::::::: Funcionalidade: " + idFuncionalidade.getId());
				System.out.println("::::::::::::::: Profissional: " + p.idProfissional());
				System.out.println("::::::::::::::: Software: " + idSoftware.getId());
				System.out.println("::::::::::::::: Nome: " + p.nome());
				System.out.println("::::::::::::::: Email: " + p.contatoInfo().getEmail());
				System.out.println("::::::::::::::: Telefone: " + p.contatoInfo().getTelefone());
				System.out.println("::::::::::::::: EMAIL ENVIADO :::::::::::::::");
		});
	}
}
