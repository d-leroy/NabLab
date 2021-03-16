/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
package fr.cea.nabla.ui.graalvm.launchconfig

import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.CoreException
import org.eclipse.debug.core.ILaunchConfiguration
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab
import org.eclipse.jface.window.Window
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Group
import org.eclipse.swt.widgets.Text
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog
import org.eclipse.ui.model.WorkbenchLabelProvider

class NablaLaunchConfigurationMainTab extends AbstractLaunchConfigurationTab
{
	public static val SourceFileExtension = 'n'
	public static val GenFileExtension = 'ngen'
	public static val OptionsFileExtension = 'json'
	public static val MoniloggerFileExtension = 'mnlg'
	boolean fDisableUpdate = false

	Text fTxtNFile
	Text fTxtNGenFile
	Text fTxtOptionsFile
	Text fTxtMoniloggerFile
	Text fTxtPythonExec

	override createControl(Composite parent) 
	{
		val topControl = new Composite(parent, SWT.NONE)
		topControl.setLayout(new GridLayout(1, false))

		val grpSource = new Group(topControl, SWT.NONE)
		grpSource.setLayout(new GridLayout(2, false))
		grpSource.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1))
		grpSource.setText("Nablab File")

		fTxtNFile = new Text(grpSource, SWT.BORDER)
		fTxtNFile.addModifyListener([e | if (!fDisableUpdate) updateLaunchConfigurationDialog])
		fTxtNFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1))

		val btnBrowseSource = new Button(grpSource, SWT::NONE)
		btnBrowseSource.addSelectionListener(new NablaFileSelectionAdapter(parent, fTxtNFile))
		btnBrowseSource.setText("Browse...")

		val grpGen = new Group(topControl, SWT.NONE)
		grpGen.setLayout(new GridLayout(2, false))
		grpGen.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1))
		grpGen.setText("Nablagen File")

		fTxtNGenFile = new Text(grpGen, SWT.BORDER)
		fTxtNGenFile.addModifyListener([e | if (!fDisableUpdate) updateLaunchConfigurationDialog])
		fTxtNGenFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1))

		val btnBrowseGen = new Button(grpGen, SWT::NONE)
		btnBrowseGen.addSelectionListener(new NablaGenFileSelectionAdapter(parent, fTxtNGenFile))
		btnBrowseGen.setText("Browse...")

		val grpOptions = new Group(topControl, SWT.NONE)
		grpOptions.setLayout(new GridLayout(2, false))
		grpOptions.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1))
		grpOptions.setText("Options File")

		fTxtOptionsFile = new Text(grpOptions, SWT.BORDER)
		fTxtOptionsFile.addModifyListener([e | if (!fDisableUpdate) updateLaunchConfigurationDialog])
		fTxtOptionsFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1))

		val btnBrowseOptions = new Button(grpOptions, SWT::NONE)
		btnBrowseOptions.addSelectionListener(new NablaOptionsFileSelectionAdapter(parent, fTxtOptionsFile))
		btnBrowseOptions.setText("Browse...")

		val grpMonilogger = new Group(topControl, SWT.NONE)
		grpMonilogger.setLayout(new GridLayout(2, false))
		grpMonilogger.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1))
		grpMonilogger.setText("Monilogger File")

		fTxtMoniloggerFile = new Text(grpMonilogger, SWT.BORDER)
		fTxtMoniloggerFile.addModifyListener([e | if (!fDisableUpdate) updateLaunchConfigurationDialog])
		fTxtMoniloggerFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1))

		val btnBrowseMonilogger = new Button(grpMonilogger, SWT::NONE)
		btnBrowseMonilogger.addSelectionListener(new MoniloggerFileSelectionAdapter(parent, fTxtMoniloggerFile))
		btnBrowseMonilogger.setText("Browse...")
		
		val grpPythonExec = new Group(topControl, SWT.NONE)
		grpPythonExec.setLayout(new GridLayout(2, false))
		grpPythonExec.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1))
		grpPythonExec.setText("Python Executable")

		fTxtPythonExec = new Text(grpPythonExec, SWT.BORDER)
		fTxtPythonExec.addModifyListener([e | if (!fDisableUpdate) updateLaunchConfigurationDialog])
		fTxtPythonExec.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1))

