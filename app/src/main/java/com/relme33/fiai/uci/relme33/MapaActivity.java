package com.relme33.fiai.uci.relme33;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.PathWrapper;
import com.graphhopper.util.Constants;
import com.graphhopper.util.Parameters.Algorithms;
import com.graphhopper.util.Parameters.Routing;
import com.graphhopper.util.PointList;
import com.graphhopper.util.StopWatch;
import android.support.v4.app.ActivityCompat;

import org.apache.xmlgraphics.io.Resource;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.oscim.android.MapView;
import org.oscim.android.canvas.AndroidGraphics;
import org.oscim.backend.canvas.Bitmap;
import org.oscim.core.GeoPoint;
import org.oscim.event.Gesture;
import org.oscim.event.GestureListener;
import org.oscim.event.MotionEvent;
import org.oscim.layers.Layer;
import org.oscim.layers.marker.ItemizedLayer;
import org.oscim.layers.marker.MarkerItem;
import org.oscim.layers.marker.MarkerSymbol;
import org.oscim.layers.tile.buildings.BuildingLayer;
import org.oscim.layers.tile.vector.VectorTileLayer;
import org.oscim.layers.tile.vector.labeling.LabelLayer;
import org.oscim.layers.vector.PathLayer;
import org.oscim.layers.vector.geometries.Style;
import org.oscim.theme.VtmThemes;
import org.oscim.tiling.source.mapfile.MapFileTileSource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class MapaActivity extends Activity {

    private MapView mapView;
    private GraphHopper hopper;
    private GeoPoint start;
    private GeoPoint end;
    private Location currentLocation;
    private volatile boolean prepareInProgress = false;
    private volatile boolean shortestPathRunning = false;
    private String currentArea = "berlin";
    private String fileListURL = "http://download2.graphhopper.com/public/maps/" + Constants.getMajorVersion() + "/";
    private String prefixURL = fileListURL;
    private String downloadURL;
    private String mapsFolder;
    private ItemizedLayer<MarkerItem> itemizedLayer;
    private PathLayer pathLayer;
    LocationManager locationManager;
    LocationListener locationListener;
    LatLong latLong1;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        mapsFolder = getApplicationInfo().dataDir + "/";
        // mapsFolder = Environment.getExternalStorageDirectory()+"/";

        setContentView(R.layout.activity_mapa);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED


                    ) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.ACCESS_FINE_LOCATION,

                        },
                        111);
            }
        }

        try {
            mapView = findViewById(R.id.mapView);
            final EditText input = new EditText(this);
            input.setText(currentArea);

            List<String> assets = new ArrayList<>();
            assets.add("edges");
            assets.add("europe_germany_berlin.map");
            assets.add("geometry");
            assets.add("location_index");
            assets.add("names");
            assets.add("nodes");
            assets.add("nodes_ch_fastest_car");
            assets.add("nodes_ch_fastest_foot");
            assets.add("properties");
            assets.add("shortcuts_fastest_car");
            assets.add("shortcuts_fastest_foot");
            for (int i = 0; i < assets.size(); i++) {

                CopyAsset(assets.get(i));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        initFiles();
        if (savedInstanceState == null) {    // first call

            actualizarPosicion();
        }

//        calcPath(23.063058,-82.439743 ,23.066765,-82.441218);
//        calcPath(23.0654,-82.439371 ,23.064811,-82.441412);
//        calcPath(23.064564,-82.438974,23.063983,-82.441177);

    }

    private void showDialogDirection(String pos){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.layout_promptdialog_map, null);
        final TextView direction = subView.findViewById(R.id.tvDireccion);
        direction.setText(pos);
        final ImageView imgVista = subView.findViewById(R.id.imagen_lugar);
        //imgVista.setImageResource(img);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(subView);
        builder.setCancelable(false);
        builder.create();
        builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();

    }
    protected boolean onLongPress(GeoPoint p) {
        if (!isReady())
            return false;

        if (shortestPathRunning) {
            logUser("Calculation still in progress");
            return false;
        }

        if (start != null && end == null) {
            end = p;
            shortestPathRunning = true;
            itemizedLayer.addItem(createMarkerItem(p, R.drawable.ic_marcador));
            mapView.map().updateMap(true);

            calcPath(start.getLatitude(), start.getLongitude(), end.getLatitude(),
                    end.getLongitude());
        } else {
            start = p;
            end = null;
            // remove routing layers
            mapView.map().layers().remove(pathLayer);
            itemizedLayer.removeAllItems();

            itemizedLayer.addItem(createMarkerItem(start, R.drawable.ic_marcador));
            mapView.map().updateMap(true);
        }
        return true;
    }

    private void CopyAsset(String asset) throws IOException {
        InputStream mInputStream = getAssets().open(asset);
        String path = getApplicationInfo().dataDir + "/";
        //path = Environment.getExternalStorageDirectory()+"/";
        String outFileName = path + asset;
        OutputStream mOutputStream = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = mInputStream.read(buffer)) > 0) {
            mOutputStream.write(buffer, 0, length);
        }
        mOutputStream.flush();
        mOutputStream.close();
        mInputStream.close();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case 111: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[2] == PackageManager.PERMISSION_GRANTED

                        ) {

                    actualizarPosicion();

                } else {
                    Toast.makeText(getBaseContext(), "permiso denegado o innesecario", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }

            }

            // other 'switch' lines to check for other
            // permissions this app might request
        }
    }

    private void actualizarPosicion() {
        //Obtenemos una referencia al LocationManager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Obtenemos la última posición conocida
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        //Mostramos la última posición conocida
        muestraPosicion(currentLocation);

        //Nos registramos para recibir actualizaciones de la posición
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                muestraPosicion(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.e("GPS", ">>>>>>>>>>>>>>>>>>>>>>" + provider + "    " + status);
                Toast.makeText(MapaActivity.this, provider + " " + status, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onProviderEnabled(String provider) {
                Toast.makeText(MapaActivity.this, "Gps activado!!!!", Toast.LENGTH_SHORT).show();
                Log.e("GPS", ">>>>>>>>>>>>>>>>>>>>>>" + provider);
            }

            @Override
            public void onProviderDisabled(String provider) {
                Toast.makeText(MapaActivity.this, "Gps desactivado!!!", Toast.LENGTH_SHORT).show();
                Log.e("GPS", ">>>>>>>>>>>>>>>>>>>>>>" + provider);
            }

        };

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 800, 0, locationListener);


    }

    private void muestraPosicion(Location loc) {

    /*    if (loc != null) {
            // this.mapView.setCenter(new LatLong(loc.getLatitude(), loc.getLongitude()));
            //  mapView.setZoomLevel((byte) 21);

            mapView.map().layers().remove(pathLayer);
            //     itemizedLayer.removeAllItems();

            mapView.map().updateMap(true);

        }*/


        if (loc != null) {
            mapView.map().layers().remove(pathLayer);
            itemizedLayer.removeAllItems();

            GeoPoint posicion_actual=new GeoPoint(loc.getLatitude(),loc.getLongitude());
            GeoPoint punto_fijo=new GeoPoint(22.990467, -82.46929);

            itemizedLayer.addItem(createMarkerItem(punto_fijo, R.drawable.ic_marcador));
            itemizedLayer.addItem(createMarkerItem(posicion_actual, R.drawable.ic_marcador));

            mapView.map().updateMap(true);

        }
    }



    boolean isReady() {
        // only return true if already loaded
        if (hopper != null)
            return true;

        if (prepareInProgress) {
            logUser("Preparation still in progress");
            return false;
        }
        logUser("Prepare finished but hopper not ready. This happens when there was an error while loading the files");
        return false;
    }

    private void initFiles() {
        prepareInProgress = true;
        currentArea = "europe_germany_berlin";
        Log.d("area", "europe_germany_berlin.map");
        loadMap(mapsFolder);

        GeoPoint d1 = new GeoPoint(22.987786, -82.465961);
        GeoPoint d2 = new GeoPoint(22.988827, -82.466845);
        GeoPoint d3 = new GeoPoint(22.987177, -82.467552);
        GeoPoint d4 = new GeoPoint(22.987462, -82.468712);
        GeoPoint d5 = new GeoPoint(22.986148, -82.468168);
        GeoPoint d6 = new GeoPoint(22.985533, -82.469204);
        GeoPoint ru = new GeoPoint(22.987182, -82.462239);
        GeoPoint hu = new GeoPoint(22.983332, -82.464232);
        GeoPoint psc = new GeoPoint(22.9861436, -82.4651212);
        GeoPoint pr = new GeoPoint(22.9861436, -82.4651212);
        GeoPoint wl = new GeoPoint(22.983548, -82.465576);
        GeoPoint pm = new GeoPoint(22.987991, -82.466827);


        itemizedLayer.addItem(createMarkerItem(d1, R.drawable.ic_docente));
        itemizedLayer.addItem(createMarkerItem(d2, R.drawable.ic_docente));
        itemizedLayer.addItem(createMarkerItem(d3, R.drawable.ic_docente));
        itemizedLayer.addItem(createMarkerItem(d4, R.drawable.ic_docente));
        itemizedLayer.addItem(createMarkerItem(d5, R.drawable.ic_docente));
        itemizedLayer.addItem(createMarkerItem(d6, R.drawable.ic_docente));
        itemizedLayer.addItem(createMarkerItem(ru, R.drawable.ic_comedor));
        itemizedLayer.addItem(createMarkerItem(hu, R.drawable.ic_hotel));
        itemizedLayer.addItem(createMarkerItem(psc, R.drawable.ic_piscina));
        itemizedLayer.addItem(createMarkerItem(pr, R.drawable.ic_plaza));
        itemizedLayer.addItem(createMarkerItem(wl, R.drawable.ic_cultural));
        itemizedLayer.addItem(createMarkerItem(pm, R.drawable.ic_plaza));



        itemizedLayer.setOnItemGestureListener(new ItemizedLayer.OnItemGestureListener<MarkerItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, MarkerItem item) {
                item.title="PASODFFOOFGG";
                Log.d("title",item.title);

                switch (index){
                    case 0:
                        showDialogDirection("Docente 1");
                        break;

                    case 1:
                        showDialogDirection("Docente 2");
                        break;

                    case 2:
                        showDialogDirection("Docente 3");
                        break;
                    case 3:
                        showDialogDirection("Docente 4");
                        break;

                    case 4:
                        showDialogDirection("Docente 5");
                        break;

                    case 5:
                        showDialogDirection("Docente 6");
                        break;
                    case 6:
                        showDialogDirection("Restaurante Universitario");
                        break;

                    case 7:
                        showDialogDirection("Hotel Universitario");
                        break;

                    case 8:
                        showDialogDirection("Piscina");
                        break;

                    case 9:
                        showDialogDirection("Plaza de la Revolución José Martí");
                        break;

                    case 10:
                        showDialogDirection("Centro Cultural Wifredo Lam");
                        break;

                    case 11:
                        showDialogDirection("Plaza Mella6");
                        break;

                }


                return true;
            }

            @Override
            public boolean onItemLongPress(int index, MarkerItem item) {
                return false;
            }
        });

        mapView.map().updateMap(true);

    }


    void loadMap(String areaFolder) {
        logUser("loading map");

        // Map events receiver
        mapView.map().layers().add(new MapEventsReceiver(mapView.map()));

        // Map file source
        MapFileTileSource tileSource = new MapFileTileSource();
        tileSource.setMapFile(new File(areaFolder, currentArea + ".map").getAbsolutePath());
        Log.d("RUTAAAA", new File(areaFolder, currentArea + ".map").getAbsolutePath());
        VectorTileLayer l = mapView.map().setBaseMap(tileSource);
        mapView.map().setTheme(VtmThemes.DEFAULT);
        mapView.map().layers().add(new BuildingLayer(mapView.map(), l));
        mapView.map().layers().add(new LabelLayer(mapView.map(), l));

        mapView.setClickable(true);
        mapView.map().getBoundingBox(2);

        // Markers layer
        itemizedLayer = new ItemizedLayer<>(mapView.map(), (MarkerSymbol) null);
        mapView.map().layers().add(itemizedLayer);


        // Map position

        GeoPoint mapCenter = new GeoPoint(22.983573, -82.46565);
        mapView.map().setMapPosition(mapCenter.getLatitude(), mapCenter.getLongitude(), 1 << 21);
        loadGraphStorage();
    }

    @SuppressLint({"NewApi", "StaticFieldLeak"})
    void loadGraphStorage() {
        logUser("loading graph (" + Constants.VERSION + ") ... ");
        new GHAsyncTask<Void, Void, Path>() {
            protected Path saveDoInBackground(Void... v) throws Exception {
                GraphHopper tmpHopp = new GraphHopper().forMobile();
                tmpHopp.load(mapsFolder);
                log("found graph " + tmpHopp.getGraphHopperStorage().toString() + ", nodes:" + tmpHopp.getGraphHopperStorage().getNodes());
                hopper = tmpHopp;
                return null;
            }

            protected void onPostExecute(Path o) {
                if (hasError()) {
                    logUser("An error happened while creating graph:"
                            + getErrorMessage());
                } else {
                    logUser("Finished loading graph. Long press to define where to start and end the route.");
                }

                finishPrepare();
            }
        }.execute();
    }

    private void finishPrepare() {
        prepareInProgress = false;
    }

    private PathLayer createPathLayer(PathWrapper response) {

        Style style = Style.builder()
                .fixed(true)
                .generalization(Style.GENERALIZATION_SMALL)
                .strokeColor(0x9900cc33)
                .strokeWidth(4 * getResources().getDisplayMetrics().density)
                .build();
        PathLayer pathLayer = new PathLayer(mapView.map(), style);
        List<GeoPoint> geoPoints = new ArrayList<>();
        PointList pointList = response.getPoints();
        for (int i = 0; i < pointList.getSize(); i++) {
            geoPoints.add(new GeoPoint(pointList.getLatitude(i), pointList.getLongitude(i)));
            Log.d("repetidos", "" + geoPoints.get(i));
        }

        pathLayer.setPoints(geoPoints);
        return pathLayer;
    }


    @SuppressWarnings("deprecation")
    private MarkerItem createMarkerItem(GeoPoint p, int resource) {
        Drawable drawable = getResources().getDrawable(resource);
        Bitmap bitmap = AndroidGraphics.drawableToBitmap(drawable);
        MarkerSymbol markerSymbol = new MarkerSymbol(bitmap, 0.5f, 1);
        MarkerItem markerItem = new MarkerItem("", "", p);
        markerItem.setMarker(markerSymbol);
        return markerItem;
    }

    @SuppressLint({"StaticFieldLeak", "ObsoleteSdkInt"})
    public void calcPath(final double fromLat, final double fromLon,
                         final double toLat, final double toLon) {

        log("calculating path ...");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            new AsyncTask<Void, Void, PathWrapper>() {
                float time;

                protected PathWrapper doInBackground(Void... v) {
                    StopWatch sw = new StopWatch().start();
                    GHRequest req = new GHRequest(fromLat, fromLon, toLat, toLon).

                            setAlgorithm(Algorithms.DIJKSTRA_BI);


                    req.getHints().
                            put(Routing.INSTRUCTIONS, "false");
                    GHResponse resp = hopper.route(req);
                    time = sw.stop().getSeconds();
                    return resp.getBest();
                }

                protected void onPostExecute(PathWrapper resp) {
                    if (!resp.hasErrors()) {
                        log("from:" + fromLat + "," + fromLon + " to:" + toLat + ","
                                + toLon + " found path with distance:" + resp.getDistance()
                                / 1000f + ", nodes:" + resp.getPoints().getSize() + ", time:"
                                + time + " " + resp.getDebugInfo());
                        logUser("the route is " + (int) (resp.getDistance() / 100) / 10f
                                + "km long, time:" + resp.getTime() / 60000f + "min, debug:" + time);

                        pathLayer = createPathLayer(resp);
                        mapView.map().layers().add(pathLayer);
                        mapView.map().updateMap(true);
                    } else {
                        logUser("Error:" + resp.getErrors());
                    }
                    shortestPathRunning = false;
                }
            }.execute();
        }
    }

    private void log(String str) {
        Log.i("GH", str);
    }


    private void logUser(String str) {
        log(str);
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    class MapEventsReceiver extends Layer implements GestureListener {

        MapEventsReceiver(org.oscim.map.Map map) {
            super(map);
        }

        @Override
        public boolean onGesture(Gesture g, MotionEvent e) {
            if (g instanceof Gesture.LongPress) {
                GeoPoint p = mMap.viewport().fromScreenPoint(e.getX(), e.getY());
                return onLongPress(p);
            }
            return false;
        }
    }
}