package arduino;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class NewClass
{
 public static SerialPort serialPort;
 
 private static final String PORT_NAME = "/dev/ttyACM0";
 public static final int TIME_OUT = 2000;
 public static final int DATA_RATE = 9600;
 
 public static BufferedReader input;
 public static OutputStream output; 
 
 public static void main(String[] args) throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException, IOException {
     
 CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(PORT_NAME);
 try{
     
 serialPort = (gnu.io.SerialPort) portId.open(PORT_NAME, TIME_OUT);
 serialPort.setSerialPortParams(DATA_RATE,8,1,0);

 input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
 System.out.println(input);
 output = serialPort.getOutputStream();
 
 Scanner reader = new Scanner(System.in);
 
 while(true)
 {
 System.out.print("Operation (0 = Off / 1 = On / 2 = Blink ): ");
 
 if(reader.hasNext())
 {

String buf = reader.next();
 char buf2[] = buf.toCharArray();
 
output.write((byte)buf2[0]);
 
System.out.println((byte) buf2[0] + " <= sent!");
 }
 
 }
 
 }
 catch (Exception ex)
 {
 
System.out.println(ex);
 
}
 }
 
}