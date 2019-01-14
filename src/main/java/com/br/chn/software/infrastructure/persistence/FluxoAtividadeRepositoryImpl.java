package com.br.chn.software.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.br.chn.software.domain.model.atividade.Atividade.TipoDeAtividade;
import com.br.chn.software.domain.model.atividade.FluxoAtividade;
import com.br.chn.software.domain.model.atividade.FluxoAtividadeRepository;

@Repository
public interface FluxoAtividadeRepositoryImpl extends MongoRepository<FluxoAtividade, TipoDeAtividade>, FluxoAtividadeRepository {
	
}
