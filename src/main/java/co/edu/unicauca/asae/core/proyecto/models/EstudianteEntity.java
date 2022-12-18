package co.edu.unicauca.asae.core.proyecto.models;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Estudiante")
public class EstudianteEntity extends PersonaEntity{
    @Column(name="fechaIngreso", nullable = false)
    private Date fechaIngreso;

    @OneToOne(optional = false, cascade = { CascadeType.ALL },mappedBy = "objEstudiante",fetch = FetchType.LAZY)
    private DireccionEntity objDireccion;
    
    @OneToMany(cascade = { CascadeType.ALL },fetch = FetchType.LAZY, mappedBy = "objEstudiante")
	private List<TelefonoEntity> telefonos;

   
}
