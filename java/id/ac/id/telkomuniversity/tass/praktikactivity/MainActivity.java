package id.ac.id.telkomuniversity.tass.praktikactivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button btn;
EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        edit = findViewById(R.id.editTextTextPersonName);


        btn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {


              dialog();
              }



        });
    }
    public void send(){
       int NOTIFICATION_ID =234;
       String CHANNEL_ID = "my_chanel_01";
       Intent intent = new Intent(this, MainActivity.class);
       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.O){
            CharSequence name = CHANNEL_ID;
            String description = CHANNEL_ID;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new
                    NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle("notif");
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentText("Berhasil pindah ke Activity kedua");
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText("Berhasil pindah ke Activity kedua"));
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(pendingIntent);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID,builder.build());
    }
    public void dialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pesan");
        builder.setMessage("yakin ingin pindah?");
         builder.setPositiveButton("ya", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {

                 String edittext;
                 edittext = edit.getText().toString();
                 if ( edittext.matches("")){
                     Toast.makeText(MainActivity.this,"input tidak boleh kosong",Toast.LENGTH_LONG).show();
                 } else {
                     Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                     intent.putExtra("edit",edittext);

                     startActivity(intent);
                     send();



                 }
             }
         });
         builder.setNegativeButton("tidak",null);
         builder.show();
    }



}