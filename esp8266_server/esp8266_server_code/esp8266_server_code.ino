#include <ESP8266WiFi.h>

const char* ssid = "room888";
const char* password = "cetrness";
const char* host = "IP OF THE ESP8266"; //it will tell you the IP once it starts up
                                        //just write it here afterwards and upload


WiFiServer server(301); //just pick any port number you like
WiFiClient client;
int wait_var = 0;
void setup() {
	/* 
		 Initializing serial communication 
	*/
  Serial.begin(115200);					
  delay(10);
	/*
		wifi initialization
 */
  //Serial.println(WiFi.localIP());
  
  //Serial.print("Connecting to ");
  //Serial.println(ssid);

  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    //Serial.print(".");
  }
  //Serial.println("");
  //Serial.println("WiFi connected");
  /* 
		 Start the server 
	*/
  server.begin();
  //Serial.println("Server started");
  /* 
		 Print the IP address 
	*/
  //Serial.println(WiFi.localIP());
  
  
}
/*
	MAIN LOOP
*/
void loop() {
  /* 
		 Check if client has connected 
	*/
	if (!client){
    while (!client) {
      client = server.available();
      //Serial.println("waiting");
    }
    delay(100);
    //Serial.println("connected to client");
  }
  /* 
		 Wait until client sends some data 
	*/
	while (!client.available()) {
    
    //Serial.println("waiting for client");
    if(wait_var++ == 5){
      client.stop();
      wait_var = 0;
    }
    delay(1);
    return;
  }
  //Serial.print("Client sent:");	
  /* 
		 Read the first line of the request and send it to arduino
	*/
  String req = client.readStringUntil('\0');
	Serial.print(req);  
  client.flush();
  /* 
		 Send response to client 
	*/
  client.print("package received");
  delay(1);
}
