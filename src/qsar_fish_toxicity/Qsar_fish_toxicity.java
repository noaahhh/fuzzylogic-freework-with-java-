/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qsar_fish_toxicity;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

/**
 *
 * @author noaahhh
 */
public class Qsar_fish_toxicity {

    /**
     * @param args the command line arguments
     * @throws java.net.URISyntaxException
     * @throws java.io.IOException
     *
     */
    public static void main(String[] args) throws URISyntaxException, IOException {

        double CIC0;
        //double ;
        double GATS1;
        int NdsCH;
        int NdssC;
        double MLOPG;
        double LC50;

        String[] parts = null;
        double totalreal;
        int index = 0;
        double real_output;
        try (BufferedReader br = Files.newBufferedReader(Paths.get("src/qsar_fish_toxicity/qsar_fish_toxicity.data"))) {

            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
                parts = line.split(";");
                CIC0 = Double.parseDouble(parts[0]);
                GATS1 = Double.parseDouble(parts[2]);
                NdsCH = Integer.parseInt(parts[3]);
                NdssC = Integer.parseInt(parts[4]);
                MLOPG = Double.parseDouble(parts[5]);
                LC50 = Double.parseDouble(parts[6]);
                real_output = LC50;

                // System.out.print(parts[0]);
                //System.out.println(index);
                Fish f = new Fish(CIC0,/*SM1_DZ,*/ GATS1, NdsCH, NdssC, MLOPG, real_output, index);
                System.out.print(f);
                index++;
                totalreal = +f.total;
                
                System.out.print("\nFault Tolerance(MSE) :" + totalreal / index + "\n");
                
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

    }

}
