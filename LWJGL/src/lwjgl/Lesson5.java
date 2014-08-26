package lwjgl;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.glu.GLU;

public class Lesson5 {
    private boolean done = false;
    private boolean fullscreen = false;
    private final String windowTitle = "3D Shapes";
    private boolean f1 = false;

    private float rquad;                // Angle For The Quad     ( NEW )
    private DisplayMode displayMode;

    public static void main(String args[]) {
        boolean fullscreen = false;
        if(args.length>0) {
            if(args[0].equalsIgnoreCase("fullscreen")) {
                fullscreen = true;
            }
        }

        Lesson5 l5 = new Lesson5();
        l5.run(fullscreen);
    }
    public void run(boolean fullscreen) {
        this.fullscreen = fullscreen;
        try {
            init();
            while (!done) {
                mainloop();
                render();
                Display.update();
            }
            cleanup();
        }
        catch (Exception e) {
            System.exit(0);
        }
    }
    private void mainloop() {
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {done = true;}
        if(Display.isCloseRequested()) { done = true;}
    }


    private boolean render() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);          // Clear The Screen And The Depth Buffer

        GL11.glLoadIdentity();                          // Reset The Current Modelview Matrix
        GL11.glTranslatef(0.0f,0.0f,-7.0f);             // Move Right 1.5 Units And Into The Screen 6.0
        GL11.glRotatef(rquad,1.0f,1.0f,1.0f);               // Rotate The Quad On The X axis ( NEW )
        GL11.glColor3f(0.5f,0.5f,1.0f);                 // Set The Color To Blue One Time Only
        GL11.glBegin(GL11.GL_QUADS);                        // Draw A Quad
            GL11.glColor3f(0.0f,1.0f,0.0f);             // Set The Color To Green
            GL11.glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Top)
            GL11.glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Top)
            GL11.glVertex3f(-1.0f, 1.0f, 1.0f);         // Bottom Left Of The Quad (Top)
            GL11.glVertex3f( 1.0f, 1.0f, 1.0f);         // Bottom Right Of The Quad (Top)
            GL11.glColor3f(1.0f,0.5f,0.0f);             // Set The Color To Orange
            GL11.glVertex3f( 1.0f,-1.0f, 1.0f);         // Top Right Of The Quad (Bottom)
            GL11.glVertex3f(-1.0f,-1.0f, 1.0f);         // Top Left Of The Quad (Bottom)
            GL11.glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Bottom)
            GL11.glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Bottom)
            GL11.glColor3f(1.0f,0.0f,0.0f);             // Set The Color To Red
            GL11.glVertex3f( 1.0f, 1.0f, 1.0f);         // Top Right Of The Quad (Front)
            GL11.glVertex3f(-1.0f, 1.0f, 1.0f);         // Top Left Of The Quad (Front)
            GL11.glVertex3f(-1.0f,-1.0f, 1.0f);         // Bottom Left Of The Quad (Front)
            GL11.glVertex3f( 1.0f,-1.0f, 1.0f);         // Bottom Right Of The Quad (Front)
            GL11.glColor3f(1.0f,1.0f,0.0f);             // Set The Color To Yellow
            GL11.glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Back)
            GL11.glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Back)
            GL11.glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Back)
            GL11.glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Back)
            GL11.glColor3f(0.0f,0.0f,1.0f);             // Set The Color To Blue
            GL11.glVertex3f(-1.0f, 1.0f, 1.0f);         // Top Right Of The Quad (Left)
            GL11.glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Left)
            GL11.glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Left)
            GL11.glVertex3f(-1.0f,-1.0f, 1.0f);         // Bottom Right Of The Quad (Left)
            GL11.glColor3f(1.0f,0.0f,1.0f);             // Set The Color To Violet
            GL11.glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Right)
            GL11.glVertex3f( 1.0f, 1.0f, 1.0f);         // Top Left Of The Quad (Right)
            GL11.glVertex3f( 1.0f,-1.0f, 1.0f);         // Bottom Left Of The Quad (Right)
            GL11.glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Right)
        GL11.glEnd();                                       // Done Drawing The Quad

        rquad-=1.0f;                                   // Decrease The Rotation Variable For The Quad     ( NEW )
        return true;
    }
    private void createWindow() throws Exception {
        Display.setFullscreen(fullscreen);
        displayMode = new DisplayMode(800,600);
        Display.setDisplayMode(displayMode);
        Display.setTitle(windowTitle);
        Display.create();
    }
    private void init() throws Exception {
        createWindow();

        initGL();
    }

    private void initGL() {
        GL11.glEnable(GL11.GL_TEXTURE_2D); // Enable Texture Mapping
        GL11.glShadeModel(GL11.GL_SMOOTH); // Enable Smooth Shading
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Black Background
        GL11.glClearDepth(1.0); // Depth Buffer Setup
        GL11.glEnable(GL11.GL_DEPTH_TEST); // Enables Depth Testing
        GL11.glDepthFunc(GL11.GL_LEQUAL); // The Type Of Depth Testing To Do

        GL11.glMatrixMode(GL11.GL_PROJECTION); // Select The Projection Matrix
        GL11.glLoadIdentity(); // Reset The Projection Matrix

        // Calculate The Aspect Ratio Of The Window
        GLU.gluPerspective(
          45.0f,
          (float) displayMode.getWidth() / (float) displayMode.getHeight(),
          0.1f,
          100.0f);
        GL11.glMatrixMode(GL11.GL_MODELVIEW); // Select The Modelview Matrix

        // Really Nice Perspective Calculations
        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
    }
    private static void cleanup() {
        Display.destroy();
    }
}

