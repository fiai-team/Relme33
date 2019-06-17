package com.relme33.fiai.uci.relme33;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.relme33.fiai.uci.relme33.fragments.ComunicacionesBrevesFragment;
import com.relme33.fiai.uci.relme33.fragments.ConcursoFotoFragment;
import com.relme33.fiai.uci.relme33.fragments.ConferenciasEspecialesFragment;
import com.relme33.fiai.uci.relme33.fragments.CursosCortosFragment;
import com.relme33.fiai.uci.relme33.fragments.GrupoDiscusionFragment;
import com.relme33.fiai.uci.relme33.fragments.MesaRedondaFragment;
import com.relme33.fiai.uci.relme33.fragments.PremiosClameFragment;
import com.relme33.fiai.uci.relme33.fragments.PresentacioMaterialesLibrosFragment;
import com.relme33.fiai.uci.relme33.fragments.ReportesInvestigacionFragment;
import com.relme33.fiai.uci.relme33.fragments.TalleresFragment;
import com.relme33.fiai.uci.relme33.utiles.Utiles;

public class ModalidadesActivity extends AppCompatActivity implements ConferenciasEspecialesFragment.OnFragmentInteractionListener,
        ReportesInvestigacionFragment.OnFragmentInteractionListener,TalleresFragment.OnFragmentInteractionListener,
        ComunicacionesBrevesFragment.OnFragmentInteractionListener, GrupoDiscusionFragment.OnFragmentInteractionListener,
        MesaRedondaFragment.OnFragmentInteractionListener, PremiosClameFragment.OnFragmentInteractionListener,
        ConcursoFotoFragment.OnFragmentInteractionListener, PresentacioMaterialesLibrosFragment.OnFragmentInteractionListener,
        CursosCortosFragment.OnFragmentInteractionListener
{

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    private Toolbar toolbar;


    private ViewPagerIndicator mViewPagerIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modalidades);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPagerIndicator = (ViewPagerIndicator) findViewById(R.id.view_pager_indicator);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPagerIndicator.setupWithViewPager(mViewPager);
        mViewPagerIndicator.addOnPageChangeListener(viewListener);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager.addOnPageChangeListener(viewListener);

    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            //Toast.makeText(getApplicationContext(),i,Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case android.R.id.home:
                Intent intenthome = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intenthome);
                finish();
                break;
            case R.id.RELME_33:
                Intent intent1 = new Intent(getApplicationContext(),RelmeInfoActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.Conferencistas:
                Intent intent2 = new Intent(getApplicationContext(),ConferencistasActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.Modalidades:
                break;
            case R.id.Programa:
                Intent intent4 = new Intent(getApplicationContext(),ProgramaActivity.class);
                startActivity(intent4);
                finish();
                break;
            case R.id.Mapa:

                finish();
                break;
            case R.id.Contactos:
                Intent intent5 = new Intent(getApplicationContext(),ContactosActivity.class);
                startActivity(intent5);
                finish();
                break;
            case R.id.Acerca_de:
                Intent intent6 = new Intent(getApplicationContext(),AcercaDeActivity.class);
                startActivity(intent6);
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intenthome = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intenthome);
        finish();
        super.onBackPressed();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    public void onClick(View view) {
       switch (view.getId()){
           case R.id.conf_especiales_b:
               Intent i = new Intent(ModalidadesActivity.this,ListaBuscadaActivity.class);
               Bundle mibundle = new Bundle();
               mibundle.putString(Utiles.KEY_PASS_MODALIDAD, Utiles.KEY_PASS_MODALIDAD_CE);
               i.putExtras(mibundle);
               startActivity(i);
               finish();
               break;

           case R.id.reportes_b:
               Intent i2 = new Intent(ModalidadesActivity.this,ListaBuscadaActivity.class);
               Bundle mibundle2 = new Bundle();
               mibundle2.putString(Utiles.KEY_PASS_MODALIDAD, Utiles.KEY_PASS_MODALIDAD_RI);
               i2.putExtras(mibundle2);
               startActivity(i2);
               finish();
               break;

           case R.id.comunicaciones_breves_b:
               Intent i3 = new Intent(ModalidadesActivity.this,ListaBuscadaActivity.class);
               Bundle mibundle3 = new Bundle();
               mibundle3.putString(Utiles.KEY_PASS_MODALIDAD, "cb");
               i3.putExtras(mibundle3);
               startActivity(i3);
               finish();
               break;

           case R.id.talleres_b:
               Intent i4 = new Intent(ModalidadesActivity.this,ListaBuscadaActivity.class);
               Bundle mibundle4 = new Bundle();
               mibundle4.putString(Utiles.KEY_PASS_MODALIDAD, Utiles.KEY_PASS_MODALIDAD_T);
               i4.putExtras(mibundle4);
               startActivity(i4);
               finish();
               break;

           case R.id.grupos_discusion_b:
               Intent i5 = new Intent(ModalidadesActivity.this,ListaBuscadaActivity.class);
               Bundle mibundle5 = new Bundle();
               mibundle5.putString(Utiles.KEY_PASS_MODALIDAD, Utiles.KEY_PASS_MODALIDAD_GD);
               i5.putExtras(mibundle5);
               startActivity(i5);
               finish();
               break;

           case R.id.mesa_redonda_b:
               Intent i6 = new Intent(ModalidadesActivity.this,ListaBuscadaActivity.class);
               Bundle mibundle6 = new Bundle();
               mibundle6.putString(Utiles.KEY_PASS_MODALIDAD, Utiles.KEY_PASS_MODALIDAD_MR);
               i6.putExtras(mibundle6);
               startActivity(i6);
               finish();
               break;

           case R.id.premios_b:
               Intent i7 = new Intent(ModalidadesActivity.this,ListaBuscadaActivity.class);
               Bundle mibundle7 = new Bundle();
               mibundle7.putString(Utiles.KEY_PASS_MODALIDAD, Utiles.KEY_PASS_MODALIDAD_PC);
               i7.putExtras(mibundle7);
               startActivity(i7);
               finish();
               break;

           case R.id.concurso_b:
               Intent i8 = new Intent(ModalidadesActivity.this,ListaBuscadaActivity.class);
               Bundle mibundle8 = new Bundle();
               mibundle8.putString(Utiles.KEY_PASS_MODALIDAD, Utiles.KEY_PASS_MODALIDAD_CF);
               i8.putExtras(mibundle8);
               startActivity(i8);
               finish();
               break;

           case R.id.material_libro_b:
               Intent i9 = new Intent(ModalidadesActivity.this,ListaBuscadaActivity.class);
               Bundle mibundle9 = new Bundle();
               mibundle9.putString(Utiles.KEY_PASS_MODALIDAD, Utiles.KEY_PASS_MODALIDAD_PM);
               i9.putExtras(mibundle9);
               startActivity(i9);
               finish();
               break;

           case R.id.cursos_cortos_b:
               Intent i10 = new Intent(ModalidadesActivity.this,ListaBuscadaActivity.class);
               Bundle mibundle10 = new Bundle();
               mibundle10.putString(Utiles.KEY_PASS_MODALIDAD, Utiles.KEY_PASS_MODALIDAD_CC);
               i10.putExtras(mibundle10);
               startActivity(i10);
               finish();
               break;


       }
        //finish();
    }

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        public static Fragment newInstance(int sectionNumber) {
            Fragment fragment = null;
            switch (sectionNumber){
                case 1:
                    fragment = new ConferenciasEspecialesFragment();
                    break;
                case 2:
                    fragment = new ReportesInvestigacionFragment();
                    break;
                case 3:
                    fragment = new ComunicacionesBrevesFragment();
                    break;
                case 4:
                    fragment = new TalleresFragment();
                    break;
                case 5:
                    fragment = new GrupoDiscusionFragment();
                    break;
                case 6:
                    fragment = new MesaRedondaFragment();
                    break;
                case 7:
                    fragment = new PremiosClameFragment();
                    break;
                case 8:
                    fragment = new ConcursoFotoFragment();
                    break;
                case 9:
                    fragment = new PresentacioMaterialesLibrosFragment();
                    break;
                case 10:
                    fragment = new CursosCortosFragment();
                    break;
            }

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_modalidades, container, false);
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 10;
        }
    }
}
