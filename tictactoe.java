import java.util.Scanner;
import java.util.Random;

class Game {
	final static Scanner scanner = new Scanner(System.in);
	final static Random random = new Random();
	static Move move = new Move();

	static char v[] = {'_' ,'_' ,'_' ,'_' ,'_' ,'_' ,'_' ,'_' ,'_' };

	static char ch = 'X';

	public static void setupV () {
		for (int i=0; i<9; i++) v[i] = '_';
	}

	public static void changeCH () {
		if (ch == 'X') ch = 'O';
		else ch = 'X';
	}

	static String s1;
	static String s2;
	static String s3;
	static String s4;
	static String s5;
	static String s6;
	static String s7;
	static String s8;

	public static void reset() {
			s1 = "";
			s2 = "";
			s3 = "";
			s4 = "";
			s5 = "";
			s6 = "";
			s7 = "";
			s8 = "";
	}

	static int turn;
	static int opening;
	static String myS;
	static String enemyS;
	static String myP[] = new String[9];
	static String enemyP[] = new String[9];

	public static void hardReset() {
		myS = "";
		enemyS = "";
		for (int i=0; i<9; i++) {
			myP[i] = "";
			enemyP[i] = "";
		}
	}

		public static void check() {
			if (v[0] != '_') {
				s1 += v[0];
				s4 += v[0];
				s7 += v[0];
			}
			if (v[1] != '_') {
				s1 += v[1];
				s5 += v[1];
			}
			if (v[2] != '_') {
				s1 += v[2];
				s6 += v[2];
				s8 += v[2];
			}
			if (v[3] != '_') {
				s2 += v[3];
				s4 += v[3];
			}
			if (v[4] != '_') {
				s2 += v[4];
				s5 += v[4];
				s7 += v[4];
				s8 += v[4];
			}
			if (v[5] != '_') {
				s2 += v[5];
				s6 += v[5];
			}
			if (v[6] != '_') {
				s3 += v[6];
				s4 += v[6];
				s8 += v[6];
			}
			if (v[7] != '_') {
				s3 += v[7];
				s5 += v[7];
			}
			if (v[8] != '_') {
				s3 += v[8];
				s6 += v[8];
				s7 += v[8];
			}
		}

	public static void show(){
		System.out.println("---------");
		System.out.println("| " + v[0] + " " + v[1] + " " + v[2] + " |");
		System.out.println("| " + v[3] + " " + v[4] + " " + v[5] + " |");
		System.out.println("| " + v[6] + " " + v[7] + " " + v[8] + " |");
		System.out.println("---------");
	}

	public static String gameState () {
		turn = 10;
		boolean stillOpenSpaces = false;
		for (int i=0; i<9; i++) {
			if (v[i] == '_') {
				stillOpenSpaces = true;
				turn--;
			}
		}
		boolean xwins = false;
		boolean owins = false;
		if (v[0]==v[1] && v[1]==v[2] && v[1]!='_') {
			if (v[1]=='X') xwins = true;
			else owins = true;
		}
		if (v[3]==v[4] && v[4]==v[5] && v[4]!='_') {
			if (v[4]=='X') xwins = true;
			else owins = true;
		}
		if (v[6]==v[7] && v[7]==v[8] && v[7]!='_') {
			if (v[7]=='X') xwins = true;
			else owins = true;
		}
		if (v[0]==v[3] && v[3]==v[6] && v[3]!='_') {
			if (v[3]=='X') xwins = true;
			else owins = true;
		}
		if (v[1]==v[4] && v[4]==v[7] && v[4]!='_') {
			if (v[4]=='X') xwins = true;
			else owins = true;
		}
		if (v[2]==v[5] && v[5]==v[8] && v[5]!='_') {
			if (v[5]=='X') xwins = true;
			else owins = true;
		}
		if (v[0]==v[4] && v[4]==v[8] && v[4]!='_') {
			if (v[4]=='X') xwins = true;
			else owins = true;
		}
		if (v[2]==v[4] && v[4]==v[6] && v[4]!='_') {
			if (v[4]=='X') xwins = true;
			else owins = true;
		}
		if (xwins) return "X wins";
		else if (owins) return "O wins";
		else if (stillOpenSpaces) return "Not Finished";
		else return "Draw";
	}

