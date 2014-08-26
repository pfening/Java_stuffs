package arduino;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.InputStreamReader;
import java.io.OutputStream;


public class arduinoComm2
{
  public SerialPort serialPort;

 private static final String PORT_NAME = "/dev/ttyACM0";
 public static final int TIME_OUT = 2000;
 public static final int DATA_RATE = 9600;
 
 public static BufferedReader input;
 public static OutputStream output;
    
 public void initialize() throws NoSuchPortException, PortInUseException {
 CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(PORT_NAME);
 
try {
 serialPort = (SerialPort) portId.open(PORT_NAME, TIME_OUT);
 serialPort.setSerialPortParams(DATA_RATE,8,1,0);
 
// open the streams
 input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
 output = serialPort.getOutputStream();
 char ch = 1;
 output.write(ch);
  
 // add event listeners
 serialPort.addEventListener((SerialPortEventListener) this);
 serialPort.notifyOnDataAvailable(true);
 } catch (Exception e) {
 System.err.println(e.toString());
 }
 }
    
 public synchronized void close() {
 if (serialPort != null) {
 serialPort.removeEventListener();
 serialPort.close();
 }
 }
 
public synchronized void serialEvent(SerialPortEvent oEvent) {
 if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
 try {
 String inputLine=input.readLine();
 System.out.println(inputLine);
 } catch (Exception e) {
 System.err.println(e.toString());
 }
 }
 
 }
 
 public static synchronized void writeData(String data) {
 System.out.println("Sent: " + data);
 try {
 output.write(data.getBytes());
 } catch (Exception e) {
 System.out.println("could not write to port");
 }
 }
 
public static void main(String[] args) throws Exception {
 SerialClass main = new SerialClass();
 main.initialize();
 Thread t=new Thread() {
 public void run() {
 //the following line will keep this app alive for 1000 seconds,
 //waiting for events to occur and responding to them (printing incoming messages to console).
 try {Thread.sleep(1500);
 writeData("2");} catch (InterruptedException ie) {}
 }
 };
 t.start();
 System.out.println("Started");
 }
}
