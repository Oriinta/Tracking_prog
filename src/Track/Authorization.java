package Track;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

//Программа трэкинга работы фриланс сотрудников (скриншот экрана каждые 10 минут с сохранением в Dropbox)

// в качестве основного класса - авторизация, который в конце вызывает следующий класс с самой программой трэкинга
// параметры авторизации записаны в переменную client, которая из другого класса вызывается геттером

public class Authorization
{
    private static DbxClientV2 client;

    public static void main(String[] args)
    {
        System.out.println("авторизация, создание потока трэкинга");
        String ACCESS_TOKEN = "OLiosiVz6kAAAAAAAAAADkn0RriOcmuly0muPtKMbMr1Li79tx8XZrFjpGkGQcZD";
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        client = new DbxClientV2(config, ACCESS_TOKEN);
        TrackThread trackThread = new TrackThread();
        trackThread.start();
    }

    public static DbxClientV2 getClient(){
        return client;
    }

}
