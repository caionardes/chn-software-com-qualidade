package com.br.chn.software.interfaces.software;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.chn.software.application.software.SoftwareService;
import com.br.chn.software.domain.model.profissional.IdProfissional;
import com.br.chn.software.domain.model.software.IdFuncionalidade;
import com.br.chn.software.domain.model.software.IdSoftware;
import com.br.chn.software.domain.model.software.Software;
import com.br.chn.software.domain.model.software.SoftwareRepository;
import com.br.chn.software.interfaces.software.request.AdicionarFuncionalidadeRequest;
import com.br.chn.software.interfaces.software.request.ConcluirAtividadeRequest;
import com.br.chn.software.interfaces.software.request.CriarSoftwareRequest;
import com.br.chn.software.interfaces.software.request.IniciarAtividadeRequest;
import com.br.chn.software.interfaces.software.view.SoftwareViewAdapter;

@RestController
@RequestMapping("/api")
public class SoftwareController {

	@Autowired
	private SoftwareService softwareService;

	@Autowired
	private SoftwareRepository softwareRepository;

	@PostMapping
	public ResponseEntity<Object> criarSoftware(@RequestBody CriarSoftwareRequest request) {
		String idSoftware = softwareService.criarSoftware(request.getNome());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(idSoftware).toUri();

		return ResponseEntity.created(location).build();
	}

	@PostMapping(path = "add-funcionalidade")
	public ResponseEntity<Void> adicionarFuncionalidade(@RequestBody AdicionarFuncionalidadeRequest request) {
		softwareService.adicionarFuncionalidade(new IdSoftware(request.getIdSoftware()),
				request.getEspecificacaoTecnicaDTO().getNomeUseCase(),
				request.getEspecificacaoTecnicaDTO().getDescricao());

		return ResponseEntity.ok().build();
	}

	@PostMapping(path = "iniciar-atividade")
	public ResponseEntity<Object> iniciarAtividade(@RequestBody IniciarAtividadeRequest request) {
		softwareService.registraInicioNovaAtividade(new IdSoftware(request.getIdSoftware()),
				new IdProfissional(request.getIdProfissional()),
				new IdFuncionalidade(request.getIdFuncionalidade()));
		return ResponseEntity.ok().build();
	}

	@PostMapping(path = "concluir-atividade")
	public ResponseEntity<Object> concluirAtividade(@RequestBody ConcluirAtividadeRequest request) {
		softwareService.registraConclusaoAtividade(new IdSoftware(request.getIdSoftware()),
				new IdProfissional(request.getIdProfissional()),
				new IdFuncionalidade(request.getIdFuncionalidade()));
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{idSoftware}")
	public ResponseEntity<Void> deletarSoftware(@PathVariable String idSoftware) {
		softwareRepository.deleteById(new IdSoftware(idSoftware));
		return ResponseEntity.accepted().build();
	}

	@GetMapping
	public ResponseEntity<List<SoftwareViewAdapter>> listarTodos() {
		List<Software> todos = softwareRepository.findAll();
		List<SoftwareViewAdapter> todosAdapter = todos.stream().map(SoftwareViewAdapter::new)
				.collect(Collectors.toList());
		return ResponseEntity.ok(todosAdapter);
	}

	@GetMapping("/{idSoftware}")
	public ResponseEntity<SoftwareViewAdapter> buscaPorId(@PathVariable String idSoftware) {
		Optional<Software> softwareOpt = softwareRepository.findById(new IdSoftware(idSoftware));

		if (softwareOpt.isPresent()) {
			return ResponseEntity.ok(new SoftwareViewAdapter(softwareOpt.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
