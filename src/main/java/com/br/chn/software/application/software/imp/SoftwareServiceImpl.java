package com.br.chn.software.application.software.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.chn.software.application.profissional.NotificaProfissionalService;
import com.br.chn.software.application.profissional.ProfissionalService;
import com.br.chn.software.application.software.SoftwareService;
import com.br.chn.software.domain.model.atividade.Atividade.TipoDeAtividade;
import com.br.chn.software.domain.model.profissional.IdProfissional;
import com.br.chn.software.domain.model.software.IdFuncionalidade;
import com.br.chn.software.domain.model.software.IdSoftware;
import com.br.chn.software.domain.model.software.Software;
import com.br.chn.software.domain.model.software.SoftwareRepository;

@Service
public class SoftwareServiceImpl implements SoftwareService {

	@Autowired
	private SoftwareRepository softwareRepository;

	@Autowired
	private ProfissionalService profissionalService;

	// Aqui poderia ser aplicado Event-Driven 
	@Autowired
	private NotificaProfissionalService notificaProfissionalService;

	@Override
	public String criarSoftware(String nome) {
		return softwareRepository.save(Software.criarSoftware(nome)).idSoftware();
	}

	@Override
	public void adicionarFuncionalidade(IdSoftware idSoftware, String nomeUseCase, String descricao) {
		Software software = consultaSoftwareExistente(idSoftware);
		software.adicionarFuncionalidade(nomeUseCase, descricao);
		softwareRepository.save(software);
	}

	@Override
	public void registraInicioNovaAtividade(IdSoftware idSoftware, IdProfissional idProfissional, IdFuncionalidade idFuncionalidade) {
		Software software = consultaSoftwareExistente(idSoftware);

		TipoDeAtividade tipoDeAtividade = software.proximaAtividadeASerIniciada(idFuncionalidade);

		profissionalService.validarSeProfissionalPodeIniciarAtividade(idProfissional, tipoDeAtividade);

		software.registraInicioNovaAtividade(idProfissional, idFuncionalidade);

		softwareRepository.save(software);
	}

	@Override
	public void registraConclusaoAtividade(IdSoftware idSoftware, IdProfissional idProfissional, IdFuncionalidade idFuncionalidade) {
		Software software = consultaSoftwareExistente(idSoftware);
		software.registraConclusaoAtividade(idProfissional, idFuncionalidade);
		softwareRepository.save(software);

		software.funcionalidades().stream().filter(f -> idFuncionalidade.equals(f.getIdFuncionalidade())).findFirst().ifPresent(f -> {
			notificaProfissionalService.notificaAtividadeDisponivel(idSoftware, idFuncionalidade, f.getAtividade().getTipo());
		});
	}

	private Software consultaSoftwareExistente(IdSoftware idSoftware) {
		return softwareRepository.findById(idSoftware).orElseThrow(
				() -> new IllegalArgumentException(String.format("Software Id:[%s] não existe!.", idSoftware)));
	}
}
