package tetris.listener;

import tetris.entities.Shape;

public interface ShapeListener {

	public void shapeMoveDown(Shape shape);

	public boolean isShapeMoveDownable(Shape shape);

}
