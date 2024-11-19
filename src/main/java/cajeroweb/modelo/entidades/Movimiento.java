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
@Table(name="movimientos")
public class Movimiento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //para indicarle que el id es autoincremental
	@Column(name="id_movimiento")
	private int idMovimiento;
	@Temporal(TemporalType.TIMESTAMP) //columna date del sql coge la fecha y hora
	private Date fecha;
	private double cantidad;
	private String operacion;
	@ManyToOne //muchos movimientos a una cuenta
	@JoinColumn(name="id_cuenta") //la columna que es la fk (para poder hacer el enlace entre ellos)
	private Cuenta cuenta;


	//metodo propio para crear un movimiento y luego poder usarlo en el controller
	public static Movimiento crearMovimiento(Cuenta cuenta, double cantidad, String operacion) {

        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(new Date());
        movimiento.setCantidad(cantidad);
        movimiento.setOperacion(operacion);
        movimiento.setCuenta(cuenta);

        return movimiento;
    }

}
