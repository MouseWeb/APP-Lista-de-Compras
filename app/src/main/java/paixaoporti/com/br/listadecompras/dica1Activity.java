package paixaoporti.com.br.listadecompras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dica1Activity extends AppCompatActivity {

    private Button Dicas1Voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dica1);

        Dicas1Voltar = (Button) findViewById(R.id.voltar1id);
        Dicas1Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(dica1Activity.this,DicasActivity.class);
                startActivity(f);
                finish();
            }

        });
    }
}
