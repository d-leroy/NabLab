/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
package fr.cea.nabla.ui.launchconfig

import org.eclipse.debug.core.ILaunchConfiguration
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.Path

class NablaLaunchConstants {
	package static val LAUNCH_CONFIGURATION_TYPE_ID = 'fr.cea.nabla.ui.launchconfig.NablaLaunchConfigurationType'
	package static val GRAAL_HOME_LOCATION = 'GraalVM Home Location'
	package static val SOURCE_FILE_LOCATION = 'Source File Location'
	package static val GEN_FILE_LOCATION = 'Gen File Location'
	package static val OPTIONS_FILE_LOCATION = 'Options File Location'
	package static val MONILOGGER_FILES_LOCATIONS = 'Monilogger Files Locations'
	
	package static def getSourceFile(ILaunchConfiguration configuration)
	{
		val file = ResourcesPlugin.workspace.root.getFile(new Path(configuration.getAttribute(NablaLaunchConstants::SOURCE_FILE_LOCATION, '')))
		if (file.exists) {
			return file
		} else {
			return null
		}
	}

}
