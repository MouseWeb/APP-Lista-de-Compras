package paixaoporti.com.br.listadecompras;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import layout.AlimentosFragment;
import layout.BebidasFragment;
import layout.CarnesFragment;
import layout.FriosFragment;
import layout.FrutasVerdurasLegumesFragment;
import layout.HigieneFragment;
import layout.LimpezaFragment;
import layout.ListaComprasFragment;
import layout.OutrosFragment;
import layout.PadariaFragment;
import layout.SobreFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
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
                intent.putExtra(Intent.EXTRA_TEXT, " Sua lista de compras, Ter uma lista de compras pronta para usar como referência facilita muito o processo de anotar os produtos necessários na compra da vez https://play.google.com/store/apps/details?id=paixaoporti.com.br.listadecompras&hl=pt_BR" );
                // inicia a intent
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.startDefaultFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        finish();

        if (id == R.id.action_settings) {
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;
        Class fragmentClass = null;

        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            fragmentClass = ListaComprasFragment.class;

        } else if (id == R.id.nav_gallery) {
            fragmentClass = AlimentosFragment.class;

        } else if (id == R.id.nav_slideshow) {
            fragmentClass = BebidasFragment.class;

        } else if (id == R.id.nav_manage) {
            fragmentClass = CarnesFragment.class;

        } else if (id == R.id.nav_frios) {
            fragmentClass = FriosFragment.class;

        } else if (id == R.id.nav_frutas) {
            fragmentClass = FrutasVerdurasLegumesFragment.class;

        } else if (id == R.id.nav_higiene) {
            fragmentClass = HigieneFragment.class;

        } else if (id == R.id.nav_limpeza) {
            fragmentClass = LimpezaFragment.class;

        } else if (id == R.id.nav_padaria) {
            fragmentClass = PadariaFragment.class;

        } else if (id == R.id.nav_outros) {
            fragmentClass = OutrosFragment.class;

        } else if (id == R.id.nav_send) {
            fragmentClass = SobreFragment.class;

        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.pag_menu, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startDefaultFragment(){
        Fragment fragment = null;
        Class fragmentClass = ListaComprasFragment.class;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.pag_menu, fragment).commit();

    }
}
