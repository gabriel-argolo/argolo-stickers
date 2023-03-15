import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Manipulador {
	public static Properties getProp() throws IOException
	{
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("./key.properties");
		props.load(file);
		return props;

	}

	//	public static void main(String args[]) throws IOException{
	//		System.out.println("**********Teste usando arquivo de propriedades**********");
	//		Properties prop = getProp();
	//		System.out.println(prop.getProperty("prop.key"));
	//	}
}