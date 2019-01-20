package me.jesuscodes.bagel;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class BagelRequestInfo {
    public URL url;

    public Map<String, List<String>> requestHeaders;
    public byte[] requestBody;
    public String requestMethod;

    public Map<String, List<String>> responseHeaders;
    public byte[] responseBody;

    public int statusCode;

    public long start;
    public long end;
}
