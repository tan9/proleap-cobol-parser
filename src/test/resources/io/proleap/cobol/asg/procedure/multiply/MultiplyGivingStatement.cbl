 IDENTIFICATION DIVISION.
 PROGRAM-ID. MULTSTMT.
 PROCEDURE DIVISION.
    MULTIPLY SOMEID1 BY
       SOMEID2 GIVING SOMEID3 SOMEID4 ROUNDED
       ON SIZE ERROR DISPLAY 'on size error'
       NOT ON SIZE ERROR DISPLAY 'not on size error'.