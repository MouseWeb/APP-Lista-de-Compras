package paixaoporti.com.br.listadecompras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dica8Activity extends AppCompatActivity {

    private Button Dicas8Voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dica8);

        Dicas8Voltar = (Button) findViewById(R.id.voltar8id);
        Dicas8Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(dica8Activity.this,DicasActivity.class);
                startActivity(f);
                finish();
            }

        });
    }
}
