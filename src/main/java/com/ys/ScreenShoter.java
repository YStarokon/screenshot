/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ys;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import ru.yandex.qatools.ashot.AShot;

/**
 *
 * @author yuriy
 */
public class ScreenShoter {
    
    
    private static final String BASE_DIRECTORY = "/home/yuriy/NetBeansProjects/screenShot";

    public static void main(String[] args) throws IOException {
        FirefoxProfile p = new FirefoxProfile();
        FirefoxBinary binary = new FirefoxBinary(new File("/home/yuriy/work/firefox/firefox"));
        //p.
        p.setPreference("network.proxy.no_proxies_on", "localhost, 127.0.0.1, 10.0.0.0/8, .it.loc, youtube.com, 192.168.59.0/16, .privatbank.ua, *.pb.ua");
        WebDriver driver = new FirefoxDriver(binary, p);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("https://pozyczkaportal.pl");

        driver.close();
        
        FileOutputStream stream = new FileOutputStream(BASE_DIRECTORY+"/img.png");
        try {
            stream.write(captureScreenShot(driver));
        } finally {
            stream.close();
        }

    }

    private static byte[] captureScreenShot(WebDriver driver) throws IOException {
        //WebDriver augmentedDriver = new Augmenter().augment(driver);
        BufferedImage image = new AShot().takeScreenshot(driver).getImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;
    }
    
    private String generateFile(){
    
    }
    
    private List<String> generateFile(){
    
    }
}
