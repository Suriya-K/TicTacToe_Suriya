## โปรแกรม Tic Tac Toe
### โดย นายสุริยะ เคนมา
---
โปรแกรม 2 ส่วนในการทำงาน 
+ 1 |  Main.java
+ 2 |  Board.java 
---
### เงื่อนไขหรือตัวแปร
+ 1 | กำหนดให้ 0 แทนด้วยกระดานที่ว่าง
+ 2 | กำหนดให้ 1 แทนตัว X
+ 3 | กำหนดให้ 2 แทนตัว O
+ 4 | ถ้าใครชนะให้ return {ผู้เล่นที่ชนะ} Win เช่น "X Win" หรือ "O Win"
+ 5 | ถ้าเสมอ ให้ retrun Draw
+ 6 | ถ้าเกมยังไม่จบ หรือไม่เข้ากรณีไหนเลยให้ return Null
---
### Main.java
โดยเริ่มแรกจะทำการ สร้าง class Board ขึ้นมา จากนั้นจะทำการเรียกใช้งาน function fillBoard() เพื่อทำข้อมูลทางแป้นพิมพ์
ในการรับข้อมูลทางแป้นพิมพ์เราจำเป็นที่ต้องใช้ try catch เพื่อป้องกันการรับข้อมูลที่ไม่ถูกต้อง
```java
	private static void fillBoard() {
		try (Scanner sc = new Scanner(System.in)) {
			for (int i = 0; i < MAX_LENGHT; i++) {
				for (int j = 0; j < MAX_LENGHT; j++) {
					inputBoard[i][j] = sc.nextInt();
				}
			}
		} catch (Exception e) {
			System.exit(0);
		}
	}
```
---
เมื่อได้รับข้อมูลทางแป้นไปคำนวณหาผลลัพธ์แล้วส่งผลลัพธ์กลับมาใน function checkResult()
```java
	private static void checkResult(Board board) {
		board.setBoard(inputBoard);
		System.out.printf("\nOutput : %s", board.getWinner());
	}
```
---
### Board.java
เริ่มจาก ทำการรับข้อมูลทางแป้นพิมพ์ที่ได้รับจาก class Main และนำมาเก็บไว้ ที่ function setBoard()
```java
private int[][] board = new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
public void setBoard(int[][] _board) {
		board = _board;
	}
```
---
#### ในส่วนของการตรวจสอบว่า X หรือ O เป็นฝ่ายชนะนั้นจะใช้การเปรียบเทียบ ค่าที่อยู่ภายใน array นั้นว่ามีค่าเท่ากันหรือไม่ ถ้ามีค่าเท่ากันให้ return true ถ้าไม่ก็ return false

เริ่มจากรับ parameter 4 ตัวที่ในการตรวจสอบคือ ตำแหน่งของ array 3 ตำแหน่ง และ ตำแหน่งนั้นเป็น X หรือ O ใน function checkEachrowcol()
```java
	private boolean checkEachrowcol(int pos1, int pos2, int pos3, int player) {
		if ((pos1 == pos2) && (pos2 == pos3) && (pos1 == player)) {
			return true;
		}
		return false;
	}
}
```
---
#### จากนั้นเรียกใช้ function checkEachrowcol() เพื่อตรวจสอบว่าตำแหน่งที่เท่ากันนั้น เป็นแนวนอน แนวตั้งหรือ แนวทแยง

เริ่มจากตรวจสอบ แนวนอน
```java
	private boolean checkRows(int player) {
		for (int i = 0; i < MAX_LENGHT; i++) {
			if (checkEachrowcol(board[i][0], board[i][1], board[i][2], player)) {
				return true;
			}
		}
		return false;
	}
```
---
จากนั้น แนวตั้ง
```java
	private boolean checkColumns(int player) {
		for (int i = 0; i < MAX_LENGHT; i++) {
			if (checkEachrowcol(board[0][i], board[1][i], board[2][i], player)) {
				return true;
			}
		}
		return false;
	}
```
---
และ แนวทแยง จะให้ได้ว่าแนวทแยงตำแหน่งที่จะเกิดขึ้นนั้นเป็นตำแหน่งที่แน่นอน จึงไม่ต้องใช้ loop ในการวนหาตำแหน่ง
```java
	private boolean checkDiagonals(int player) {
		if (checkEachrowcol(board[0][0], board[1][1], board[2][2], player)
				|| checkEachrowcol(board[0][2], board[1][1], board[2][0], player)) {
			return true;
		}
		return false;
	}
```
---
ตรวจสอบว่า กระดานเต็มหรือยัง ใช้ในการตรวจสอบในกรณีที่ ยังหาผู้ชนะไม่ได้
```java
	public boolean isFull() {
		for (int i = 0; i < MAX_LENGHT; i++) {
			for (int j = 0; j < MAX_LENGHT; j++) {
				if (board[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
```
---
จากนั้นทำการ ตรวจสอบว่า ใครเป็นผู้ชนะ โดยการตรวจสอบ ว่า 3 function ที่ใช้ มีค่าเป็นจริงหรือไม่
```java
	private boolean checkWin(int player) {
		if (checkRows(player) || checkColumns(player) || checkDiagonals(player)) {
			return true;
		}
		return false;
	}
```

ในส่วนสุดท้าย จะทำการส่งผลลัพธ์ของผู้ชนะ โดยเรียกใช้ function ตรวจสอบผู้ชนะ แล้ว return ผลลัพธ์ เป็น string เพื่อแสดงผลทางหน้าจอ
```java
	public String getWinner() {
		boolean xWin = checkWin(X);
		boolean oWin = checkWin(O);

		if (isFull() && (!xWin) && !oWin) {
			return drawResult;
		}
		if (xWin && oWin) {
			return nullResult;
		}
		if (xWin) {
			return "X Win";
		}
		if (oWin) {
			return "O Win";
		}
		return nullResult;
	}	
```
---
ในส่วนนี้หากสังเกตุว่า ทำไมถึงเรียกใช้ function ซ้อน function หรือเงื่อนไขแปลกๆ เพราะว่า ป้องกันการเกิดข้อผิดพลาดในกรณีประหลาด เช่น
หาก input กระดานเป็น
```
[[1,2,1],
  [1,2,1],
  [1,2,1]]
```
ตามปกติ output ควรออกมาเป็น null ถ้าเขียนไม่ดี ผลลัพธ์อาจจะออกมาเป็น X ชนะแทนได้
และก็เพื่อต้องการให้ code นั้น อ่านเข้าใจได้ง่าย ไม่ต้องเขียน comment และ คนอื่นมาพัฒนาต่อได้ 

