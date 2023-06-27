package se.task.objectmovingsimulation.entities.table;

public class CircleTable extends Table {

    public CircleTable(int width, int height) {
        super();
        createTable(width, height);
    }

    @Override
    void createTable(int width, int height) {
        //code here for create the circle, but for avoiding the crash, it's the same as the square's
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.fieldList.add(new Field(j, i));
            }
        }
    }
}
