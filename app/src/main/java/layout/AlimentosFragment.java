package layout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import paixaoporti.com.br.listadecompras.R;
import static android.content.Context.MODE_PRIVATE;

public class AlimentosFragment extends Fragment {

    private EditText textoTarefa2;
    private ImageView botaoAdicionar2;
    private ListView listaTarefas2;
    private SQLiteDatabase bancoDados2;
    private ArrayAdapter<String> itensAdaptador2;
    private ArrayList<String> itens2;
    private ArrayList<Integer> ids2;
    private ImageView C_Alimentos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alimentos, container, false);

        C_Alimentos = (ImageView) view.findViewById(R.id.C_Alimentosid);

        C_Alimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareText();
            }

            private void shareText() {
                // cria a intent e define a ação
                Intent intent = new Intent(Intent.ACTION_SEND);
                // tipo de conteúdo da intent
                intent.setType("text/plain");
                // string a ser enviada para outra intent
                intent.putExtra(Intent.EXTRA_TEXT, "LISTA DE COMPRAS (ALIMENTOS)" + itens2 );
                // inicia a intent
                startActivity(intent);
            }

        });

        try {

            //Recuperar componentes
            textoTarefa2 = (EditText) view.findViewById(R.id.textoAlimentosid);
            botaoAdicionar2 = (ImageView) view.findViewById(R.id.botaoAlimentosid);

            //lista
            listaTarefas2 = (ListView) view.findViewById(R.id.listaAlimentosid);

            //Banco dados
            bancoDados2 = getActivity().openOrCreateDatabase("apptarefas", MODE_PRIVATE, null);

            //tabela tarefas
            bancoDados2.execSQL("CREATE TABLE IF NOT EXISTS Alimentos(id INTEGER PRIMARY KEY AUTOINCREMENT, Alimentos VARCHAR ) ");

            botaoAdicionar2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String textoDigitado = textoTarefa2.getText().toString();
                    salvarTarefa(textoDigitado);

                }
            });

            listaTarefas2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    removerTarefa( ids2.get( position ) );
                }
            });

            listaTarefas2.setLongClickable(true);
            listaTarefas2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    removerTarefa( ids2.get( position ) );
                    return true;
                }
            });

            //recuperar tarefas
            recuperarTarefas();


        }catch(Exception e){
            e.printStackTrace();
        }
        return view;
    }

    private void salvarTarefa(String texto){

        try{

            if( texto.equals("") ){
                Toast.makeText(getActivity(), "Digite um produto", Toast.LENGTH_SHORT).show();
            }else{
                bancoDados2.execSQL("INSERT INTO Alimentos (Alimentos) VALUES('" + texto + "') ");
                Toast.makeText(getActivity(), "Produto salvo com sucesso!", Toast.LENGTH_SHORT).show();
                recuperarTarefas();
                textoTarefa2.setText("");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void recuperarTarefas(){
        try{

            //Recuperar as tarefas
            Cursor cursor = bancoDados2.rawQuery("SELECT * FROM Alimentos ORDER BY id DESC", null);

            //recuperar os ids das colunas
            int indiceColunaId = cursor.getColumnIndex("id");
            int indiceColunaTarefa = cursor.getColumnIndex("Alimentos");

            //Criar adaptador
            itens2 = new ArrayList<String>();
            ids2 = new ArrayList<Integer>();
            itensAdaptador2 = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                    android.R.layout.simple_list_item_2,
                    android.R.id.text2,
                    itens2){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {

                    View view = super.getView(position, convertView, parent);
                    TextView text = (TextView) view.findViewById(android.R.id.text2);
                    text.setTextColor(Color.BLACK);
                    return view;

                }
            };
            listaTarefas2.setAdapter( itensAdaptador2 );

            //listar as tarefas
            cursor.moveToFirst();
            while ( cursor != null ){

                Log.i("Resultado - ", "Id Alimentos: " + cursor.getString( indiceColunaId ) + " Alimentos: " + cursor.getString( indiceColunaTarefa ) );
                itens2.add(cursor.getString(indiceColunaTarefa));
                ids2.add( Integer.parseInt(cursor.getString(indiceColunaId)) );

                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void removerTarefa(Integer id){
        try{

            bancoDados2.execSQL("DELETE FROM Alimentos WHERE id="+id);
            recuperarTarefas();
            Toast.makeText(getActivity(), "Produto removido com sucesso!", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

