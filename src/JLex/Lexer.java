package JLex;
import java.lang.System;
import java.io.*;
class Lexer {
	Yylex tokenizer;
	public  Lexer(String fileName)
	{
	  try
	  {
	  tokenizer=new Yylex(new BufferedReader(new FileReader(fileName)));
	  }
	  catch(Exception e)
	  {
	  }
	}
	public Token nextToken()
	{
		Token next=null;
		try
		{
		 next=  tokenizer.getToken();
		}
		catch(Exception e)
		{
		}
		return next;
	}
	}


class Yylex {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

	//initialize  variables to be used by class
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

//Add code to be executed on initialization of the lexer
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int SCOPED = 1;
	private final int yy_state_dtrans[] = {
		0,
		110
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NOT_ACCEPT,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NOT_ACCEPT,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NOT_ACCEPT,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NOT_ACCEPT,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NOT_ACCEPT,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"53:9,52,49,53:2,47,53:18,52,38,45,53:2,37,40,53,27,28,34,22,36,23,33,43,44:" +
"10,15,35,39,16,42,53:2,50:26,29,46,30,53,51,48,2,17,3,20,6,12,5,19,7,50,4,1" +
"3,8,24,9,1,50,10,14,11,21,32,18,50,31,50,25,41,26,53:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,118,
"0,1,2,3,4,5,6,1:6,7,1:4,4,8,9,10,11,12,13,1:2,14,1:8,15,1,14:2,1,14:12,16,1" +
"7,18,1,13:2,19,1,20,21,22,20,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,3" +
"8,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,6" +
"3,64,65,66,67,68,69,70,71,14,72,14,73")[0];

