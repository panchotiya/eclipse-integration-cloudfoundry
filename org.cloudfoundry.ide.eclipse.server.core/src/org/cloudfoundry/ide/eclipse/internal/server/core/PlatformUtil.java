/*******************************************************************************
 * Copyright (c) 2013 VMware, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     VMware, Inc. - initial API and implementation
 *******************************************************************************/
package org.cloudfoundry.ide.eclipse.internal.server.core;

import org.eclipse.core.runtime.Platform;

public class PlatformUtil {

	private static String os;

	/**
	 * 
	 * @return OS as defined by Platform
	 * @see Platform
	 */
	public static String getOS() {

		if (os == null) {

			os = Platform.getOS();

			if (os != Platform.OS_MACOSX && os != Platform.OS_LINUX) {
				String osName = System.getProperty("os.name").toLowerCase();
				if (osName != null && osName.startsWith("windows")) {
					os = Platform.OS_WIN32;
				}
			}

		}

		return os;
	}

}
