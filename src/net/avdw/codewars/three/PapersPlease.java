package net.avdw.codewars.three;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PapersPlease {
    Set<String> allowedNations = new HashSet<>();
    Set<String> deniedNations = new HashSet<>();
    Map<String, Set<String>> requiredDocuments = new HashMap<>();
    Map<String, Set<String>> requiredVaccinations = new HashMap<>();
    Set<String> wanted = new HashSet<>();
    static Set<String> months = new HashSet<>();
    Date date;

    static {
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
    }

    public void receiveBulletin(String bulletin) {

        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
        try {
            date = sdf.parse("November 22, 1982");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("\n=== BULLETIN === \n" + bulletin);
        Scanner bulletinScanner = new Scanner(bulletin);
        while (bulletinScanner.hasNextLine()) {
            String bulletinLine = bulletinScanner.nextLine();

            if (bulletinLine.startsWith("Allow")) {
                String input = bulletinLine.substring("Allow citizens of ".length());
                Scanner bulletinLineScanner = new Scanner(input);
                bulletinLineScanner.useDelimiter(",");
                while (bulletinLineScanner.hasNext()) {
                    allowedNations.add(bulletinLineScanner.next().trim());
                }
            } else if (bulletinLine.startsWith("Wanted by the State:")) {
                String input = bulletinLine.substring("Wanted by the State:".length());
                Scanner bulletinLineScanner = new Scanner(input);
                bulletinLineScanner.useDelimiter(",");
                while (bulletinLineScanner.hasNext()) {
                    wanted.add(bulletinLineScanner.next().trim());
                }
            } else if (bulletinLine.contains("require")) {
                if (bulletinLine.contains("vaccination")) {
                    Pattern pattern = Pattern.compile("(\\w+) of (\\w+) require (\\w+) vaccination");
                    Matcher matcher = pattern.matcher(bulletinLine);
                    if (matcher.find()) {
                        requiredVaccinations.putIfAbsent(matcher.group(2), new HashSet<>());
                        requiredVaccinations.get(matcher.group(2)).add(matcher.group(3));
                        allowedNations.add(matcher.group(2));
                    } else {
                        pattern = Pattern.compile("(\\w+) require (\\w+) vaccination");
                        Matcher matcher2 = pattern.matcher(bulletinLine);
                        if (matcher2.find()) {
                            if (matcher2.group(1).equals("Entrants")) {
                                allowedNations.forEach(allowed -> {
                                    requiredVaccinations.putIfAbsent(allowed, new HashSet<>());
                                    requiredVaccinations.get(allowed).add(matcher2.group(2));
                                });
                            }
                        } else {
                            System.out.println("ERROR: " + bulletinLine);
                        }
                    }
                } else {
                    if (bulletinLine.startsWith("Foreigners")) {
                        requiredDocuments.putIfAbsent("Foreigners", new HashSet<>());
                        Scanner nationScanner = new Scanner(bulletinLine.substring("Foreigners require ".length()));
                        nationScanner.useDelimiter(",");
                        while (nationScanner.hasNext()) {
                            requiredDocuments.get("Foreigners").add(nationScanner.next().trim().replaceAll(" ", "_"));
                        }
                    } else if (bulletinLine.startsWith("Citizens")) {
                        requiredDocuments.putIfAbsent("Citizens", new HashSet<>());
                        Scanner nationScanner = new Scanner(bulletinLine.substring("Citizens of Arstotzka require ".length()));
                        nationScanner.useDelimiter(",");
                        while (nationScanner.hasNext()) {
                            requiredDocuments.get("Citizens").add(nationScanner.next().trim().replaceAll(" ", "_"));
                        }
                    } else if (bulletinLine.startsWith("Workers")) {
                        requiredDocuments.putIfAbsent("Workers", new HashSet<>());
                        Scanner nationScanner = new Scanner(bulletinLine.substring("Workers require ".length()));
                        nationScanner.useDelimiter(",");
                        while (nationScanner.hasNext()) {
                            requiredDocuments.get("Workers").add(nationScanner.next().trim().replaceAll(" ", "_"));
                        }
                    } else if (bulletinLine.startsWith("Entrants")) {
                        requiredDocuments.putIfAbsent("Foreigners", new HashSet<>());
                        requiredDocuments.putIfAbsent("Citizens", new HashSet<>());
                        requiredDocuments.putIfAbsent("Workers", new HashSet<>());
                        Scanner nationScanner = new Scanner(bulletinLine.substring("Entrants require ".length()));
                        nationScanner.useDelimiter(",");
                        while (nationScanner.hasNext()) {
                            String nation = nationScanner.next().trim();
                            requiredDocuments.get("Foreigners").add(nation);
                            requiredDocuments.get("Citizens").add(nation);
                            requiredDocuments.get("Workers").add(nation);
                        }
                    } else {
                        System.out.println("ERROR: " + bulletinLine);
                    }
                }
            }
        }
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

    public String inspect(Map<String, String> person) {
        System.out.println(String.format("\ninspecting %s", person));
        List<Map<String, String>> documents = new ArrayList<>();
        Map<String, String> passport = (person.containsKey("passport")) ? new HashMap<>() : null;
        if (passport != null) {
            Scanner docScanner = new Scanner(person.get("passport"));
            while (docScanner.hasNextLine()) {
                String line = docScanner.nextLine();
                passport.put(line.substring(0, line.indexOf(":")), line.substring(line.indexOf(":") + 1).trim());
            }
            documents.add(passport);
        }

        if (passport != null) {
            Scanner nameScanner = new Scanner(passport.get("NAME"));
            nameScanner.useDelimiter(",");
            String lastName = nameScanner.next().trim();
            String firstName = nameScanner.next().trim();

            if (wanted.contains(String.format("%s %s", firstName, lastName))) {
                return "Detainment: Entrant is a wanted criminal.";
            }
        }

        Map<String, String> grantOfAsylum = (person.containsKey("grant_of_asylum")) ? new HashMap<>() : null;
        if (grantOfAsylum != null) {
            Scanner docScanner = new Scanner(person.get("grant_of_asylum"));
            while (docScanner.hasNextLine()) {
                String line = docScanner.nextLine();
                grantOfAsylum.put(line.substring(0, line.indexOf(":")), line.substring(line.indexOf(":") + 1).trim());
            }
            documents.add(grantOfAsylum);
        }

        if (grantOfAsylum != null) {

            for (int i = 0; i < documents.size(); i++) {
                for (int j = i + 1; j < documents.size(); j++) {
                    Map<String, String> doci = documents.get(i);
                    Map<String, String> docj = documents.get(j);

                    for (String k : doci.keySet()) {
                        if (!k.equals("EXP") && docj.containsKey(k) && !doci.get(k).equals(docj.get(k))) {
                            return String.format("Detainment: %s mismatch.", k.equals("ID#") ? "ID number" : k.equals("NATION") ? "nationality" : k);
                        }
                    }
                }
            }

            Scanner nameScanner = new Scanner(grantOfAsylum.get("NAME"));
            nameScanner.useDelimiter(",");
            String lastName = nameScanner.next().trim();
            String firstName = nameScanner.next().trim();

            if (wanted.contains(String.format("%s %s", firstName, lastName))) {
                return "Detainment: Entrant is a wanted criminal.";
            }
        }

        Map<String, String> diplomaticAuthorization = (person.containsKey("diplomatic_authorization")) ? new HashMap<>() : null;
        if (diplomaticAuthorization != null) {
            Scanner docScanner = new Scanner(person.get("diplomatic_authorization"));
            while (docScanner.hasNextLine()) {
                String line = docScanner.nextLine();
                diplomaticAuthorization.put(line.substring(0, line.indexOf(":")), line.substring(line.indexOf(":") + 1).trim());
            }
            documents.add(diplomaticAuthorization);
        }

        if (diplomaticAuthorization != null) {


            for (int i = 0; i < documents.size(); i++) {
                for (int j = i + 1; j < documents.size(); j++) {
                    Map<String, String> doci = documents.get(i);
                    Map<String, String> docj = documents.get(j);

                    for (String k : doci.keySet()) {
                        if (!k.equals("EXP") && docj.containsKey(k) && !doci.get(k).equals(docj.get(k))) {
                            return String.format("Detainment: %s mismatch.", k.equals("ID#") ? "ID number" : k.equals("NATION") ? "nationality" : k);
                        }
                    }
                }
            }

            Scanner nameScanner = new Scanner(diplomaticAuthorization.get("NAME"));
            nameScanner.useDelimiter(",");
            String lastName = nameScanner.next().trim();
            String firstName = nameScanner.next().trim();

            if (wanted.contains(String.format("%s %s", firstName, lastName))) {
                return "Detainment: Entrant is a wanted criminal.";
            }
        }

        Map<String, String> accessPermit = (person.containsKey("access_permit")) ? new HashMap<>() : null;
        if (accessPermit != null) {
            Scanner docScanner = new Scanner(person.get("access_permit"));
            while (docScanner.hasNextLine()) {
                String line = docScanner.nextLine();
                accessPermit.put(line.substring(0, line.indexOf(":")), line.substring(line.indexOf(":") + 1).trim());
                if (line.startsWith("EXP: ")) {
                    try {
                        Date docDate = sdf.parse(line.substring("EXP: ".length()));
                        if (date != null && docDate.before(date)) {
                            return "Entry denied: access permit expired.";
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
            documents.add(accessPermit);
        }
        Map<String, String> workPass = (person.containsKey("work_pass")) ? new HashMap<>() : null;
        if (workPass != null) {
            Scanner docScanner = new Scanner(person.get("work_pass"));
            while (docScanner.hasNextLine()) {
                String line = docScanner.nextLine();
                workPass.put(line.substring(0, line.indexOf(":")), line.substring(line.indexOf(":") + 1).trim());

            }
            documents.add(workPass);
        }

        Map<String, String> idCard = (person.containsKey("ID_card")) ? new HashMap<>() : null;
        if (idCard != null) {
            Scanner docScanner = new Scanner(person.get("ID_card"));
            while (docScanner.hasNextLine()) {
                String line = docScanner.nextLine();
                idCard.put(line.substring(0, line.indexOf(":")), line.substring(line.indexOf(":") + 1).trim());
            }
            documents.add(idCard);
        }

        Map<String, String> vaccination = (person.containsKey("certificate_of_vaccination")) ? new HashMap<>() : null;
        if (vaccination != null) {
            Scanner docScanner = new Scanner(person.get("certificate_of_vaccination"));
            while (docScanner.hasNextLine()) {
                String line = docScanner.nextLine();
                vaccination.put(line.substring(0, line.indexOf(":")), line.substring(line.indexOf(":") + 1).trim());
            }
            documents.add(vaccination);
        }

        for (int i = 0; i < documents.size(); i++) {
            for (int j = i + 1; j < documents.size(); j++) {
                Map<String, String> doci = documents.get(i);
                Map<String, String> docj = documents.get(j);

                for (String k : doci.keySet()) {
                    if (!k.equals("EXP") && docj.containsKey(k) && !doci.get(k).equals(docj.get(k))) {
                        return String.format("Detainment: %s mismatch.", k.equals("ID#") ? "ID number" : k.equals("NATION") ? "nationality" : k.toLowerCase());
                    }
                }
            }
        }

        try {
            if (passport != null) {
                Date docDate = sdf.parse(passport.get("EXP"));
                if (date != null && docDate.before(date)) {
                    return "Entry denied: passport expired.";
                }
            }
            if (grantOfAsylum != null) {
                Date docDate = sdf.parse(grantOfAsylum.get("EXP"));
                if (date != null && docDate.before(date)) {
                    return "Entry denied: grant of asylum expired.";
                }
            }
            if (workPass != null) {
                Date docDate = sdf.parse(workPass.get("EXP"));
                if (date != null && docDate.before(date)) {
                    return "Entry denied: work pass expired.";
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        String nation = (person.containsKey("ID_card")) ? "Citizens" : "Foreigners";
        if (passport != null && !nation.equals("Citizens")) {
            nation = passport.get("NATION").equals("Arstotzka") ? "Citizens" : "Foreigners";
        }

        if (requiredDocuments.containsKey(nation)) {
            Set<String> personRequires = requiredDocuments.isEmpty() ? new HashSet<>() : new HashSet<>(requiredDocuments.get(nation));
            personRequires.removeAll(person.keySet());
            if (!personRequires.isEmpty() && grantOfAsylum == null) {
                if (documents.stream().noneMatch(d -> d.containsKey("ACCESS"))) {
                    return String.format("Entry denied: missing required %s.", String.join(",", personRequires).replaceAll("_", " "));
                } else {
                    if (documents.stream().filter(d -> d.containsKey("ACCESS")).noneMatch(d -> d.get("ACCESS").contains("Arstotzka"))) {
                        return String.format("Entry denied: invalid diplomatic authorization.", String.join(",", personRequires));
                    }
                }
            }
        }


        if (!nation.equals("Citizens") && !documents.isEmpty()) {
            if (documents.stream().noneMatch(d -> d.containsKey("ACCESS"))) {
                if (deniedNations.contains(documents.get(0).get("NATION")) || !allowedNations.contains(documents.get(0).get("NATION"))) {
                    return String.format("Entry denied: citizen of banned nation.");
                }
            } else {
                if (documents.stream().filter(d -> d.containsKey("ACCESS")).noneMatch(d -> d.get("ACCESS").contains("Arstotzka"))) {
                    if (deniedNations.contains(documents.get(0).get("NATION")) || !allowedNations.contains(documents.get(0).get("NATION"))) {
                        return String.format("Entry denied: citizen of banned nation.");
                    }
                }
            }
        }

        if (requiredVaccinations.containsKey(documents.get(0).get("NATION"))) {
            if (vaccination == null) {
                return "Entry denied: missing required certificate of vaccination.";
            }
        }

        return passport.getOrDefault("NATION", "").equals("Arstotzka") ? "Glory to Arstotzka." : "Cause no trouble.";
    }
}
