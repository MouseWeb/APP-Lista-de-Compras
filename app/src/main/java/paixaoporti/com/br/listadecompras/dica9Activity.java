package paixaoporti.com.br.listadecompras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dica9Activity extends AppCompatActivity {

    private Button Dicas9Voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dica9);

        Dicas9Voltar = (Button) findViewById(R.id.voltar9id);
        Dicas9Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(dica9Activity.this,DicasActivity.class);
                startActivity(f);
                finish();
            }

        });
    }
}
