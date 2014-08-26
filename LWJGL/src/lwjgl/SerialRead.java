package lwjgl;

import jssc.SerialPort;
import jssc.SerialPortException;

public class SerialRead {

    public static void main(String[] args) {
        SerialPort serialPort = new SerialPort("/dev/ttyACM0");
        try {
            serialPort.openPort();//Open serial port
            serialPort.setParams(9600, 8, 1, 0);//Set params.
           
            String inputLine=serialPort.readString(2);
            System.out.println(inputLine);
            //serialPort.closePort();//Close serial port
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }
}