	private int yy_nxt[][] = unpackFromString(74,54,
"1,2,116,91,116:2,92,54,116:2,111,93,77,116,112,3,4,102,116:2,117,116,5,6,11" +
"6,7,8,9,10,11,12,116,78,13,14,15,16,17,18,19,20,21,55,22,23,24,56,-1,61,25," +
"116:2,26,56,-1:55,116,113,116:12,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11,114,-" +
"1:5,116,114,-1:18,28,-1:53,60,-1:59,29,-1:54,30,-1:63,53,-1:36,60,-1:6,31,-" +
"1:15,32,-1:54,33,-1:54,34,-1:55,36,-1:54,23,-1:10,58:44,37,62,58:2,-1,58:4," +
"-1,116:14,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11,114,-1:5,116,114,-1:3,36:46," +
"-1,36,-1,36:4,-1:33,40,-1:21,116:7,104,116:3,27,116:2,-1:2,116:5,-1:2,116,-" +
"1:6,116:2,-1:11,114,-1:5,116,114,-1:18,60,-1:25,35,-1:12,116:9,38,116:4,-1:" +
"2,116:5,-1:2,116,-1:6,116:2,-1:11,114,-1:5,116,114,-1:3,64:47,37,64:5,-1,58" +
":44,57,62,58:2,-1,58:4,-1,116:9,39,116:4,-1:2,116:5,-1:2,116,-1:6,116:2,-1:" +
"11,114,-1:5,116,114,-1:3,116:5,41,116:8,-1:2,116:5,-1:2,116,-1:6,116:2,-1:1" +
"1,114,-1:5,116,114,-1:3,116:5,42,116:8,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11" +
",114,-1:5,116,114,-1:3,116:5,43,116:8,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11," +
"114,-1:5,116,114,-1:3,116:2,44,116:11,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11," +
"114,-1:5,116,114,-1:3,116:10,45,116:3,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11," +
"114,-1:5,116,114,-1:3,116:3,46,116:10,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11," +
"114,-1:5,116,114,-1:3,116:10,47,116:3,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11," +
"114,-1:5,116,114,-1:3,116:14,-1:2,116:5,-1:2,48,-1:6,116:2,-1:11,114,-1:5,1" +
"16,114,-1:3,116:10,49,116:3,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11,114,-1:5,1" +
"16,114,-1:3,116:14,-1:2,116:2,50,116:2,-1:2,116,-1:6,116:2,-1:11,114,-1:5,1" +
"16,114,-1:3,116:5,51,116:8,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11,114,-1:5,11" +
"6,114,-1:3,116:10,52,116:3,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11,114,-1:5,11" +
"6,114,-1:3,116:8,59,116:5,-1:2,116:4,82,-1:2,116,-1:6,116:2,-1:11,114,-1:5," +
"116,114,-1:3,116,63,116:12,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11,114,-1:5,11" +
"6,114,-1:3,116:13,65,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11,114,-1:5,116,114," +
"-1:3,116:13,66,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11,114,-1:5,116,114,-1:3,6" +
"7,116:13,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11,114,-1:5,116,114,-1:3,116:14," +
"-1:2,116:5,-1:2,68,-1:6,116:2,-1:11,114,-1:5,116,114,-1:3,116:13,69,-1:2,11" +
"6:5,-1:2,116,-1:6,116:2,-1:11,114,-1:5,116,114,-1:3,116,70,116:12,-1:2,116:" +
"5,-1:2,116,-1:6,116:2,-1:11,114,-1:5,116,114,-1:3,116:9,71,116:4,-1:2,116:5" +
",-1:2,116,-1:6,116:2,-1:11,114,-1:5,116,114,-1:3,116:9,72,116:4,-1:2,116:5," +
"-1:2,116,-1:6,116:2,-1:11,114,-1:5,116,114,-1:3,116:2,73,116:11,-1:2,116:5," +
"-1:2,116,-1:6,116:2,-1:11,114,-1:5,116,114,-1:3,116:2,74,116:11,-1:2,116:5," +
"-1:2,116,-1:6,116:2,-1:11,114,-1:5,116,114,-1:3,116:4,75,116:9,-1:2,116:5,-" +
"1:2,116,-1:6,116:2,-1:11,114,-1:5,116,114,-1:3,116:12,76,116,-1:2,116:5,-1:" +
"2,116,-1:6,116:2,-1:11,114,-1:5,116,114,-1:3,116,79,116:6,94,116:5,-1:2,116" +
":5,-1:2,116,-1:6,116:2,-1:11,114,-1:5,116,114,-1:3,116:12,80,116,-1:2,116:5" +
",-1:2,116,-1:6,116:2,-1:11,114,-1:5,116,114,-1:3,116:14,-1:2,116:5,-1:2,116" +
",-1:6,81,116,-1:11,114,-1:5,116,114,-1:3,116:14,-1:2,116:5,-1:2,83,-1:6,116" +
":2,-1:11,114,-1:5,116,114,-1:3,116:5,84,116:8,-1:2,116:5,-1:2,116,-1:6,116:" +
"2,-1:11,114,-1:5,116,114,-1:3,116:8,85,116:5,-1:2,116:5,-1:2,116,-1:6,116:2" +
",-1:11,114,-1:5,116,114,-1:3,116:14,-1:2,116:4,86,-1:2,116,-1:6,116:2,-1:11" +
",114,-1:5,116,114,-1:3,116:14,-1:2,116:4,87,-1:2,116,-1:6,116:2,-1:11,114,-" +
"1:5,116,114,-1:3,116:10,88,116:3,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11,114,-" +
"1:5,116,114,-1:3,116,89,116:12,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11,114,-1:" +
"5,116,114,-1:3,116:14,-1:2,116:4,90,-1:2,116,-1:6,116:2,-1:11,114,-1:5,116," +
"114,-1:3,116:9,95,116:4,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11,114,-1:5,116,1" +
"14,-1:3,116:11,27,116:2,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11,114,-1:5,116,1" +
"14,-1:3,96,116:13,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11,114,-1:5,116,114,-1:" +
"3,116:10,97,116:3,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11,114,-1:5,116,114,-1:" +
"3,116:9,98,116:4,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11,114,-1:5,116,114,-1:3" +
",116:6,99,116:7,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11,114,-1:5,116,114,-1:3," +
"116:3,100,116:10,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11,114,-1:5,116,114,-1:3" +
",116,101,116:12,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11,114,-1:5,116,114,-1:2," +
"1,116:2,91,116:2,92,103,116:2,111,93,77,116,112,3,4,102,116:2,117,116,5,6,1" +
"16,7,8,9,10,11,12,116,78,13,14,15,16,17,18,19,20,21,55,22,23,24,56,-1,61,25" +
",116:2,26,56,-1,116:5,105,116:8,-1:2,116:5,-1:2,116,-1:6,116:2,-1:11,114,-1" +
":5,116,114,-1:3,116:10,106,116:3,-1:2,116,107,116:3,-1:2,116,-1:6,116:2,-1:" +
"11,114,-1:5,116,114,-1:3,116:2,108,116:11,-1:2,116:5,-1:2,116,-1:6,116:2,-1" +
":11,114,-1:5,116,114,-1:3,116:11,109,116:2,-1:2,116:5,-1:2,116,-1:6,116:2,-" +
"1:11,114,-1:5,116,114,-1:3,116:5,115,116:8,-1:2,116:5,-1:2,116,-1:6,116:2,-" +
"1:11,114,-1:5,116,114,-1:2");

