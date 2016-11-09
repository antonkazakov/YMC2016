package com.antonkazakov.foodfinder.utils;

import android.location.Location;

import com.antonkazakov.foodfinder.data.content.Geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antonkazakov on 30.10.16.
 */

public class CalculateUtils {

    public static  double calculateTeoreticalAzimuth(Geometry geometry, Location location) {
        double dX = geometry.getLocation().getLat() - location.getLatitude();
        double dY = geometry.getLocation().getLng() - location.getLongitude();

        double phiAngle;
        double tanPhi;


        tanPhi = Math.abs(dY / dX);
        phiAngle = Math.atan(tanPhi);
        phiAngle = Math.toDegrees(phiAngle);

        if (dX > 0 && dY > 0) {
            return phiAngle;
        } else if (dX < 0 && dY > 0) {
            return 180 - phiAngle;
        } else if (dX < 0 && dY < 0) {
            return 180 + phiAngle;
        } else if (dX > 0 && dY < 0) {
            return 360 - phiAngle;
        }

        return phiAngle;
    }

    public static List<Double> calculateAzimuthAccuracy(double azimuth) {
        double minAngle = azimuth - 8;
        double maxAngle = azimuth + 8;
        List<Double> minMax = new ArrayList<>();

        if (minAngle < 0)
            minAngle += 360;

        if (maxAngle >= 360)
            maxAngle -= 360;

        minMax.clear();
        minMax.add(minAngle);
        minMax.add(maxAngle);

        return minMax;
    }

    public static boolean isBetween(double minAngle, double maxAngle, double azimuth) {
        if (minAngle > maxAngle) {
            if (isBetween(0, maxAngle, azimuth) && isBetween(minAngle, 360, azimuth))
                return true;
        } else {
            if (azimuth > minAngle && azimuth < maxAngle)
                return true;
        }
        return false;
    }


}
