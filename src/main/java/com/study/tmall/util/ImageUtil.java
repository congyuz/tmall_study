package com.study.tmall.util;

import javax.imageio.ImageIO;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class ImageUtil {
    public static BufferedImage change2jpg(File f){
        try{
            Image i= Toolkit.getDefaultToolkit().createImage(f.getAbsolutePath());
            //PixelGrabber从指定的图像i中抓取矩形部分的像素子集
            //forceRGB 参数为 true，则像素将总是以默认 RGB ColorModel 形式存储
            PixelGrabber pg=new PixelGrabber(i,0,0,-1,-1,true);
            //请求 Image 或 ImageProducer 开始传递像素，并等待传递完相关矩形中的所有像素
            pg.grabPixels();
            int width=pg.getWidth(),height=pg.getHeight();
            final int[] RGB_MASKS={0xFF0000,0xFF00,0xFF};
            //DirectColorModel类是使用像素值的ColorModel类，像素值以单独样本的形式表示RGB颜色
            final ColorModel RGB_OPAQUE=new DirectColorModel(32,RGB_MASKS[0],RGB_MASKS[1],RGB_MASKS[2]);
            DataBuffer buffer = new DataBufferInt((int[])pg.getPixels(),pg.getWidth()*pg.getHeight());
            //该类供像素写入功能
            //根据SinglePixelPackedSampleModel创建一个具有指定DataBuffer、宽度、高度、扫描行跨度和band掩码的Raster
            WritableRaster raster=Raster.createPackedRaster(buffer,width,height,width,RGB_MASKS,null);
            //Raster 的 SampleModel 中 band 的数量和类型必须与 ColorModel 所要求的数量和类型相匹配
            //isRasterPremultiplied如果为true，则raster中的数据已预乘以alpha
            BufferedImage img = new BufferedImage(RGB_OPAQUE,raster,false,null);
            return img;
        }catch(InterruptedException e){
            e.printStackTrace();
            return null;
        }
    }
    public static void resizeImage(File srcFile,int width,int height,File destFile){
        try{
            if(!destFile.getParentFile().exists()) destFile.getParentFile().mkdirs();
            Image i= ImageIO.read(srcFile);
            i=resizeImage(i,width,height);
            ImageIO.write((RenderedImage)i,"jpg",destFile);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static Image resizeImage(Image srcImage,int width,int height){
        try{
            BufferedImage buffImg=null;
            //TYPE_INT_RGB表示一个图像具有合成整数像素的 8 位 RGB 颜色分量
            buffImg=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            //getScaledInstance创建此图像的缩放版本，使用Image.SCALE_SMOOTH算法，压缩后的图片质量相对比较光滑，没有明显的锯齿形
            buffImg.getGraphics().drawImage(srcImage.getScaledInstance(width,height,Image.SCALE_SMOOTH),0,0,null);
            return buffImg;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
