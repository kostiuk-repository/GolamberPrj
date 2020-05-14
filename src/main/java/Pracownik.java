import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.text.ParseException;
import java.util.Date;

public class Pracownik {

    @JacksonXmlProperty(localName = "Imie")
    private String imie;
    @JacksonXmlProperty(localName ="Nazwisko")
    private String nazwisko;
    @JacksonXmlProperty(localName ="Data_Zatrudnienia")
    private Date dataZatrudnienia;
    @JacksonXmlProperty(localName ="Pesja_Brutto")
    private Long pesjaBrutto;

    public Pracownik(){}

    public Pracownik(String imie, String nazwisko, Date dataZatrudnienia, Long pesjaBrutto) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataZatrudnienia = dataZatrudnienia;
        this.pesjaBrutto = pesjaBrutto;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public Date getDataZatrudnienia() {
        return dataZatrudnienia;
    }

    public void setDataZatrudnienia(Date dataZatrudnienia) {
        this.dataZatrudnienia = dataZatrudnienia;
    }

    public Long getPesjaBrutto() {
        return pesjaBrutto;
    }

    public void setPesjaBrutto(Long pesjaBrutto) {
        this.pesjaBrutto = pesjaBrutto;
    }

    @Override
    public String toString() {
        return "Pracownik{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", dataZatrudnienia=" + dataZatrudnienia +
                ", pesjaBrutto=" + pesjaBrutto +
                '}';
    }

    public Pracownik createNewEmployee()throws ParseException{ return null;}
}
