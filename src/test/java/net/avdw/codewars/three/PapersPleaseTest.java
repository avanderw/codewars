package net.avdw.codewars.three;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class PapersPleaseTest {

    static String bullentin01 = "Entrants require passport\n" +
            "Allow citizens of Arstotzka, Obristan";
    static String bullentin02 = "Entrants require passport\n" +
            "Allow citizens of Arstotzka\n" +
            "Wanted by the State: Alexis Schumer";
    static String bullentin03 = "Entrants require passport\n" +
            "Allow citizens of Arstotzka\n" +
            "Wanted by the State: Rachel Owsianka";
    static String bullentin04 = "Allow citizens of Antegria, Impor, Kolechia, Obristan, Republia, United Federation\n" +
            "Wanted by the State: Aleksi Hertzog";
    static String bullentin05 = "Foreigners require access permit\n" +
            "Wanted by the State: Dominik Bosch";
    static String bullentin06 = "Foreigners require access permit\n" +
            "Wanted by the State: Kascha Borshiki";
    static String bullentin07 = "Citizens of Arstotzka require ID card\n" +
            "Deny citizens of Antegria\n" +
            "Wanted by the State: Beatrix Sajarvi";
    static String bullentin08 = "Citizens of Arstotzka require ID card\n" +
            "Deny citizens of Kolechia\n" +
            "Wanted by the State: Joseph Smirnov";
    static String bullentin09 = "Deny citizens of Impor\n" +
            "Citizens of Antegria require polio vaccination\n" +
            "Workers require work pass\n" +
            "Wanted by the State: Stefan Rosenfeld";
    static String bullentin10 = "Citizens of Arstotzka require ID card\n" +
            "Deny citizens of Republia\n" +
            "Foreigners require polio vaccination\n" +
            "Wanted by the State: Natalia Lukowski";
    static String bullentin11 = "Citizens of Arstotzka require ID card\n" +
            "Deny citizens of Impor\n" +
            "Citizens of Kolechia, Impor, Antegria require tetanus vaccination\n" +
            "Wanted by the State: Fyodor Sorenson";

    static Map<String, String> josef = new HashMap<>();
    static Map<String, String> guyovich = new HashMap<>();
    static Map<String, String> roman = new HashMap<>();
    static Map<String, String> yulia = new HashMap<>();
    static Map<String, String> rachel = new HashMap<>();
    static Map<String, String> wilma = new HashMap<>();
    static Map<String, String> josefina = new HashMap<>();
    static Map<String, String> attila = new HashMap<>();
    static Map<String, String> romeo = new HashMap<>();
    static Map<String, String> spektor = new HashMap<>();
    static Map<String, String> ada = new HashMap<>();
    static Map<String, String> illya = new HashMap<>();
    static Map<String, String> ivanka = new HashMap<>();
    static Map<String, String> mismatch = new HashMap<>();
    static Map<String, String> ekaterina = new HashMap<>();


    @BeforeClass
    static public void setupPeople() {
        josef.put("passport", "ID#: GC07D-FU8AR\nNATION: Arstotzka\nNAME: Costanza, Josef\nDOB: 1933.11.28\nSEX: M\nISS: East Grestin\nEXP: 1983.03.15");
        guyovich.put("access_permit", "NAME: Guyovich, Russian\nNATION: Obristan\nID#: TE8M1-V3N7R\nPURPOSE: TRANSIT\nDURATION: 14 DAYS\nHEIGHT: 159cm\nWEIGHT: 60kg\nEXP: 1983.07.13");
        roman.put("passport", "ID#: WK9XA-LKM0Q\nNATION: United Federation\nNAME: Dolanski, Roman\nDOB: 1933.01.01\nSEX: M\nISS: Shingleton\nEXP: 1983.05.12");
        roman.put("grant_of_asylum", "NAME: Dolanski, Roman\nNATION: United Federation\nID#: Y3MNC-TPWQ2\nDOB: 1933.01.01\nHEIGHT: 176cm\nWEIGHT: 71kg\nEXP: 1983.09.20");
        yulia.put("passport", "NATION: Antegria\nDOB: 1961.01.13\nSEX: F\nISS: Outer Grouse\nID#: XDT2B-CYPRM\nEXP: 1985.08.10\nNAME: Borg, Yulia");
        rachel.put("passport", "NATION: Antegria\nDOB: 1924.01.31\nSEX: F\nISS: St. Marmero\nID#: A229E-DRQL8\nEXP: 1984.09.07\nNAME: Owsianka, Rachel");
        wilma.put("passport", "NATION: Republia\n" +
                "DOB: 1938.01.14\n" +
                "SEX: F\n" +
                "ISS: Lesrenadi\n" +
                "ID#: REUZ4-QNKVY\n" +
                "EXP: 1981.02.14\n" +
                "NAME: DeGraff, Wilma");
        josefina.put("passport", "NATION: Impor\n" +
                "DOB: 1916.01.08\n" +
                "SEX: F\n" +
                "ISS: Tsunkeido\n" +
                "ID#: Y3PA5-FT84H\n" +
                "EXP: 1982.08.06\n" +
                "NAME: Zhang, Josefina");
        attila.put("passport", "NATION: Arstotzka\n" +
                "DOB: 1934.12.04\n" +
                "SEX: M\n" +
                "ISS: East Grestin\n" +
                "ID#: R2CY0-TKR57\n" +
                "EXP: 1982.01.15\n" +
                "NAME: Khan, Attila");
        romeo.put("passport", "NATION: Impor\n" +
                "DOB: 1923.09.18\n" +
                "SEX: M\n" +
                "ISS: Haihan\n" +
                "ID#: X1D0E-T50XB\n" +
                "EXP: 1983.04.10\n" +
                "NAME: Gregorovich, Romeo");
        romeo.put("diplomatic_authorization", "NATION: Impor\n" +
                "NAME: Gregorovich, Romeo\n" +
                "ID#: X1D0E-T50XB\n" +
                "ACCESS: Arstotzka, Antegria");
        spektor.put("passport", "NATION: United Federation\n" +
                "DOB: 1927.07.29\n" +
                "SEX: F\n" +
                "ISS: Shingleton\n" +
                "ID#: OTSEY-DFH7B\n" +
                "EXP: 1984.09.23\n" +
                "NAME: Spektor, Josefina");
        spektor.put("access_permit", "NAME: Spektor, Josefina\n" +
                "NATION: United Federation\n" +
                "ID#: N98H3-C6PON\n" +
                "PURPOSE: VISIT\n" +
                "DURATION: 3 MONTHS\n" +
                "HEIGHT: 188.0cm\n" +
                "WEIGHT: 102.0kg\n" +
                "EXP: 1984.03.13");
        ada.put("passport","NATION: Arstotzka\n" +
                "DOB: 1939.10.06\n" +
                "SEX: F\n" +
                "ISS: Orvech Vonor\n" +
                "ID#: BBTVM-NQ9EY\n" +
                "EXP: 1984.04.16\n" +
                "NAME: Rasmussen, Ada");
        ada.put("ID_card", "NAME: Rasmussen, Ada\n" +
                "DOB: 1939.10.06\n" +
                "HEIGHT: 179.0cm\n" +
                "WEIGHT: 89.0kg");
        illya.put("passport","NATION: Kolechia\n" +
                "DOB: 1926.02.09\n" +
                "SEX: F\n" +
                "ISS: West Grestin\n" +
                "ID#: RQDKU-TXIDG\n" +
                "EXP: 1984.10.06\n" +
                "NAME: Tjell, Ilya");
        illya.put("grant_of_asylum","NAME: Tjell, Ilya\n" +
                "NATION: Kolechia\n" +
                "ID#: RQDKU-TXIDG\n" +
                "DOB: 1926.02.09\n" +
                "HEIGHT: 156.0cm\n" +
                "WEIGHT: 56.0kg\n" +
                "EXP: 1983.01.19");
        ivanka.put("passport","NATION: Antegria\n" +
                "DOB: 1928.11.07\n" +
                "SEX: F\n" +
                "ISS: Glorian\n" +
                "ID#: F2WMC-ZYNYE\n" +
                "EXP: 1984.05.09\n" +
                "NAME: Hammacher, Ivanka");
        ivanka.put("access_permit","NAME: Hammacher, Ivanka\n" +
                "NATION: Antegria\n" +
                "ID#: F2WMC-ZYNYE\n" +
                "PURPOSE: VISIT\n" +
                "DURATION: 1 MONTH\n" +
                "HEIGHT: 176.0cm\n" +
                "WEIGHT: 84.0kg\n" +
                "EXP: 1984.06.13");
        mismatch.put("passport","NATION: Arstotzka\n" +
                "DOB: 1957.08.17\n" +
                "SEX: M\n" +
                "ISS: Orvech Vonor\n" +
                "ID#: V7ZFN-JCG9W\n" +
                "EXP: 1984.06.07\n" +
                "NAME: Kerr, Erik");
        mismatch.put("ID_card","NAME: Steiner, Maciej\n" +
                "DOB: 1957.08.17\n" +
                "HEIGHT: 147.0cm\n" +
                "WEIGHT: 43.0kg");
        ekaterina.put("passport","NATION: Antegria\n" +
                "DOB: 1921.06.24\n" +
                "SEX: F\n" +
                "ISS: Glorian\n" +
                "ID#: EXMBY-GG2O4\n" +
                "EXP: 1985.05.09\n" +
                "NAME: Schneider, Ekaterina");
        ekaterina.put("access_permit","NAME: Schneider, Ekaterina\n" +
                "NATION: Antegria\n" +
                "ID#: EXMBY-GG2O4\n" +
                "PURPOSE: TRANSIT\n" +
                "DURATION: 2 DAYS\n" +
                "HEIGHT: 146.0cm\n" +
                "WEIGHT: 41.0kg\n" +
                "EXP: 1985.07.25");
        ekaterina.put("certificate_of_vaccination","NAME: Schneider, Ekaterina\n" +
                "ID#: EXMBY-GG2O4\n" +
                "VACCINES: cowpox, HPV, yellow fever");
    }


    @Test
    public void GivenMissingDocument_WhenValidated_ThenFail() {
        PapersPlease inspector = new PapersPlease();
        inspector.receiveBulletin(bullentin01);
        assertEquals("Entry denied: missing required passport.", inspector.inspect(guyovich));
    }

    @Test
    public void GivenValidCitizen_WhenValidated_ThenSucceed() {
        PapersPlease inspector = new PapersPlease();
        inspector.receiveBulletin(bullentin01);
        assertEquals("Glory to Arstotzka.", inspector.inspect(josef));

    }

    @Test
    public void GivenIdMismatch_WhenValidated_ThenFail() {
        PapersPlease inspector = new PapersPlease();
        inspector.receiveBulletin(bullentin01);
        assertEquals("Detainment: ID number mismatch.", inspector.inspect(roman));


        inspector = new PapersPlease();
        inspector.receiveBulletin(bullentin06);
        assertEquals("Detainment: ID number mismatch.", inspector.inspect(spektor));
    }

    @Test
    public void GivenBannedState_WhenValidated_ThenFail() {
        PapersPlease inspector = new PapersPlease();
        inspector.receiveBulletin(bullentin02);
        assertEquals("Entry denied: citizen of banned nation.", inspector.inspect(yulia));

         inspector = new PapersPlease();
        inspector.receiveBulletin(bullentin08);
        assertEquals("Entry denied: citizen of banned nation.", inspector.inspect(illya));
    }

    @Test
    public void GivenWantedStatus_WhenValidated_ThenFail() {
        PapersPlease inspector = new PapersPlease();
        inspector.receiveBulletin(bullentin03);
        assertEquals("Detainment: Entrant is a wanted criminal.", inspector.inspect(rachel));
    }

    @Test
    public void GivenExpiredPassport_WhenValidated_ThenFail() {
        PapersPlease inspector = new PapersPlease();
        inspector.receiveBulletin(bullentin04);
        assertEquals("Entry denied: passport expired.", inspector.inspect(wilma));
        assertEquals("Entry denied: passport expired.", inspector.inspect(josefina));

        inspector = new PapersPlease();
        inspector.receiveBulletin(bullentin02);
        assertEquals("Entry denied: passport expired.", inspector.inspect(attila));
    }

    @Test
    public void GivenDiplomaticAuthorization_WhenValidated_ThenSucceed() {
        PapersPlease inspector = new PapersPlease();
        inspector.receiveBulletin(bullentin05);

        assertEquals("Cause no trouble.", inspector.inspect(romeo));
    }


    @Test
    public void GivenIdCard_WhenValidated_ThenSucceed() {
        PapersPlease inspector = new PapersPlease();
        inspector.receiveBulletin(bullentin07);

        assertEquals("Glory to Arstotzka.", inspector.inspect(ada));
    }

    @Test
    public void GivenNoVaccination_WhenValidated_ThenFail() {
        PapersPlease inspector = new PapersPlease();
        inspector.receiveBulletin(bullentin09);
        assertEquals("Entry denied: missing required certificate of vaccination.", inspector.inspect(ivanka));

        inspector = new PapersPlease();
        inspector.receiveBulletin(bullentin11);
        assertEquals("Entry denied: missing required certificate of vaccination.", inspector.inspect(ekaterina));
    }

    @Test
    public void GivenNameMismatch_WhenValidated_ThenFail() {
        PapersPlease inspector = new PapersPlease();
        inspector.receiveBulletin(bullentin10);

        assertEquals("Detainment: name mismatch.", inspector.inspect(mismatch));
    }
}