package com.br.chn.software.domain.model.atividade;

import java.util.List;
import java.util.Optional;

public interface FluxoAtividadeRepository {

	void delete(FluxoAtividade entity);
	List<FluxoAtividade> findAll();
	FluxoAtividade save(FluxoAtividade software);
	Optional<FluxoAtividade> findById(TipoDeAtividade id);
}
