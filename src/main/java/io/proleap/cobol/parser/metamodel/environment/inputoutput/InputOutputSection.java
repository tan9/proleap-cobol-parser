/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput;

import java.util.List;

import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.FileControlParagraph;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.IoControlParagraph;

public interface InputOutputSection extends CobolDivisionElement {

	void addFileControlParagraph(FileControlParagraph fileControlParagraph);

	void addIoControlParagraph(IoControlParagraph ioControlParagraph);

	List<FileControlParagraph> getFileControlParagraphs();

	List<IoControlParagraph> getIoControlParagraphs();

}