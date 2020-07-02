package Track;

import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TrackThread extends Thread
{
    private DbxClientV2 client = Authorization.getClient();;

    // переписываем метод run
    @Override
    public void run()
    {
        System.out.println("старт трэкинга");
        // в бесконечном цикле
        for(;;)
        {
            try
            {
                //делать скрин экрана, передавать фото в байтовом формате, сохранить как png
                BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

                ByteArrayOutputStream image_io = new ByteArrayOutputStream();
                ImageIO.write(image, "png", image_io);
                InputStream screenshot = new ByteArrayInputStream(image_io.toByteArray());


                //определяем текущее время, чтобы назвать файл аналогично
                SimpleDateFormat date_format = new SimpleDateFormat("yyyyMMdd_HHmmss");
                Date now = new Date();

                // Загружаем в Dropbox с новым названием файла, ждем 10 минут до след скриншота

                client.files().uploadBuilder("/"+date_format.format(now)+".png")
                        .uploadAndFinish(screenshot);
                System.out.println("сохраняю скрин");
                sleep(600_000);
            }

            // Отлавливаем исключения, если есть - пишем список

            catch (Exception e) {
                e.printStackTrace(); }
        }

    }
}
