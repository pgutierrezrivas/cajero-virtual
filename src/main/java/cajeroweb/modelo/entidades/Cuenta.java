package cajeroweb.modelo.entidades;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="cuentas")
public class Cuenta implements Serializable{

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@Column(name="id_cuenta")
	private int idCuenta;
	private double saldo;
	@Column(name="tipo_cuenta")
	private String tipoCuenta;


	// metodo propio para ingresar dinero
    public void ingresar(double cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a ingresar debe ser positiva");
        }

        this.saldo += cantidad;
    }
   
    // metodo propio para extraer dinero
    public boolean extraer(double cantidad) {

    	if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a extraer debe ser positiva");
        }

        if (this.saldo >= cantidad) {
            this.saldo -= cantidad;
            return true;
        }

        return false;
    }

}
