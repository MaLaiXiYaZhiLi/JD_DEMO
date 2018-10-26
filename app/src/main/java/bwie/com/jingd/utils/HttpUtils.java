package bwie.com.jingd.utils;

import java.io.IOException;

import bwie.com.jingd.api.Api;
import bwie.com.jingd.api.Api2;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtils {

    public final Api2 apiClient;
    private HttpUtils(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new LoggingInterceptor()).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiClient = retrofit.create(Api2.class);
    }
    public class LoggingInterceptor implements Interceptor {
        @Override public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
     //   Logger.info(String.format("Sending request %s on %s%n%s",
       //         request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
//           logger.info(String.format("Received response for %s in %.1fms%n%s",
//                  response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }
    private static class GetHttpUtilsInstance{
        private static HttpUtils httpUtils = new HttpUtils();
    }
    public static HttpUtils getHttpUtilsInstance(){
        return  GetHttpUtilsInstance.httpUtils;
    }
}
