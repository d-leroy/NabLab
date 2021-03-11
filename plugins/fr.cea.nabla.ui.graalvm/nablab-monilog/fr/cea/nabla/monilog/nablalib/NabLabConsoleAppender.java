package fr.cea.nabla.monilog.nablalib;

import com.google.inject.Inject;

import fr.cea.nabla.generator.NablaGeneratorMessageDispatcher.MessageType;
import fr.cea.nabla.ui.NabLabConsoleFactory;
import org.gemoc.monilog.api.IMoniLogAppender;

public class NabLabConsoleAppender implements IMoniLogAppender {

	@Inject NabLabConsoleFactory consoleFactory;

	@Override
	public void call(String message, Object... args) {
		consoleFactory.printConsole(MessageType.Exec, message);
	}

	@Override
	public int getNbMinArgs() {
		return 0;
	}

	@Override
	public int getNbMaxArgs() {
		return 0;
	}

	@Override
	public Class<?> getArgType(int argIndex) {
		return null;
	}
	
}
