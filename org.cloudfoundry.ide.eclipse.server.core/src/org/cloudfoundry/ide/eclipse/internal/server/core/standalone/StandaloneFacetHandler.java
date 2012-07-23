/*******************************************************************************
 * Copyright (c) 2012 VMware, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     VMware, Inc. - initial API and implementation
 *******************************************************************************/
package org.cloudfoundry.ide.eclipse.internal.server.core.standalone;

import org.cloudfoundry.ide.eclipse.internal.server.core.CloudFoundryPlugin;
import org.cloudfoundry.ide.eclipse.internal.server.core.CloudFoundryProjectUtil;
import org.cloudfoundry.ide.eclipse.internal.server.core.CloudFoundryServer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class StandaloneFacetHandler {

	private final IProject project;

	public StandaloneFacetHandler(IProject project) {
		this.project = project;
	}

	public static final IProjectFacet FACET = ProjectFacetsManager
			.getProjectFacet(CloudFoundryServer.ID_JAVA_STANDALONE_APP);

	public boolean hasFacet() {
		try {
			IFacetedProject facetedProject = ProjectFacetsManager.create(project);
			return facetedProject != null && facetedProject.hasProjectFacet(FACET);
		}
		catch (CoreException e) {
			CloudFoundryPlugin.logError(e);
			return false;
		}
	}

	public boolean addFacet() {
		if (!hasFacet()) {
			try {
				IFacetedProject facetedProject = ProjectFacetsManager.create(project);
				if (facetedProject != null) {
					facetedProject.installProjectFacet(FACET.getDefaultVersion(), null, null);
					return true;
				}
			}
			catch (CoreException e) {
				CloudFoundryPlugin.logError(e);
			}
		}
		return false;
	}

	public static class CFFacetInstallDelegate implements IDelegate {
		public void execute(IProject project, IProjectFacetVersion fv, Object config, IProgressMonitor monitor)
				throws CoreException {
			IJavaProject javaProject = CloudFoundryProjectUtil.getJavaProject(project);
			if (!project.isAccessible() || javaProject == null || !javaProject.exists()) {
				throw new CoreException(
						CloudFoundryPlugin
								.getErrorStatus("Cloud Foundry Standalone Facet can only be installed on a Java project."));
			}
		}
	}

	public static class CFFacetUninstallDelegate implements IDelegate {
		public void execute(IProject project, IProjectFacetVersion fv, Object config, IProgressMonitor monitor)
				throws CoreException {
			// Nothing. Allow Cloud Foundry facets to always be uninstallable.
		}
	}

}
