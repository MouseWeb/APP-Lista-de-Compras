package paixaoporti.com.br.listadecompras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dica3Activity extends AppCompatActivity {

    private Button Dicas3Voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dica3);

        Dicas3Voltar = (Button) findViewById(R.id.voltar3id);
        Dicas3Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(dica3Activity.this,DicasActivity.class);
                startActivity(f);
                finish();
            }

        });
    }
}
