package server;

import java.awt.Robot;
import java.awt.event.InputEvent;
 
public class MouseClass {
 
 public static void main(String[] args) throws Exception {
 
            Robot robot = new Robot();

            robot.mouseMove(15, 15);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
 
 }
}
