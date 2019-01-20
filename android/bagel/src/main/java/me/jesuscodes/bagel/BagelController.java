package me.jesuscodes.bagel;

import java.util.ArrayList;

class BagelController {

    private final BagelBrowser browser;
    private final BabelConfiguration configuration;
    public ArrayList<BagelOkHttpInterceptor> interceptors = new ArrayList<>();

    BagelController(BabelConfiguration configuration) {
        this.configuration = configuration;
        this.browser = new BagelBrowser(configuration);
    }

    void send(BagelRequestPacket packet) {
        packet.device = this.configuration.device;
        packet.project = this.configuration.project;
        this.browser.send(packet);
    }
}
