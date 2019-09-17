package br.com.myfoto;

import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.net.URI;

public class MainActivity extends AppCompatActivity {

    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCapturar(View v){
        File diretorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String nomeImagem  = diretorio.getPath() + "/" + System.currentTimeMillis() + ".jpg";
        uri = Uri.fromFile(new File(nomeImagem));
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent,52);
    }

    public void onVisualizar(View view){
        Intent intent = new Intent (Intent.ACTION_VIEW);
        intent.setDataAndType(uri,"image/jpeg");
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 52 && resultCode == RESULT_OK) {
            Toast.makeText(this, "Imagem carturada!!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setData(uri);
            this.sendBroadcast(intent);
        }
        else{
            Toast.makeText(this,"Imagem não capturada",Toast.LENGTH_LONG).show();
        }
    }


}
