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
}
