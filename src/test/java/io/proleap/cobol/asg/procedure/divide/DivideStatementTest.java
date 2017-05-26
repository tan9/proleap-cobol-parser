package io.proleap.cobol.asg.procedure.divide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.divide.DivideByGivingStatement;
import io.proleap.cobol.asg.metamodel.procedure.divide.Giving;
import io.proleap.cobol.asg.metamodel.procedure.divide.DivideIntoGivingStatement;
import io.proleap.cobol.asg.metamodel.procedure.divide.DivideIntoStatement;
import io.proleap.cobol.asg.metamodel.procedure.divide.DivideStatement;
import io.proleap.cobol.asg.metamodel.procedure.divide.Remainder;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DivideStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/divide/DivideStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("DivideStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(3, procedureDivision.getStatements().size());

		{
			final DivideStatement divideStatement = (DivideStatement) procedureDivision.getStatements().get(0);
			assertNotNull(divideStatement);
			assertEquals(StatementTypeEnum.DIVIDE, divideStatement.getStatementType());
			assertEquals(DivideStatement.DivideType.INTO, divideStatement.getDivideType());

			{
				assertNotNull(divideStatement.getDivisorValueStmt());
				final CallValueStmt divisorCallValueStmt = (CallValueStmt) divideStatement.getDivisorValueStmt();
				assertEquals(CallType.UNDEFINED_CALL, divisorCallValueStmt.getCall().getCallType());
			}

			{
				final DivideIntoStatement divideIntoStatement = divideStatement.getDivideIntoStatement();
				assertNotNull(divideIntoStatement);
				assertNotNull(divideIntoStatement.getIntoValueStmt());

				final CallValueStmt intoCallValueStmt = (CallValueStmt) divideIntoStatement.getIntoValueStmt();
				assertEquals(CallType.UNDEFINED_CALL, intoCallValueStmt.getCall().getCallType());

				assertNotNull(divideIntoStatement.getGivingPhrase());
				assertEquals(1, divideIntoStatement.getGivingPhrase().getGivings().size());

				{
					final Giving giving = divideIntoStatement.getGivingPhrase().getGivings().get(0);
					assertTrue(giving.isRounded());
					assertEquals(CallType.UNDEFINED_CALL, giving.getCall().getCallType());
				}
			}

			{
				final Remainder remainder = divideStatement.getRemainder();
				assertNotNull(remainder);
				assertNotNull(remainder.getRemainderCall());
				assertEquals(CallType.UNDEFINED_CALL, remainder.getRemainderCall().getCallType());
			}
		}

		{
			final DivideStatement divideStatement = (DivideStatement) procedureDivision.getStatements().get(1);
			assertNotNull(divideStatement);
			assertEquals(StatementTypeEnum.DIVIDE, divideStatement.getStatementType());

			assertEquals(DivideStatement.DivideType.INTO_GIVING, divideStatement.getDivideType());
			{
				assertNotNull(divideStatement.getDivisorValueStmt());
				final CallValueStmt divisorCallValueStmt = (CallValueStmt) divideStatement.getDivisorValueStmt();
				assertEquals(CallType.UNDEFINED_CALL, divisorCallValueStmt.getCall().getCallType());
			}

			{
				final DivideIntoGivingStatement divideIntoGivingStatement = divideStatement
						.getDivideIntoGivingStatement();
				assertNotNull(divideIntoGivingStatement.getGivings());
				assertEquals(2, divideIntoGivingStatement.getGivings().size());

				{
					final Giving giving = divideIntoGivingStatement.getGivings().get(0);
					assertTrue(giving.isRounded());
					assertEquals(CallType.UNDEFINED_CALL, giving.getCall().getCallType());
				}

				{
					final Giving giving = divideIntoGivingStatement.getGivings().get(1);
					assertFalse(giving.isRounded());
					assertEquals(CallType.UNDEFINED_CALL, giving.getCall().getCallType());
				}
			}

			{
				final Remainder remainder = divideStatement.getRemainder();
				assertNotNull(remainder);
				assertNotNull(remainder.getRemainderCall());
				assertEquals(CallType.UNDEFINED_CALL, remainder.getRemainderCall().getCallType());
			}
		}

		{
			final DivideStatement divideStatement = (DivideStatement) procedureDivision.getStatements().get(2);
			assertNotNull(divideStatement);
			assertEquals(StatementTypeEnum.DIVIDE, divideStatement.getStatementType());
			assertEquals(DivideStatement.DivideType.BY_GIVING, divideStatement.getDivideType());

			{
				assertNotNull(divideStatement.getDivisorValueStmt());
				final CallValueStmt divisorCallValueStmt = (CallValueStmt) divideStatement.getDivisorValueStmt();
				assertEquals(CallType.UNDEFINED_CALL, divisorCallValueStmt.getCall().getCallType());
			}

			{
				final DivideByGivingStatement divideIntoByGivingStatement = divideStatement
						.getDivideByGivingStatement();
				assertNotNull(divideIntoByGivingStatement);
				assertNotNull(divideIntoByGivingStatement.getByValueStmt());

				final CallValueStmt intoByGivingCallValueStmt = (CallValueStmt) divideIntoByGivingStatement
						.getByValueStmt();
				assertEquals(CallType.UNDEFINED_CALL, intoByGivingCallValueStmt.getCall().getCallType());

				assertNotNull(divideIntoByGivingStatement.getGivingPhrase());
				assertEquals(1, divideIntoByGivingStatement.getGivingPhrase().getGivings().size());

				{
					final Giving giving = divideIntoByGivingStatement.getGivingPhrase().getGivings()
							.get(0);
					assertTrue(giving.isRounded());
					assertEquals(CallType.UNDEFINED_CALL, giving.getCall().getCallType());
				}
			}

			{
				final Remainder remainder = divideStatement.getRemainder();
				assertNotNull(remainder);
				assertNotNull(remainder.getRemainderCall());
				assertEquals(CallType.UNDEFINED_CALL, remainder.getRemainderCall().getCallType());
			}
		}
	}
}
