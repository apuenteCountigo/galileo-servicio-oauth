package com.formacionbdi.springboot.app.oauth.services;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.galileo.cu.commons.models.Usuarios;

@CrossOrigin
public interface IUsuarioService {
	
	//public Usuarios findByUsername(String username);
	
	public Usuarios findByTip(String tip);
	
	public Usuarios update(Usuarios usuario, Long id);

}
