package com.westcoastdealershop.init;

import com.westcoastdealershop.models.entities.Brand;
import com.westcoastdealershop.models.entities.User;
import com.westcoastdealershop.models.entities.enums.Role;
import com.westcoastdealershop.models.serviceModels.ModelServiceModel;
import com.westcoastdealershop.services.BrandService;
import com.westcoastdealershop.services.ModelService;
import com.westcoastdealershop.services.OfferService;
import com.westcoastdealershop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class Init implements CommandLineRunner {
    private final BrandService brandService;
    private final UserService userService;
    private final ModelService modelService;

    @Autowired
    public Init(BrandService brandService, UserService userService, ModelService modelService) {
        this.brandService = brandService;
        this.userService = userService;
        this.modelService = modelService;
    }

    private static final List<Brand> SAMPLE_BRANDS = List.of(
            new Brand("BMW", new ArrayList<>()),
            new Brand("Opel", new ArrayList<>())
    );

    private static final List<ModelServiceModel> SAMPLE_MODELS = List.of(
            new ModelServiceModel("BMW", "M1", "CAR", 1978, 1981, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/26/BMW_M1%2C_front_right_%28Brooklyn%29.jpg/560px-BMW_M1%2C_front_right_%28Brooklyn%29.jpg"),
            new ModelServiceModel("BMW", "M2", "CAR", 2016, "https://upload.wikimedia.org/wikipedia/commons/thumb/6/66/2017_BMW_M2_Automatic_3.0_Front.jpg/560px-2017_BMW_M2_Automatic_3.0_Front.jpg"),
            new ModelServiceModel("BMW", "M3", "CAR", 2008, 2012, "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/2018_BMW_M3_3.0.jpg/560px-2018_BMW_M3_3.0.jpg"),
            new ModelServiceModel("BMW", "M4", "CAR", 2014, "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/2015_BMW_M4_%28F82%29_coupe_%2824220553394%29.jpg/560px-2015_BMW_M4_%28F82%29_coupe_%2824220553394%29.jpg"),
            new ModelServiceModel("BMW", "M5", "CAR", 1984, 1988, "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1d/BMW%2C_Techno_Classica_2018%2C_Essen_%28IMG_8995%29.jpg/560px-BMW%2C_Techno_Classica_2018%2C_Essen_%28IMG_8995%29.jpg"),
            new ModelServiceModel("BMW", "M6", "CAR", 1990, 2004, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/2017-03-07_Geneva_Motor_Show_0996.JPG/560px-2017-03-07_Geneva_Motor_Show_0996.JPG"),
            new ModelServiceModel("BMW", "7", "CAR", 1977, "https://upload.wikimedia.org/wikipedia/commons/thumb/6/62/2006_BMW_730d_%28E65%29_sedan_%282015-07-09%29_01.jpg/560px-2006_BMW_730d_%28E65%29_sedan_%282015-07-09%29_01.jpg"),
            new ModelServiceModel("BMW", "8", "CAR", 2018, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/2019_BMW_M850i_xDrive%2C_front_1.23.20.jpg/560px-2019_BMW_M850i_xDrive%2C_front_1.23.20.jpg"),
            new ModelServiceModel("BMW", "5", "CAR", 1972, "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/2018_BMW_520d_M_Sport_Automatic_2.0_%281%29.jpg/560px-2018_BMW_520d_M_Sport_Automatic_2.0_%281%29.jpg"),
            new ModelServiceModel("BMW", "6", "CAR", 2003, "https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/2018_BMW_630i_GT_M_Sport_Automatic_2.0_Front.jpg/560px-2018_BMW_630i_GT_M_Sport_Automatic_2.0_Front.jpg"),
            new ModelServiceModel("BMW", "X1", "CAR", 2009, "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/BMW_X1_%28E84%2C_Facelift%29_%E2%80%93_Frontansicht%2C_31._Dezember_2012%2C_D%C3%BCsseldorf.jpg/560px-BMW_X1_%28E84%2C_Facelift%29_%E2%80%93_Frontansicht%2C_31._Dezember_2012%2C_D%C3%BCsseldorf.jpg"),
            new ModelServiceModel("BMW", "i3", "CAR", 2013, "https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/2018_BMW_i3_facelift_%281%29.jpg/560px-2018_BMW_i3_facelift_%281%29.jpg"),
            new ModelServiceModel("BMW", "X2", "CAR", 2017, "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c2/2018_BMW_X2_xDrive20D_M_Sport_X_Automatic_2.0.jpg/560px-2018_BMW_X2_xDrive20D_M_Sport_X_Automatic_2.0.jpg"),
            new ModelServiceModel("BMW", "X3", "CAR", 2003, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/2018_BMW_X3_xDrive20d_SE_Automatic_2.0_Front.jpg/560px-2018_BMW_X3_xDrive20d_SE_Automatic_2.0_Front.jpg"),
            new ModelServiceModel("BMW", "X4", "CAR", 2014, "https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/2018_BMW_X4_xDrive20d_M_Sport_Automatic_2.0_Front.jpg/560px-2018_BMW_X4_xDrive20d_M_Sport_Automatic_2.0_Front.jpg"),
            new ModelServiceModel("BMW", "X5", "CAR", 1999, "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/2019_BMW_X5_M50d_Automatic_3.0.jpg/560px-2019_BMW_X5_M50d_Automatic_3.0.jpg"),
            new ModelServiceModel("BMW", "X6", "CAR", 2007, "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cd/2020_BMW_X6_xDrive30d_M_Sport_Automatic_3.0.jpg/560px-2020_BMW_X6_xDrive30d_M_Sport_Automatic_3.0.jpg"),
            new ModelServiceModel("BMW", "X7", "CAR", 2018, "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a9/2019_BMW_X7_xDrive40i_in_white%2C_front_left.jpg/560px-2019_BMW_X7_xDrive40i_in_white%2C_front_left.jpg"),
            new ModelServiceModel("BMW", "G11", "CAR", 2015, "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/2017_BMW_750i_%28G12%29_front_3.23.18.jpg/560px-2017_BMW_750i_%28G12%29_front_3.23.18.jpg"),
            new ModelServiceModel("BMW", "G30", "CAR", 2016, "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/2018_BMW_520d_M_Sport_Automatic_2.0_%281%29.jpg/560px-2018_BMW_520d_M_Sport_Automatic_2.0_%281%29.jpg"),
            new ModelServiceModel("BMW", "G32", "CAR", 2017, "https://upload.wikimedia.org/wikipedia/commons/thumb/0/04/2018_BMW_630d_GT_M_Sport_Automatic_3.0_Front.jpg/560px-2018_BMW_630d_GT_M_Sport_Automatic_3.0_Front.jpg"),
            new ModelServiceModel("BMW", "Z1", "CAR", 1989, 1991, "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ec/BMWZ1.jpg/560px-BMWZ1.jpg"),
            new ModelServiceModel("BMW", "Z3", "CAR", 1995, 2002, "https://upload.wikimedia.org/wikipedia/commons/thumb/a/aa/2001_BMW_Z3_Roadster_Automatic_2.2_Front.jpg/560px-2001_BMW_Z3_Roadster_Automatic_2.2_Front.jpg"),
            new ModelServiceModel("BMW", "Z4", "CAR", 1995, "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/2019_BMW_Z4_M40i_Automatic_3.0.jpg/560px-2019_BMW_Z4_M40i_Automatic_3.0.jpg"),
            new ModelServiceModel("BMW", "Z5", "CAR", 1989, 2016, "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d6/BMW_Z4%2C_Paris_Motor_Show_2018%2C_Paris_%281Y7A1387%29.jpg/560px-BMW_Z4%2C_Paris_Motor_Show_2018%2C_Paris_%281Y7A1387%29.jpg"),
            new ModelServiceModel("BMW", "Z8", "CAR", 2000, 2003, "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/BMW_Z8_%282009-05-20%29.JPG/560px-BMW_Z8_%282009-05-20%29.JPG"),
            new ModelServiceModel("BMW", "F800S", "MOTORCYCLE", 2006, 2010, "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/BMW_F_800_Sport_Edition_1.jpg/440px-BMW_F_800_Sport_Edition_1.jpg"),
            new ModelServiceModel("BMW", "R1200GS", "MOTORCYCLE", 2004, "https://upload.wikimedia.org/wikipedia/commons/thumb/7/79/BMW_R1200GS_July_2010.jpg/1024px-BMW_R1200GS_July_2010.jpg"),
            new ModelServiceModel("BMW", "R1300S", "MOTORCYCLE", 2008, 2016, "https://upload.wikimedia.org/wikipedia/commons/thumb/0/03/BMW_K1300S.jpg/600px-BMW_K1300S.jpg"),
            new ModelServiceModel("BMW", "K1600", "MOTORCYCLE", 2001, "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f5/K1600_Seite.jpg/600px-K1600_Seite.jpg"),
            new ModelServiceModel("Opel", "Agila", "CAR", 2000, 2007, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/Opel_Agila_A_1.2_Njoy.JPG/300px-Opel_Agila_A_1.2_Njoy.JPG"),
            new ModelServiceModel("Opel", "Adam", "CAR", 2013, 2019, "https://upload.wikimedia.org/wikipedia/commons/thumb/9/90/Opel_Adam_1.4_Slam_%E2%80%93_Frontansicht%2C_15._Januar_2014%2C_D%C3%BCsseldorf.jpg/1600px-Opel_Adam_1.4_Slam_%E2%80%93_Frontansicht%2C_15._Januar_2014%2C_D%C3%BCsseldorf.jpg"),
            new ModelServiceModel("Opel", "Karl", "CAR", 2015, 2019, "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/Opel_KARL_%281%29.jpg/300px-Opel_KARL_%281%29.jpg"),
            new ModelServiceModel("Opel", "Chevette", "CAR", 1980, 1982, "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e4/Vauxhall_Chevette_4_door_notchback_Trumpington.jpg/300px-Vauxhall_Chevette_4_door_notchback_Trumpington.jpg"),
            new ModelServiceModel("Opel", "Corsa A", "CAR", 1982, 1993, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/Opel_Corsa_A_front_20080131.jpg/300px-Opel_Corsa_A_front_20080131.jpg"),
            new ModelServiceModel("Opel", "Corsa B", "CAR", 1993, 2000, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Opel_Corsa_front_20080417.jpg/300px-Opel_Corsa_front_20080417.jpg"),
            new ModelServiceModel("Opel", "Tigra A", "CAR", 1994, 2000, "https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/Opel_Tigra_front_20071212.jpg/300px-Opel_Tigra_front_20071212.jpg"),
            new ModelServiceModel("Opel", "Corsa C", "CAR", 2000, 2006, "https://upload.wikimedia.org/wikipedia/commons/thumb/6/61/Opel_Corsa_C_1.2_Elegance_front_20100912.jpg/300px-Opel_Corsa_C_1.2_Elegance_front_20100912.jpg"),
            new ModelServiceModel("Opel", "Tigra TwinTop B", "CAR", 2004, 2009, "https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/OPEL-VAUX-TIGRA-B.jpg/300px-OPEL-VAUX-TIGRA-B.jpg"),
            new ModelServiceModel("Opel", "Corsa D", "CAR", 2006, 2014, "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/Opel_Corsa_Satellite_%28D%2C_Facelift%29_%E2%80%93_Frontansicht%2C_2._April_2011%2C_D%C3%BCsseldorf.jpg/300px-Opel_Corsa_Satellite_%28D%2C_Facelift%29_%E2%80%93_Frontansicht%2C_2._April_2011%2C_D%C3%BCsseldorf.jpg"),
            new ModelServiceModel("Opel", "Corsa E", "CAR", 2014, 2019, "https://upload.wikimedia.org/wikipedia/commons/thumb/3/35/Opel_Corsa_1.3_CDTI_ecoFLEX_Innovation_%28E%29_%E2%80%93_Frontansicht%2C_24._Dezember_2015%2C_Ratingen.jpg/300px-Opel_Corsa_1.3_CDTI_ecoFLEX_Innovation_%28E%29_%E2%80%93_Frontansicht%2C_24._Dezember_2015%2C_Ratingen.jpg"),
            new ModelServiceModel("Opel", "Corse F", "CAR", 2019, "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e9/Opel_Corsa-e_at_IAA_2019_IMG_0738.jpg/300px-Opel_Corsa-e_at_IAA_2019_IMG_0738.jpg"),
            new ModelServiceModel("Opel", "Olympia", "CAR", 1947, 1953, "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/Opel_Olympia.jpg/300px-Opel_Olympia.jpg"),
            new ModelServiceModel("Opel", "Kadett A", "CAR", 1962, 1965, "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/Opel_Kadett_A.jpg/300px-Opel_Kadett_A.jpg"),
            new ModelServiceModel("Opel", "Kadett B", "CAR", 1965, 1973, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Opel_Kadett_B_BW_2016-09-03_13-52-40.jpg/300px-Opel_Kadett_B_BW_2016-09-03_13-52-40.jpg"),
            new ModelServiceModel("Opel", "Kadett C", "CAR", 1973, 1979, "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/Opel_Kadett_C_City_front_20081127.jpg/300px-Opel_Kadett_C_City_front_20081127.jpg"),
            new ModelServiceModel("Opel", "Kadett D", "CAR", 1979, 1984, "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/Opel_Kadett_D_1_v_sst.jpg/300px-Opel_Kadett_D_1_v_sst.jpg"),
            new ModelServiceModel("Opel", "Kadett E", "CAR", 1984, 1991, "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/Opel_Kadett_Kombi_front_20080224.jpg/300px-Opel_Kadett_Kombi_front_20080224.jpg"),
            new ModelServiceModel("Opel", "Astra F", "CAR", 1991, 1998, "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Opel_Astra_F_front_20081229.jpg/300px-Opel_Astra_F_front_20081229.jpg"),
            new ModelServiceModel("Opel", "Astra G", "CAR", 1998, 2004, "https://upload.wikimedia.org/wikipedia/commons/thumb/3/32/Opel_Astra_G_Coup%C3%A9.JPG/300px-Opel_Astra_G_Coup%C3%A9.JPG"),
            new ModelServiceModel("Opel", "Astra H", "CAR", 2004, 2009, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/86/Opel_Astra_H_Caravan_1.9_CDTI_front.JPG/300px-Opel_Astra_H_Caravan_1.9_CDTI_front.JPG"),
            new ModelServiceModel("Opel", "Astra J", "CAR", 2009, 2015, "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/Opel_Astra_J.JPG/300px-Opel_Astra_J.JPG"),
            new ModelServiceModel("Opel", "Astra K", "CAR", 2015, "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ea/Opel_Astra_1.4_EDIT_ecoFLEX_Innovation_%28K%29_%E2%80%93_Frontansicht%2C_10._Oktober_2015%2C_D%C3%BCsseldorf.jpg/300px-Opel_Astra_1.4_EDIT_ecoFLEX_Innovation_%28K%29_%E2%80%93_Frontansicht%2C_10._Oktober_2015%2C_D%C3%BCsseldorf.jpg"),
            new ModelServiceModel("Opel", "Ampera", "CAR", 2011, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Opel_Ampera_%E2%80%93_Frontansicht%2C_18._Juni_2012%2C_D%C3%BCsseldorf.jpg/300px-Opel_Ampera_%E2%80%93_Frontansicht%2C_18._Juni_2012%2C_D%C3%BCsseldorf.jpg"),
            new ModelServiceModel("Opel", "Olympia A", "CAR", 1967, 1970, "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6a/1968-Opel-Olympia.jpg/300px-1968-Opel-Olympia.jpg"),
            new ModelServiceModel("Opel", "Ascona A", "CAR", 1970, 1975, "https://upload.wikimedia.org/wikipedia/commons/d/df/Opel_Ascona_A_1974.jpg"),
            new ModelServiceModel("Opel", "Manta A", "CAR", 1970, 1975, "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/Manta_A_GTE.JPG/300px-Manta_A_GTE.JPG"),
            new ModelServiceModel("Opel", "Ascona B", "CAR", 1975, 1981, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Ascona_B_rechts.jpg/300px-Ascona_B_rechts.jpg"),
            new ModelServiceModel("Opel", "Manta B", "CAR", 1975, 1988, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/1975_Opel_Manta_B_Heck.jpg/300px-1975_Opel_Manta_B_Heck.jpg"),
            new ModelServiceModel("Opel", "Ascona C", "CAR", 1981, 1988, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/57/Opel_ascona_c.jpg/300px-Opel_ascona_c.jpg"),
            new ModelServiceModel("Opel", "Vectra A", "CAR", 1988, 1995, "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c0/Opel_Vectra_front_20071109.jpg/300px-Opel_Vectra_front_20071109.jpg"),
            new ModelServiceModel("Opel", "Calibra", "CAR", 1990, 1997, "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/Opel_Calibra_front_20071007.jpg/300px-Opel_Calibra_front_20071007.jpg"),
            new ModelServiceModel("Opel", "Vectra B", "CAR", 1995, 2002, "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c6/Opel_vectra_b.jpg/300px-Opel_vectra_b.jpg"),
            new ModelServiceModel("Opel", "Vectra C", "CAR", 2002, 2008, "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Vec_C2006.jpg/300px-Vec_C2006.jpg"),
            new ModelServiceModel("Opel", "Signum", "CAR", 2003, 2008, "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9b/Opel_Signum_rear_20090919.jpg/300px-Opel_Signum_rear_20090919.jpg"),
            new ModelServiceModel("Opel", "Insignia A", "CAR", 2008, 2017, "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c4/Opel_Insignia_20090717_front.jpg/300px-Opel_Insignia_20090717_front.jpg"),
            new ModelServiceModel("Opel", "Cascada", "CAR", 2017, 2019, "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/Opel_Cascada_1.6_EDIT_Innovation_%E2%80%93_Frontansicht%2C_23._M%C3%A4rz_2014%2C_D%C3%BCsseldorf.jpg/300px-Opel_Cascada_1.6_EDIT_Innovation_%E2%80%93_Frontansicht%2C_23._M%C3%A4rz_2014%2C_D%C3%BCsseldorf.jpg"),
            new ModelServiceModel("Opel", "Insignia B", "CAR", 2017, "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ed/Opel_Insignia_Sports_Tourer_1.5_DIT_Innovation_%28B%29_%E2%80%93_Frontansicht%2C_12._Mai_2017%2C_D%C3%BCsseldorf.jpg/300px-Opel_Insignia_Sports_Tourer_1.5_DIT_Innovation_%28B%29_%E2%80%93_Frontansicht%2C_12._Mai_2017%2C_D%C3%BCsseldorf.jpg"),
            new ModelServiceModel("Opel", "Omega A", "CAR", 1986, 1993, "https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/Opel_Omega_front_20071007.jpg/300px-Opel_Omega_front_20071007.jpg"),
            new ModelServiceModel("Opel", "Senator B", "CAR", 1987, 1993, "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Opel_Senator_B_front_20080102.jpg/300px-Opel_Senator_B_front_20080102.jpg"),
            new ModelServiceModel("Opel", "Omega B", "CAR", 1994, 2003, "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Opel_Omega_II_2.2i_Facelift_front_20100509.jpg/300px-Opel_Omega_II_2.2i_Facelift_front_20100509.jpg"),
            new ModelServiceModel("Opel", "Speedster", "CAR", 2000, 2005, "https://upload.wikimedia.org/wikipedia/commons/thumb/3/38/Opel_Speedster_22_v03.jpg/300px-Opel_Speedster_22_v03.jpg"),
            new ModelServiceModel("Opel", "GT (Roadster)", "CAR", 2007, 2009, "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/Opel_GT_front.jpg/300px-Opel_GT_front.jpg"),
            new ModelServiceModel("Opel", "Meriva A", "CAR", 2003, 2010, "https://upload.wikimedia.org/wikipedia/commons/thumb/9/97/Opel_Meriva_Facelift_20090812_front.JPG/300px-Opel_Meriva_Facelift_20090812_front.JPG"),
            new ModelServiceModel("Opel", "Agila B", "CAR", 2008, 2015, "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Opel_Agila_1.2_ecoFLEX_Edition_%28B%29_%E2%80%93_Frontansicht%2C_7._April_2011%2C_Velbert.jpg/300px-Opel_Agila_1.2_ecoFLEX_Edition_%28B%29_%E2%80%93_Frontansicht%2C_7._April_2011%2C_Velbert.jpg"),
            new ModelServiceModel("Opel", "Sintra", "CAR", 1996, 1999, "https://upload.wikimedia.org/wikipedia/commons/thumb/6/62/OPEL-VAUX-SINTRA-A.jpg/300px-OPEL-VAUX-SINTRA-A.jpg"),
            new ModelServiceModel("Opel", "Zafira Tourer C", "CAR", 2011, 2019, "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9f/Opel_Zafira_C_2016_facelift_at_Schaffen_Diest_%282017%29.jpg/300px-Opel_Zafira_C_2016_facelift_at_Schaffen_Diest_%282017%29.jpg"),
            new ModelServiceModel("Opel", "Movano A", "BUS", 1998, 2010, "https://upload.wikimedia.org/wikipedia/commons/thumb/0/07/Opel_Movano_MTF.jpg/300px-Opel_Movano_MTF.jpg"),
            new ModelServiceModel("Opel", "Vivaro A", "BUS", 2001, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Opel_Vivaro_front_20080108.jpg/300px-Opel_Vivaro_front_20080108.jpg"),
            new ModelServiceModel("Opel", "Movano B", "BUS", 2010, "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6c/Opel_Movano_B_front_20100705.jpg/300px-Opel_Movano_B_front_20100705.jpg"),
            new ModelServiceModel("Opel", "Blitz", "TRUCK", 1966, 1975, "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Opel_blitz_3_sst.jpg/300px-Opel_blitz_3_sst.jpg")

    );
    private static final List<User> SAMPLE_USERS = List.of(
            new User("admin", "Admin", "Adminov", "admin", true, Set.of(Role.ROLE_ADMIN), "/img/user-avatar.svg"),
            new User("ivan1", "Ivan", "Ivanov", "ivan123", true, Set.of(Role.ROLE_BUYER), "/img/user-avatar.svg"),
            new User("dimitar2", "Dimitar", "Georgiev", "dimitar", true, Set.of(Role.ROLE_SELLER), "/img/user-avatar.svg")
    );

    @Override
    public void run(String... args) {
        if (userService.getUsersCount() == 0) {
            this.userService.createUsersBatch(SAMPLE_USERS);
        }

        if (this.brandService.brandsCount() == 0) {
            this.brandService.createBrandsBatch(SAMPLE_BRANDS);
        }

        if (this.modelService.modelsCount() == 0) {
            this.modelService.createModelsBatch(SAMPLE_MODELS);
        }

    }
}

