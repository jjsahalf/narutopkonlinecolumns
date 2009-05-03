package tetris.listener;

import tetris.entities.Shape;
import tetris.entities.ShapeForReplay;

public interface ShapeListener {

	public void shapeMoveDown(Shape shape);

	public boolean isShapeMoveDownable(Shape shape);

        public boolean isShapeMoveDownable(ShapeForReplay shapeForReplay);

        public void shapeMoveDown(ShapeForReplay shapeForReplay);

}
