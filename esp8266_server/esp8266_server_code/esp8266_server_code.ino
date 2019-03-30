#include <ESP8266WiFi.h>

const char* ssid = "room888";
const char* password = "cetrness";
const char* host = "IP OF THE ESP8266"; 
                                        
WiFiServer server(301); 
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
  
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);    
  }
  /* 
		 Start the server 
	*/
  server.begin();  
  
  
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
    }
    delay(100);    
  }
  /*
		 Wait until client sends some data 
	*/
	while (!client.available()) {
        
    if(wait_var++ == 5){
      client.stop();
      wait_var = 0;
    }
    delay(1);
    return;
  }  
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
