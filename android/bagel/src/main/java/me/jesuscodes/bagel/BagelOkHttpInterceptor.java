package me.jesuscodes.bagel;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

class BagelOkHttpInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        long start = System.currentTimeMillis();
        Request request = chain.request();
        Response response = chain.proceed(request);
        long end = System.currentTimeMillis();

        BagelRequestInfo info = new BagelRequestInfo();
        info.url = request.url().url();
        info.requestHeaders = request.headers().toMultimap();
        if (request.body() != null) {
            Buffer requestBuffer = new Buffer();
            request.body().writeTo(requestBuffer);
            info.requestBody = requestBuffer.readByteArray();
        }
        info.requestMethod = request.method();
        info.responseHeaders = response.headers().toMultimap();
        if (response.body() != null) {
            info.responseBody = response.body().bytes();
        }
        info.statusCode = response.code();

        info.start = start;
        info.end = end;

        BagelRequestPacket packet = new BagelRequestPacket();
        packet.packedId = BagelUtility.uuid();
        packet.requestInfo = info;

        return response;
    }
}
