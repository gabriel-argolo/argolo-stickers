import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

	public	void cria(InputStream inputStream, String nomeArquivo) throws Exception {
		//leitura da imagem
		//InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BZGUzYTI3M2EtZmM0Yy00NGUyLWI4ODEtN2Q3ZGJlYzhhZjU3XkEyXkFqcGdeQXVyNTM0OTY1OQ@@").openStream();

		BufferedImage imagemOriginal = ImageIO.read(inputStream);

		//nova imagem em memora com transparencia e com tamamho novo
		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura+200;

		BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
		//copiar a imagem original pra novo imagem (Em memoria)
		Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
		graphics.drawImage(imagemOriginal,0,0,null);

		//configurar fonte
		var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
		graphics.setFont(fonte);
		graphics.setColor(Color.YELLOW);
		
		//escrever uma frase na nova imagem
		
		
		
		graphics.drawString("TOPZERA", 0, novaAltura-100);

		//escrever a nova imagem em um arquivo
		ImageIO.write(novaImagem,"png", new File(nomeArquivo));
	}
	//	public static void main(String[] args) throws Exception {
	//		var geradora = new GeradoraDeFigurinhas();
	//		geradora.cria();
	//	}
}
