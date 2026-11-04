// busca el x para el que la integral de la distribucion t de 0 a x da p
public class SearchX {

    public double buscarX(double p, int dof) {
        Logic logic = new Logic();

        double x = 1.0;
        double d = 0.5;
        double dblE = 0.00001;

        double error = logic.integrar(x, dof) - p;

        // si la integral queda corta se suma d, si se pasa se resta d;
        // cuando el error cambia de signo, d se parte a la mitad
        while (Math.abs(error) >= dblE) {
            if (error < 0) {
                x = x + d;
            } else {
                x = x - d;
            }

            double nuevoError = logic.integrar(x, dof) - p;

            if ((error < 0 && nuevoError > 0)
                    || (error > 0 && nuevoError < 0)) {
                d = d / 2;
            }

            error = nuevoError;
        }

        return x;
    }
}
