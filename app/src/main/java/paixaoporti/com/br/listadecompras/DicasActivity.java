package paixaoporti.com.br.listadecompras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DicasActivity extends AppCompatActivity {

    private Button Dicas11;
    private Button Dicas1;
    private Button Dicas2;
    private Button Dicas3;
    private Button Dicas4;
    private Button Dicas5;
    private Button Dicas6;
    private Button Dicas7;
    private Button Dicas8;
    private Button Dicas9;
    private Button Dicas10;
    private Button VoltarHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dicas);

        Dicas11 = (Button) findViewById(R.id.dica11id);
        Dicas11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(DicasActivity.this,dica11Activity.class);
                startActivity(f);
                finish();
            }

        });

        Dicas1 = (Button) findViewById(R.id.dica1id);
        Dicas1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(DicasActivity.this,dica1Activity.class);
                startActivity(f);
                finish();
            }

        });

        Dicas2 = (Button) findViewById(R.id.dica2id);
        Dicas2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(DicasActivity.this,dica2Activity.class);
                startActivity(f);
                finish();
            }

        });

        Dicas3 = (Button) findViewById(R.id.dica3id);
        Dicas3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(DicasActivity.this,dica3Activity.class);
                startActivity(f);
                finish();
            }

        });

        Dicas4 = (Button) findViewById(R.id.dica4id);
        Dicas4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(DicasActivity.this,dica4Activity.class);
                startActivity(f);
                finish();
            }

        });

        Dicas5 = (Button) findViewById(R.id.dica5id);
        Dicas5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(DicasActivity.this,dica5Activity.class);
                startActivity(f);
                finish();
            }

        });

        Dicas6 = (Button) findViewById(R.id.dica6id);
        Dicas6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(DicasActivity.this,dica6Activity.class);
                startActivity(f);
                finish();
            }

        });

        Dicas7 = (Button) findViewById(R.id.dica7id);
        Dicas7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(DicasActivity.this,dica7Activity.class);
                startActivity(f);
                finish();
            }

        });

        Dicas8 = (Button) findViewById(R.id.dica8id);
        Dicas8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(DicasActivity.this,dica8Activity.class);
                startActivity(f);
                finish();
            }

        });

        Dicas9 = (Button) findViewById(R.id.dica9id);
        Dicas9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(DicasActivity.this,dica9Activity.class);
                startActivity(f);
                finish();
            }

        });

        Dicas10 = (Button) findViewById(R.id.dica10id);
        Dicas10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(DicasActivity.this,dica10Activity.class);
                startActivity(f);
                finish();
            }

        });

        VoltarHome = (Button) findViewById(R.id.voltarhomeid);
        VoltarHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                Intent f = new Intent(DicasActivity.this,MainActivity.class);
                startActivity(f);
                finish();
            }

        });

    }
}
