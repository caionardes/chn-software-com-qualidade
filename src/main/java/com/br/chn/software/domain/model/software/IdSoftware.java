package com.br.chn.software.domain.model.software;

import com.br.chn.software.domain.shared.ValueObject;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class IdSoftware implements ValueObject<IdSoftware> {

	private static final long serialVersionUID = 1L;

	private String id;
}