	public Token getToken ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

//Add code to be executed when the end of the file is reached
return (new Token(Token.EOF, "Done"));
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -3:
						break;
					case 3:
						{ return new Token(Token.COLON, yytext()); }
					case -4:
						break;
					case 4:
						{ return new Token(Token.EQUAL, yytext()); }
					case -5:
						break;
					case 5:
						{ return new Token(Token.PLUS, yytext()); }
					case -6:
						break;
					case 6:
						{ return new Token(Token.MINUS, yytext()); }
					case -7:
						break;
					case 7:
						{ return new Token(Token.OPEN_CURLY, yytext()); }
					case -8:
						break;
					case 8:
						{ return new Token(Token.CLOSE_CURLY, yytext()); }
					case -9:
						break;
					case 9:
						{ return new Token(Token.OPEN_PARAN, yytext()); }
					case -10:
						break;
					case 10:
						{ return new Token(Token.CLOSE_PARAN, yytext()); }
					case -11:
						break;
					case 11:
						{ return new Token(Token.OPEN_SQUARE, yytext()); }
					case -12:
						break;
					case 12:
						{ return new Token(Token.CLOSE_SQUARE, yytext()); }
					case -13:
						break;
					case 13:
						{ return new Token(Token.DOT, yytext()); }
					case -14:
						break;
					case 14:
						{ return new Token(Token.ASTRISK, yytext()); }
					case -15:
						break;
					case 15:
						{ return new Token(Token.SEMI_COLON, yytext()); }
					case -16:
						break;
					case 16:
						{ return new Token(Token.COMMA, yytext()); }
					case -17:
						break;
					case 17:
						{ return new Token(Token.PERCENT, yytext()); }
					case -18:
						break;
					case 18:
						{ return new Token(Token.EXCLAMATION, yytext()); }
					case -19:
						break;
					case 19:
						{ return new Token(Token.REL_OP, yytext()); }
					case -20:
						break;
					case 20:
						{ return new Token(Token.AMBERSAND, yytext()); }
					case -21:
						break;
					case 21:
						{ return new Token(Token.BAR, yytext()); }
					case -22:
						break;
					case 22:
						{ return new Token(Token.SLASH, yytext()); }
					case -23:
						break;
					case 23:
						{ return new Token(Token.INT_LIT, yytext()); }
					case -24:
						break;
					case 24:
						{ return new Token(Token.ERROR, "Invalid input: " + yytext().trim() + " in line " + (yyline + 1) ); }
					case -25:
						break;
					case 25:
						{}
					case -26:
						break;
					case 26:
						{}
					case -27:
						break;
					case 27:
						{ return new Token(Token.IF, yytext()); }
					case -28:
						break;
					case 28:
						{ return new Token(Token.COLON_EQUAL, yytext()); }
					case -29:
						break;
					case 29:
						{ return new Token(Token.INCREMENT, yytext()); }
					case -30:
						break;
					case 30:
						{ return new Token(Token.DECREMENT, yytext()); }
					case -31:
						break;
					case 31:
						{ return new Token(Token.LESS_DASH, yytext()); }
					case -32:
						break;
					case 32:
						{ return new Token(Token.SHIFT_LEFT, yytext()); }
					case -33:
						break;
					case 33:
						{ return new Token(Token.AND_OP, yytext()); }
					case -34:
						break;
					case 34:
						{ return new Token(Token.OR_OP, yytext()); }
					case -35:
						break;
					case 35:
						{ return new Token(Token.SHIFT_RIGHT, yytext()); }
					case -36:
						break;
					case 36:
						{}
					case -37:
						break;
					case 37:
						{ return new Token(Token.STRING_LIT, yytext()); }
					case -38:
						break;
					case 38:
						{ return new Token(Token.FOR, yytext()); }
					case -39:
						break;
					case 39:
						{ return new Token(Token.VAR, yytext()); }
					case -40:
						break;
					case 40:
						{ return new Token(Token.CDOTS, yytext()); }
					case -41:
						break;
					case 41:
						{ return new Token(Token.CASE, yytext()); }
					case -42:
						break;
					case 42:
						{ return new Token(Token.ELSE, yytext()); }
					case -43:
						break;
					case 43:
						{ return new Token(Token.TYPE, yytext()); }
					case -44:
						break;
					case 44:
						{ return new Token(Token.FUNC, yytext()); }
					case -45:
						break;
					case 45:
						{ return new Token(Token.CONST, yytext()); }
					case -46:
						break;
					case 46:
						{ return new Token(Token.BREAK, yytext()); }
					case -47:
						break;
					case 47:
						{ return new Token(Token.IMPORT, yytext()); }
					case -48:
						break;
					case 48:
						{ return new Token(Token.RETURN, yytext()); }
					case -49:
						break;
					case 49:
						{ return new Token(Token.STRUCT, yytext()); }
					case -50:
						break;
					case 50:
						{ return new Token(Token.SWITCH, yytext()); }
					case -51:
						break;
					case 51:
						{ return new Token(Token.PACKAGE, yytext()); }
					case -52:
						break;
					case 52:
						{ return new Token(Token.DEFAULT, yytext()); }
					case -53:
						break;
					case 54:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -54:
						break;
					case 55:
						{ return new Token(Token.REL_OP, yytext()); }
					case -55:
						break;
					case 56:
						{ return new Token(Token.ERROR, "Invalid input: " + yytext().trim() + " in line " + (yyline + 1) ); }
					case -56:
						break;
					case 57:
						{ return new Token(Token.STRING_LIT, yytext()); }
					case -57:
						break;
					case 59:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -58:
						break;
					case 60:
						{ return new Token(Token.REL_OP, yytext()); }
					case -59:
						break;
					case 61:
						{ return new Token(Token.ERROR, "Invalid input: " + yytext().trim() + " in line " + (yyline + 1) ); }
					case -60:
						break;
					case 63:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -61:
						break;
					case 65:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -62:
						break;
					case 66:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -63:
						break;
					case 67:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -64:
						break;
					case 68:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -65:
						break;
					case 69:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -66:
						break;
					case 70:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -67:
						break;
					case 71:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -68:
						break;
					case 72:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -69:
						break;
					case 73:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -70:
						break;
					case 74:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -71:
						break;
					case 75:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -72:
						break;
					case 76:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -73:
						break;
					case 77:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -74:
						break;
					case 78:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -75:
						break;
					case 79:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -76:
						break;
					case 80:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -77:
						break;
					case 81:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -78:
						break;
					case 82:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -79:
						break;
					case 83:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -80:
						break;
					case 84:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -81:
						break;
					case 85:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -82:
						break;
					case 86:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -83:
						break;
					case 87:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -84:
						break;
					case 88:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -85:
						break;
					case 89:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -86:
						break;
					case 90:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -87:
						break;
					case 91:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -88:
						break;
					case 92:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -89:
						break;
					case 93:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -90:
						break;
					case 94:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -91:
						break;
					case 95:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -92:
						break;
					case 96:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -93:
						break;
					case 97:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -94:
						break;
					case 98:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -95:
						break;
					case 99:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -96:
						break;
					case 100:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -97:
						break;
					case 101:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -98:
						break;
					case 102:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -99:
						break;
					case 103:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -100:
						break;
					case 104:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -101:
						break;
					case 105:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -102:
						break;
					case 106:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -103:
						break;
					case 107:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -104:
						break;
					case 108:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -105:
						break;
					case 109:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -106:
						break;
					case 111:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -107:
						break;
					case 112:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -108:
						break;
					case 113:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -109:
						break;
					case 114:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -110:
						break;
					case 115:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -111:
						break;
					case 116:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -112:
						break;
					case 117:
						{ return new Token(Token.IDENTIFIER, yytext()); }
					case -113:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
