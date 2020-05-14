import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Program {

    private static Map<String, List<Pracownik>> cache = new HashMap<>();

    public static void main(String[] args) throws Exception {
        System.out.println("Podaj nazwe pliku do wczytywania : ");
        Scanner scanner = new Scanner(System.in);
        String nazwaPliku = scanner.next();
        File file = new File(nazwaPliku.concat(".xml"));
        if(file.exists()){
            readFileToCache(nazwaPliku);
        } else {
            createEmptyFile(nazwaPliku);
        }
        System.out.println("Podaj komende : ");
        String komenda = scanner.next();
        while (!komenda.equals("Koniec")){
            if(komenda.equals("Nowy")){
                System.out.println("Napisz 1 by dodac Kierowce");
                System.out.println("Napisz 2 by dodac Magazyniera");
                komenda = scanner.next();
                if(komenda.equals("1")){
                    Kierowca kierowca = new Kierowca().createNewEmployee();
                    cache.computeIfAbsent("Kierowca", k -> new ArrayList<>());
                    cache.get("Kierowca").add(kierowca);
                    System.out.println("Kierowca dodany");
                } else if(komenda.equals("2")) {
                    Magazynier magazynier = new Magazynier().createNewEmployee();
                    cache.computeIfAbsent("Magazynier", k -> new ArrayList<>());
                    cache.get("Magazynier").add(magazynier);
                    System.out.println("Magazynier dodany");
                }
                komenda = "error";
            } else if(komenda.equals("Lista")){
                printTable(cache);
                komenda = "clear";
            } else if(komenda.equals("clear")) {
                komenda = scanner.next();
            } else {
                System.out.println("Nieznana komenda");
                komenda = scanner.next();
            }
        }
        saveCacheToFile(nazwaPliku);
        System.exit(0);
    }

    private static void saveCacheToFile(String fileName) throws IOException {
        XmlMapper mapper = new XmlMapper();
        mapper.configure( ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true );

        String pracowniki = mapper
                .writerWithDefaultPrettyPrinter()
                .withRootName("Pracowniki")
                .writeValueAsString(cache);

        File myFoo = new File(fileName.concat(".xml"));
        FileWriter fooWriter = new FileWriter(myFoo, false); // true to append
        // false to overwrite.
        fooWriter.write(pracowniki);
        fooWriter.close();
        System.out.println("Cache zachowany do pliku: " + fileName.concat(".xml"));
    }

    private static void createEmptyFile(String fileName) throws IOException {
        boolean newFile = new File(fileName.concat(".xml")).createNewFile();
        System.out.println("Stworzono nowy plik o nazwie " + fileName.concat(".xml"));
    }

    private static void readFileToCache(String fileName) throws Exception {
        cache.put("Kierowca", new ArrayList<>());
        cache.put("Magazynier", new ArrayList<>());
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document dom = db.parse(new File(fileName.concat(".xml")));
        Element docEle = dom.getDocumentElement();
        NodeList nl = docEle.getChildNodes();
        int length = nl.getLength();
        for (int i = 0; i < length; i++) {
            if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element) nl.item(i);
                Kierowca kierowca = new Kierowca();
                if (el.getNodeName().contains("Kierowca")) {
                    cache.get("Kierowca").add(new Kierowca(
                            el.getElementsByTagName("Imie").item(0).getTextContent(),
                            el.getElementsByTagName("Nazwisko").item(0).getTextContent(),
                            new SimpleDateFormat("dd/MM/yyyy").parse(el.getElementsByTagName("Data_Zatrudnienia").item(0).getTextContent()),
                            Long.valueOf(el.getElementsByTagName("Pesja_Brutto").item(0).getTextContent()),
                            el.getElementsByTagName("Kategoria_Prawa_Jazdy").item(0).getTextContent(),
                            new SimpleDateFormat("dd/MM/yyyy").parse(el.getElementsByTagName("Data_Uzyskania_Prawa_Jazdy").item(0).getTextContent()),
                            Long.valueOf(el.getElementsByTagName("Liczba_Punktow_Karnych").item(0).getTextContent())));
                } else if(el.getNodeName().equals("Magazynier")){
                    cache.get("Magazynier").add(new Magazynier(
                            el.getElementsByTagName("Imie").item(0).getTextContent(),
                            el.getElementsByTagName("Nazwisko").item(0).getTextContent(),
                            new SimpleDateFormat("dd/MM/yyyy").parse(el.getElementsByTagName("Data_Zatrudnienia").item(0).getTextContent()),
                            Long.valueOf(el.getElementsByTagName("Pesja_Brutto").item(0).getTextContent()),
                            el.getElementsByTagName("Plec").item(0).getTextContent(),
                            new SimpleDateFormat("dd/MM/yyyy").parse(el.getElementsByTagName("Data_Waznosci_Badan_Zdrowotnych").item(0).getTextContent()),
                            el.getElementsByTagName("Oznaczenie_Przypisanego_Magazynu").item(0).getTextContent()));
                }
            }
        }
        System.out.println("Plik o nazwie " + fileName + " zostal wczytany");
    }

    private  static <T extends Pracownik> void printTable(Map<String, List<T>> map) {
        List<T> magazyniery = map.get("Magazynier");
        List<T> kierowcy = map.get("Kierowca");
        TableList table = new TableList(11, "Stanowisko", "Imie", "Nazwisko", "Data Zatrudnienia", "Pesja Brutto",
                "Kategoria Prawa Jazdy", "Data Uzyskania PrawaJazdy", "Liczba Punktow Karnych",
                "Plec", "Data Waznosci Badan Zdrowotnych", "Oznaczenie Przypisanego Magazynu").sortBy(0).withUnicode(true);
        for (int i = 0; i < 11; i++) {
            table.align(i, TableList.EnumAlignment.CENTER);
        }
        if(magazyniery != null)
        magazyniery.forEach(magazynier -> {
            table.addRow("Magazynier", magazynier.getImie(), magazynier.getNazwisko(), magazynier.getDataZatrudnienia().toString(), magazynier.getPesjaBrutto().toString(),
                    "-", "-", "-",
                    ((Magazynier) magazynier).getPlec(),
                    ((Magazynier) magazynier).getDataWaznosciBadanZdrowotnych().toString(),
                    ((Magazynier) magazynier).getOznaczeniePrzypisanegoMagazynu());
        });
        if(kierowcy != null)
        kierowcy.forEach(kierowca -> {
            table.addRow("Kierowca", kierowca.getImie(), kierowca.getNazwisko(), kierowca.getDataZatrudnienia().toString(), kierowca.getPesjaBrutto().toString(),
                    ((Kierowca) kierowca).getKategoriaPrawaJazdy(),
                    ((Kierowca) kierowca).getDataUzyskaniaPrawaJazdy().toString(),
                    ((Kierowca) kierowca).getLiczbaPunktowKarnych().toString(),
                    "-", "-", "-");
        });
        table.print();
    }
}
