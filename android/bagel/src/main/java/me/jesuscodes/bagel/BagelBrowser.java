package me.jesuscodes.bagel;

import android.util.Log;

import com.github.druk.dnssd.BrowseListener;
import com.github.druk.dnssd.DNSSD;
import com.github.druk.dnssd.DNSSDEmbedded;
import com.github.druk.dnssd.DNSSDException;
import com.github.druk.dnssd.DNSSDService;

class BagelBrowser {
    private final BabelConfiguration configuration;
    private final DNSSDService browseService;

    public BagelBrowser(BabelConfiguration configuration) {
        DNSSDService browser;
        this.configuration = configuration;
        DNSSD dnssd = new DNSSDEmbedded(Bagel.instance.context);
        try {
            browser = dnssd.browse(configuration.netserviceType, new BrowseListener() {

                @Override
                public void serviceFound(DNSSDService browser, int flags, int ifIndex,
                                         final String serviceName, String regType, String domain) {
                    Log.i("TAG", String.format("Found name=%s, domain=%s", serviceName, domain));
                }

                @Override
                public void serviceLost(DNSSDService browser, int flags, int ifIndex,
                                        String serviceName, String regType, String domain) {
                    Log.i("TAG", "Lost " + serviceName);
                }

                @Override
                public void operationFailed(DNSSDService service, int errorCode) {
                    Log.e("TAG", "error: " + errorCode);
                }
            });
        } catch (DNSSDException e) {
            browser = null;
            Log.e("TAG", "error", e);
        }
        browseService = browser;
    }

    public void send(BagelRequestPacket packet) {

    }
}
