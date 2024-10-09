package com.formacionbdi.springboot.app.oauth.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.formacionbdi.springboot.app.oauth.clients.UsuarioFeignClient;
import com.galileo.cu.commons.models.Usuarios;

import feign.FeignException;

@CrossOrigin
@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {

	private Logger log = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioFeignClient client;

	@Override
	public UserDetails loadUserByUsername(String tip) throws UsernameNotFoundException {

		try {
			Usuarios usuario = client.findByTip(tip);
			if (usuario != null) {
				log.info("---------------------------------"+usuario.getNombre());
				log.info(usuario.getPerfil().getDescripcion());
			} else {
				log.info("********************username*************************");
			}

			/*
			 * List<GrantedAuthority> authorities = usuario.getRoles().stream() .map(role ->
			 * new SimpleGrantedAuthority(role.getNombre())) .peek(authority ->
			 * log.info("Role: " + authority.getAuthority())).collect(Collectors.toList());
			 */

			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(usuario.getPerfil().getDescripcion()));

			log.info("Usuario autenticado: " + tip);

			return new User(usuario.getTip(), usuario.getPassword(), true, true, true, true, authorities);
			//return new User(usuario.getNombre(), usuario.getPassword(), true, true, true, true, authorities);

		} catch (FeignException e) {
			String error = "Error en el login, no existe el usuario '" + tip + "' en el sistema";
			log.error(error);

			throw new UsernameNotFoundException(error);
		}
	}

	@Override
	public Usuarios findByTip(String tip) {
		return client.findByTip(tip);
	}

	@Override
	public Usuarios update(Usuarios usuario, Long id) {
		// TODO Auto-generated method stub
		return client.update(usuario, id);
	}
}
