package paixaoporti.com.br.listadecompras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dica6Activity extends AppCompatActivity {

    private Button Dicas6Voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dica6);

        Dicas6Voltar = (Button) findViewById(R.id.voltar6id);
        Dicas6Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(dica6Activity.this,DicasActivity.class);
                startActivity(f);
                finish();
            }

        });
    }
}
