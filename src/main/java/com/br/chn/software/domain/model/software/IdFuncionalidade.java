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
public class IdFuncionalidade implements ValueObject<IdSoftware> {

	private static final long serialVersionUID = 4267981290631261251L;

	private String id;
}
