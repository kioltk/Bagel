const HOST = "192.168.252.81";
const PORT = 43435;

const mdns = require("mdns");

const ad = mdns.createAdvertisement(mdns.tcp("Bagel"), PORT);
ad.start();

require("net")
  .createServer(function(sock) {
    console.log("CONNECTED: " + sock.remoteAddress + ":" + sock.remotePort);
    sock.on("data", function(data) {
      console.log("" + data);
      console.log("DATA " + sock.remoteAddress + ": ", JSON.parse("" + data));
      sock.write('You said "' + data + '"');
    });
    sock.on("close", function(data) {
      console.log("CLOSED: " + sock.remoteAddress + " " + sock.remotePort);
    });
  })
  .listen(PORT, HOST);

console.log("Server listening on " + HOST + ":" + PORT);

// var a = [
//   {
//     addresses: ["fe80::7f:e25f:deeb:13b", "192.168.252.81"],
//     rawTxt: "",
//     txt: {},
//     name: "iMac — David",
//     fqdn: "iMac — David._Bagel._tcp.local",
//     host: "iMac-David.local",
//     referer: {
//       address: "192.168.252.81",
//       family: "IPv4",
//       port: 5353,
//       size: 255
//     },
//     port: 43435,
//     type: "Bagel",
//     protocol: "tcp",
//     subtypes: []
//   },
//   {
//     addresses: [
//       "fe80::7f:e25f:deeb:13b",
//       "192.168.252.81",
//       "fe80::b074:96ff:fe90:f4dd",
//       "fe80::d89e:132a:be13:40f3"
//     ],
//     name: "Bagel",
//     fqdn: "Bagel._Bagel._tcp.local",
//     host: "iMac-David.local",
//     referer: {
//       address: "192.168.252.81",
//       family: "IPv4",
//       port: 5353,
//       size: 325
//     },
//     port: 43435,
//     type: "Bagel",
//     protocol: "tcp",
//     subtypes: [],
//     rawTxt: "",
//     txt: {}
//   }
// ];
