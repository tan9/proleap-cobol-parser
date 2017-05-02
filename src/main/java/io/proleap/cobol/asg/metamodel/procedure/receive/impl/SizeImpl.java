/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.receive.impl;

import io.proleap.cobol.Cobol85Parser.ReceiveSizeContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.receive.Size;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class SizeImpl extends CobolDivisionElementImpl implements Size {

	protected final ReceiveSizeContext ctx;

	protected ValueStmt sizeValueStmt;

	public SizeImpl(final ProgramUnit programUnit, final ReceiveSizeContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ValueStmt getSizeValueStmt() {
		return sizeValueStmt;
	}

	@Override
	public void setSizeValueStmt(final ValueStmt sizeValueStmt) {
		this.sizeValueStmt = sizeValueStmt;
	}

}
