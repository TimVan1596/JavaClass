package web.atb.ceshi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 用字节流实现图片的复制
 *
 * @author 安天宝
 * @date 2018/9/21
 */
public class CopyPicture {
    public static void main(String[] args) {
        try {
            //FileInputStream fis = new FileInputStream("E:\\touxiang.jpg");
            //FileOutputStream fos = new FileOutputStream("E:\\JAVA\\java_direction_class\\web\\atb\\ceshi\\img\\touxiang.jpg");
            FileInputStream fis = new FileInputStream("localhost:3306\\atb\\ceshi\\img\\touxiang.jpg");
            FileOutputStream fos = new FileOutputStream("E:\\JAVA\\java_direction_class\\web\\atb\\ceshi\\img\\ce.jpg");
            int n;
            byte[] bytes = new byte[1024];
            while ((n = (fis.read(bytes))) != -1) {
                fos.write(bytes, 0, n);
            }
            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println("要复制的图片不存在，路径为：'D:\\touxiang.jpg'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
