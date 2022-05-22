package battleship;

public abstract class Ships {
    int length;
    protected Deck[] deckArray;

    Deck[] createDecks(int shipLength) {
        deckArray = new Deck[shipLength];
        for (int i = 0; i < shipLength; i++) {
            deckArray[i] = new Deck();
        }
        //System.out.println("Deck created");
        return deckArray;
    }

    static boolean isSunk(Ships ship) {
        for (int i = 0; i < ship.deckArray.length; i++) {
            if(!ship.deckArray[i].isDamaged) {
                return false;
            }
        }
        return true;
    }
    Deck getDeckByCoordinate(Coordinate coordinate) {
        for (int i = 0; i < deckArray.length; i++) {
            if(deckArray[i].getCoordinate().equals(coordinate)) {
                return deckArray[i];
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return "I'm a ship of type: " + this.getClass();
    }
}

class AircraftCarrier extends Ships {
    AircraftCarrier() {
        super.length = 5;
        super.deckArray = createDecks(super.length);
        System.out.println(super.length);
    }

}

class Battleship  extends Ships {
    Battleship(){
        super.length = 4;
        super.deckArray = createDecks(super.length);
    }

}

class Submarine   extends Ships {
    Submarine(){
        super.length = 3;
        super.deckArray = createDecks(super.length);
    }
}

class Cruiser    extends Ships {
    Cruiser(){
        super.length = 3;
        super.deckArray = createDecks(super.length);
    }
}

class Destroyer     extends Ships {
    Destroyer(){
        super.length = 2;
        super.deckArray = createDecks(super.length);
    }

}

class Deck {

    Coordinate coordinate;
    boolean isDamaged = false;

    public Coordinate getCoordinate() {
        return coordinate;
    }
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public boolean isDamaged() {
        return isDamaged;
    }
    public void setDamaged(boolean damaged) {
        isDamaged = damaged;
    }
}
