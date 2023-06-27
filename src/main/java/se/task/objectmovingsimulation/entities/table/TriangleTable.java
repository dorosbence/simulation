package se.task.objectmovingsimulation.entities.table;

public class TriangleTable extends Table {
    public TriangleTable(int width, int height) {
        super();
        createTable(width, height);
    }

    @Override
    void createTable(int width, int height) {
        //code here for create the table, but avoiding the crash it's an isoceles triangle
        for (int i = width - 1; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                this.fieldList.add(new Field(j, width - 1 - i));
            }
        }
    }

}
