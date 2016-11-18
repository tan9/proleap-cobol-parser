/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.communication;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.CommunicationDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.CommunicationDescriptionEntryFormat1Context;
import io.proleap.cobol.Cobol85Parser.CommunicationDescriptionEntryFormat2Context;
import io.proleap.cobol.Cobol85Parser.CommunicationDescriptionEntryFormat3Context;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntryContainer;

public interface CommunicationSection extends DataDescriptionEntryContainer {

	CommunicationDescriptionEntryInput addCommunicationDescriptionEntryInput(
			CommunicationDescriptionEntryFormat1Context ctx);

	CommunicationDescriptionEntryInputOutput addCommunicationDescriptionEntryInputOutput(
			CommunicationDescriptionEntryFormat3Context ctx);

	CommunicationDescriptionEntryOutput addCommunicationDescriptionEntryOutput(
			CommunicationDescriptionEntryFormat2Context ctx);

	CommunicationDescriptionEntry createCommunicationDescriptionEntry(
			CommunicationDescriptionEntryContext communicationDescriptionEntryContext);

	List<CommunicationDescriptionEntry> getCommunicationDescriptionEntries();

	CommunicationDescriptionEntry getCommunicationDescriptionEntry(String name);

}