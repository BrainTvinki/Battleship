package battleship;


public class Gameplay {

    public static void shoot(Field field, Coordinate whereToShot) {

        if(field.fieldOfCells[whereToShot.getyPos()-64][whereToShot.getxPos()].isEmpty) {
            field.fieldOfCells[whereToShot.getyPos()-64][whereToShot.getxPos()].setStringRepresentation("M");
            field.fieldOfCells[whereToShot.getyPos()-64][whereToShot.getxPos()].setStringRepresentationInFog("M");
            field.showTheFieldInFog(field.fieldOfCells);
            System.out.println("You missed!");
        }
        if(!field.fieldOfCells[whereToShot.getyPos()-64][whereToShot.getxPos()].isEmpty) {
            field.fieldOfCells[whereToShot.getyPos()-64][whereToShot.getxPos()].setStringRepresentation("X");
            field.fieldOfCells[whereToShot.getyPos()-64][whereToShot.getxPos()].setStringRepresentationInFog("X");
            field.fieldOfCells[whereToShot.getyPos()-64][whereToShot.getxPos()].getShip().getDeckByCoordinate(whereToShot).setDamaged(true);
            field.showTheFieldInFog(field.fieldOfCells);
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

    public static Field placeShipsBeforeStart(Player player) {
        player.setFieldOwnShips(new Field());
        player.getFieldOwnShips().initialize(field.fieldOfCells);
        field.showTheField(field.fieldOfCells);
        Coordinate[] shipCoordinates;
    }

}
