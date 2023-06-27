package se.task.objectmovingsimulation.entities.table;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class Table {
    @Getter
    final protected List<Field> fieldList;

    public Table() {
        this.fieldList = new ArrayList<>();
    }

    abstract void createTable(int width, int height);

    public boolean isFieldOnTable(int positionX, int positionY) {
        return fieldList.stream().noneMatch(field -> field.positionX == positionX && field.positionY == positionY);
    }

    @AllArgsConstructor
    protected static class Field {

        @Getter
        private int positionX;

        @Getter
        private int positionY;
    }
}
