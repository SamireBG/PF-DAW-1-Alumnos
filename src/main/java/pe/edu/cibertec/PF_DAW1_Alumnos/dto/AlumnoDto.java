package pe.edu.cibertec.PF_DAW1_Alumnos.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record AlumnoDto(Integer id,
                        String nombres,
                        String apellidos,
                        String genero,
                        String dni,
                        String nivelAcademico,
                        Boolean repitente,
                        @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaNacimiento,
                        Integer edad,
                        Boolean activo) {
}
