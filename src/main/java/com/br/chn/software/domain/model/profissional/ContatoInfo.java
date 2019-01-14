package com.br.chn.software.domain.model.profissional;

import com.br.chn.software.domain.shared.ValueObject;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ContatoInfo implements ValueObject<IdProfissional> {

	private static final long serialVersionUID = 6399168571628334118L;

	private String email;
	private String telefone;
}
