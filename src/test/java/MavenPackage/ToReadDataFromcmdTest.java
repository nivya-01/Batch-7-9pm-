package MavenPackage;

public class ToReadDataFromcmdTest 
{
	public static void main(String[] args) {
		String URL = System.getProperty("url");
		String USERNAME = System.getProperty("username");
		String PASSWORD = System.getProperty("password");
		System.out.println(URL);
		System.out.println(USERNAME);
		System.out.println(PASSWORD);
	}

}
