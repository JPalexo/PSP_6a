// arma cada prueba de la Tabla 1: busca el x que da el area p con la
// distribucion t y escribe el resultado
public class Logic {

    private int intNumSeg;
    private double dblE;
    private int intDOF;
    private double dblX;

    public void logic1a(int prueba) {
        Output output = new Output();
        SearchX search = new SearchX();

        double[] valoresP = {0.20, 0.45, 0.495};
        int[] valoresDOF = {6, 15, 4};

        double p = valoresP[prueba - 1];
        int dof = valoresDOF[prueba - 1];
        double x = search.buscarX(p, dof);

        String resultado = String.format("Test %d  p=%.3f  dof=%d  x=%.5f",
                prueba, p, dof, x);

        System.out.println(resultado);
        output.writeData("data/out_test" + prueba + ".txt", resultado + "\n");
    }

    // duplica los segmentos hasta que dos resultados seguidos difieran
    // menos que el error aceptable
    public double integrar(double x, int dof) {
        dblX = x;
        intDOF = dof;
        intNumSeg = 10;
        dblE = 0.00001;

        double anterior = calcularPasada();
        intNumSeg = intNumSeg * 2;
        double actual = calcularPasada();

        while (Math.abs(actual - anterior) > dblE) {
            anterior = actual;
            intNumSeg = intNumSeg * 2;
            actual = calcularPasada();
        }

        return actual;
    }

    // una pasada de Simpson con los segmentos actuales
    private double calcularPasada() {
        SimpsonIntegration simpson = new SimpsonIntegration();

        simpson.computeW(intNumSeg, dblX);
        double[] xi = simpson.computeXi(intNumSeg);
        simpson.computeFirstBaseTerms(intNumSeg, xi, intDOF);
        simpson.computeExponent(intDOF);
        simpson.computeCoefficient(intDOF);
        simpson.computeFxi(intNumSeg);
        simpson.computeFinalTerms(intNumSeg);

        return simpson.computeFinalValue(intNumSeg);
    }
}
