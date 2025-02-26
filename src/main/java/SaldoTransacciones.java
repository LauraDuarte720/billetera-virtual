import java.util.ArrayList;
import java.util.List;

//CLASE PARA EL METODO CONSULTAR SALDO Y TRANSACCIONES
public class SaldoTransacciones {

    private ArrayList<Transaccion> transaccions;
    private double saldo;

    public ArrayList<Transaccion> getTransaccions() {
        return transaccions;
    }

    public void setTransaccions(ArrayList<Transaccion> transaccions) {
        this.transaccions = transaccions;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