	public static class Move {
		public static int humanMove() {
			System.out.println("Enter the coordinates: ");
			char line1[] = scanner.nextLine().toCharArray();
			for (int i=0; i<line1.length; i++) {
				if ((line1[i]<'0' || line1[i]>'9') && line1[i]!=' ') {
					System.out.println("You should enter numbers!");
					return humanMove();
				}
			}
			if (line1.length>3) {
				System.out.println("Coordinates should be from 1 to 3!");
				return humanMove();
			}
			if (line1.length<2) {
				return humanMove();
			}
			if (line1[1]!=' ') {
				System.out.println("Coordinates should be from 1 to 3!");
				return humanMove();
			}
			int i = '3' - line1[2];
			if (line1[2]>'3') {
				System.out.println("Coordinates should be from 1 to 3!");
				return humanMove();
			}
			int j = line1[0] - '1';
			if (i>2) {
				System.out.println("Coordinates should be from 1 to 3!");
				return humanMove();
			}
			if (j>2) {
				System.out.println("Coordinates should be from 1 to 3!");
				return humanMove();
			}
			int k = i*3+j;
			if (v[k]!='_') {
				System.out.println("This cell is occupied! Choose another one!");
				return humanMove();
			}
			else return k;
		}

		public static int easyAIMove() {
			int q = random.nextInt(9);
			if (v[q] != '_') return easyAIMove();
			else {
				System.out.println("Making move level \"easy\"");
				return q;
			}
		}

		public static int mediumAIMove() {
			int q = random.nextInt(9);
			System.out.println("Making move level \"medium\"");
			Game.reset();
			Game.check();
			String mych = "" + ch;
			changeCH();
			String notmych = "" + ch;
			changeCH();
			if (!s1.contains(notmych)) {
				if (s1.length() == 2) {
					if (v[0] == '_') return 0;
					if (v[1] == '_') return 1;
					if (v[2] == '_') return 2;
				}
			}
			if (!s2.contains(notmych)) {
				if (s2.length() == 2) {
					if (v[3] == '_') return 3;
					if (v[4] == '_') return 4;
					if (v[5] == '_') return 5;
				}
			}
			if (!s3.contains(notmych)) {
				if (s3.length() == 2) {
					if (v[6] == '_') return 6;
					if (v[7] == '_') return 7;
					if (v[8] == '_') return 8;
				}
			}
			if (!s4.contains(notmych)) {
				if (s4.length() == 2) {
					if (v[0] == '_') return 0;
					if (v[3] == '_') return 3;
					if (v[6] == '_') return 6;
				}
			}
			if (!s5.contains(notmych)) {
				if (s5.length() == 2) {
					if (v[1] == '_') return 1;
					if (v[4] == '_') return 4;
					if (v[7] == '_') return 7;
				}
			}
			if (!s6.contains(notmych)) {
				if (s6.length() == 2) {
					if (v[2] == '_') return 2;
					if (v[5] == '_') return 5;
					if (v[8] == '_') return 8;
				}
			}
			if (!s7.contains(notmych)) {
				if (s7.length() == 2) {
					if (v[0] == '_') return 0;
					if (v[4] == '_') return 4;
					if (v[8] == '_') return 8;
				}
			}
			if (!s8.contains(notmych)) {
				if (s8.length() == 2) {
					if (v[2] == '_') return 2;
					if (v[4] == '_') return 4;
					if (v[6] == '_') return 6;
				}
			}
			if (!s1.contains(mych)) {
				if (s1.length() == 2) {
					if (v[0] == '_') return 0;
					if (v[1] == '_') return 1;
					if (v[2] == '_') return 2;
				}
			}
			if (!s2.contains(mych)) {
				if (s2.length() == 2) {
					if (v[3] == '_') return 3;
					if (v[4] == '_') return 4;
					if (v[5] == '_') return 5;
				}
			}
			if (!s3.contains(mych)) {
				if (s3.length() == 2) {
					if (v[6] == '_') return 6;
					if (v[7] == '_') return 7;
					if (v[8] == '_') return 8;
				}
			}
			if (!s4.contains(mych)) {
				if (s4.length() == 2) {
					if (v[0] == '_') return 0;
					if (v[3] == '_') return 3;
					if (v[6] == '_') return 6;
				}
			}
			if (!s5.contains(mych)) {
				if (s5.length() == 2) {
					if (v[1] == '_') return 1;
					if (v[4] == '_') return 4;
					if (v[7] == '_') return 7;
				}
			}
			if (!s6.contains(mych)) {
				if (s6.length() == 2) {
					if (v[2] == '_') return 2;
					if (v[5] == '_') return 5;
					if (v[8] == '_') return 8;
				}
			}
			if (!s7.contains(mych)) {
				if (s7.length() == 2) {
					if (v[0] == '_') return 0;
					if (v[4] == '_') return 4;
					if (v[8] == '_') return 8;
				}
			}
			if (!s8.contains(mych)) {
				if (s8.length() == 2) {
					if (v[2] == '_') return 2;
					if (v[4] == '_') return 4;
					if (v[6] == '_') return 6;
				}
			}
			while (v[q] != '_') q = random.nextInt(9);
			return q;
		}

