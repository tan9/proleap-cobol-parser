/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.entry;

import java.util.List;

import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

/**
 * Establishes an alternate entry point into a COBOL program.
 */
public interface EntryStatement extends Statement {

	void addUsingCall(Call usingCall);

	ValueStmt getEntryValueStmt();

	List<Call> getUsingCalls();

	void setEntryValueStmt(ValueStmt entryValueStmt);

}
