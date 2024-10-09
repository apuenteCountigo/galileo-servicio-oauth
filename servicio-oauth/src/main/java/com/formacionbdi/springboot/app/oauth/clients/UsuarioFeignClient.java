package com.formacionbdi.springboot.app.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.galileo.cu.commons.models.Usuarios;

@CrossOrigin
@FeignClient(name="servicio-usuarios")
public interface UsuarioFeignClient {
//, consumes = MediaTypes.HAL_JSON_VALUE
	/*@GetMapping(value="/usuarios/search/buscar-nombre")
	public Usuarios findByUsername(@RequestParam("nombre") String nombre);*/
	
	@GetMapping(value="/usuarios/search/buscarTip")
	public Usuarios findByTip(@RequestParam("tip") String tip);
	
	@PutMapping("/usuarios/{id}")
	public Usuarios update(@RequestBody Usuarios usuario, @PathVariable Long id);
}
