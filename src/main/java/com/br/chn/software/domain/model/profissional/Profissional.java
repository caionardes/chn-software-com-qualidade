package com.br.chn.software.domain.model.profissional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.br.chn.software.domain.model.atividade.TipoDeAtividade;
import com.br.chn.software.domain.shared.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Profissional implements Entity<Profissional> {

	@Id
	private IdProfissional idProfissional;

	private String nome;
	private ContatoInfo contatoInfo;

	private List<Skill> skills;

	public static Profissional criar(String nome, ContatoInfo contatoInfo, List<Skill> skills) {
		return new Profissional(new IdProfissional(UUID.randomUUID().toString()), nome, contatoInfo, skills);
	}

	public boolean possuiPerfilParaExecutarEsseTipoDeAtividade(TipoDeAtividade tipoDeAtividade) {
		return skills.stream().anyMatch(s -> s.possuiPerfilParaExecutar(tipoDeAtividade));
	}
	
	public String nome() {
		return nome;
	}

	public List<Skill> skills() {
		return Collections.unmodifiableList(skills);
	}
	
	public String idProfissional() {
		return idProfissional.getId();
	}

	public ContatoInfo contatoInfo() {
		return contatoInfo;
	}
}
