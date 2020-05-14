import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Kierowca extends Pracownik {

    @JacksonXmlProperty(localName = "Kategoria_Prawa_Jazdy")
    private String kategoriaPrawaJazdy;
    @JacksonXmlProperty(localName = "Data_Uzyskania_Prawa_Jazdy")
    private Date dataUzyskaniaPrawaJazdy;
    @JacksonXmlProperty(localName = "Liczba_Punktow_Karnych")
    private Long liczbaPunktowKarnych;

    public Kierowca(){}

    public Kierowca(String imie, String nazwisko, Date dataZatrudnienia, Long pesjaBrutto, String kategoriaPrawaJazdy, Date dataUzyskaniaPrawaJazdy, Long liczbaPunktowKarnych) {
        super(imie, nazwisko, dataZatrudnienia, pesjaBrutto);
        this.kategoriaPrawaJazdy = kategoriaPrawaJazdy;
        this.dataUzyskaniaPrawaJazdy = dataUzyskaniaPrawaJazdy;
        this.liczbaPunktowKarnych = liczbaPunktowKarnych;
    }

    public String getKategoriaPrawaJazdy() {
        return kategoriaPrawaJazdy;
    }

    public void setKategoriaPrawaJazdy(String kategoriaPrawaJazdy) {
        this.kategoriaPrawaJazdy = kategoriaPrawaJazdy;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public Date getDataUzyskaniaPrawaJazdy() {
        return dataUzyskaniaPrawaJazdy;
    }

    public void setDataUzyskaniaPrawaJazdy(Date dataUzyskaniaPrawaJazdy) {
        this.dataUzyskaniaPrawaJazdy = dataUzyskaniaPrawaJazdy;
    }

    public Long getLiczbaPunktowKarnych() {
        return liczbaPunktowKarnych;
    }

    public void setLiczbaPunktowKarnych(Long liczbaPunktowKarnych) {
        this.liczbaPunktowKarnych = liczbaPunktowKarnych;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kierowca)) return false;
        Kierowca kierowca = (Kierowca) o;
        return getKategoriaPrawaJazdy().equals(kierowca.getKategoriaPrawaJazdy()) &&
                getDataUzyskaniaPrawaJazdy().equals(kierowca.getDataUzyskaniaPrawaJazdy()) &&
                getLiczbaPunktowKarnych().equals(kierowca.getLiczbaPunktowKarnych());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKategoriaPrawaJazdy(), getDataUzyskaniaPrawaJazdy(), getLiczbaPunktowKarnych());
    }

    @Override
    public String toString() {
        return "Kierowca{" +
                "kategoriaPrawaJazdy='" + kategoriaPrawaJazdy + '\'' +
                ", dataUzyskaniaPrawaJazdy=" + dataUzyskaniaPrawaJazdy +
                ", liczbaPunktowKarnych=" + liczbaPunktowKarnych +
                '}';
    }

    @Override
    public Kierowca createNewEmployee() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Napisz Imie Kierowcy: ");
        String imie = scanner.next();
        System.out.println("Napisz Nazwisko Kierowcy: ");
        String nazwisko = scanner.next();
        System.out.println("Napisz Date Zatrudnienia Kierowcy w formacie [dd/MM/yyyy]: ");
        Date dataZatrudnienia = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.next());
        System.out.println("Napisz Pesje Brutto Kierowcy: ");
        String pesje = scanner.next();
        System.out.println("Napisz Kategorie Prawa Jazdy Kierowcy: ");
        String kategoriaPrawaJazdy = scanner.next();
        System.out.println("Napisz Data Uzyskania Prawa Jazdy Kierowcy w formacie [dd/MM/yyyy]: ");
        Date dataUzyskaniaPrawaJazdy = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.next());
        System.out.println("Napisz Liczbe Punktow Karnych Kierowcy: ");
        String liczbaPunktowKarnych = scanner.next();
        return new Kierowca(imie,nazwisko,dataZatrudnienia,Long.valueOf(pesje),kategoriaPrawaJazdy,dataUzyskaniaPrawaJazdy,Long.valueOf(liczbaPunktowKarnych));
    }
}
