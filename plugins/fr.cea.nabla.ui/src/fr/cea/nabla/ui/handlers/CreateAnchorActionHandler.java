package fr.cea.nabla.ui.handlers;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.xtext.resource.EObjectAtOffsetHelper;
import org.eclipse.xtext.resource.ILocationInFileProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.outline.quickoutline.QuickOutlinePopup;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.inject.Inject;
import com.google.inject.Injector;

public class CreateAnchorActionHandler extends AbstractHandler {
	
	@Inject
	private Injector injector;
	
	@Inject
	private ILocationInFileProvider locationProvider;
	
	@Inject
	private EObjectAtOffsetHelper objectAtOffsetHelper;
	
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final XtextEditor xtextEditor = EditorUtils.getActiveXtextEditor(event);
		if (xtextEditor != null) {
			final IXtextDocument document = xtextEditor.getDocument();
			final ITextSelection selection = (ITextSelection) xtextEditor.getSelectionProvider().getSelection();
			final int offset = selection.getOffset();
			final int length = selection.getLength();
			document.priorityReadOnly(new IUnitOfWork.Void<XtextResource>()  {
				@Override
				public void process(XtextResource state) throws Exception {
					final Set<EObject> objects = new HashSet<>(); 
					for (int i = offset; i < offset + length; i++) {
						objects.add(objectAtOffsetHelper.resolveElementAt(state, offset));
					}
				}
			});
		}
		return null;
	}
	
	/**
	 * @since 2.2
	 */
	protected QuickOutlinePopup createPopup(Shell parent) {
		QuickOutlinePopup result = new QuickOutlinePopup(parent);
		injector.injectMembers(result);
		return result;
	}

}