		public static int hardAIMove() {
			int q = random.nextInt(9);
			System.out.println("Making move level \"hard\"");
			Game.reset();
			Game.check();
			Game.hardReset();
			String mych = "" + ch;
			changeCH();
			String notmych = "" + ch;
			changeCH();
			if (turn == 1) {
				opening = q;
				return q;
			}
			if (turn == 2) {
				if (v[4] == '_') return 4;
				else return 0;
			}
			if (turn == 3) {
				if (opening == 4) {
					if (v[0] == '_') return 0;
					else return 2;
				}
				switch (opening % 2) {
					case 0:
						if (v[0] == 'O' || v[2] == 'O' || v[6] == 'O' || v[8] == 'O') {
							if (v[0] == '_') return 0;
							if (v[2] == '_') return 2;
							if (v[6] == '_') return 6;
						}
						if (v[4] == '_') return 4;
						return 8 - opening;
					case 1:
						if (v[0] == 'O') {
							if (opening == 7) return 6;
							if (opening == 5) return 2;
						}
						if (v[2] == 'O') {
							if (opening == 7) return 8;
							if (opening == 3) return 0;
						}
						if (v[6] == 'O') {
							if (opening == 1) return 0;
							if (opening == 5) return 8;
						}
						if (v[8] == 'O') {
							if (opening == 1) return 2;
							if (opening == 3) return 6;
						}
						if (v[4] == '_') return 4;
						return 0;
				}
			}
			if (!s1.contains(notmych)) {
				if (s1.length() == 2) {
					if (v[0] == '_') return 0;
					if (v[1] == '_') return 1;
					if (v[2] == '_') return 2;
				}
			}
			if (!s2.contains(notmych)) {
				if (s2.length() == 2) {
					if (v[3] == '_') return 3;
					if (v[4] == '_') return 4;
					if (v[5] == '_') return 5;
				}
			}
			if (!s3.contains(notmych)) {
				if (s3.length() == 2) {
					if (v[6] == '_') return 6;
					if (v[7] == '_') return 7;
					if (v[8] == '_') return 8;
				}
			}
			if (!s4.contains(notmych)) {
				if (s4.length() == 2) {
					if (v[0] == '_') return 0;
					if (v[3] == '_') return 3;
					if (v[6] == '_') return 6;
				}
			}
			if (!s5.contains(notmych)) {
				if (s5.length() == 2) {
					if (v[1] == '_') return 1;
					if (v[4] == '_') return 4;
					if (v[7] == '_') return 7;
				}
			}
			if (!s6.contains(notmych)) {
				if (s6.length() == 2) {
					if (v[2] == '_') return 2;
					if (v[5] == '_') return 5;
					if (v[8] == '_') return 8;
				}
			}
			if (!s7.contains(notmych)) {
				if (s7.length() == 2) {
					if (v[0] == '_') return 0;
					if (v[4] == '_') return 4;
					if (v[8] == '_') return 8;
				}
			}
			if (!s8.contains(notmych)) {
				if (s8.length() == 2) {
					if (v[2] == '_') return 2;
					if (v[4] == '_') return 4;
					if (v[6] == '_') return 6;
				}
			}
			if (!s1.contains(mych)) {
				if (s1.length() == 2) {
					if (v[0] == '_') return 0;
					if (v[1] == '_') return 1;
					if (v[2] == '_') return 2;
				}
			}
			if (!s2.contains(mych)) {
				if (s2.length() == 2) {
					if (v[3] == '_') return 3;
					if (v[4] == '_') return 4;
					if (v[5] == '_') return 5;
				}
			}
			if (!s3.contains(mych)) {
				if (s3.length() == 2) {
					if (v[6] == '_') return 6;
					if (v[7] == '_') return 7;
					if (v[8] == '_') return 8;
				}
			}
			if (!s4.contains(mych)) {
				if (s4.length() == 2) {
					if (v[0] == '_') return 0;
					if (v[3] == '_') return 3;
					if (v[6] == '_') return 6;
				}
			}
			if (!s5.contains(mych)) {
				if (s5.length() == 2) {
					if (v[1] == '_') return 1;
					if (v[4] == '_') return 4;
					if (v[7] == '_') return 7;
				}
			}
			if (!s6.contains(mych)) {
				if (s6.length() == 2) {
					if (v[2] == '_') return 2;
					if (v[5] == '_') return 5;
					if (v[8] == '_') return 8;
				}
			}
			if (!s7.contains(mych)) {
				if (s7.length() == 2) {
					if (v[0] == '_') return 0;
					if (v[4] == '_') return 4;
					if (v[8] == '_') return 8;
				}
			}
			if (!s8.contains(mych)) {
				if (s8.length() == 2) {
					if (v[2] == '_') return 2;
					if (v[4] == '_') return 4;
					if (v[6] == '_') return 6;
				}
			}
			if (s1.contains(mych) && !s1.contains(notmych)) {
				myS += "s1";
			}
			if (s2.contains(mych) && !s2.contains(notmych)) {
				myS += "s2";
			}
			if (s3.contains(mych) && !s3.contains(notmych)) {
				myS += "s3";
			}
			if (s4.contains(mych) && !s4.contains(notmych)) {
				myS += "s4";
			}
			if (s5.contains(mych) && !s5.contains(notmych)) {
				myS += "s5";
			}
			if (s6.contains(mych) && !s6.contains(notmych)) {
				myS += "s6";
			}
			if (s7.contains(mych) && !s7.contains(notmych)) {
				myS += "s7";
			}
			if (s8.contains(mych) && !s8.contains(notmych)) {
				myS += "s8";
			}
			if (!s1.contains(mych) && s1.contains(notmych)) {
				enemyS += "s1";
			}
			if (!s2.contains(mych) && s2.contains(notmych)) {
				enemyS += "s2";
			}
			if (!s3.contains(mych) && s3.contains(notmych)) {
				enemyS += "s3";
			}
			if (!s4.contains(mych) && s4.contains(notmych)) {
				enemyS += "s4";
			}
			if (!s5.contains(mych) && s5.contains(notmych)) {
				enemyS += "s5";
			}
			if (!s6.contains(mych) && s6.contains(notmych)) {
				enemyS += "s6";
			}
			if (!s7.contains(mych) && s7.contains(notmych)) {
				enemyS += "s7";
			}
			if (!s8.contains(mych) && s8.contains(notmych)) {
				enemyS += "s8";
			}
			if (myS.contains("s1")) {
				myP[0] += "s1";
				myP[1] += "s1";
				myP[2] += "s1";
			}
			if (myS.contains("s2")) {
				myP[3] += "s2";
				myP[4] += "s2";
				myP[5] += "s2";
			}
			if (myS.contains("s3")) {
				myP[6] += "s3";
				myP[7] += "s3";
				myP[8] += "s3";
			}
			if (myS.contains("s4")) {
				myP[0] += "s4";
				myP[3] += "s4";
				myP[6] += "s4";
			}
			if (myS.contains("s5")) {
				myP[1] += "s5";
				myP[4] += "s5";
				myP[7] += "s5";
			}
			if (myS.contains("s6")) {
				myP[2] += "s6";
				myP[5] += "s6";
				myP[8] += "s6";
			}
			if (myS.contains("s7")) {
				myP[0] += "s7";
				myP[4] += "s7";
				myP[8] += "s7";
			}
			if (myS.contains("s8")) {
				myP[2] += "s8";
				myP[4] += "s8";
				myP[6] += "s8";
			}
			if (enemyS.contains("s1")) {
				enemyP[0] += "s1";
				enemyP[1] += "s1";
				enemyP[2] += "s1";
			}
			if (enemyS.contains("s2")) {
				enemyP[3] += "s2";
				enemyP[4] += "s2";
				enemyP[5] += "s2";
			}
			if (enemyS.contains("s3")) {
				enemyP[6] += "s3";
				enemyP[7] += "s3";
				enemyP[8] += "s3";
			}
			if (enemyS.contains("s4")) {
				enemyP[0] += "s4";
				enemyP[3] += "s4";
				enemyP[6] += "s4";
			}
			if (enemyS.contains("s5")) {
				enemyP[1] += "s5";
				enemyP[4] += "s5";
				enemyP[7] += "s5";
			}
			if (enemyS.contains("s6")) {
				enemyP[2] += "s6";
				enemyP[5] += "s6";
				enemyP[8] += "s6";
			}
			if (enemyS.contains("s7")) {
				enemyP[0] += "s7";
				enemyP[4] += "s7";
				enemyP[8] += "s7";
			}
			if (enemyS.contains("s8")) {
				enemyP[2] += "s8";
				enemyP[4] += "s8";
				enemyP[6] += "s8";
			}
			for (int i=0 ; i<9; i++) {
				if (v[i] != '_') {
					myP[i] += "T";
					enemyP[i] += "T";
				}
			}
			for (int i=0 ; i<9; i++) {
				if (!myP[i].contains("T") && myP[i].length()>=4) return i;
			}
			for (int i=0; i<9; i++) {
				if (!myP[i].contains("T") && myP[i]!="") {
					if (myP[i].contains("s1")) {
						for (int j=0; j<9; j++) {
							if (!myP[j].contains("T") && myP[j]!="" && i!=j) {
								if (myP[j].contains("s1")) {
									if (enemyP[j].length()<4) return i;
								}
							}
						}
					}
					if (myP[i].contains("s2")) {
						for (int j=0; j<9; j++) {
							if (!myP[j].contains("T") && myP[j]!="" && i!=j) {
								if (myP[j].contains("s2")) {
									if (enemyP[j].length()<4) return i;
								}
							}
						}
					}
					if (myP[i].contains("s3")) {
						for (int j=0; j<9; j++) {
							if (!myP[j].contains("T") && myP[j]!="" && i!=j) {
								if (myP[j].contains("s3")) {
									if (enemyP[j].length()<4) return i;
								}
							}
						}
					}
					if (myP[i].contains("s4")) {
						for (int j=0; j<9; j++) {
							if (!myP[j].contains("T") && myP[j]!="" && i!=j) {
								if (myP[j].contains("s4")) {
									if (enemyP[j].length()<4) return i;
								}
							}
						}
					}
					if (myP[i].contains("s5")) {
						for (int j=0; j<9; j++) {
							if (!myP[j].contains("T") && myP[j]!="" && i!=j) {
								if (myP[j].contains("s5")) {
									if (enemyP[j].length()<4) return i;
								}
							}
						}
					}
					if (myP[i].contains("s6")) {
						for (int j=0; j<9; j++) {
							if (!myP[j].contains("T") && myP[j]!="" && i!=j) {
								if (myP[j].contains("s6")) {
									if (enemyP[j].length()<4) return i;
								}
							}
						}
					}
					if (myP[i].contains("s7")) {
						for (int j=0; j<9; j++) {
							if (!myP[j].contains("T") && myP[j]!="" && i!=j) {
								if (myP[j].contains("s7")) {
									if (enemyP[j].length()<4) return i;
								}
							}
						}
					}
					if (myP[i].contains("s8")) {
						for (int j=0; j<9; j++) {
							if (!myP[j].contains("T") && myP[j]!="" && i!=j) {
								if (myP[j].contains("s8")) {
									if (enemyP[j].length()<4) return i;
								}
							}
						}
					}
				}
			}
			if (v[4] == '_') return 4;
			if (v[0] == '_') return 0;
			if (v[2] == '_') return 2;
			if (v[6] == '_') return 6;
			if (v[8] == '_') return 8;
			if (v[1] == '_') return 1;
			if (v[3] == '_') return 3;
			if (v[5] == '_') return 5;
			if (v[7] == '_') return 7;
			return q;
		}
	}
}

