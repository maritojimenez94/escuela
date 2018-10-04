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

import com.escuela.Services.IAlumnosService;
import com.escuela.entities.Alumnos;



@Controller
@SessionAttributes("alumnos")
public class AlumnosController {

	@Autowired
	private IAlumnosService alumnosService;
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("","listado de alumnos");
		model.addAttribute("alumnos", alumnosService.findAll());
		return "listar";
	}
	
	@RequestMapping(value="/form")
	public String crear(Map<String, Object> model) {
		Alumnos alumno = new Alumnos();
		model.put("alumnos", alumno);
		model.put("", "Formulario de Alumnos");
		return "form";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar(@Valid Alumnos alumnos, BindingResult bindingResult, RedirectAttributes flash, SessionStatus sessionStatus ) {
		if(bindingResult.hasErrors()) {
			return "form";
		}
		alumnosService.save(alumnos);
		sessionStatus.setComplete();
		
		flash.addFlashAttribute("success","Alumno creado con exito");
		return "redirect:listar";
	}
	
	@RequestMapping(value="/form/{id_alumnos}")
	public String editar(@PathVariable(value="id_alumnos") Long id_alumnos, Map<String, Object> model, RedirectAttributes flash) {
		Alumnos alumnos = null;
		if (id_alumnos > 0) {
			alumnos = alumnosService.findOne(id_alumnos);
		}else {
			flash.addFlashAttribute("error","El Id del alumno no puede ser cero");
			return "redirect:/listar";
		}
		model.put("alumnos", alumnos);
		model.put("title", "Editar Alumnos");
		
		return "form";
	}
	
	@RequestMapping(value="/eliminara/{id_alumnos}")
	public String eliminara(@PathVariable(value="id_alumnos") Long id_alumnos, RedirectAttributes flash) {
		if (id_alumnos > 0 ) {
			alumnosService.delete(id_alumnos);
		}
		flash.addFlashAttribute("success","Alumno eliminado con exito");
		return "redirect:/listar";
	}
	
	@RequestMapping(value="/ver/{id_alumnos}", method=RequestMethod.GET)
	public String ver(@PathVariable(value = "id_alumnos") Long id_alumnos, Map<String, Object> model, RedirectAttributes flashl) {
		Alumnos alumnos = alumnosService.findOne(id_alumnos);
		if (alumnos == null) {
			flashl.addFlashAttribute("error", "El alumno no existe en la base de datos");
			return "redirect:/listar";
		}

		model.put("alumnos", alumnos);
		model.put("titulo", "Editar Alumnos");
		return "ver";
	}
}
