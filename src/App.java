//Imports
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

class App {

	public static void main(String[] args) throws Exception {

		//acessar endereço da API, fazer a conexao HTTP e buscar os top 250 filmes;
		String url = "https://imdb-api.com/en/API/MostPopularTVs/k_v4b55g91";
		HttpClient client = HttpClient.newHttpClient();
		client.newBuilder();
		URI endereco = URI.create(url);

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

		for (Map<String, String> filme : listaFilmes) {
			System.out.println("\u001b[1mTitulo: \u001b[m" +(filme.get("title")));
			System.out.println("\u001b[1mPoster: \u001b[m" +(filme.get("image")));
			System.out.println("\u001b[1m\u001b[45mClassificação: " +filme.get(("imDbRating"))+"\u001b[m");

			double stars=0;
			stars = Double.parseDouble(filme.get("imDbRating"));
			if(stars == 0 && stars<=2) {
				System.out.println("\uD83D\uDC99");
			}else if(stars>2 && stars<=4) {
				System.out.println("\uD83D\uDC99\uD83D\uDC99");
			}else if(stars>4 && stars <= 6) {
				System.out.println("\uD83D\uDC99\uD83D\uDC99\uD83D\uDC99");
			}else if(stars>6 && stars <=8) {
				System.out.println("\uD83D\uDC99\uD83D\uDC99\uD83D\uDC99\uD83D\uDC99");
			}else if(stars>8) {
				System.out.println("\uD83D\uDC99\uD83D\uDC99\uD83D\uDC99\uD83D\uDC99\uD83D\uDC99");;
			}
			System.out.println();
		}
	}
}
