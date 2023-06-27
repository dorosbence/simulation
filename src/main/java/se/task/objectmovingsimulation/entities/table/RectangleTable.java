package se.task.objectmovingsimulation.entities.table;

public class RectangleTable extends Table {
    public RectangleTable(int width, int height) {
        super();
        createTable(width, height);
    }

    @Override
    public void createTable(int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.fieldList.add(new Field(j, i));
            }
        }
    }

}
