package study.spring.hellospring.hello;

import java.util.Random;

public class Calc4 {
	private int x;
	private int y;
	
	public void init() {
		
		Random r = new Random(System.currentTimeMillis());
		
		x = r.nextInt();
		y = r.nextInt();
	}
	
	public int sum() {
		return this.x + this.y;
	}
}
