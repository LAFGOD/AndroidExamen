package utad.app2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.milib.asyntasks.HttpJsonAsyncTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*HttpJsonAsyncTask httpJsonAsyncTask=new HttpJsonAsyncTask();
        String url = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=%s&appid=%s",40.4165,-3.52256,"metric","a37ce897b2c637531a86e89b71f6efa1" +
                "");
        httpJsonAsyncTask.execute(url);*/

        HttpJsonAsyncTask httpJsonAsyncTask2=new HttpJsonAsyncTask();
        String url2 = String.format("http://10.0.2.2/EDU/xdhaha/login.php");
        httpJsonAsyncTask2.execute(url2);

    }


}
