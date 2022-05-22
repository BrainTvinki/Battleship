package battleship;


public class Gameplay {

    public static void shoot(Field field, Coordinate whereToShot) {

        if(field.fieldOfCells[whereToShot.getyPos()-64][whereToShot.getxPos()].isEmpty) {
            field.fieldOfCells[whereToShot.getyPos()-64][whereToShot.getxPos()].setStringRepresentation("M");
            field.fieldOfCells[whereToShot.getyPos()-64][whereToShot.getxPos()].setStringRepresentationInFog("M");
            field.showTheFieldInFog();
            System.out.println("You missed!");
        }
        if(!field.fieldOfCells[whereToShot.getyPos()-64][whereToShot.getxPos()].isEmpty) {
            field.fieldOfCells[whereToShot.getyPos()-64][whereToShot.getxPos()].setStringRepresentation("X");
            field.fieldOfCells[whereToShot.getyPos()-64][whereToShot.getxPos()].setStringRepresentationInFog("X");
            field.fieldOfCells[whereToShot.getyPos()-64][whereToShot.getxPos()].getShip().getDeckByCoordinate(whereToShot).setDamaged(true);
            field.showTheFieldInFog();
            if(isAllShipsSunk(field.allShips)){
                System.out.println("You sank the last ship. You won. Congratulations!");
                System.exit(0);
            }
            if(Ships.isSunk(field.fieldOfCells[whereToShot.getyPos()-64][whereToShot.getxPos()].getShip())) {
                System.out.println("You sank a ship! Specify a new target:");
            } else {
                System.out.println("You hit a ship!");
            }
        }
    }
    public static boolean isAllShipsSunk (Ships[] arrayOfShips) {
        for(Ships ship : arrayOfShips) {
            if(!Ships.isSunk(ship)) {
                return false;
            }
        }
        return true;
    }

    public void placeShipsBeforeStart(Player player) {
        player.setFieldOwnShips(new Field());
        player.getFieldOwnShips().initialize();
        player.getFieldOwnShips().showTheField();

        Coordinate[] shipCoordinates;

        for (int i = 0; i < 5; i++) {
            while (!Main.isCoordinatesGot) {
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
                    shipCoordinates = Main.getCoordinate();
                    //System.out.println("Coordinates are got");
                    Main.shipPlacer(shipCoordinates[0], shipCoordinates[1], player.fieldOwnShips, i);
                    //System.out.println("Ship placed");
                    //System.out.println(shipCoordinates.length);
                }catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                    System.out.println(e);
                }
            }
            player.fieldOwnShips.showTheField();
            Main.isCoordinatesGot = false;
        }
    }

    public void turnForOnePlayer (Player player) {
        player.getFieldFoeShips().showTheFieldInFog();
        //System.out.println(" ");
        System.out.print("---------------------\n");
        System.out.println();
        player.getFieldOwnShips().showTheField();
        System.out.println();
        System.out.println(player.getName() + ", it's your turn:");
        System.out.println();

        Coordinate[] whereToShoot = new Coordinate[2];


        while (!Main.isCoordinatesGot) {
            try {
                whereToShoot = Main.getCoordinate();
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                System.out.println("Something goes wrong");
            }
        }
        Gameplay.shoot(player.getFieldFoeShips(), whereToShoot[0]);
        Main.isCoordinatesGot = false;

    }

}
