package paixaoporti.com.br.listadecompras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dica5Activity extends AppCompatActivity {

    private Button Dicas5Voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dica5);

        Dicas5Voltar = (Button) findViewById(R.id.voltar5id);
        Dicas5Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(dica5Activity.this,DicasActivity.class);
                startActivity(f);
                finish();
            }

        });
    }
}
