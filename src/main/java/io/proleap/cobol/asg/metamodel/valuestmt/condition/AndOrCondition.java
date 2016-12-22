/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition;

import io.proleap.cobol.Cobol85Parser.AbbreviationRestContext;
import io.proleap.cobol.Cobol85Parser.CombinableConditionContext;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.AbbreviationRest;

public interface AndOrCondition extends ValueStmt {

	enum Type {
		And, Or
	}

	AbbreviationRest addAbbreviationRest(AbbreviationRestContext abbreviationRest);

	CombinableCondition addCombinableCondition(CombinableConditionContext ctx);

	AbbreviationRest getAbbreviationRest();

	CombinableCondition getCombinableCondition();

	Type getType();

	@Override
	String getValue();

	void setType(Type type);

}