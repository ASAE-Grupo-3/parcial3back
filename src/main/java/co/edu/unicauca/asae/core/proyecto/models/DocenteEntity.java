package co.edu.unicauca.asae.core.proyecto.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter 
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Docente",uniqueConstraints = { @UniqueConstraint(columnNames = { "noIdentificacion", "tipoIdentificacion" }) })
public class DocenteEntity extends PersonaEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4033743796760865730L;

	@Column(name="universidad", nullable = false, length = 150)
    private String universidad;
    
    @Column(name="tipoDocente", nullable = false, length = 150)
    private String tipoDocente;
    
    @Column(name="salario", nullable = false, length = 150)
    private Float salario;

    @ManyToMany(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
	@JoinTable(name="Docente_Asignatura", joinColumns = @JoinColumn(name="idPersona"), inverseJoinColumns = @JoinColumn(name="idAsignatura"),
	uniqueConstraints = {@UniqueConstraint(columnNames = { "idPersona", "idAsignatura" })})
	private List<AsignaturaEntity> asignaturas = new ArrayList<>();
    

    public void addAsignatura(AsignaturaEntity asignatura){
        asignaturas.add(asignatura);
    }

}
