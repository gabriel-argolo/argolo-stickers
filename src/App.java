//Imports
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

class App {

	public static void main(String[] args) throws Exception {

		//acessar endereço da API, fazer a conexao HTTP e buscar os top 250 filmes;
		String url = Manipulador.getProp().getProperty("prop.key");
		HttpClient client = HttpClient.newHttpClient();
		HttpClient.newBuilder();
		URI endereco = URI.create(url);

		String umEstrela = "\uD83D\uDC99";
		String doisEstrela = "\uD83D\uDC99\uD83D\uDC99";
		String tresEstrela = "\uD83D\uDC99\uD83D\uDC99\uD83D\uDC99";
		String quatroEstrela = "\uD83D\uDC99\uD83D\uDC99\uD83D\uDC99\uD83D\uDC99";
		String cincoEstrela = "\uD83D\uDC99\uD83D\uDC99\uD83D\uDC99\uD83D\uDC99\uD83D\uDC99";

		System.out.println("Iniciando a Conexão...");

		HttpRequest request= HttpRequest.newBuilder(endereco).GET().build();	
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();
		System.out.println(body);
		System.out.println();

		//extrair dados, Titulo, Poster e Classificação
		//PARSEAR OS DADOS
		JsonParser parser= new JsonParser();
		List<Map<String,String>> listaFilmes = parser.parse(body);

		//Exibir e manipular os dados
		var geradora = new GeradoraDeFigurinhas();
		for (Map<String, String> filme : listaFilmes) {
			String padrao = "._(.+)";
			String urlImagem = filme.get("image").replaceAll(padrao, "");
			String titulo = filme.get("title");
			
			try {	
				InputStream inputStream = new URL(urlImagem).openStream();
				Files.createDirectories(Paths.get("./saida"));
				String nomeArquivo = "saida/"+titulo+".png";
				geradora.cria(inputStream, nomeArquivo);

				System.out.println("\u001b[1mTitulo: \u001b[m" +titulo);
				System.out.println("\u001b[1mPoster: \u001b[m" +(filme.get("image")));
				System.out.println("\u001b[1m\u001b[45mClassificação: " +filme.get(("imDbRating"))+"\u001b[m");
			}catch (FileNotFoundException f) {
				System.out.println("Imagem Nao Encontrada!");
			}
			
			double stars=0;

			try {
				stars = Double.parseDouble(filme.get("imDbRating"));	
			} catch (Exception e ) {
				System.out.println("Filme sem nota encontrado");
			}

			if(stars == 0 && stars<=2) {
				System.out.println(umEstrela);
			}else if(stars>2 && stars<=4) {
				System.out.println(doisEstrela);
			}else if(stars>4 && stars <= 6) {
				System.out.println(tresEstrela);
			}else if(stars>6 && stars <=8) {
				System.out.println(quatroEstrela);
			}else if(stars>8) {
				System.out.println(cincoEstrela);
			}
			System.out.println();
		}
	}
}