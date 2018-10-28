
public class CaesarCipher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String  s="BHUMIKA";
		//char[] b= s.toCharArray();
	
		char a[] = new char[26];
		int i=0;
		for(int c=65; c<=90; c++){
		
			int d= 65+((c-65)+3)% 26;
			a[i]= (char) d;
			
			
			
			System.out.println(a[i]);
			i++;
			
		}

	}

}
