package battleship;


import java.util.Objects;

class Field {
    public Cell[][] fieldOfCells = new Cell[12][12];
    Ships[] allShips = new Ships[5];

    void initialize() {
        // everyCell
        for (int i = 0; i < fieldOfCells.length; i++) {
            for (int j = 0; j < fieldOfCells[0].length ; j++) {
                fieldOfCells[i][j] = new Cell(new Coordinate((char) ('@' + i),j));
            }
        }
        //the first line
        fieldOfCells[0][0].setStringRepresentation(" ");
        for (int i = 1; i < fieldOfCells[0].length - 1; i++) {
            fieldOfCells[0][i].setStringRepresentation(String.valueOf(i));
            fieldOfCells[0][i].setStringRepresentationInFog(String.valueOf(i));
        }
        // the first column
       // System.out.println();
        for (int i = 1; i < fieldOfCells.length - 1; i++) {
            fieldOfCells[i][0].setStringRepresentation(String.valueOf((char) ('@' + i)) );
            fieldOfCells[i][0].setStringRepresentationInFog(String.valueOf((char) ('@' + i)) );
        }
        // fill the ~ char
        for (int i = 1; i < fieldOfCells.length - 1; i++) {
            for (int j = 1; j < fieldOfCells[0].length - 1; j++) {
                fieldOfCells[i][j].setStringRepresentation("~");
                fieldOfCells[i][j].setStringRepresentationInFog("~");
            }
        }
    }

    void showTheField() {
        for (int i = 0; i < fieldOfCells.length -1; i++) {
            for (int j = 0; j < fieldOfCells.length; j++) {
                    System.out.print(fieldOfCells[i][j] + " ");
            }
            //if(i < fieldOfCells.length - 1) {
                System.out.print("\n");
           // }
        }

    }

    void showTheFieldOfCoordintaes(Cell[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                System.out.print(field[i][j].coordinate + " ");
            }
            System.out.print("\n");
        }
    }

    void showTheFieldInFog() {
        for (int i = 0; i < fieldOfCells.length - 1; i++) {
            for (int j = 0; j < fieldOfCells.length; j++) {
                System.out.print(fieldOfCells[i][j].stringRepresentationInFog + " ");
            }
           // if(i < fieldOfCells.length - 1) {
                System.out.print("\n");
           // }
        }
    }

    class Cell {
        String stringRepresentation = " ";
        String stringRepresentationInFog = " ";
        Coordinate coordinate;
        Ships ship;
        boolean isEmpty = true;

        Cell(Coordinate coordinate) {
            this.coordinate = coordinate;
        }
        Cell(Coordinate coordinate, String stringRepresentation) {
            this.coordinate = coordinate;
            this.stringRepresentation = stringRepresentation;
            this.stringRepresentationInFog = stringRepresentation;
        }


        @Override
        public String toString() {
            return String.valueOf(stringRepresentation);
        }

        public String getStringRepresentation() {
            return stringRepresentation;
        }

        public void setStringRepresentation(String stringRepresentation) {
            this.stringRepresentation = stringRepresentation;
        }

        public Coordinate getCoordinate() {
            return coordinate;
        }

        public void setCoordinate(Coordinate coordinate) {
            this.coordinate = coordinate;
        }
        public boolean isEmpty() {
            return isEmpty;
        }

        public void setEmpty(boolean empty) {
            isEmpty = empty;
        }

        public String getStringRepresentationInFog() {
            return stringRepresentationInFog;
        }

        public void setStringRepresentationInFog(String stringRepresentationInFog) {
            this.stringRepresentationInFog = stringRepresentationInFog;
        }
        public Ships getShip() {
            return ship;
        }

        public void setShip(Ships ship) {
            this.ship = ship;
        }

    }
}

class Coordinate {
    char yPos;
    int xPos;


    Coordinate(char yPos, int xPos){
        this.yPos = yPos;
        this.xPos = xPos;
    }

    public char getyPos() {
        return yPos;
    }

    public void setyPos(char yPos) {
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    @Override
    public String toString() {
        return "y " + yPos + ", x " + xPos;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Coordinate other = (Coordinate) obj;
        if (!Objects.equals(this.xPos, other.xPos)) {
            return false;
        }

        if (!Objects.equals(this.yPos, other.yPos)) {
            return false;
        }

        return true;
    }
}



