package battleship;

public class Player {

    String name;
    int number;
    Field fieldOwnShips;
    Field fieldFoeShips;

    Player (int number){
        this.name = "Player " + number;
        this.number = number;
    }

    public Field getFieldOwnShips() {
        return fieldOwnShips;
    }

    public void setFieldOwnShips(Field fieldOwnShips) {
        this.fieldOwnShips = fieldOwnShips;
    }

    public Field getFieldFoeShips() {
        return fieldFoeShips;
    }

    public void setFieldFoeShips(Field fieldFoeShips) {
        this.fieldFoeShips = fieldFoeShips;
    }

    public String getName() { return name; }

}

