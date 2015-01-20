package net.blog.dev.check.stocks.mail.modules;

import dagger.Module;
import dagger.Provides;

import javax.inject.Named;

@Module(library = true)
public class PropertiesModule {

    @Provides
    @Named("stocks.codes")
    public String providesStocksCode() {
        return "SOLB,AF,VIRP,VCT,ALT,ICAD,ERF,BOL,NEX,ACA,MAU,ATO,RCF,RMS,RIN,MMT,DIM,UBI,TFI,FDR,ATE,RIA,SAF,IPS,DEC,GFT,AI,CGG,CA,CNP,FP,OR,VK,AC,EN,LG,NEO,SAN,CS,BN,KN,RI,NK,BB,MC,RF,EO,MF,SW,RUI,ML,HO,KER,UG,EI,SK,HAV,LI,SU,VIE,POM,UL,SGO,CAP,ING,DG,CO,ZC,VIV,ALU,MMB,FR,RCO,FGR,PUB,DSY,GLE,BNP,TEC,ERA,RNO,ORA,ORP,SOI,ILD,GNFT,TKTT,ELE,BVI,GFC,LOCAL,BIM,NXI,SAFT,GSZ,ALO,ETL,MERY,EDF,IPN,LR,AKE,ADP,KORI,SCR,RXL,GET,SEV,EDEN,TCH,AREVA,NUM,GTT,SESG,MT,APAM,STM,AIR,GTO";
    }
}
