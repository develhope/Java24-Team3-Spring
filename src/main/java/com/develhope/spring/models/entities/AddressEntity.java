package com.develhope.spring.models.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_address;

    @Column
    private String country;

    /*
        I codici postali (postcode) possono variare significativamente a seconda del paese e del sistema postale in uso. Ecco alcuni formati comuni utilizzati in vari paesi:

            - Numeri interi semplici:
                Italia: 00100 (Roma), 20100 (Milano)
                Stati Uniti: 90210 (Beverly Hills), 10001 (New York)
            - Numeri con trattini:
                Stati Uniti (ZIP+4): 90210-1234
            - Combinazioni di numeri e lettere:
                Regno Unito: SW1A 1AA (Londra), EH1 1BQ (Edimburgo)
                Canada: K1A 0B1 (Ottawa), M5W 1E6 (Toronto)
            - Solo lettere:
                Alcuni paesi utilizzano lettere per determinati codici speciali:
                Londra: EC1A 1BB
                Ambasciate o organizzazioni specifiche: SW1A 2AA
            - Altri formati misti:
                Paesi Bassi: 1234 AB (Amsterdam)
                Australia: 2000 (Sydney), 3000 (Melbourne)
                Germania: 10115 (Berlino), 80331 (Monaco)
            - Codici alfanumerici più complessi:
                Argentina: C1002AAR (Buenos Aires)
                India: 110001 (New Delhi)
            Ogni paese ha un proprio sistema di codici postali, e i formati possono essere molto specifici e strutturati
            per includere informazioni regionali, cittadine o di quartiere.
         */
    @Column
    private String postcode;

    @Column
    private String city;

    @Column
    private String street;

    /*
    I numeri civici (housenumber) possono essere composti da diverse combinazioni, non solo da numeri interi. Di seguito sono elencati alcuni formati comuni per i numeri civici:

            - Numeri interi semplici: ad esempio, 1, 23, 456.
            - Numeri con lettere: ad esempio, 23A, 45B, 100C.
            - Numeri con trattini: ad esempio, 12-14, 45-1, 123-4.
            - Numeri frazionari: ad esempio, 12 1/2, 45 3/4.
            - Combinazioni di lettere e numeri: ad esempio, A1, B23, C100.
            - Numeri con barre: ad esempio, 12/14, 45/1, 123/4.


     */
    @Column
    private String number;

    @Column
    private String note;

    /*
    La convenzione standard comunemente accettata per rappresentare le coordinate geografiche
    è di usare l'ordine {latitude, longitude}. Questo ordine è utilizzato da molti sistemi
    e standard internazionali, tra cui il formato WGS84, che è il sistema di riferimento utilizzato da GPS.
     */
    @Column
    private BigDecimal  lat;

    @Column
    private BigDecimal  lon;

    public AddressEntity() {
    }

    public AddressEntity(String id_address, String country, String postcode, String city, String street, String number, String note, BigDecimal lat, BigDecimal lon) {
        this.id_address = id_address;
        this.country = country;
        this.postcode = postcode;
        this.city = city;
        this.street = street;
        this.number = number;
        this.note = note;
        this.lat = lat;
        this.lon = lon;
    }

    public String getId_address() {
        return id_address;
    }

    public void setId_address(String id_address) {
        this.id_address = id_address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }
}
