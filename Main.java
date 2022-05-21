package battleship;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Main {
    static boolean isCoordinatesGot = false;

    public static void main(String[] args) {
        Player player1 = new Player(1);
        Player player2 = new Player(2);

        Field field = new Field();
        field.initialize(field.fieldOfCells);
        field.showTheField(field.fieldOfCells);
        Coordinate[] shipCoordinates;
        Coordinate[] whereToShoot = new Coordinate[2];
        for (int i = 0; i < 5; i++) {
            while (!isCoordinatesGot) {
                try {
                    switch (i){
                        case 0:
                            System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
                            break;
                        case 1:
                            System.out.println("Enter the coordinates of the Battleship (4 cells):");
                            break;
                        case 2:
                            System.out.println("Enter the coordinates of the Submarine (3 cells):");
                            break;
                        case 3:
                            System.out.println("Enter the coordinates of the Cruiser (3 cells):");
                            break;
                        case 4:
                            System.out.println("Enter the coordinates of the Destroyer (2 cells):");
                            break;
                        default:
                            System.out.println("Incor input");
                    }
                    shipCoordinates = getCoordinate();
                    System.out.println("Coordinates are got");
                    shipPlacer(shipCoordinates[0], shipCoordinates[1], field, i);
                    System.out.println("Ship placed");
                //System.out.println(shipCoordinates.length);
                }catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                    System.out.println(e);
                }
            }
            field.showTheField(field.fieldOfCells);
            isCoordinatesGot = false;
        }
        System.out.println("The game starts!");
        field.showTheFieldInFog(field.fieldOfCells);
        while(true) {
            while (!isCoordinatesGot) {
                try {
                    whereToShoot = getCoordinate();
                } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {

                    System.out.println("Something goes wrong");
                }
            }
            Gameplay.shoot(field, whereToShoot[0]);
            field.showTheField(field.fieldOfCells);
            isCoordinatesGot = false;
        }
    }



    public static Coordinate[] getCoordinate() throws IllegalArgumentException {
        String inputCoordinates;
        String[] allCoordinatesAsStringArray;
        String[] coordinateAsStringArray;

        char letter;
        int number;
        Scanner scanner = new Scanner(System.in);
        inputCoordinates = scanner.nextLine();


        allCoordinatesAsStringArray = inputCoordinates.split(" ");
        Coordinate[] coordinates = new Coordinate[allCoordinatesAsStringArray.length];
        for (int i = 0; i < allCoordinatesAsStringArray.length; i++) {
            coordinateAsStringArray = allCoordinatesAsStringArray[i].split("(?<=[A-Z])");
            letter = coordinateAsStringArray[0].charAt(0);
            try{
                if(letter > 'J' || letter < 'A') {
                    throw new IllegalArgumentException("Error: Coordinate is out of field!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e);
                return null;
            }
            number = Integer.parseInt(coordinateAsStringArray[1]);
            coordinates[i] = new Coordinate(letter, number);
            try {
                if(number > 10 || number < 1) {
                    throw new IllegalArgumentException("Error: Coordinate is out of field!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e);
                return null;
            }

        }
        try {
            if(coordinates.length > 2) {
                throw new IllegalArgumentException("Too much points for 2D coordinates");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return null;
        }
        if(coordinates.length == 2) {
            if (coordinates[0].getyPos() > coordinates[1].getyPos() || coordinates[0].getxPos() > coordinates[1].getxPos()) {
                Coordinate temp = coordinates[0];
                coordinates[0] = coordinates[1];
                coordinates[1] = temp;
            }
        }
        isCoordinatesGot = true;
        System.out.println(Arrays.toString(coordinates));
        return coordinates;
    }

    public static void shipPlacer(Coordinate startPos, Coordinate finishPos, Field field,int theOrderOfShips) {

        switch (theOrderOfShips){
            case 0:
                field.allShips[theOrderOfShips] = new AircraftCarrier();
               // allShips[theOrderOfShips] = ship;
                System.out.println("ship created");
                break;
            case 1:
                field.allShips[theOrderOfShips] = new Battleship();
                break;
            case 2:
                field.allShips[theOrderOfShips] = new Submarine();
                break;
            case 3:
                field.allShips[theOrderOfShips] = new Cruiser();
                break;
            case 4:
                field.allShips[theOrderOfShips] = new Destroyer();
                break;
            default:
                System.out.println("Incor input");
        }


        try {
        if(startPos.getxPos() != finishPos.getxPos() && startPos.getyPos() != finishPos.getyPos()) {
           throw new IllegalArgumentException("Error! Wrong ship location! Try again:");
        }
        } catch (IllegalArgumentException e) {
            System.out.println("Error! Wrong ship location! Try again:");
            isCoordinatesGot = false;
            return;
        }

        try {
            if(finishPos.getxPos() - startPos.getxPos() + 1 != field.allShips[theOrderOfShips].length && finishPos.getyPos()
                    - startPos.getyPos() + 1 != field.allShips[theOrderOfShips].length) {
                throw new IllegalArgumentException("Error! Wrong length! Try again:");

            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error! Wrong length! Try again:");
            isCoordinatesGot = false;
            return;
        }



        // check the empty cells around

        for(int i = startPos.getyPos() - 65; i <= finishPos.getyPos() - 63; i++ ) {
            for(int j = startPos.getxPos() - 1; j <= finishPos.getxPos() + 1; j++) {
                try {
                    if(!field.fieldOfCells[i][j].isEmpty()) {
                        throw new IllegalArgumentException("Error! Too close");
                    }
                } catch (IllegalArgumentException e){
                    System.out.println("Error! Too close");
                    isCoordinatesGot = false;
                    return;

                }
            }
        }




        // for vertical ships
        if (startPos.getxPos() == finishPos.getxPos()) {
            //System.out.println("Verti");
            if(abs(startPos.getyPos() - finishPos.getyPos()) == (field.allShips[theOrderOfShips].length - 1)) {

                if((startPos.getyPos() - finishPos.getyPos()) < 0) {
                    for (int i = 0;i <= abs(startPos.getyPos() - finishPos.getyPos());i++) {
                        field.fieldOfCells[startPos.getyPos() - 64 + i][startPos.getxPos()].setEmpty(false);
                        field.allShips[theOrderOfShips].deckArray[i].setCoordinate(field.fieldOfCells[startPos.getyPos() - 64 + i][startPos.getxPos()].getCoordinate());
                        field.fieldOfCells[startPos.getyPos() - 64 + i][startPos.getxPos()].setShip(field.allShips[theOrderOfShips]);
                        field.fieldOfCells[startPos.getyPos() - 64 + i][startPos.getxPos()].setStringRepresentation("O");
                        System.out.println(field.allShips[theOrderOfShips]);
                    }
                }


            }
        }

        // for horizontal ships
        if (startPos.getyPos() == finishPos.getyPos()) { // it checks the orientation
            if(abs(startPos.getxPos() - finishPos.getxPos()) == ( field.allShips[theOrderOfShips].length - 1)) { //it checks the correctness of ship's length

                //code below places the ship
                    for (int i = 0;i <= abs(startPos.getxPos() - finishPos.getxPos());i++) { //for each cell of ship
                        System.out.printf("it is cell %d %d",startPos.getyPos() - 64,startPos.getxPos() + i);
                        field.fieldOfCells[startPos.getyPos() - 64][startPos.getxPos() + i].setEmpty(false);
                        field.allShips[theOrderOfShips].deckArray[i].setCoordinate(field.fieldOfCells[startPos.getyPos() - 64][startPos.getxPos() + i].getCoordinate());

                        System.out.println( field.allShips[theOrderOfShips].deckArray[i].getCoordinate());
                        field.fieldOfCells[startPos.getyPos() - 64][startPos.getxPos() + i].setShip(field.allShips[theOrderOfShips]);
                        field.fieldOfCells[startPos.getyPos() - 64][startPos.getxPos() + i].setStringRepresentation("O");
                        System.out.println( field.allShips[theOrderOfShips]);
                    }
                //field.showTheFieldOfCoordintaes(field.fieldOfCells);


            }
        }


    }

}

