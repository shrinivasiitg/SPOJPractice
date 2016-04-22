package com.javaCoding.practice;

public class PassByReference {

	public static void main(String[] args) {
		Operation op = new Operation(4);
		System.out.println(op.getData());
		Operate operate = new Operate();
		operate.changeData(op, 3);
		System.out.println(op.getData());
	}

}

class Operation {
	public int data;
	public Operation(int data) {
		this.data = data;
	}
	int getData() {
		return data;
	}
}

class Operate {
	void changeData(Operation op, int data) {
		op.data = data;
	}
}