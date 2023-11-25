import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    private static final String FILE = "src/auxFile.txt";

    //reads a CSV file and return a planning matrix
    static int[][] createPlanningMatrixViaFile() throws FileNotFoundException {
        Scanner in = new Scanner(new File(Main.FILE));

        in.nextLine();
        int[][] planningMatrix = new int[in.nextInt()][in.nextInt()];

        for (int i = 0; i < planningMatrix.length; i++) {
            for (int j = 0; j < planningMatrix[i].length; j++) {
                planningMatrix[i][j] = in.nextInt();
            }
        }
        in.close();
        return planningMatrix;
    }

    //create cities matrix.
    static int[][] createCityMatrix(){
        //City codes:
        //0 = Porto
        //1 = Aveiro
        //2 = Braga
        //3 = Coimbra
        //4 = Lisboa
        //5 = Fátima
        int[][] cidadesEdistancias = new int[6][6];
        cidadesEdistancias[0][0] = 0;
        cidadesEdistancias[0][1] = 50;
        cidadesEdistancias[0][2] = 60;
        cidadesEdistancias[0][3] = 130;
        cidadesEdistancias[0][4] = 300;
        cidadesEdistancias[0][5] = 200;
        cidadesEdistancias[1][0] = cidadesEdistancias[0][1];
        cidadesEdistancias[1][1] = 0;
        cidadesEdistancias[1][2] = 130;
        cidadesEdistancias[1][3] = 70;
        cidadesEdistancias[1][4] = 250;
        cidadesEdistancias[1][5] = 140;
        cidadesEdistancias[2][0] = 60;
        cidadesEdistancias[2][1] = 130;
        cidadesEdistancias[2][2] = 0;
        cidadesEdistancias[2][3] = 180;
        cidadesEdistancias[2][4] = 370;
        cidadesEdistancias[2][5] = 250;
        cidadesEdistancias[3][0] = 130;
        cidadesEdistancias[3][1] = 70;
        cidadesEdistancias[3][2] = 180;
        cidadesEdistancias[3][3] = 0;
        cidadesEdistancias[3][4] = 200;
        cidadesEdistancias[3][5] = 90;
        cidadesEdistancias[4][0] = 300;
        cidadesEdistancias[4][1] = 250;
        cidadesEdistancias[4][2] = 370;
        cidadesEdistancias[4][3] = 200;
        cidadesEdistancias[4][4] = 0;
        cidadesEdistancias[4][5] = 130;
        cidadesEdistancias[5][0] = 200;
        cidadesEdistancias[5][1] = 140;
        cidadesEdistancias[5][2] = 250;
        cidadesEdistancias[5][3] = 90;
        cidadesEdistancias[5][4] = 130;
        cidadesEdistancias[5][5] = 0;

        return cidadesEdistancias;
    }


    //prints the generic matrix specified.
    static void printMatrix(int[][] matrix){
        for (int[] line: matrix) {
            for(int element: line){
                System.out.printf("%4d", element);
            }
            System.out.println(" ");
        }
    }

    static void printBusPlanMatrix(int[][] matrix){
        int busIdentificationNumber = 0;
        for (int[] line: matrix) {
            System.out.print("Bus" + busIdentificationNumber + " : ");
            for(int element: line){
                System.out.printf("%4d", element);
            }
            busIdentificationNumber++;
            System.out.println(" ");
        }
    }

    static int[][] showingDistanceBetweenCities(int[][] busRoutes, int[][] citiesMap) {
        int[][] busRouteDistances = new int[busRoutes.length][busRoutes[0].length];

        for (int bus = 0; bus < busRoutes.length; bus++) {
            for (int stop = 0; stop < busRoutes[bus].length; stop++) {
                if (stop == 0) {
                    busRouteDistances[bus][stop] = citiesMap[busRoutes[bus][stop]][busRoutes[bus][stop]];

                } else if (busRoutes[bus][stop] != busRoutes[bus][stop - 1]) {
                    busRouteDistances[bus][stop] = citiesMap[busRoutes[bus][stop - 1]][busRoutes[bus][stop]];

                } else if (busRoutes[bus][stop] == busRoutes[bus][stop - 1]) {
                    busRouteDistances[bus][stop] = citiesMap[busRoutes[bus][stop - 1]][busRoutes[bus][stop]];
                }
            }
        }
        return busRouteDistances;
    }

    static int[] calculateTotalRouteDistance(int[][] busRoutesByDistance){
        int[] totalDistancesByBus = new int[busRoutesByDistance.length];

        for (int i = 0; i < totalDistancesByBus.length; i++) {
            for (int j = 0; j < busRoutesByDistance[i].length; j++) {
                totalDistancesByBus[i] += busRoutesByDistance[i][j];
            }
        }
        return totalDistancesByBus;
    }

    static void printUnidimensionalArrayForBuses(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.println("bus" + i +": "+ array[i] + "km");

        }
    }

    static int calculateTotalDistanceTraveledByTheFleet(int[] totalDistancesByBus){
        int totalDistanceTraveledBytheFleet = 0;
        for (int distancesByBus : totalDistancesByBus) {
            totalDistanceTraveledBytheFleet += distancesByBus;
        }

        return totalDistanceTraveledBytheFleet;
    }

    static int returnTheLongestDistanceTraveledBytheFleetDay(int[][] distanceTraveledInEachDay){
        int biggestTotalDistance = 0;

        for (int i = 0; i < distanceTraveledInEachDay[0].length ; i++ ){
            int totalDistance = 0;
            for (int[] ints : distanceTraveledInEachDay) {
                totalDistance += ints[i];
            }
            if (biggestTotalDistance < totalDistance){
                biggestTotalDistance = totalDistance;
            }
        }
        return biggestTotalDistance;
    }
    static int returnDayThatFleetTraveledTheMost(int[][] distanceTraveledInEachDay){
        int day = 0;
        int biggestTotalDistance = 0;

        for (int i = 0; i < distanceTraveledInEachDay[0].length ; i++ ){
            int totalDistance = 0;
            for (int[] ints : distanceTraveledInEachDay) {
                totalDistance += ints[i];
            }
            if (biggestTotalDistance < totalDistance){
                biggestTotalDistance = totalDistance;
                day = i;
            }
        }
        return day;
    }

    public static void main(String[] args) throws FileNotFoundException {
        int[][] cities = createCityMatrix();
        int[][] busRoute = createPlanningMatrixViaFile();

        printMatrix(cities);

        System.out.println("B)");
        printBusPlanMatrix(busRoute);

        System.out.println("C)");
        int[][] busRoutesBydistance = showingDistanceBetweenCities(busRoute,cities);
        printBusPlanMatrix(busRoutesBydistance
        );

        System.out.println("D)");
        int[] totalDistanceTraveledByEachBus = calculateTotalRouteDistance(busRoutesBydistance);
        printUnidimensionalArrayForBuses(totalDistanceTraveledByEachBus);

        System.out.println("E)");
        System.out.println("Total de km a percorrer pela frota = " + calculateTotalDistanceTraveledByTheFleet(totalDistanceTraveledByEachBus)+ "km");

        System.out.println("F)");
        System.out.println("máximo de kms num dia: (" + (returnTheLongestDistanceTraveledBytheFleetDay(busRoutesBydistance)+") , dia: "
                                                             + returnDayThatFleetTraveledTheMost(busRoutesBydistance)));
    }
}