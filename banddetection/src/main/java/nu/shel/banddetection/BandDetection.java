package nu.shel.banddetection;

/**
 * Created by seth on 4/3/16.
 */

import android.util.Log;

import java.util.ArrayList;

import static nu.shel.banddetection.LTEBand.GetBandFromEarfcn;

/**
 * BandDetection
 * Created by Seth Shelnutt on 04/03/16.
 * @author Seth Shelnutt
 * @since 4/03/2016
 * This file is copyrighted By Seth Shelnutt and licensed under terms of the LGPL v2.
 */
public class BandDetection {

    private static String TAG = "BandDetection";
    public static LTEBand DetectBand(){
        Modem mModem = new Modem();
        while(mModem.lastReturnStatus == null || mModem.path == null){

        }
        ArrayList<String> output = mModem.RunModemCommand("AT\\$QCRSRP?");
        if(mModem.lastReturnStatus == Modem.returnCodes.COMMAND_SUCCESS){
            if(!output.isEmpty()){
                for (String line:output) {
                    Log.d(TAG, "DetectBand: " + line);
                    String[] splitString = line.split(",");
                    if(splitString.length > 1) {
                        String earfcn = splitString[1];
                        Log.d(TAG, "DetectBand: earfcn: " + earfcn);
                        return GetBandFromEarfcn(Double.parseDouble(earfcn));
                    }
                }
            }
        }
        return LTEBand.bands.get(0);
    }
}
