/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub.copybook.impl;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FileUtils;

import io.proleap.cobol.Cobol85PreprocessorParser.LiteralContext;
import io.proleap.cobol.preprocessor.sub.copybook.LiteralCopyBookFinder;
import io.proleap.cobol.preprocessor.sub.util.StringUtils;

public class LiteralCopyBookFinderImpl implements LiteralCopyBookFinder {

	@Override
	public File findCopyBook(final List<File> copyBookFilesAndDirs, final LiteralContext ctx) {
		for (final File copyBookCandidate : copyBookFilesAndDirs) {
			if (!copyBookCandidate.isDirectory()) {
				if (isMatchingCopyBook(copyBookCandidate, null, ctx)) {
					return copyBookCandidate;
				}
			} else {
				final File validCopyBook = findCopyBookInDirectory(copyBookCandidate, ctx);

				if (validCopyBook != null) {
					return validCopyBook;
				}
			}
		}

		return null;
	}

	protected File findCopyBookInDirectory(final File copyBooksDirectory, final LiteralContext ctx) {
		for (final File copyBookCandidate : FileUtils.listFiles(copyBooksDirectory, null, true)) {
			final boolean isMatchingCopyBook = isMatchingCopyBook(copyBookCandidate, copyBooksDirectory, ctx);

			if (isMatchingCopyBook) {
				return copyBookCandidate;
			}
		}

		return null;
	}

	protected boolean isMatchingCopyBook(final File copyBookCandidate, final File cobolCopyDir,
			final LiteralContext ctx) {
		final String copyBookIdentifier = StringUtils.trimQuotes(ctx.getText()).replace("\\", "/");
		final boolean result;

		if (cobolCopyDir == null) {
			result = isMatchingCopyBookRelative(copyBookCandidate, copyBookIdentifier);
		} else {
			result = isMatchingCopyBookAbsolute(copyBookCandidate, cobolCopyDir, copyBookIdentifier);
		}

		return result;
	}

	protected boolean isMatchingCopyBookAbsolute(final File copyBookCandidate, final File cobolCopyDir,
			final String copyBookIdentifier) {
		final Path copyBookCandidateAbsolutePath = Paths.get(copyBookCandidate.getAbsolutePath()).normalize();
		final Path copyBookIdentifierAbsolutePath = Paths.get(cobolCopyDir.getAbsolutePath(), copyBookIdentifier)
				.normalize();
		final boolean result = copyBookIdentifierAbsolutePath.toString()
				.equalsIgnoreCase(copyBookCandidateAbsolutePath.toString());
		return result;
	}

	protected boolean isMatchingCopyBookRelative(final File copyBookCandidate, final String copyBookIdentifier) {
		final Path copyBookCandidateAbsolutePath = Paths.get(copyBookCandidate.getAbsolutePath()).normalize();
		final Path copyBookIdentifierRelativePath;

		if (copyBookIdentifier.startsWith("/") || copyBookIdentifier.startsWith("./")
				|| copyBookIdentifier.startsWith("\\") || copyBookIdentifier.startsWith(".\\")) {
			copyBookIdentifierRelativePath = Paths.get(copyBookIdentifier).normalize();
		} else {
			copyBookIdentifierRelativePath = Paths.get("/" + copyBookIdentifier).normalize();
		}

		final boolean result = copyBookCandidateAbsolutePath.toString().toLowerCase()
				.endsWith(copyBookIdentifierRelativePath.toString().toLowerCase());
		return result;
	}
}
