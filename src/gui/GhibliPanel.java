package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GhibliPanel extends JPanel {

    private List<String> ghibliImagePaths;
    private int currentImageIndex = 0;
    private Image currentImage;

    public GhibliPanel(){
        ghibliImagePaths = loadGhibliImagePaths();
        loadImage();
    }

    private List<String> loadGhibliImagePaths(){
        List<String> paths = new ArrayList<>();
        String ghibliDirectory = "resources/Ghibli";
        File directory = new File(ghibliDirectory);
        if (directory.isDirectory()){
            for (File file : directory.listFiles()){
                if (file.isFile() && (file.getName().endsWith(".jpg") || file.getName().endsWith(".png"))){
                    paths.add(file.getAbsolutePath());
                }
            }
        }
        return paths;
    }

    private void loadImage(){
        if (ghibliImagePaths.size() > 0){
            try {
                currentImage = ImageIO.read(new File(ghibliImagePaths.get(currentImageIndex)));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (currentImage != null){
            g.drawImage(currentImage, 0, 0, getWidth(), getHeight(), null);
        }
    }

    public void nextImage(){
        currentImageIndex = (currentImageIndex + 1) % ghibliImagePaths.size();
        loadImage();
        repaint();
    }

    public void previousImage(){
        currentImageIndex = (currentImageIndex - 1 + ghibliImagePaths.size()) % ghibliImagePaths.size();
        loadImage();
        repaint();
    }


}