//		val btnBrowsePythonExec = new Button(grpPythonExec, SWT::NONE)
//		btnBrowsePythonExec.addSelectionListener(new MoniloggerFileSelectionAdapter(parent, fTxtPythonExec))
//		btnBrowsePythonExec.setText("Browse...")

		setControl(topControl)
	}
	
	override getName()
	{
		'Global'
	}

	override initializeFrom(ILaunchConfiguration configuration)
	{
		fDisableUpdate = true

		fTxtNFile.text = ''
		fTxtNGenFile.text = ''
		fTxtOptionsFile.text = ''
		fTxtMoniloggerFile.text = ''
		fTxtPythonExec.text = ''

		try
		{
			fTxtNFile.text = configuration.getAttribute(NablaLaunchConstants::N_FILE_LOCATION, '')
			fTxtNGenFile.text = configuration.getAttribute(NablaLaunchConstants::NGEN_FILE_LOCATION, '')
			fTxtOptionsFile.text = configuration.getAttribute(NablaLaunchConstants::JSON_FILE_LOCATION, '')
			val moniloggers = configuration.getAttribute(NablaLaunchConstants::MONILOGGER_FILES_LOCATIONS, newArrayList)
			fTxtMoniloggerFile.text = if (moniloggers.empty) '' else moniloggers.head
			fTxtPythonExec.text = configuration.getAttribute(NablaLaunchConstants::PYTHON_EXEC_LOCATION, '')
		}
		catch (CoreException e)
		{
		}
		fDisableUpdate = false
	}

	override performApply(ILaunchConfigurationWorkingCopy configuration)
	{
		configuration.setAttribute(NablaLaunchConstants::N_FILE_LOCATION, fTxtNFile.text)
		configuration.setAttribute(NablaLaunchConstants::NGEN_FILE_LOCATION, fTxtNGenFile.text)
		configuration.setAttribute(NablaLaunchConstants::JSON_FILE_LOCATION, fTxtOptionsFile.text)
		configuration.setAttribute(NablaLaunchConstants::MONILOGGER_FILES_LOCATIONS, newArrayList(fTxtMoniloggerFile.text))
		configuration.setAttribute(NablaLaunchConstants::PYTHON_EXEC_LOCATION, fTxtPythonExec.text)
	}

	override setDefaults(ILaunchConfigurationWorkingCopy configuration)
	{
		configuration.setAttribute(NablaLaunchConstants::N_FILE_LOCATION, '')
		configuration.setAttribute(NablaLaunchConstants::NGEN_FILE_LOCATION, '')
		configuration.setAttribute(NablaLaunchConstants::JSON_FILE_LOCATION, '')
		configuration.setAttribute(NablaLaunchConstants::MONILOGGER_FILES_LOCATIONS, newArrayList)
		configuration.setAttribute(NablaLaunchConstants::PYTHON_EXEC_LOCATION, '')
	}
}

class NablaFileSelectionAdapter extends SelectionAdapter
{
	val Composite parent
	val Text fTxtFile

	new(Composite parent, Text fTxtFile)
	{
		this.parent = parent
		this.fTxtFile = fTxtFile
	}

	override void widgetSelected(SelectionEvent e)
	{
		val dialog = new ElementTreeSelectionDialog(parent.shell, new WorkbenchLabelProvider, new fr.cea.nabla.ui.launchconfig.SourceFileContentProvider(NablaLaunchConfigurationMainTab::SourceFileExtension))
		dialog.setTitle("Select Nablab File")
		dialog.setMessage("Select the nablab file to execute:")
		dialog.setInput(ResourcesPlugin.workspace.root)
		if (dialog.open == Window.OK)
			fTxtFile.setText((dialog.firstResult as IFile).fullPath.makeRelative.toPortableString)
	}
}

class NablaGenFileSelectionAdapter extends SelectionAdapter
{
	val Composite parent
	val Text fTxtFile

	new(Composite parent, Text fTxtFile)
	{
		this.parent = parent
		this.fTxtFile = fTxtFile
	}

	override void widgetSelected(SelectionEvent e)
	{
		val dialog = new ElementTreeSelectionDialog(parent.shell, new WorkbenchLabelProvider, new fr.cea.nabla.ui.launchconfig.SourceFileContentProvider(NablaLaunchConfigurationMainTab::GenFileExtension))
		dialog.setTitle("Select Nablagen File")
		dialog.setMessage("Select the nablagen file to use for the execution:")
		dialog.setInput(ResourcesPlugin.workspace.root)
		if (dialog.open == Window.OK)
			fTxtFile.setText((dialog.firstResult as IFile).fullPath.makeRelative.toPortableString)
	}
}

class NablaOptionsFileSelectionAdapter extends SelectionAdapter
{
	val Composite parent
	val Text fTxtFile

	new(Composite parent, Text fTxtFile)
	{
		this.parent = parent
		this.fTxtFile = fTxtFile
	}

	override void widgetSelected(SelectionEvent e)
	{
		val dialog = new ElementTreeSelectionDialog(parent.shell, new WorkbenchLabelProvider, new fr.cea.nabla.ui.launchconfig.SourceFileContentProvider(NablaLaunchConfigurationMainTab::OptionsFileExtension))
		dialog.setTitle("Select Options File")
		dialog.setMessage("Select the options file to use for the execution:")
		dialog.setInput(ResourcesPlugin.workspace.root)
		if (dialog.open == Window.OK)
			fTxtFile.setText((dialog.firstResult as IFile).fullPath.makeRelative.toPortableString)
	}
}

class MoniloggerFileSelectionAdapter extends SelectionAdapter
{
	val Composite parent
	val Text fTxtFile

	new(Composite parent, Text fTxtFile)
	{
		this.parent = parent
		this.fTxtFile = fTxtFile
	}

	override void widgetSelected(SelectionEvent e)
	{
		val dialog = new ElementTreeSelectionDialog(parent.shell, new WorkbenchLabelProvider, new fr.cea.nabla.ui.launchconfig.SourceFileContentProvider(NablaLaunchConfigurationMainTab::MoniloggerFileExtension))
		dialog.setTitle("Select Monilogger File")
		dialog.setMessage("Select a monilogger file to add to the execution:")
		dialog.setInput(ResourcesPlugin.workspace.root)
		if (dialog.open == Window.OK)
			fTxtFile.setText((dialog.firstResult as IFile).fullPath.makeRelative.toPortableString)
	}
}
