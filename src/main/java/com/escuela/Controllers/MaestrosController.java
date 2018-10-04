package com.escuela.Controllers;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.escuela.Services.IMaestrosService;
import com.escuela.entities.Maestros;




@Controller
@SessionAttributes("maestros")
public class MaestrosController {

	@Autowired
	private IMaestrosService maestrosService;
	
	@RequestMapping(value="/listar1", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("","listado de maestros");
		model.addAttribute("maestros", maestrosService.findAll());
		return "listar1";
	}
	
	@RequestMapping(value="/form1")
	public String crear(Map<String, Object> model) {
		Maestros maestros = new Maestros();
		model.put("maestros", maestros);
		model.put("", "Formulario de maestros");
		return "form1";
	}
	
	@RequestMapping(value="/form1", method=RequestMethod.POST)
	public String guardar(@Valid Maestros maestros, BindingResult bindingResult, RedirectAttributes flash, SessionStatus sessionStatus ) {
		if(bindingResult.hasErrors()) {
			return "form1";
		}
		maestrosService.save(maestros);
		sessionStatus.setComplete();
		
		flash.addFlashAttribute("success","Maestro creado con exito");
		return "redirect:listar1";
	}
	
	@RequestMapping(value="/form1/{id_maestros}")
	public String editarm(@PathVariable(value="id_maestros") Long id_maestros, Map<String, Object> model, RedirectAttributes flash) {
		Maestros maestros = null;
		if (id_maestros > 0) {
			maestros = maestrosService.findOne(id_maestros);
		}else {
			flash.addFlashAttribute("error","El Id del maestro no puede ser cero");
			return "redirect:/listar1";
		}
		model.put("maestros", maestros);
		model.put("title", "Editar Maestros");
		
		return "form1";
	}
	
	@RequestMapping(value="/eliminarm/{id_maestros}")
	public String eliminarm(@PathVariable(value="id_maestros") Long id_maestros, RedirectAttributes flash) {
		if (id_maestros > 0 ) {
			maestrosService.delete(id_maestros);
		}
		flash.addFlashAttribute("success","Maestros eliminado con exito");
		return "redirect:/listar1";
	}

	@RequestMapping(value="/ver1/{id_maestros}", method=RequestMethod.GET)
	public String ver(@PathVariable(value = "id_maestros") Long id_maestros, Map<String, Object> model, RedirectAttributes flashl) {
		Maestros maestros = maestrosService.findOne(id_maestros);
		if (maestros == null) {
			flashl.addFlashAttribute("error", "El maestro no existe en la base de datos");
			return "redirect:/listar1";
		}

		model.put("maestros", maestros);
		model.put("titulo", "Editar Maestros");
		return "ver1";
	}
}
