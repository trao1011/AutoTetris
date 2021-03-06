package autotetris.view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;

import autotetris.model.Cell;
import autotetris.model.Tetromino;
import autotetris.model.TetrominoPrototype;

public class NextPiecePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int PADDING = 3;
	private Application app;

	public NextPiecePanel(Application app) {
		this.app = app;
	}

	public void paintComponent(Graphics g) {
		TetrominoPrototype tp = app.model.getNextPiece();

		// Make a fake tetromino
		Tetromino t = new Tetromino(tp, new Cell(1, 1));


		if (tp != null) {
			// find square
			Dimension size = this.getSize();
			int xoff = (size.width > size.height ? (size.width - size.height) / 2 : 0),
					yoff = (size.height > size.width ? (size.height - size.width) / 2 : 0),
					totalSize = Math.min(size.width - 2 * PADDING, size.height - 2 * PADDING),
					squareSize = (totalSize - tp.getOrientations()[0].length + 1) / tp.getOrientations()[0].length;
			g.setColor(Application.BACKGROUND_COLOR);
			g.fillRect(xoff, yoff, (squareSize + 1) * tp.getOrientations()[0].length + 2 * PADDING - 1,
									(squareSize + 1) * tp.getOrientations()[0].length + 2 * PADDING - 1);
			g.setColor(tp.color);
			for(Cell c : t.project()) {
				int y = c.row;
				int x = c.col;
				g.fillRect(PADDING + xoff + (squareSize + 1) * x, PADDING + yoff + (squareSize + 1) * y, squareSize, squareSize);
			}
		}
	}

}
