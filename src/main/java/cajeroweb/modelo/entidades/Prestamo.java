package cajeroweb.modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="prestamos")
public class Prestamo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //para indicarle que el id es autoincremental
	@Column(name="id_prestamo")
	private int idPrestamo;
	private String descripcion;
	@Column(name="cantidad_prestamo")
	private double cantidadPrestamo;
	@Column(name="fecha_prestamo")
	@Temporal(TemporalType.DATE) //columna date del sql coge solo la fecha
	private Date fechaPrestamo;
	@Column(name="tasa_interes_anual")
	private double tasaInteresAnual;
	@Column(name="plazo_meses")
	private int plazoMeses;
	@Column(name="tipo_cuota")
	private String tipoCuota;
	@ManyToOne //muchos prestamos a una cuenta
	@JoinColumn(name="id_cuenta") //la columna que es la fk (para poder hacer el enlace entre ellos)
	private Cuenta cuenta;

}
