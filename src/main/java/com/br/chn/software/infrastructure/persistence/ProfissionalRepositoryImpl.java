package com.br.chn.software.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.br.chn.software.domain.model.profissional.IdProfissional;
import com.br.chn.software.domain.model.profissional.Profissional;
import com.br.chn.software.domain.model.profissional.ProfissionalRepository;

@Repository
public interface ProfissionalRepositoryImpl extends MongoRepository<Profissional, IdProfissional>, ProfissionalRepository {

}
