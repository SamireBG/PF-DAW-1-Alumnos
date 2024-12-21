package pe.edu.cibertec.PF_DAW1_Alumnos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.cibertec.PF_DAW1_Alumnos.dto.AlumnoDto;
import pe.edu.cibertec.PF_DAW1_Alumnos.service.AlumnoService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    AlumnoService alumnoService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/restricted")
    public String restricted(Model model) {
        return "restricted";
    }

    @GetMapping("/listar")
    public String listarAlumnos(Model model) {

        try {
            List<AlumnoDto> alumnos = alumnoService.listarAlumnos();
            model.addAttribute("alumnos", alumnos);
            model.addAttribute("alumno", new AlumnoDto(null,"","",
                    "","","",true,null,null,true));
            model.addAttribute("error",null);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error","Ocurrio un error al listar alumnos");
        }

        return "alumnos/alumnos_listado";

    }

    @GetMapping("/buscar/{id}")
    @ResponseBody
    public ResponseEntity<AlumnoDto> buscar(@PathVariable("id") int id) {
        try {
            Optional<AlumnoDto> alumno = alumnoService.buscarAlumnoPorId(id);
            if (alumno.isPresent()) {
                return ResponseEntity.ok(alumno.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/actualizar/{id}")
    public String actualizarAlumno(@PathVariable("id") int id,Model model) {
        try{
            Optional<AlumnoDto> alumno = alumnoService.buscarAlumnoPorId(id);
            if (alumno.isPresent()) {
                model.addAttribute("alumno", alumno.get());
                return "alumnos/alumno_actualizar";
            }else {
                model.addAttribute("error","Ocurrio un error al actualizar alumno");
                return "alumnos/error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error","Ocurrio un error al actualizar alumno");
            return "alumnos/error";
        }
    }

    @PostMapping("/actualizar")
    public String actualizarAlumno(@ModelAttribute AlumnoDto alumnoDto, RedirectAttributes redirectAttributes) {

        try{
            boolean isUpdate = alumnoService.actualizarAlumno(alumnoDto);
            if(isUpdate) {
                redirectAttributes.addFlashAttribute("success", "Alumno modificado exitosamente.");
            }else {
                redirectAttributes.addFlashAttribute("error", "Ocurrio un error al actualizar alumno");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error","Ocurrio un error al actualizar alumno");
        }
        return "redirect:/alumnos/listar";
    }

    @PostMapping("/agregar")
    public String agregarAlumno(@ModelAttribute AlumnoDto alumnoDto, RedirectAttributes redirectAttributes) {

        try{
            boolean resultado = alumnoService.agregarAlumno(alumnoDto);
            if(resultado) {
                redirectAttributes.addFlashAttribute("success", "Alumno agregado exitosamente.");
            }else {
                redirectAttributes.addFlashAttribute("error", "Ocurrio un error al agregar alumno");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error","Ocurrio un error al agregar alumno");
        }
        return "redirect:/alumnos/listar";

    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAlumno(@PathVariable("id") int id, Model model) {

        try{
            boolean resultado = alumnoService.eliminarAlumno(id);
            if (resultado) {
                return "redirect:/alumnos/listar";
            }else {
                model.addAttribute("error", "Ocurrio un error al eliminar alumno");
                return "alumnos/error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error","Ocurrio un error al eliminar alumno");
            return "alumnos/error";
        }

    }

}
