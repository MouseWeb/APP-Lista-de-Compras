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

public class PadariaFragment extends Fragment {

    private EditText textoTarefa9;
    private ImageView botaoAdicionar9;
    private ListView listaTarefas9;
    private SQLiteDatabase bancoDados9;
    private ArrayAdapter<String> itensAdaptador9;
    private ArrayList<String> itens9;
    private ArrayList<Integer> ids9;
    private ImageView C_Padaria;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_padaria, container, false);

        C_Padaria = (ImageView) view.findViewById(R.id.C_Padariaid);

        C_Padaria.setOnClickListener(new View.OnClickListener() {
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
                intent.putExtra(Intent.EXTRA_TEXT, "LISTA DE COMPRAS (PADARIA)" + itens9 );
                // inicia a intent
                startActivity(intent);
            }

        });

        try {

            //Recuperar componentes
            textoTarefa9 = (EditText) view.findViewById(R.id.textoPadariaid);
            botaoAdicionar9 = (ImageView) view.findViewById(R.id.botaoPadariaid);

            //lista
            listaTarefas9 = (ListView) view.findViewById(R.id.listaPadariaid);

            //Banco dados
            bancoDados9 = getActivity().openOrCreateDatabase("apptarefas", MODE_PRIVATE, null);

            //tabela tarefas
            bancoDados9.execSQL("CREATE TABLE IF NOT EXISTS Padaria(id INTEGER PRIMARY KEY AUTOINCREMENT, Padaria VARCHAR ) ");

            botaoAdicionar9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String textoDigitado = textoTarefa9.getText().toString();
                    salvarTarefa(textoDigitado);

                }
            });

            listaTarefas9.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    removerTarefa( ids9.get( position ) );
                }
            });

            listaTarefas9.setLongClickable(true);
            listaTarefas9.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    removerTarefa( ids9.get( position ) );
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
                bancoDados9.execSQL("INSERT INTO Padaria (Padaria) VALUES('" + texto + "') ");
                Toast.makeText(getActivity(), "Produto salvo com sucesso!", Toast.LENGTH_SHORT).show();
                recuperarTarefas();
                textoTarefa9.setText("");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void recuperarTarefas(){
        try{

            //Recuperar as tarefas
            Cursor cursor = bancoDados9.rawQuery("SELECT * FROM Padaria ORDER BY id DESC", null);

            //recuperar os ids das colunas
            int indiceColunaId = cursor.getColumnIndex("id");
            int indiceColunaTarefa = cursor.getColumnIndex("Padaria");

            //Criar adaptador
            itens9 = new ArrayList<String>();
            ids9 = new ArrayList<Integer>();
            itensAdaptador9 = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                    android.R.layout.simple_list_item_2,
                    android.R.id.text2,
                    itens9){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {

                    View view = super.getView(position, convertView, parent);
                    TextView text = (TextView) view.findViewById(android.R.id.text2);
                    text.setTextColor(Color.BLACK);
                    return view;

                }
            };
            listaTarefas9.setAdapter( itensAdaptador9 );

            //listar as tarefas
            cursor.moveToFirst();
            while ( cursor != null ){

                Log.i("Resultado - ", "Id Padaria: " + cursor.getString( indiceColunaId ) + " Padaria: " + cursor.getString( indiceColunaTarefa ) );
                itens9.add(cursor.getString(indiceColunaTarefa));
                ids9.add( Integer.parseInt(cursor.getString(indiceColunaId)) );

                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void removerTarefa(Integer id){
        try{

            bancoDados9.execSQL("DELETE FROM Padaria WHERE id="+id);
            recuperarTarefas();
            Toast.makeText(getActivity(), "Produto removido com sucesso!", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
