package co.com.sp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.com.sp.capadominio.ParametroPersona;
import co.com.sp.capadominio.Persona;
import co.com.sp.capaservicio.ParametroPersonaService;
import co.com.sp.capaservicio.PersonaService;
import co.com.sp.capaservicio.excepciones.BusinessException;
import co.com.sp.dto.PersonaDTO;

@Controller
@RequestMapping("/private/personas")
public class PersonaController {
    
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private ParametroPersonaService parametroPersonaService;
	
	final Long ID_TIPO_PARAMETRO_PERSONA = (long) 1;
	
	@RequestMapping(method = RequestMethod.GET)
    public ModelAndView listar(){
        ModelAndView mav = new ModelAndView("/personas/listar"); //view.jsp
        List<ParametroPersona> generos = new ArrayList<ParametroPersona>();
        List<Persona> lstPersonas = new ArrayList<Persona>();
        try {
    		generos = parametroPersonaService.findByTipo(ID_TIPO_PARAMETRO_PERSONA);
    		Float totalTiempo = (float) 0.0;
			for (int i = 0; i < 1; i++) {
				Long time_start_service = System.currentTimeMillis(); 
	    		lstPersonas = personaService.listar();
	    		Long time_end_service = System.currentTimeMillis(); 
				totalTiempo = totalTiempo+(time_end_service-time_start_service);
			}
			
			Float totalTiempo2 = (float) 0.0;
			for (int i = 0; i < 1; i++) {
				Long time_start_service = System.currentTimeMillis(); 
	    		lstPersonas = personaService.listarServiceArray();
	    		Long time_end_service = System.currentTimeMillis(); 
				totalTiempo2 = totalTiempo2+(time_end_service-time_start_service);
			}
			System.out.println("Promedio"+totalTiempo2);
			System.out.println("Promedio Daos"+totalTiempo);
			
			
		} catch (BusinessException e) {
			System.out.println("Error en la capa web consultando Personas "+e);
		}
        PersonaDTO persona = new PersonaDTO();
        mav.addObject("personas",lstPersonas);
        mav.addObject("personaDetalle",persona);
        mav.addObject("generos",generos);
        mav.addObject("accion","");
        return mav;
    }
    
    @RequestMapping(params = "agregarPersona")
    public ModelAndView agregarPersona(@Valid @ModelAttribute("personaDetalle") Persona p, BindingResult valid) throws Exception{
        ModelAndView mav = null;
        if(valid.hasErrors()){
        	List<ParametroPersona> generos = new ArrayList<ParametroPersona>();
    		generos = parametroPersonaService.findByTipo(ID_TIPO_PARAMETRO_PERSONA);
        	mav = new ModelAndView("/personas/components/modificarPersona");
            mav.addObject("personaDetalle",p);
            mav.addObject("generos",generos);
        }else{
        	try {
	            List<Persona> lstPersonas = new ArrayList<Persona>();
	    		mav = new ModelAndView("/personas/components/listado");
				personaService.insertar(p);
				lstPersonas = personaService.listar();
	            mav.addObject("personas",lstPersonas);
        	} catch (BusinessException e) {
    			System.out.println("Error en la capa web consultando Personas "+e);
        		throw new Exception("Se ha presentado un error al insertar el registro");
    		}
        }
        return mav;
    }
    
    
    @RequestMapping(params = "detallarPersona")
    public ModelAndView detallarPersona(@ModelAttribute PersonaDTO p) throws Exception{
        ModelAndView mav = new ModelAndView("/personas/components/consultarPersona");
    	List<ParametroPersona> generos = new ArrayList<ParametroPersona>();
        try { 
    		generos = parametroPersonaService.findByTipo(ID_TIPO_PARAMETRO_PERSONA);
			p = (PersonaDTO) personaService.encontrarPorIdCompleta(p.getIdPersona());
		} catch (BusinessException e) {
			System.out.println("Error en la capa web consultando una persona "+e);
    		throw new Exception("Se ha presentado un error consultando una persona");
		}
        if(p.getAccion().equals("consultar")){
        	mav = new ModelAndView("/personas/components/consultarPersona");
        }else{
        	mav = new ModelAndView("/personas/components/modificarPersona");
        }
        mav.addObject("personaDetalle",p);
        mav.addObject("generos",generos);
        return mav;
    }
}
