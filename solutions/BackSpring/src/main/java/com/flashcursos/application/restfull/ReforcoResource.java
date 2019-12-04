package com.flashcursos.application.restfull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flashcursos.model.entity.Reforco;
import com.flashcursos.model.entity.Professor;
import com.flashcursos.model.service.ReforcoService;

@Component
@RestController
@RequestMapping( "/api/admin/reforco" )
public class ReforcoResource {

	@Autowired
	private ReforcoService reforcoService;
	
	@GetMapping("/list")
	public List<Reforco> listar() {
		return this.reforcoService.listarReforcos();
	}
	
	@PostMapping( "/insert" )
	public Reforco cadastrar( @RequestBody Reforco reforco ){
		return this.reforcoService.cadastrarReforco(reforco);
	}
	
	@PostMapping( "/update" )
	public Reforco atualizar( @RequestBody Reforco reforco ){
		return this.reforcoService.atualizarReforco(reforco);
	}

	@GetMapping("/find")
	public Reforco detalhar(@RequestParam("id") Long id) {
		return this.reforcoService.detalharReforco(id);
	}
	
	@GetMapping("/remove")
	public void remover(@RequestParam("id") Long id) {
		this.reforcoService.removerReforco(id);;
	}
}



