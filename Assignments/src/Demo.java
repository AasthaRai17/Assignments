
interface Print{
	static void show(String abc) {
		System.out.println(abc + "test");
	}
}


public class Demo implements Print {

	public static void main(String[] args) {

     String s2 = "developer" ;
     
     Print.show(s2);
		

	}

}
