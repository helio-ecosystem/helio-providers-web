package helio.providers;

import com.google.gson.JsonObject;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;

public class Test {
	
	public static String body = "_:B935cefed5e49ed1d030eb1bcb05046f2 <https://auroral.iot.linkeddata.es/def/core#hasId> \"urn:uuid:upm:sensor:pm2_5:1683223156\" .\n"
			+ "_:B935cefed5e49ed1d030eb1bcb05046f2 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <https://saref.etsi.org/core#Measurement> .\n"
			+ "_:B6a207c43d0777f1156dfa3c7300be518 <https://auroral.iot.linkeddata.es/def/core#hasId> \"urn:uuid:upm:sensor:luminosity:1683223156\" .\n"
			+ "_:B6a207c43d0777f1156dfa3c7300be518 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <https://saref.etsi.org/core#Measurement> .\n"
			+ "_:B7c891179bf16087d308303293912ad46 <https://auroral.iot.linkeddata.es/def/core#hasId> \"urn:uuid:upm:sensor:uv:1683223156\" .\n"
			+ "_:B7c891179bf16087d308303293912ad46 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <https://saref.etsi.org/core#Measurement> .\n"
			+ "_:B6f25b91fe165bf0fa90416832ccc0e22 <https://auroral.iot.linkeddata.es/def/core#hasId> \"urn:uuid:upm:sensor:co2:1683223156\" .\n"
			+ "_:B6f25b91fe165bf0fa90416832ccc0e22 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <https://saref.etsi.org/core#Measurement> .\n"
			+ "_:B5193a0c903cd7c861b321f216a95d2d9 <https://auroral.iot.linkeddata.es/def/core#hasId> \"urn:uuid:upm:sensor:humidity:1683223156\" .\n"
			+ "_:B5193a0c903cd7c861b321f216a95d2d9 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <https://saref.etsi.org/core#Measurement> .\n"
			+ "_:B3e2c4612d74f403652df4ca7d5ca7d4f <https://auroral.iot.linkeddata.es/def/core#hasId> \"urn:uuid:upm:sensor:pm10:1683223156\" .\n"
			+ "_:B3e2c4612d74f403652df4ca7d5ca7d4f <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <https://saref.etsi.org/core#Measurement> .\n"
			+ "_:B36629ba0c45b418f188910d734638454 <https://auroral.iot.linkeddata.es/def/core#hasId> \"urn:uuid:upm:sensor:sound:1683223156\" .\n"
			+ "_:B36629ba0c45b418f188910d734638454 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <https://saref.etsi.org/core#Measurement> .\n"
			+ "_:B132202cbdd80b7363975f1b124bfe44b <https://auroral.iot.linkeddata.es/def/core#hasId> \"urn:uuid:upm:sensor:pressure:1683223156\" .\n"
			+ "_:B132202cbdd80b7363975f1b124bfe44b <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <https://saref.etsi.org/core#Measurement> .\n"
			+ "_:Ba4d89b64f323850e36354eb3e91ac8c7 <https://saref.etsi.org/core#makesMeasurement> _:B6f25b91fe165bf0fa90416832ccc0e22 .\n"
			+ "_:Ba4d89b64f323850e36354eb3e91ac8c7 <https://saref.etsi.org/core#makesMeasurement> _:B935cefed5e49ed1d030eb1bcb05046f2 .\n"
			+ "_:Ba4d89b64f323850e36354eb3e91ac8c7 <https://saref.etsi.org/core#makesMeasurement> _:B6a207c43d0777f1156dfa3c7300be518 .\n"
			+ "_:Ba4d89b64f323850e36354eb3e91ac8c7 <https://saref.etsi.org/core#makesMeasurement> _:B132202cbdd80b7363975f1b124bfe44b .\n"
			+ "_:Ba4d89b64f323850e36354eb3e91ac8c7 <https://saref.etsi.org/core#makesMeasurement> _:B3e2c4612d74f403652df4ca7d5ca7d4f .\n"
			+ "_:Ba4d89b64f323850e36354eb3e91ac8c7 <https://saref.etsi.org/core#makesMeasurement> _:B367befedcc1879c84e66d1bea87287d2 .\n"
			+ "_:Ba4d89b64f323850e36354eb3e91ac8c7 <https://saref.etsi.org/core#makesMeasurement> _:B7c891179bf16087d308303293912ad46 .\n"
			+ "_:Ba4d89b64f323850e36354eb3e91ac8c7 <https://saref.etsi.org/core#makesMeasurement> _:B36629ba0c45b418f188910d734638454 .\n"
			+ "_:Ba4d89b64f323850e36354eb3e91ac8c7 <https://saref.etsi.org/core#makesMeasurement> _:B3c6ad169ca85d7890d5452a28a19df1d .\n"
			+ "_:Ba4d89b64f323850e36354eb3e91ac8c7 <https://saref.etsi.org/core#makesMeasurement> _:B5193a0c903cd7c861b321f216a95d2d9 .\n"
			+ "_:B3c6ad169ca85d7890d5452a28a19df1d <https://auroral.iot.linkeddata.es/def/core#hasId> \"urn:uuid:upm:sensor:o3:1683223156\" .\n"
			+ "_:B3c6ad169ca85d7890d5452a28a19df1d <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <https://saref.etsi.org/core#Measurement> .\n"
			+ "_:B367befedcc1879c84e66d1bea87287d2 <https://auroral.iot.linkeddata.es/def/core#hasId> \"urn:uuid:upm:sensor:temperature:1683223156\" .\n"
			+ "_:B367befedcc1879c84e66d1bea87287d2 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <https://saref.etsi.org/core#Measurement> .\n"
			+ "";

	public static String QUERY = "INSERT DATA { _:B935cefed5e49ed1d030eb1bcb05046f2 <https://auroral.iot.linkeddata.es/def/core#hasId> <https://auroral.iot.linkeddata.es/def/core#AMELIA> . }";
	public static String QUERY2 = "SELECT *  { ?s ?p ?o }";

	
	public static void main(String[] args) {
		JsonObject headers = new JsonObject();
		//headers.addProperty("Content-Type", "text/turtle");
		headers.addProperty("Content-Type", "application/sparql-update");

		headers.addProperty("Accept", "*/*");
		JsonObject conf = new JsonObject();
		//conf.addProperty("url", "http://localhost:7200/repositories/app/rdf-graphs/service?graph=http%3A%2F%2Fonto.fel.cvut.cz%2Fontologies%2Fslovn%C3%ADk%2Fdatov%C3%BD-psp-2016");
		conf.addProperty("url", "http://localhost:7200/repositories/app/statements");

		conf.addProperty("method", "POST");
		
		conf.addProperty("body", QUERY);
		conf.add("headers", headers);
		
		HttpProvider provider = new HttpProvider();
		provider.configure(conf);
	
		String blocked = Flowable.create(provider, BackpressureStrategy.BUFFER).blockingFirst();
		System.out.println(">"+blocked);

	}

}
