package javaEclipseTest;

public class OuterClass {
	 int x = 5;
	
	 class InnerClass{
		public int myInnerMethod() {
			return x;
		}
	}

	 //static class di maaccess ang outer class
}
