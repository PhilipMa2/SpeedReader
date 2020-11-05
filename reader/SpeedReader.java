package reader;

/**
 * Use the WordGenerator class to read the file and after displaying the text
 * file, should report the number of words and number of sentences it proccessed
 * 
 * @author Philip Ma
 */

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.io.PrintWriter;

public class SpeedReader {
    /**
     * Draw a String centered in the middle of a Rectangle.
     * 
     * @param g The Graphics instance.
     * @param text The String to draw.
     * @param width Width of the Rectangle.
     * @param height Height of the Rectangle.
     */
    public static void drawCenteredString(Graphics g, String text, int width, int height) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics();
        // Determine X coordinate
        int x = (width - metrics.stringWidth(text)) / 2;
        // Determine Y coordinate
        int y = (height - metrics.getHeight()) / 2 + metrics.getAscent();
        
        g.drawString(text, x, y);
    }

    /**
     * Draw a String centered around the focus character in the middle of a
     * Rectangle where the focus letter is in red.
     * 
     * @param g The Graphics instance.
     * @param text The String to draw.
     * @param width Width of the Rectangle.
     * @param height Height of the Rectangle.
     */
    public static void drawFocusedString(Graphics g, String text, int width, int height) {
        int focusIndex;
        if (text.length() < 2) {
            focusIndex = 0;
        } else if (text.length() < 6) {
            focusIndex = 1;
        } else if (text.length() < 10) {
            focusIndex = 2;
        } else if (text.length() < 14) {
            focusIndex = 3;
        } else {
            focusIndex = 4;
        }

        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics();
        // Determine X coordinate
        int x = width / 2;
        for (int i = 0; i < focusIndex; i++) {
            x -= metrics.charWidth(text.charAt(i));
        }
        // Determine Y coordinate
        int y = (height - metrics.getHeight()) / 2 + metrics.getAscent();
        
        for (int i = 0; i < text.length(); i++) {
            if (i == focusIndex) {
                g.setColor(Color.RED);
                g.drawString(text.substring(i, i + 1), x, y);
                g.setColor(Color.BLACK);
            } else {
                g.drawString(text.substring(i, i + 1), x, y);
            }
            x += metrics.charWidth(text.charAt(i));
        }
    }

    public static void main(String[] args) throws Exception {
        final String usage = new String("Usage: SpeedReader <filename> <width> <height> <font size> <wpm>");

        // Incorrect number of parameters
        if (args.length != 5) {
            System.err.println(usage);
            System.exit(1);
        }

        WordGenerator generator = new WordGenerator(args[0]);
        int width = Integer.parseInt(args[1]);
        int height = Integer.parseInt(args[2]);

        DrawingPanel panel = new DrawingPanel(width, height);
        Graphics g = panel.getGraphics();
        Font f = new Font("Courier", Font.BOLD, Integer.parseInt(args[3]));
        g.setFont(f);
        
        while (generator.hasNext()) {
            drawFocusedString(g, generator.next(), width, height);
            Thread.sleep(60000/Integer.parseInt(args[4]));
            panel.clear();
        }

        PrintWriter pen = new PrintWriter(System.out, true);
        pen.println("Number of words: " + generator.getWordCount());
        pen.println("Number of sentences: " + generator.getSentenceCount());
    }
}
