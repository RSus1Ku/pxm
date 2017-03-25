package ximbalBO;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.Display;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;


import java.util.List;

import miproyecto.ximbal2.MainActivity;

/**
 * Created by joseph on 08/03/2017.
 */

public class ubicacion implements LocationListener {

    private static final int PERMISO = 0;
    private Context ctx;
    LocationManager locationManager;
    String Proveedor;
    private boolean GpsOn;

    public ubicacion(Context ctx) {
        this.ctx = ctx;
        locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        Proveedor = LocationManager.GPS_PROVIDER;
        GpsOn = locationManager.isProviderEnabled(Proveedor);
        if (ActivityCompat.checkSelfPermission(this.ctx, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(Proveedor, 0, 0, this);
        } else {
            ActivityCompat.requestPermissions((Activity) this.ctx, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISO  );
        }



    }

    public LatLng getLocation()
    {
       LatLng builder = null;
        Location ic = null;
        if (GpsOn) {
            if (ActivityCompat.checkSelfPermission(this.ctx, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                ic = locationManager.getLastKnownLocation(Proveedor);
            } else {
                ActivityCompat.requestPermissions((Activity) this.ctx, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISO  );
            }

            if (ic != null)
            {
                double latitude = ic.getLongitude();
                double longitude = ic.getLatitude();
                LatLng location = new LatLng(latitude, longitude);
                return location;
            }

        }
        return builder;
    }
    @Override
    public void onLocationChanged(Location location) {
        getLocation();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
