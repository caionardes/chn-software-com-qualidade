package com.br.chn.software;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.br.chn.software.application.software.SoftwareService;
import com.br.chn.software.domain.model.atividade.FluxoAtividade;
import com.br.chn.software.domain.model.atividade.FluxoAtividadeRepository;
import com.br.chn.software.domain.model.atividade.TipoDeAtividade;
import com.br.chn.software.domain.model.profissional.ContatoInfo;
import com.br.chn.software.domain.model.profissional.IdProfissional;
import com.br.chn.software.domain.model.profissional.Profissional;
import com.br.chn.software.domain.model.profissional.ProfissionalRepository;
import com.br.chn.software.domain.model.profissional.Skill;
import com.br.chn.software.domain.model.software.Funcionalidade;
import com.br.chn.software.domain.model.software.IdSoftware;
import com.br.chn.software.domain.model.software.Software;
import com.br.chn.software.domain.model.software.SoftwareRepository;

@SpringBootApplication
public class StartSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartSpringApplication.class, args);
	}

	@Bean
	public CommandLineRunner evt(
			ProfissionalRepository profissionalRepository,
			SoftwareService softwareService,
			SoftwareRepository softwareRepository,
			FluxoAtividadeRepository fluxoAtividadeRepository) {
		return evt -> {
			System.out.println("####################### INICIO ");
			fluxoAtividadeRepository.save(new FluxoAtividade(TipoDeAtividade.ANALISAR, TipoDeAtividade.EM_ANALISE));
			fluxoAtividadeRepository.save(new FluxoAtividade(TipoDeAtividade.EM_ANALISE, TipoDeAtividade.DESENVOLVER));
			fluxoAtividadeRepository.save(new FluxoAtividade(TipoDeAtividade.DESENVOLVER, TipoDeAtividade.EM_DESENV));
			fluxoAtividadeRepository.save(new FluxoAtividade(TipoDeAtividade.EM_DESENV, TipoDeAtividade.TESTAR));
			fluxoAtividadeRepository.save(new FluxoAtividade(TipoDeAtividade.TESTAR, TipoDeAtividade.EM_TESTE));
			fluxoAtividadeRepository.save(new FluxoAtividade(TipoDeAtividade.EM_TESTE, TipoDeAtividade.APRESENTAR));
			fluxoAtividadeRepository.save(new FluxoAtividade(TipoDeAtividade.APRESENTAR, TipoDeAtividade.EM_APRESENTACAO));
			fluxoAtividadeRepository.save(new FluxoAtividade(TipoDeAtividade.EM_APRESENTACAO, TipoDeAtividade.ACEITE, TipoDeAtividade.ANALISAR));

			profissionalRepository.findAll().forEach(profissionalRepository::delete);
			Stream.of(Skill.values()).forEach(skill -> {
				String nome = "Nome: " + skill.name();
				String email = "contato." + nome.toLowerCase().replaceAll(" ", "").replaceAll(":", ".") + "@gmail.com";
				String telefone = String.valueOf(new Random().nextInt(99999) + 1);

				profissionalRepository.save(Profissional.criar(
						"Nome: " + skill.name(),
						new ContatoInfo(email, telefone), Arrays.asList(skill)));
			});
			List<Profissional> profissionais = profissionalRepository.findAll();

			Map<Skill, Profissional> mapaProfissionalPorSkill = new HashMap<>();
			profissionais.forEach(p -> mapaProfissionalPorSkill.put(p.skills().stream().findFirst().get(), p));
			
			System.out.println("----------- PROFISSIONAIS INCLUIDOS ");
			profissionais.forEach(System.out::println);

			softwareRepository.findAll().forEach(softwareRepository::delete);

			IdSoftware idSoftware = new IdSoftware(softwareService.criarSoftware("Microsoft Word"));

			System.out.println("----------- SOFTWARE INCLUIDO ");
			softwareRepository.findAll().forEach(System.out::println);

			softwareService.adicionarFuncionalidade(idSoftware, "US-100", "desc");
			softwareService.adicionarFuncionalidade(idSoftware, "US-200", "desc");
			softwareService.adicionarFuncionalidade(idSoftware, "US-300", "desc");
			System.out.println("----------- FUNCIONALIDADES INCLUIDAS ");

			Optional<Software> software = softwareRepository.findById(idSoftware);

			List<Funcionalidade> funcionalidades = software.get().funcionalidades();
			funcionalidades.forEach(System.out::println);

			System.out.println("----------- REGISTRO DE ATIVIDADES ");

			softwareService.registraInicioNovaAtividade(idSoftware, new IdProfissional(mapaProfissionalPorSkill.get(Skill.ANALISTA).idProfissional()), funcionalidades.get(0).getIdFuncionalidade());
			softwareService.registraConclusaoAtividade(idSoftware, new IdProfissional(mapaProfissionalPorSkill.get(Skill.ANALISTA).idProfissional()), funcionalidades.get(0).getIdFuncionalidade());
			softwareService.registraInicioNovaAtividade(idSoftware, new IdProfissional(mapaProfissionalPorSkill.get(Skill.DESENVOLVEDOR).idProfissional()), funcionalidades.get(0).getIdFuncionalidade());
			softwareService.registraConclusaoAtividade(idSoftware, new IdProfissional(mapaProfissionalPorSkill.get(Skill.DESENVOLVEDOR).idProfissional()), funcionalidades.get(0).getIdFuncionalidade());

			softwareService.registraInicioNovaAtividade(idSoftware, new IdProfissional(mapaProfissionalPorSkill.get(Skill.ANALISTA).idProfissional()), funcionalidades.get(1).getIdFuncionalidade());

			System.out.println("####################### FIM ");
		};
	}
}

