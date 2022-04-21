package helio.prividers.test;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonObject;

import helio.providers.HttpProvider;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;

public class Tests {

	@Test
	public void test01() {
		JsonObject conf = new JsonObject();
		conf.addProperty("url", "https://ptsv2.com/t/bhwll-1650473754/post");
		conf.addProperty("method", "POST");
		conf.addProperty("body", "test post");
		HttpProvider provider = new HttpProvider();
		provider.configure(conf);
		String blocked = Flowable.create(provider, BackpressureStrategy.BUFFER).blockingFirst();
		Assert.assertTrue(blocked.equals("Thank you for this dump. I hope you have a lovely day!"));
	}
	
	@Test
	public void test02() {
		JsonObject conf = new JsonObject();
		conf.addProperty("url", "https://ptsv2.com/t/bhwll-1650473754/post");
		conf.addProperty("method", "GET");
		
		HttpProvider provider = new HttpProvider();
		provider.configure(conf);
		String blocked = Flowable.create(provider, BackpressureStrategy.BUFFER).blockingFirst();
		Assert.assertTrue(blocked.equals("Thank you for this dump. I hope you have a lovely day!"));
	}
	
	@Test
	public void test03() {
		JsonObject conf = new JsonObject();
		conf.addProperty("url", "https://datahub.ren.pt/umbraco/api/KeyPairs/GetVal");
		conf.addProperty("method", "POST");
		conf.addProperty("body", "{\"p\":[\"{Electricity/ProductionBreakdown}{field|PRODUCAO_TOTAL}{date|2022-03-11 12:44:35}{unit|GWh}{useGasDate|False}\",\"last_update_hour\",\"{Electricity/ImportBalance}{field|TOTAL}{date|2022-03-11 12:44:35}{unit|GWh}{useGasDate|False}\",\"{Electricity/ProductionBreakdown}{field|RENOVAVEL}{date|2022-03-11 12:44:35}{unit|%}{country|PT}{percentageField|PRODUCAO_TOTAL}{useGasDate|False}\",\"{Electricity/DiaryMarketPrices}{field|PRECO_PT}{date|2022-03-11 12:44:35}{unit|€/MWh}{useGasDate|False}\"]}");
		JsonObject obj = new JsonObject();
		obj.addProperty("Content-Length", 516);
		obj.addProperty("Content-Type", "application/json;charset=utf-8");

		conf.add("headers", obj);

		
		HttpProvider provider = new HttpProvider();
		provider.configure(conf);
		String blocked = Flowable.create(provider, BackpressureStrategy.BUFFER).blockingFirst();
		System.out.println(blocked);
		Assert.assertTrue(blocked.equals("{\"{Electricity/ProductionBreakdown}{field|PRODUCAO_TOTAL}{date|2022-03-11 12:44:35}{unit|GWh}{useGasDate|False}\":\"145\",\"last_update_hour\":\"12h15\",\"{Electricity/ImportBalance}{field|TOTAL}{date|2022-03-11 12:44:35}{unit|GWh}{useGasDate|False}\":\"15\",\"{Electricity/ProductionBreakdown}{field|RENOVAVEL}{date|2022-03-11 12:44:35}{unit|%}{country|PT}{percentageField|PRODUCAO_TOTAL}{useGasDate|False}\":\"69\",\"{Electricity/DiaryMarketPrices}{field|PRECO_PT}{date|2022-03-11 12:44:35}{unit|€/MWh}{useGasDate|False}\":\"285\"}\n"));
	}
}
