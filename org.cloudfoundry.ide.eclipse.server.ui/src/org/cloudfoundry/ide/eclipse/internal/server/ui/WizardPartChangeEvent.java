/*******************************************************************************
 * Copyright (c) 2013 GoPivotal, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     GoPivotal, Inc. - initial API and implementation
 *******************************************************************************/
package org.cloudfoundry.ide.eclipse.internal.server.ui;

import org.eclipse.core.runtime.IStatus;

public class WizardPartChangeEvent extends PartChangeEvent {

	private final boolean updateWizardButtons;

	public WizardPartChangeEvent(Object data, IStatus status, UIPart source, int type, boolean updateWizardButtons) {
		super(data, status, source, type);
		this.updateWizardButtons = updateWizardButtons;
	}

	public boolean updateWizardButtons() {
		return updateWizardButtons;
	}

}
