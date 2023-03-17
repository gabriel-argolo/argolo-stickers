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
		BufferedImage imagemOriginal = ImageIO.read(inputStream);

		//nova imagem em memora com transparencia e com tamamho novo
		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura+300;

		BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
		//copiar a imagem original pra novo imagem (Em memoria)
		Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
		graphics.drawImage(imagemOriginal,0,0,null);

		//configurar fonte
		var fonte = new Font("impact", Font.ITALIC, 250);
		graphics.setFont(fonte);
		graphics.setColor(Color.YELLOW);

		//escrever uma frase na nova imagem
		var texto = "Topzera";
		var larguraTexto = graphics.getFontMetrics().stringWidth(texto);
		var posicaoHorizontal = ((novaImagem.getWidth())/2) - (larguraTexto/2);
		graphics.drawString(texto, posicaoHorizontal, novaAltura-50);

		//escrever a nova imagem em um arquivo
		ImageIO.write(novaImagem,"png", new File(nomeArquivo));
	}

}
