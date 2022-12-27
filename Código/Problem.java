public class Problem {

        static int coste[] = { 65, 55, 60, 45, 40, 50 };

        public static int nVariables = coste.length;

        public static int restricciones[][] = {
                        { 1, 1, 0, 0, 0, 0 },
                        { 1, 1, 0, 0, 0, 1 },
                        { 0, 0, 1, 1, 1, 0 },
                        { 0, 0, 0, 1, 0, 1 },
                        { 0, 1, 0, 0, 1, 1 }
        };

}