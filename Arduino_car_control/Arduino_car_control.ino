#include <avr/io.h>
#include <string.h>
//#include <util/delay.h>
//#include <avr/interrupt.h>


char command[10];
byte length = 0;
/*
	Setup function
*/
void setup() {
	Serial.begin(115200);
	//Serial.begin(9600);
	DDRB = 0xff;
  DDRD = 0xff;
}
/*
	MAIN LOOP
*/
void loop() {  
	if (Serial.available() > 0) {
		
		delay(5);
		length = Serial.available();
		Serial.readBytes(command, length);
		command[length] = '\0';
		Serial.print(command);
		PORTB = check_command(command);		
	}
}
/**
 *	Function that checks what command was sent from esp8266
 *	Input parameter is "String command", and that string is being deciphered
*/
byte check_command (char *command){
	if (command[0] == 'F'){		
		return 0x01;
	}
	else if (command[0] == 'B' && command[1] == 'L'){		
		return 0x06;
	}
	else if (command[0] == 'B' && command[1] == 'R'){		
		return 0x0A;
	}
	else if (command[0] == 'B')		
		return 0x02;
	else if (command[0] == 'L')
		return 0x05;
	else if (command[0] == 'R')
		return 0x09;
	else
		return 0x00;
}
