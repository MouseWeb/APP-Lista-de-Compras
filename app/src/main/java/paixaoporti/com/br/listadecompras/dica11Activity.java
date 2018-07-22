package paixaoporti.com.br.listadecompras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dica11Activity extends AppCompatActivity {

    private Button Dicas11Voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dica11);

        Dicas11Voltar = (Button) findViewById(R.id.voltar11id);
        Dicas11Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(dica11Activity.this,DicasActivity.class);
                startActivity(f);
                finish();
            }

        });
    }
}
