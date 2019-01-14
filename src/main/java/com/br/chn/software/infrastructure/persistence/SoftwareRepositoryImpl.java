package com.br.chn.software.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.br.chn.software.domain.model.software.IdSoftware;
import com.br.chn.software.domain.model.software.Software;
import com.br.chn.software.domain.model.software.SoftwareRepository;

@Repository
public interface SoftwareRepositoryImpl extends MongoRepository<Software, IdSoftware>, SoftwareRepository {

}
