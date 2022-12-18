package co.edu.unicauca.asae.core.proyecto.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Direccion")
public class DireccionEntity {

	@Id
	private Integer idEstudiante;
	@Column(name="calle", nullable = false, length = 150)
	private String calle;
	@Column(name="localidad", nullable = false, length = 150)
	private String localidad;
	
	@OneToOne(cascade = { CascadeType.ALL },fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "idPersona")
	private EstudianteEntity objEstudiante;

}
