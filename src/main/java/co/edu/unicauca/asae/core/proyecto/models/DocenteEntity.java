package co.edu.unicauca.asae.core.proyecto.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
@AllArgsConstructor
@Table(name = "Docente")
public class DocenteEntity extends PersonaEntity{

	@Column(name="universidad", nullable = false, length = 150)
    private String universidad;
    
    @Column(name="tipoDocente", nullable = false, length = 150)
    private String tipoDocente;
    
    @Column(name="salario", nullable = false, length = 150)
    private Float salario;

    @ManyToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
	@JoinTable(name="Docente_Asignatura", joinColumns = @JoinColumn(name="idPersona"), inverseJoinColumns = @JoinColumn(name="idAsignatura"))
	private List<AsignaturaEntity> Asignaturas = new ArrayList<>();
    

    public void addAsignatura(AsignaturaEntity asignatura){
        Asignaturas.add(asignatura);
    }

}
