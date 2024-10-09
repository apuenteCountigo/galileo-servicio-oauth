package com.formacionbdi.springboot.app.oauth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.formacionbdi.springboot.app.oauth.services.IUsuarioService;
import com.galileo.cu.commons.models.Usuarios;

@Component
public class InfoAdicionalToken implements TokenEnhancer{

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();
		
		Usuarios usuario = usuarioService.findByTip(authentication.getName());
		info.put("Id", usuario.getId());
		info.put("tip", usuario.getTip());
		info.put("nombre", usuario.getNombre());
		info.put("apellido", usuario.getApellidos());
		info.put("correo", usuario.getEmail());
		info.put("Perfil", usuario.getPerfil());
		info.put("traccar", usuario.getTraccar());
		info.put("traccarID", usuario.getTraccarID());
		
		info.put("nuevo", passwordEncoder.matches(usuario.getTip(), usuario.getPassword()));
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}
