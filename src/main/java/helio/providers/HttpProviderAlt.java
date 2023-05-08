package helio.providers;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import helio.blueprints.DataProvider;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.FlowableEmitter;


/**
 * This object implements the {@link DataProvider} interface allowing to retrieve data from using HTTP(s) methods GET, POST, PUT, DELETE, or PATCH.
 * This object can be configured with a {@link JsonObject} that may contain three keys: 'url' (mandatory) specifying a valid HTTP(s) URL, 'method' (mandatory) specifies any HTTP method supported by {@link HttpURLConnection} (GET,POST,PUT,POST,DELETE,PATCH), and headers (optional) that is a Json document (key-value) with the headers with which the request will be sent.
 * @author Andrea Cimmino
 *
 */
public class HttpProviderAlt implements DataProvider {

	private String endpoint;
	private String method;
	private String body;
	private Map<String,Object> headers;
	Logger logger = LoggerFactory.getLogger(HttpProviderAlt.class);


	/**
	 * This constructor creates an empty {@link HttpProviderAlt} that will need to be configured using a valid {@link JsonObject}
	 */
	public HttpProviderAlt() {
		headers= new HashMap<>();
	}

	@Override
	public void subscribe(@NonNull FlowableEmitter<@NonNull String> emitter) throws Throwable {
		
		try {
			HttpRequest request = null;
			Builder builder = HttpRequest.newBuilder()
					  .uri(new URI(endpoint));
			for(Entry<String, Object> header:headers.entrySet()) {
				builder.header(header.getKey(), String.valueOf(header.getValue()));
			}

			if(method.equalsIgnoreCase("get")) {
				builder.GET();
			}else if(method.equalsIgnoreCase("post")) {
				builder.POST(HttpRequest.BodyPublishers.ofByteArray(body.getBytes()));
			}
			request = builder.build();
			HttpClient client = HttpClient.newBuilder().build();
			
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			System.out.println(response.statusCode());
			System.out.println(response.body());
			emitter.onNext(response.toString());
			emitter.onComplete();
		} catch(Exception e) {
			logger.error(e.toString());
			emitter.onError(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void configure(JsonObject configuration) {
		if(configuration.has("url")) {
			String resourceURLAux = configuration.get("url").getAsString();
			if(resourceURLAux.isEmpty()) {
				throw new IllegalArgumentException("HttpProvider needs to receive non empty value for the key 'url'");
			}else{
				this.endpoint = resourceURLAux;
			}
		}else {
			throw new IllegalArgumentException("HttpProvider needs to receive json object with the mandatory key 'url'");
		}
		if(configuration.has("headers")) {
			JsonObject headersJson = configuration.get("headers").getAsJsonObject();
			Gson gson = new Gson();
			headers = gson.fromJson(headersJson, HashMap.class);

		}
		if(configuration.has("method")) {
			String methodAux = configuration.get("method").getAsString();
			if(methodAux.isEmpty()) {
				throw new IllegalArgumentException("HttpProvider needs to receive non empty value for the key 'method', allowed values are GET, POST, PUT");
			}else{
				this.method = methodAux;
			}
		}else {
			throw new IllegalArgumentException("HttpProvider needs to receive json object with the mandatory key 'method'");
		}
		if(configuration.has("body")) {
			String bodyAux = configuration.get("body").getAsString();
			if(bodyAux.isEmpty()) {
				throw new IllegalArgumentException("HttpProvider needs to receive non empty value for the key 'body'");
			}else{
				this.body = bodyAux;
			}

		}
	}

	

}
