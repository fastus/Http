package TestExersise;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpCheck {

	public static void main(String[] args) {
		String url = "http://maps.googleapis.com/maps/api/elevation/json?locations=39.7391536,-104.9847034&sensor=true";

		JSONObject object;
		try {
			object = getJSONfromUrl(url);
			List<Double> list = new ArrayList<>();
			System.out.println(object);
			JSONArray array = object.getJSONArray("results");
			System.out.println(array);
			
			System.out.println(array.getJSONObject(0).getJSONObject("location").getDouble("lng"));
			System.out.println(array.getJSONObject(0).getJSONObject("location").getDouble("lat"));
			System.out.println(array.getJSONObject(0).getDouble("elevation"));
			System.out.println(array.getJSONObject(0).getDouble("resolution"));
			
//			for(int i = 0 ; i < array.length() ; i++){ 
//				list.add(array.getJSONObject(i).getJSONObject("location").getDouble("lng")); 
//				System.out.println(list.get(i));
//			} 
			
				
		} catch (ParseException | JSONException | IOException e) {
			e.printStackTrace();
		}

	}

	public static JSONObject getJSONfromUrl(String url)
			throws ClientProtocolException, ParseException, JSONException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse response = httpClient.execute(get);
		JSONObject obj = new JSONObject(EntityUtils.toString(response.getEntity()));
		return obj;
	}
}
