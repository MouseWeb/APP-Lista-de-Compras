package paixaoporti.com.br.listadecompras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dica2Activity extends AppCompatActivity {

    private Button Dicas2Voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dica2);

        Dicas2Voltar = (Button) findViewById(R.id.voltar2id);
        Dicas2Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(dica2Activity.this,DicasActivity.class);
                startActivity(f);
                finish();
            }

        });
    }
}
