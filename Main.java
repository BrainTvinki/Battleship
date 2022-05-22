package battleship;

import java.util.Arrays;
import java.util.Scanner;


import static java.lang.Math.abs;

public class Main {
    static boolean isCoordinatesGot = false;
    static Player currentPlayer;

    public static void main(String[] args) {

        Player player1 = new Player(1);
        Player player2 = new Player(2);
        Gameplay gameplay = new Gameplay();

        Scanner scanner = new Scanner(System.in);


        System.out.println("Player 1, place your ships on the game field");
        gameplay.placeShipsBeforeStart(player1);
        System.out.println("Press Enter and pass the move to another player");
        System.out.println("...");
        System.out.println("Player 2, place your ships on the game field");
        //clearTheScreen(scanner.next().charAt(0));
        gameplay.placeShipsBeforeStart(player2);



        Coordinate[] whereToShoot = new Coordinate[2];

        System.out.println("Press Enter and pass the move to another player\n" +
                "...\n");
        player1.setFieldFoeShips(player2.getFieldOwnShips());
        player2.setFieldFoeShips(player1.getFieldOwnShips());
        while (true) {

        currentPlayer = player1;
        gameplay.turnForOnePlayer(currentPlayer);

        currentPlayer = player2;
        gameplay.turnForOnePlayer(currentPlayer);

        }

    }

    private static void clearTheScreen (char enterCode) {
        if(enterCode == 10) {
            for(int clear = 0; clear < 50; clear++)
            {
                System.out.println("\n") ;
            }
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

            try {
                letter = coordinateAsStringArray[0].charAt(0);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Error: Coordinate is incorrect");
                return null;
            }

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
        //System.out.println(Arrays.toString(coordinates));
        return coordinates;
    }

    public static void shipPlacer(Coordinate startPos, Coordinate finishPos, Field field,int theOrderOfShips) {

        switch (theOrderOfShips){
            case 0:
                field.allShips[theOrderOfShips] = new AircraftCarrier();
               // allShips[theOrderOfShips] = ship;
                //System.out.println("ship created");
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
                        //System.out.println(field.allShips[theOrderOfShips]);
                    }
                }


            }
        }

        // for horizontal ships
        if (startPos.getyPos() == finishPos.getyPos()) { // it checks the orientation
            if(abs(startPos.getxPos() - finishPos.getxPos()) == ( field.allShips[theOrderOfShips].length - 1)) { //it checks the correctness of ship's length

                //code below places the ship
                    for (int i = 0;i <= abs(startPos.getxPos() - finishPos.getxPos());i++) { //for each cell of ship
                        //System.out.printf("it is cell %d %d",startPos.getyPos() - 64,startPos.getxPos() + i);
                        field.fieldOfCells[startPos.getyPos() - 64][startPos.getxPos() + i].setEmpty(false);
                        field.allShips[theOrderOfShips].deckArray[i].setCoordinate(field.fieldOfCells[startPos.getyPos() - 64][startPos.getxPos() + i].getCoordinate());

                        //System.out.println( field.allShips[theOrderOfShips].deckArray[i].getCoordinate());
                        field.fieldOfCells[startPos.getyPos() - 64][startPos.getxPos() + i].setShip(field.allShips[theOrderOfShips]);
                        field.fieldOfCells[startPos.getyPos() - 64][startPos.getxPos() + i].setStringRepresentation("O");
                        //System.out.println( field.allShips[theOrderOfShips]);
                    }
                //field.showTheFieldOfCoordintaes(field.fieldOfCells);


            }
        }


    }

}

