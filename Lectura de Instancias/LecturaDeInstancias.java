import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LecturaDeInstancias {

    /**
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        String ruta = "..\\Instancias\\mscp.txt";
        File archivo = new File(ruta);
        FileReader fr = new FileReader(archivo);
        BufferedReader reader = new BufferedReader(fr);

        int Restricciones[][];
        int rows;
        int columns;
        int ContVariables = 0;

        List<String> registro = null;
        String line;

        line = reader.readLine().trim();
        registro = Arrays.asList(line.split(" "));
        rows = Integer.parseInt(registro.get(0));
        columns = Integer.parseInt(registro.get(1));
        // System.out.println(rows);
        // System.out.println(columns);

        int coste[] = new int[columns];
        int restricciones[][] = new int[rows][columns];
        String[] linea;
        String[] Columna;
        String[] Columnas;

        try {

            while (line != "" && ContVariables < columns) {
                // read next line
                line = reader.readLine().trim();
                linea = line.split(" ");
                // System.out.println(Arrays.toString(linea));
                for (int i = 0; i < linea.length; i++) {
                    coste[ContVariables] = Integer.parseInt(linea[i]);
                    ContVariables = ContVariables + 1;
                    // System.out.println(ContVariables);
                }

            }

            ContVariables = 1;
            int Fila = 0;
            while (line != null) {

                line = reader.readLine();
                // System.out.println(line);

                if (line == null) {
                    break;
                } else {
                    line = line.trim();
                    linea = line.split(" ");

                    // System.out.println(Arrays.toString(linea));
                    int CantidadValoresUno = Integer.parseInt(linea[0]);
                    // System.out.println(CantidadValoresUno + " CantidadValoresUno");
                    int ContadorValoresUno = 0;
                    line = reader.readLine();

                    while (line != null && ContadorValoresUno < CantidadValoresUno) {

                        line = line.trim();/*
                                            * System.out.println(line + " Columnas");
                                            */

                        Columnas = line.split(" ");
                        // System.out.println(Arrays.toString(Columnas) + " Columnas");

                        for (int contador = 0; contador < Columnas.length; contador++) {
                            int Columnaas = Integer.parseInt(Columnas[contador]);
                            Columnaas = Columnaas - 1;

                            restricciones[Fila][Columnaas] = 1;
                            ContadorValoresUno = ContadorValoresUno + 1;
                            // System.out.println(ContadorValoresUno);
                        }

                    }
                }
                Fila = Fila + 1;

            }

            reader.close();

            System.out.println("\nCostos ---> " + (Arrays.toString(coste) + "\n"));
            System.out.println("Matriz de restricciones ---> " +
                    (Arrays.deepToString(restricciones)));

        } catch (

        IOException e) {
            // e.printStackTrace();
        }
        // System.out.println("Fin!");
    }

}
