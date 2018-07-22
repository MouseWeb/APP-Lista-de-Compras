package paixaoporti.com.br.listadecompras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dica4Activity extends AppCompatActivity {

    private Button Dicas4Voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dica4);

        Dicas4Voltar = (Button) findViewById(R.id.voltar4id);
        Dicas4Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(dica4Activity.this,DicasActivity.class);
                startActivity(f);
                finish();
            }

        });
    }
}