public class Main {
	static Game game = new Game();
	final static Scanner scanner = new Scanner(System.in);

	public static void gameLoop(){
		System.out.println("Input command: ");
		String input = scanner.nextLine();
		String[] gameSettings = input.split(" ");
		if (gameSettings[0].equals("exit")) {
			return;
		}
		if (gameSettings.length < 3) {
			System.out.println("Bad parameters!");
			gameLoop();
			return;
		}
		if (!gameSettings[1].equals("easy") && !gameSettings[1].equals("user") && !gameSettings[1].equals("medium") && !gameSettings[1].equals("hard")) {
			System.out.println("Bad parameters!");
			gameLoop();
			return;
		}
		if (!gameSettings[2].equals("easy") && !gameSettings[2].equals("user") && !gameSettings[2].equals("medium") && !gameSettings[2].equals("hard")) {
			System.out.println("Bad parameters!");
			gameLoop();
			return;
		}
		if ("start".equals(gameSettings[0])) {
			game.setupV();
			game.ch = 'X';
			actualGameLoop(gameSettings);
			gameLoop();
			return;
		}
		System.out.println("Bad parameters!");
		gameLoop();
	}

	public static void actualGameLoop(String[] gameSettings) {
		game.show();
		if (game.gameState() == "Not Finished") {
			if (game.ch == 'X') {
				switch (gameSettings[1]) {
					case "user":
						game.v[game.move.humanMove()] = game.ch;
						break;
					case "easy":
						game.v[game.move.easyAIMove()] = game.ch;
						break;
					case "medium":
						game.v[game.move.mediumAIMove()] = game.ch;
						break;
					case "hard":
						game.v[game.move.hardAIMove()] = game.ch;
						break;
					default:
						break;
				}
				game.changeCH();
			}
			else {
				switch (gameSettings[2]) {
					case "user":
						game.v[game.move.humanMove()] = game.ch;
						break;
					case "easy":
						game.v[game.move.easyAIMove()] = game.ch;
						break;
					case "medium":
						game.v[game.move.mediumAIMove()] = game.ch;
						break;
					case "hard":
						game.v[game.move.hardAIMove()] = game.ch;
						break;
					default:
						break;
				}
				game.changeCH();
			}
			actualGameLoop(gameSettings);
		}
		else System.out.println(game.gameState());
	}

	public static void main(String[] args) {

		gameLoop();
	}
}
