/**
 * Created by gokhany on 4/23/17.
 */
import java.util.Random;
import java.util.UUID;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.fluttercode.datafactory.impl.DataFactory;
import org.joda.time.DateTime;
/**
 *
 * <Call Detail Record Generator>
 * @author GokhanY.
 *
 */

public class App {

    private static final String FILENAME = "CDR.txt";

    public static void main(String[] args) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME)))
        {
            DataFactory df = new DataFactory();
            Random r = new Random();
            String[] cities={"ISTANBUL","SAKARYA","ANKARA","BURSA",
                    "IZMIR","KONYA","MALATYA","TUNCELI","SIVAS","BOLU","ADANA",
                    "HAKKARİ","MUGLA","MERSIN","TOKAT","EDIRNE","TEKIRDAG",
                    "KAHRAMANMARAS","KAYSERI","AFYON","SIRNAK","ADIYAMAN",
                    "KUTAHYA","AMASYA","ARDAHAN","ARTVIN","MUS","ANTALYA",
                    "KARS","VAN","DENIZLI","ELAZIG","ERZURUM","ERZINCAN",
                    "AGRI","SAMSUN","SINOP","TRABZON","ZONGULDAK","DUZCE",
                    "RIZE","ESKISEHIR","KIRSEHIR","KIRKLARELI","MANISA",
                    "BILECIK","YALOVA","YOZGAT","SANLIURFA","IZMIT","KILIS",
                    "HATAY","KARABUK","BARTIN","KASTAMONU","ORDU","GIRESUN",
                    "IGDIR","SIIRT","DIYARBAKIR","BINGOL","BITLIS","KILIS",
                    "OSMANIYE","AYDIN","BALIKESIR","CANAKKALE","USAK","ISPARTA",
                    "KIRIKKALE","CANKIRI","CORUM","GUMUSHANE","BAYBURT","BATMAN",
                    "NIGDE","AKSARAY","NEVSEHIR","KARAMAN","BURDUR","MARDIN"};


            for (int i = 0; i < 1000; i++) {

                UUID id = UUID.randomUUID();

                String calling_num = df.getNumberText(10);
                String called_num = df.getNumberText(10);

                String sourceLocation = df.getItem(cities);
                String destination=df.getItem(cities);

                long t1 = System.currentTimeMillis() + r.nextInt();
                long t2 = t1 + 2 * 60 * 1000 + r.nextInt(60 * 1000) + 1;
                DateTime d1 = new DateTime(t1);
                DateTime d2 = new DateTime(t2);
                String callType = "";

                if ( r.nextInt() % 2 == 0){callType = "VOICE";}
                else {callType = "SMS";}

                if ("SMS" == callType) {d2 = new DateTime(t1);}

                String callResult = "ANSWERED";

                if ((i % 10 )== 0 && callType == "VOICE") {
                    callResult = "BUSY";
                    d2 = new DateTime(t1);
                }
                Float charge = r.nextFloat();

                String content = id.toString()+"|"+calling_num+"|"+called_num+"|"+d1.toString()+"|"+d2.toString()+"|"+sourceLocation+"|"+destination+"|"+callType+"|"+charge+"|"+callResult+"\n";
                bw.write(content);

            }

            System.out.println("\n"+"CdrUniqueID"+"|"+"CallingParty"+"|"+"CalledParty"+"|"+"CallStartTime"+"|"+"CallEndTime"+"|"+"Location"+"|"+"Destination"+"|"+"CallType"+"|"+"Charge"+"|"+"CallResult"+"\n");
            System.out.println("Process is done!");

        } catch (IOException e) {e.printStackTrace();}
    }
}

