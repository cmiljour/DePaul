package view.gui;

import view.interfaces.ICommand;

import java.io.IOException;

public class UndoCommand implements ICommand {

	@Override
	public void run() {
		CommandHistory.undo();
	}

}
