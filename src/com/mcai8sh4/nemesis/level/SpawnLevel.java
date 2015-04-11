package com.mcai8sh4.nemesis.level;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpawnLevel extends Level {


    public SpawnLevel(String path) {
        super(path);
    }

    protected void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            int w = width = image.getWidth();
            int h = height = image.getHeight();
            tiles = new int[w * h];
            image.getRGB(0, 0, w, h, tiles, 0, w);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception! Could not load Level File");
        }
    }


    // Grass = 0x00ff00
    // Flower = 0xFFFF00
    // Rock = 0x7F7F00
    protected void generateLevel() {

    }
}


