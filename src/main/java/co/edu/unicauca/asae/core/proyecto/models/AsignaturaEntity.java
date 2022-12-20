package co.edu.unicauca.asae.core.proyecto.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Asignatura")
public class AsignaturaEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1231420302668201794L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAsignatura;
	@Column(name = "nombre", nullable = false, length = 150)
	private String nombre;

	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "Docente_Asignatura", joinColumns = @JoinColumn(name = "idAsignatura"), inverseJoinColumns = @JoinColumn(name = "idPersona"),
			uniqueConstraints = {@UniqueConstraint(columnNames = { "idPersona", "idAsignatura" })})
	private List<DocenteEntity> docentes  = new ArrayList<>();

	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "objAsignatura")
	private List<CursoEntity> cursos  = new ArrayList<>();

}