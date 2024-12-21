package pe.edu.cibertec.PF_DAW1_Alumnos.service;

import pe.edu.cibertec.PF_DAW1_Alumnos.dto.AlumnoDto;

import java.util.List;
import java.util.Optional;

public interface AlumnoService {

    List<AlumnoDto> listarAlumnos() throws Exception;

    Optional<AlumnoDto> buscarAlumnoPorId(int idAlumno) throws Exception;

    boolean actualizarAlumno(AlumnoDto alumnoDto) throws Exception;

    boolean eliminarAlumno(int idAlumno) throws Exception;

    boolean agregarAlumno(AlumnoDto alumnoDto) throws Exception;


}
