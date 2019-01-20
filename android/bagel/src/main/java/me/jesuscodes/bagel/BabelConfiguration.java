package me.jesuscodes.bagel;

class BabelConfiguration {

    BagelProject project;
    BagelDevice device;

    int netservicePort;
    String netserviceType;
    String netserviceDomain;
    String netserviceName;

    String deepLinkStarterURL;
    String publicKeyName;


    public static BabelConfiguration defaultConfiguration() {
        return new BabelConfiguration() {{
            project = new BagelProject();
            project.projectName = BagelUtility.projectName();

            device = new BagelDevice();
            device.deviceId = BagelUtility.deviceId();
            device.deviceName = BagelUtility.deviceName();
            device.deviceDescription = BagelUtility.deviceDescription();

            netservicePort = 43435;
            netserviceDomain = "";
            netserviceType = "_Bagel._tcp";
            netserviceName = "";

        }};
    }
}
