public class Downcast{
	static class A { int x;}
	static class B extends A { int y;}
	
	static B convert(A x){
		B aToB = (B) x;
		return aToB;
	}

	public static void main(String args[]) {
		A objTypeA = new A();
		objTypeA.x = 5;
		B objTypeB = convert(objTypeA);
		System.out.println(objTypeB);
	}
	
}

