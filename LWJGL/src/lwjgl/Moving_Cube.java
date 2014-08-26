package lwjgl;

import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Moving_Cube {

public static final Logger LOGGER = Logger.getLogger(Main.class.getName());

private float squareO;
private float squareX;
private float squareY;
private float squareZ;

public static void main(String[] args) throws LWJGLException {

Moving_Cube main = null;

try {
System.out.println("Keys:");
System.out.println("down  - Shrink");
System.out.println("up    - Grow");
System.out.println("left  - Rotate left");
System.out.println("right - Rotate right");
System.out.println("esc   - Exit");

main = new Moving_Cube();
main.create();
main.run();
}

finally {
if(main != null) {
main.destroy();
}
}
}

public Moving_Cube() {
squareX = 0;
squareY = 0;
squareZ = 0;
squareO = -70;
}

public void create() throws LWJGLException {

Display.setDisplayMode(new DisplayMode(800,600));
Display.setFullscreen(false);
Display.setTitle("Moving Cube");
Display.create();
Keyboard.create();
Mouse.setGrabbed(false);
Mouse.create();
initGL();
}

public void destroy() {
Mouse.destroy();
Keyboard.destroy();
Display.destroy();
}

public void initGL() {
        GL11.glEnable(GL11.GL_TEXTURE_2D); // Enable Texture Mapping
        GL11.glShadeModel(GL11.GL_SMOOTH); // Enable Smooth Shading
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Black Background
        GL11.glClearDepth(1.0); // Depth Buffer Setup
        GL11.glEnable(GL11.GL_DEPTH_TEST); // Enables Depth Testing
        GL11.glDepthFunc(GL11.GL_LEQUAL); // The Type Of Depth Testing To Do

        GL11.glMatrixMode(GL11.GL_PROJECTION); // Select The Projection Matrix
        GL11.glLoadIdentity(); // Reset The Projection Matrix
        // Calculate The Aspect Ratio Of The Window
        GLU.gluPerspective( 45.0f, (float) 800 / (float) 600, 0.1f, 100.0f);
        GL11.glMatrixMode(GL11.GL_MODELVIEW); // Select The Modelview Matrix
        // Really Nice Perspective Calculations
        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
}

public void processKeyboard() {
if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {--squareO;}
if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {++squareO;}
if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {++squareZ;}
if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {--squareZ;}
}

public void processMouse() {
squareX = Mouse.getX();
squareY = Mouse.getY();

}

public boolean render() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);          // Clear The Screen And The Depth Buffer
        GL11.glLoadIdentity();                          // Reset The Current Modelview Matrix

        GL11.glTranslatef(0.0f,0.0f,(squareO/10));             // Move Right 1.5 Units And Into The Screen 6.0
        GL11.glRotatef(squareY,1.0f,0.0f,0.0f); 
        GL11.glRotatef(squareX,0.0f,1.0f,0.0f);
        GL11.glRotatef(squareZ,0.0f,0.0f,1.0f);


        GL11.glColor3f(0.5f,0.5f,1.0f);                 // Set The Color To Blue One Time Only
        GL11.glBegin(GL11.GL_QUADS);                        // Draw A Quad
            GL11.glColor3f(0.0f,1.0f,0.0f);             // Set The Color To Green
            GL11.glVertex3f( 1.0f, 1.5f,-0.5f);         // Top Right Of The Quad (Top)
            GL11.glVertex3f(-1.0f, 1.5f,-0.5f);         // Top Left Of The Quad (Top)
            GL11.glVertex3f(-1.0f, 1.5f, 0.5f);         // Bottom Left Of The Quad (Top)
            GL11.glVertex3f( 1.0f, 1.5f, 0.5f);         // Bottom Right Of The Quad (Top)
            
            GL11.glColor3f(1.0f,0.5f,0.0f);             // Set The Color To Orange
            GL11.glVertex3f( 1.0f,-1.5f, 0.5f);         // Top Right Of The Quad (Bottom)
            GL11.glVertex3f(-1.0f,-1.5f, 0.5f);         // Top Left Of The Quad (Bottom)
            GL11.glVertex3f(-1.0f,-1.5f,-0.5f);         // Bottom Left Of The Quad (Bottom)
            GL11.glVertex3f( 1.0f,-1.5f,-0.5f);         // Bottom Right Of The Quad (Bottom)
            
            GL11.glColor3f(1.0f,0.0f,0.0f);             // Set The Color To Red
            GL11.glVertex3f( 1.0f, 1.5f, 0.5f);         // Top Right Of The Quad (Front)
            GL11.glVertex3f(-1.0f, 1.5f, 0.5f);         // Top Left Of The Quad (Front)
            GL11.glVertex3f(-1.0f,-1.5f, 0.5f);         // Bottom Left Of The Quad (Front)
            GL11.glVertex3f( 1.0f,-1.5f, 0.5f);         // Bottom Right Of The Quad (Front)
            
            GL11.glColor3f(1.0f,1.0f,0.0f);             // Set The Color To Yellow
            GL11.glVertex3f( 1.0f,-1.5f,-0.5f);         // Bottom Left Of The Quad (Back)
            GL11.glVertex3f(-1.0f,-1.5f,-0.5f);         // Bottom Right Of The Quad (Back)
            GL11.glVertex3f(-1.0f, 1.5f,-0.5f);         // Top Right Of The Quad (Back)
            GL11.glVertex3f( 1.0f, 1.5f,-0.5f);         // Top Left Of The Quad (Back)
            
            GL11.glColor3f(0.0f,0.0f,1.0f);             // Set The Color To Blue
            GL11.glVertex3f(-1.0f, 1.5f, 0.5f);         // Top Right Of The Quad (Left)
            GL11.glVertex3f(-1.0f, 1.5f,-0.5f);         // Top Left Of The Quad (Left)
            GL11.glVertex3f(-1.0f,-1.5f,-0.5f);         // Bottom Left Of The Quad (Left)
            GL11.glVertex3f(-1.0f,-1.5f, 0.5f);         // Bottom Right Of The Quad (Left)
            
            GL11.glColor3f(1.0f,0.0f,1.0f);             // Set The Color To Violet
            GL11.glVertex3f( 1.0f, 1.5f,-0.5f);         // Top Right Of The Quad (Right)
            GL11.glVertex3f( 1.0f, 1.5f, 0.5f);         // Top Left Of The Quad (Right)
            GL11.glVertex3f( 1.0f,-1.5f, 0.5f);         // Bottom Left Of The Quad (Right)
            GL11.glVertex3f( 1.0f,-1.5f,-0.5f);         // Bottom Right Of The Quad (Right)
        GL11.glEnd();
      return true;
}

public void run() {
while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
if(Display.isVisible()) {
processKeyboard();
processMouse();
render();
}
else {
if(Display.isDirty()) {
render();
}
try {
Thread.sleep(100);
}
catch(InterruptedException ex) {
}
}
Display.update();
Display.sync(60);
}
}
}