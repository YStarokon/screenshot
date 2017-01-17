/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ys;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import ru.yandex.qatools.ashot.AShot;

/**
 *
 * @author yuriy
 */
public class ScreenShoter {

    public static void main(String[] args) throws IOException {
        FirefoxProfile p = new FirefoxProfile();
        p.setPreference("network.proxy.no_proxies_on", "localhost, 127.0.0.1, 10.0.0.0/8, .it.loc, youtube.com, 192.168.59.0/16, .privatbank.ua, *.pb.ua");
        WebDriver driver = new FirefoxDriver(p);
         driver.get("http://google.com");
        driver.findElement(By.id("lst-ib")).clear();
        driver.findElement(By.id("lst-ib")).sendKeys("privat bank");
        driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
        System.out.println("success");

        driver.close();

        FileOutputStream stream = new FileOutputStream("/home/yuriy/NetBeansProjects/screenShot/img.png");
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
}
