package pe.edu.cibertec.PF_DAW1_Alumnos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlumno;
    private String nombres;
    private String apellidos;
    private String genero;
    private String dni;
    private String nivelAcademico;
    private Boolean repitente;
    private Date fechaNacimiento;
    private Integer edad;
    private Boolean activo;

}
