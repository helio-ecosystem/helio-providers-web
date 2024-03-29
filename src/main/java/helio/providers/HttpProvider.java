package helio.providers;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

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
public class HttpProvider implements DataProvider {

	private String endpoint;
	private String method;
	private String body;
	private Map<String,Object> headers;
	Logger logger = LoggerFactory.getLogger(HttpProvider.class);


	/**
	 * This constructor creates an empty {@link HttpProvider} that will need to be configured using a valid {@link JsonObject}
	 */
	public HttpProvider() {
		headers= new HashMap<>();
	}

	@Override
	public void subscribe(@NonNull FlowableEmitter<@NonNull String> emitter) throws Throwable {
		
		try {
			URL url = new URL(endpoint);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod(method);
			if(!body.isEmpty()) {
				byte[] input = body.getBytes("utf-8");
				headers.put("Content-Length", input.length);
			}
			headers.entrySet().stream().forEach(header -> con.setRequestProperty(header.getKey(), String.valueOf(header.getValue())));
			// check https://www.baeldung.com/java-http-request for further advanced configurations: cookies, time outs, ...
			if((method.toLowerCase().equals("post") || method.toLowerCase().equals("put")) && !body.isBlank()) {
				con.setDoOutput(true);
				try(OutputStream os = con.getOutputStream()) {
				    byte[] input = body.getBytes("utf-8");
				    os.write(input, 0, input.length);			
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			String content = new String(con.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
			emitter.onNext(content);
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
