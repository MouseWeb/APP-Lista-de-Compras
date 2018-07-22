package paixaoporti.com.br.listadecompras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dica7Activity extends AppCompatActivity {

    private Button Dicas7Voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dica7);

        Dicas7Voltar = (Button) findViewById(R.id.voltar7id);
        Dicas7Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(dica7Activity.this,DicasActivity.class);
                startActivity(f);
                finish();
            }

        });
    }
}
