package com.br.chn.software.domain.model.profissional;

import java.util.List;
import java.util.Optional;

public interface ProfissionalRepository {
	
	void delete(Profissional entity);
	List<Profissional> findAll();
	Profissional save(Profissional profissional);
	Optional<Profissional> findById(IdProfissional id);
}
