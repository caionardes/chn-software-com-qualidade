package com.br.chn.software.domain.model.software;

import java.util.List;
import java.util.Optional;

public interface SoftwareRepository {

	void delete(Software entity);
	void deleteById(IdSoftware id);

	List<Software> findAll();
	Software save(Software software);
	Optional<Software> findById(IdSoftware id);
}
