package edu.usfca.cs.mr.test;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.util.*;

/**
 * Created by tuo on 06/11/17.
 */
public class Test {

    protected static String getMonth(String timestamp){
       // System.out.println(timestamp);
        SimpleDateFormat t = new SimpleDateFormat("yyyy-MM-dd");
        long s = Long.parseLong(timestamp);
        return t.format(s*1000);
    }

    private static class E {
        String value;
        int x;
        int y;

        public E(String value, int x, int y){
            this.value = value;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {

        String[] features = {"geopotential_height_lltw",
                "water_equiv_of_accum_snow_depth_surface",
                "drag_coefficient_surface",
                "sensible_heat_net_flux_surface",
                "categorical_ice_pellets_yes1_no0_surface",
                "visibility_surface",
                "number_of_soil_layers_in_root_zone_surface",
                "categorical_freezing_rain_yes1_no0_surface",
                "pressure_reduced_to_msl_msl",
                "upward_short_wave_rad_flux_surface",
                "relative_humidity_zerodegc_isotherm",
                "categorical_snow_yes1_no0_surface",
                "u-component_of_wind_tropopause",
                "surface_wind_gust_surface",
                "total_cloud_cover_entire_atmosphere",
                "upward_long_wave_rad_flux_surface",
                "land_cover_land1_sea0_surface",
                "vegitation_type_as_in_sib_surface",
                "v-component_of_wind_pblri",
                "albedo_surface",
                "lightning_surface",
                "ice_cover_ice1_no_ice0_surface",
                "convective_inhibition_surface",
                "pressure_surface",
                "transpiration_stress-onset_soil_moisture_surface",
                "soil_porosity_surface",
                "vegetation_surface",
                "categorical_rain_yes1_no0_surface",
                "downward_long_wave_rad_flux_surface",
                "planetary_boundary_layer_height_surface",
                "soil_type_as_in_zobler_surface",
                "geopotential_height_cloud_base",
                "friction_velocity_surface",
                "maximumcomposite_radar_reflectivity_entire_atmosphere",
                "plant_canopy_surface_water_surface",
                "v-component_of_wind_maximum_wind",
                "geopotential_height_zerodegc_isotherm",
                "mean_sea_level_pressure_nam_model_reduction_msl",
                "temperature_surface",
                "snow_cover_surface",
                "geopotential_height_surface",
                "convective_available_potential_energy_surface",
                "latent_heat_net_flux_surface",
                "surface_roughness_surface",
                "pressure_maximum_wind",
                "temperature_tropopause",
                "geopotential_height_pblri",
                "pressure_tropopause",
                "snow_depth_surface",
                "v-component_of_wind_tropopause",
                "downward_short_wave_rad_flux_surface",
                "u-component_of_wind_maximum_wind",
                "wilting_point_surface",
                "precipitable_water_entire_atmosphere",
                "u-component_of_wind_pblri",
                "direct_evaporation_cease_soil_moisture_surface"};



        File input = new File("/Users/yuntuotuo/Bigdata/p2-hetuo/src/main/java/edu/usfca/cs/mr/test/test.txt");
        File output = new File ("/Users/yuntuotuo/Bigdata/p2-hetuo/src/main/java/edu/usfca/cs/mr/test/newtest.txt");
        Scanner sc = new Scanner(input);
        PrintWriter printWriter = new PrintWriter(output);
        String[][] matrix = new String[56][56];
        PriorityQueue<E> q = new PriorityQueue<>(new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                if (o1.value.startsWith("-"))
                    o1.value = o1.value.substring(1);
                if (o2.value.startsWith("-"))
                    o2.value = o2.value.substring(1);
                return o2.value.compareTo(o1.value);
            }
        });
        int j = 0;
        while (sc.hasNextLine()){
            String s = sc.nextLine();
            //System.out.println(s);
            String[] tokens = s.split("\\t");
            for (int i = 0; i < tokens.length; i++)
                matrix[j][i] = tokens[i];
            j++;
        }
        for (int x = 0; x < 56; x++)
            for (int y = 0; y < 56; y++)
                if (matrix[x][y].equals("x")){
                    matrix[x][y] = matrix[y][x];
                }
        for (int x = 0; x < 56; x++){
            String s = "";
            for (int y = 0; y < 55; y++){
                    s += (matrix[x][y] + '\t');
                    q.add(new E(matrix[x][y], x, y));
            }
            s += matrix[x][55];
            q.add(new E(matrix[x][55], x, 55));
            printWriter.write(s + System.lineSeparator());
            printWriter.flush();
        }

        while (!q.isEmpty()){
            E e = q.poll();
            System.out.println(features[e.x] + ", " + features[e.y] + ": " + e.value);
        }


    }
}
