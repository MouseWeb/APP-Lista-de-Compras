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

public class BebidasFragment extends Fragment {

    private EditText textoTarefa1;
    private ImageView botaoAdicionar1;
    private ListView listaTarefas1;
    private SQLiteDatabase bancoDados1;
    private ArrayAdapter<String> itensAdaptador1;
    private ArrayList<String> itens1;
    private ArrayList<Integer> ids1;
    private ImageView C_Bebidas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bebidas, container, false);

        C_Bebidas = (ImageView) view.findViewById(R.id.C_Bebidasid);

        C_Bebidas.setOnClickListener(new View.OnClickListener() {
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
                intent.putExtra(Intent.EXTRA_TEXT, "LISTA DE COMPRAS (BEBIDAS)" + itens1 );
                // inicia a intent
                startActivity(intent);
            }

        });

        try {

            //Recuperar componentes
            textoTarefa1 = (EditText) view.findViewById(R.id.textoBebidaid);
            botaoAdicionar1 = (ImageView) view.findViewById(R.id.botaoBebidaid);

            //lista
            listaTarefas1 = (ListView) view.findViewById(R.id.listaBebidaid);

            //Banco dados
            bancoDados1 = getActivity().openOrCreateDatabase("apptarefas", MODE_PRIVATE, null);

            //tabela tarefas
            bancoDados1.execSQL("CREATE TABLE IF NOT EXISTS bebidas(id INTEGER PRIMARY KEY AUTOINCREMENT, bebidas VARCHAR ) ");

            botaoAdicionar1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String textoDigitado = textoTarefa1.getText().toString();
                    salvarTarefa(textoDigitado);

                }
            });

            listaTarefas1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    removerTarefa( ids1.get( position ) );
                }
            });

            listaTarefas1.setLongClickable(true);
            listaTarefas1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    removerTarefa( ids1.get( position ) );
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
                bancoDados1.execSQL("INSERT INTO bebidas (bebidas) VALUES('" + texto + "') ");
                Toast.makeText(getActivity(), "Produto salvo com sucesso!", Toast.LENGTH_SHORT).show();
                recuperarTarefas();
                textoTarefa1.setText("");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void recuperarTarefas(){
        try{

            //Recuperar as tarefas
            Cursor cursor = bancoDados1.rawQuery("SELECT * FROM bebidas ORDER BY id DESC", null);

            //recuperar os ids das colunas
            int indiceColunaId = cursor.getColumnIndex("id");
            int indiceColunaTarefa = cursor.getColumnIndex("bebidas");

            //Criar adaptador
            itens1 = new ArrayList<String>();
            ids1 = new ArrayList<Integer>();
            itensAdaptador1 = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                    android.R.layout.simple_list_item_2,
                    android.R.id.text2,
                    itens1){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {

                    View view = super.getView(position, convertView, parent);
                    TextView text = (TextView) view.findViewById(android.R.id.text2);
                    text.setTextColor(Color.BLACK);
                    return view;

                }
            };
            listaTarefas1.setAdapter( itensAdaptador1 );

            //listar as tarefas
            cursor.moveToFirst();
            while ( cursor != null ){

                Log.i("Resultado - ", "Id bebidas: " + cursor.getString( indiceColunaId ) + " bebidas: " + cursor.getString( indiceColunaTarefa ) );
                itens1.add(cursor.getString(indiceColunaTarefa));
                ids1.add( Integer.parseInt(cursor.getString(indiceColunaId)) );

                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void removerTarefa(Integer id){
        try{

            bancoDados1.execSQL("DELETE FROM bebidas WHERE id="+id);
            recuperarTarefas();
            Toast.makeText(getActivity(), "Produto removido com sucesso!", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

