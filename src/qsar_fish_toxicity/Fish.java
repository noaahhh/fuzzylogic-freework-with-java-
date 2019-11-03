/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qsar_fish_toxicity;

import java.io.File;
import java.net.URISyntaxException;
import net.sourceforge.jFuzzyLogic.FIS;

/**
 *
 * @author noaahhh
 */
public class Fish {

    private double CIC0;
    private double SM1_DZ;
    private double GATS1;
    private int NdsCH;
    private int NdssC;
    private double MLOPG;
    private double real_output;
    private FIS fis;
    static double total = 0;
    private int line_count;

    public Fish(double CIC0, /*double SM1_DZ, */ double GATS1, int NdsCH, int NdssC, double MLOPG, double real_output, int index) throws URISyntaxException {
        this.CIC0 = CIC0;
        //this.SM1_DZ = SM1_DZ;
        this.GATS1 = GATS1;
        this.NdsCH = NdsCH;
        this.NdssC = NdssC;
        this.MLOPG = MLOPG;
        this.real_output = real_output;
        //this.line_count=index;

        File dosya = new File(getClass().getResource("model.fcl").toURI());
        fis = FIS.load(dosya.getPath(), true);
        fis.setVariable("CIC0", CIC0);
        //fis.setVariable("SM1_DZ", SM1_DZ);
        fis.setVariable("GATS1", GATS1);
        fis.setVariable("NdsCH", NdsCH);
        fis.setVariable("NdssC", NdssC);
        fis.setVariable("MLOGP", MLOPG);
        fis.evaluate();

        total += MSE(fis.getVariable("LC50").getValue(), real_output);

    }

    @Override
    public String toString() {
        String output = "CIC0 : " + CIC0 +/*"/tSM1_DZ : "+ SM1_DZ +*/ "\tGATS1 :" + GATS1 + "\tNdsCH :" + NdsCH + "\tNdssC" + NdssC + "\tMLOGP" + MLOPG;
        output += "\tactual LC50: "+ real_output + "\tLC50: " + fis.getVariable("LC50").getValue()+"\n";

        return output;
    }

    FIS getModel() {
        return fis;
    }

    private double MAPE(double actual, double forecast) {
        double out;
        out = Math.abs((actual - forecast) / forecast);
        return out;
    }

    private double MSE(double actual, double forecast) {
        double out;
        out = Math.pow((actual - forecast), 2);
        return out;
    }

}
