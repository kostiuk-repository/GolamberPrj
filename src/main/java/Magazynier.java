import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Magazynier extends Pracownik {

    @JacksonXmlProperty(localName = "Plec")
    private String plec;
    @JacksonXmlProperty(localName = "Data_Waznosci_Badan_Zdrowotnych")
    private Date dataWaznosciBadanZdrowotnych;
    @JacksonXmlProperty(localName = "Oznaczenie_Przypisanego_Magazynu")
    private String oznaczeniePrzypisanegoMagazynu;

    public Magazynier(){}

    public Magazynier(String imie, String nazwisko, Date dataZatrudnienia, Long pesjaBrutto, String plec, Date dataWaznosciBadanZdrowotnych, String oznaczeniePrzypisanegoMagazynu) {
        super(imie, nazwisko, dataZatrudnienia, pesjaBrutto);
        this.plec = plec;
        this.dataWaznosciBadanZdrowotnych = dataWaznosciBadanZdrowotnych;
        this.oznaczeniePrzypisanegoMagazynu = oznaczeniePrzypisanegoMagazynu;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public Date getDataWaznosciBadanZdrowotnych() {
        return dataWaznosciBadanZdrowotnych;
    }

    public void setDataWaznosciBadanZdrowotnych(Date dataWaznosciBadanZdrowotnych) {
        this.dataWaznosciBadanZdrowotnych = dataWaznosciBadanZdrowotnych;
    }

    public String getOznaczeniePrzypisanegoMagazynu() {
        return oznaczeniePrzypisanegoMagazynu;
    }

    public void setOznaczeniePrzypisanegoMagazynu(String oznaczeniePrzypisanegoMagazynu) {
        this.oznaczeniePrzypisanegoMagazynu = oznaczeniePrzypisanegoMagazynu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Magazynier)) return false;
        Magazynier that = (Magazynier) o;
        return getPlec().equals(that.getPlec()) &&
                getDataWaznosciBadanZdrowotnych().equals(that.getDataWaznosciBadanZdrowotnych()) &&
                getOznaczeniePrzypisanegoMagazynu().equals(that.getOznaczeniePrzypisanegoMagazynu());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlec(), getDataWaznosciBadanZdrowotnych(), getOznaczeniePrzypisanegoMagazynu());
    }

    @Override
    public String toString() {
        return "Magazynier{" +
                "plec='" + plec + '\'' +
                ", dataWaznosciBadanZdrowotnych=" + dataWaznosciBadanZdrowotnych +
                ", oznaczeniePrzypisanegoMagazynu='" + oznaczeniePrzypisanegoMagazynu + '\'' +
                '}';
    }

    @Override
    public Magazynier createNewEmployee() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Napisz Imie Magazyniera: ");
        String imie = scanner.next();
        System.out.println("Napisz Nazwisko Magazyniera: ");
        String nazwisko = scanner.next();
        System.out.println("Napisz Date Zatrudnienia Magazyniera w formacie [dd/MM/yyyy]: ");
        Date dataZatrudnienia = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.next());
        System.out.println("Napisz Pesje Brutto Magazyniera: ");
        String pesje = scanner.next();
        System.out.println("Napisz plec Magazyniera: ");
        String plec = scanner.next();
        System.out.println("Napisz Data Waznosci Badan Zdrowotnych Magazyniera w formacie [dd/MM/yyyy]: ");
        Date dataWaznosciBadanZdrowotnych = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.next());
        System.out.println("Napisz Oznaczenie Przypisanego Magazynu Magazyniera: ");
        String oznaczeniePrzypisanegoMagazynu = scanner.next();
        return new Magazynier(imie,nazwisko,dataZatrudnienia,Long.valueOf(pesje),plec,dataWaznosciBadanZdrowotnych,oznaczeniePrzypisanegoMagazynu);
    }
}
