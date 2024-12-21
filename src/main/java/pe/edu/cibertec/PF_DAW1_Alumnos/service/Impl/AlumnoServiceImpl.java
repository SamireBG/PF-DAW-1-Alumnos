package pe.edu.cibertec.PF_DAW1_Alumnos.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.PF_DAW1_Alumnos.dto.AlumnoDto;
import pe.edu.cibertec.PF_DAW1_Alumnos.entity.Alumno;
import pe.edu.cibertec.PF_DAW1_Alumnos.repository.AlumnoRepository;
import pe.edu.cibertec.PF_DAW1_Alumnos.service.AlumnoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    AlumnoRepository alumnoRepository;

    @Override
    public List<AlumnoDto> listarAlumnos() throws Exception {

        List<AlumnoDto> alumnos = new ArrayList<>();
        Iterable<Alumno> iterable = alumnoRepository.findAll();
        iterable.forEach(alumno -> {
            alumnos.add(new AlumnoDto(
                    alumno.getIdAlumno(),
                    alumno.getNombres(),
                    alumno.getApellidos(),
                    alumno.getGenero(),
                    alumno.getDni(),
                    alumno.getNivelAcademico(),
                    alumno.getRepitente(),
                    alumno.getFechaNacimiento(),
                    alumno.getEdad(),
                    alumno.getActivo()));
        });
        return alumnos;

    }

    @Override
    public Optional<AlumnoDto> buscarAlumnoPorId(int idAlumno) throws Exception {

        Optional<Alumno> optional = alumnoRepository.findById(idAlumno);
        return optional.map(alumno -> new AlumnoDto(alumno.getIdAlumno(),
                alumno.getNombres(),
                alumno.getApellidos(),
                alumno.getGenero(),
                alumno.getDni(),
                alumno.getNivelAcademico(),
                alumno.getRepitente(),
                alumno.getFechaNacimiento(),
                alumno.getEdad(),
                alumno.getActivo()
        ));

    }

    @Override
    public boolean actualizarAlumno(AlumnoDto alumnoDto) throws Exception {

        Optional<Alumno> optional = alumnoRepository.findById(alumnoDto.id());
        return optional.map( alumno -> {
            alumno.setNombres(alumnoDto.nombres());
            alumno.setApellidos(alumnoDto.apellidos());
            alumno.setGenero(alumnoDto.genero());
            alumno.setDni(alumnoDto.dni());
            alumno.setNivelAcademico(alumnoDto.nivelAcademico());
            alumno.setRepitente(alumnoDto.repitente());
            alumno.setFechaNacimiento(alumnoDto.fechaNacimiento());
            alumno.setEdad(alumnoDto.edad());
            alumno.setActivo(alumnoDto.activo());
            alumnoRepository.save(alumno);
            return true;
        }).orElse(false);

    }

    @Override
    public boolean eliminarAlumno(int idAlumno) throws Exception {

        Optional<Alumno> optional = alumnoRepository.findById(idAlumno);
        return optional.map(alumno -> {
            alumnoRepository.delete(alumno);
            return true;
        }).orElse(false);

    }

    @Override
    public boolean agregarAlumno(AlumnoDto alumnoDto) throws Exception {

        try{
            Alumno alumno = new Alumno(
                    null,
                    alumnoDto.nombres(),
                    alumnoDto.apellidos(),
                    alumnoDto.genero(),
                    alumnoDto.dni(),
                    alumnoDto.nivelAcademico(),
                    alumnoDto.repitente(),
                    alumnoDto.fechaNacimiento(),
                    alumnoDto.edad(),
                    alumnoDto.activo()
            );
            alumnoRepository.save(alumno);
            return true;
        }catch (Exception e){
            throw new Exception("Error al agregar alumno");
        }

    }
}
