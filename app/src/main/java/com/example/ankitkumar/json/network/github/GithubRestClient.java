package com.example.ankitkumar.json;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Github rest client. All github related stuff is here.
 * 
 * Only GET requests are supported.
 * 
 * TODO: Make sure we add support for ETAGs so we can properly use the 304 response
 * 		code 

 */
public class GithubRestClient {
	
	/** Actual Async client. */
	protected static AsyncHttpClient client = new AsyncHttpClient();

	/** Base URL. */
	private static final String BASE_URL = "https://api.github.com/";

	/** Do GET request on the absolute url 
	 * 
	 * @param url the url to GET to
	 * @param params params to send as the query string
	 * @param responseHandler callback
	 */
	public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		client.get(getAbsoluteUrl(url), params, responseHandler);
	}
	
	/** Get proper github URL
	 * 
	 * @param relativeUrl action to call
	 * @return full github url
	 */
	protected static String getAbsoluteUrl(String relativeUrl) {
	    return BASE_URL + relativeUrl;
	}
}
