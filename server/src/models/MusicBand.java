package models;

import utility.IdGenerator;
import utility.Validatable;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class MusicBand implements Validatable, Comparable<MusicBand>, Serializable {
    @Serial
    private static final long serialVersionUID = 1337L;
    private Long ID; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date CREATION_DATE; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int numberOfParticipants; //Значение поля должно быть больше 0
    private LocalDateTime establishmentDate; //Поле может быть null
    private MusicGenre genre; //Поле может быть null
    private Studio studio; //Поле может быть null

    public MusicBand(String name, Coordinates coordinates, int numberOfParticipants, LocalDateTime establishmentDate, MusicGenre genre, Studio studio) {
        this.ID = IdGenerator.generate();
        this.name = name;
        this.coordinates = coordinates;
        this.CREATION_DATE = new Date();
        this.numberOfParticipants = numberOfParticipants;
        this.establishmentDate = establishmentDate;
        this.genre = genre;
        this.studio = studio;
    }

    public MusicBand(Long ID, String name, Coordinates coordinates, Date CREATION_DATE, int numberOfParticipants, LocalDateTime establishmentDate, MusicGenre genre, Studio studio) {
        this.ID = ID;
        this.name = name;
        this.coordinates = coordinates;
        this.CREATION_DATE = CREATION_DATE;
        this.numberOfParticipants = numberOfParticipants;
        this.establishmentDate = establishmentDate;
        this.genre = genre;
        this.studio = studio;
    }

    public Long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCREATION_DATE() {
        return CREATION_DATE;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public LocalDateTime getEstablishmentDate() {
        return establishmentDate;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public void setEstablishmentDate(LocalDateTime establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public void setCREATION_DATE(Date CREATION_DATE) {
        this.CREATION_DATE = CREATION_DATE;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    @Override
    public boolean validate() {
        return ID != null && ID > 0 &&
                name != null && !name.isEmpty() &&
                coordinates != null &&
                CREATION_DATE != null &&
                numberOfParticipants > 0 &&
                establishmentDate != null &&
                genre != null &&
                studio != null;
    }

    @Override
    public String toString() {
        return "MusicBand{" +
                "id=" + ID +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + CREATION_DATE +
                ", numberOfParticipants=" + numberOfParticipants +
                ", establishmentDate=" + establishmentDate +
                ", genre=" + genre +
                ", studio=" + studio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicBand musicBand = (MusicBand) o;
        return numberOfParticipants == musicBand.numberOfParticipants && Objects.equals(ID, musicBand.ID) && Objects.equals(name, musicBand.name) && Objects.equals(coordinates, musicBand.coordinates) && Objects.equals(CREATION_DATE, musicBand.CREATION_DATE) && Objects.equals(establishmentDate, musicBand.establishmentDate) && genre == musicBand.genre && Objects.equals(studio, musicBand.studio);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(ID, name, coordinates, CREATION_DATE, numberOfParticipants, establishmentDate, genre, studio);
    }

    @Override
    public int compareTo(MusicBand o) {
        return getName().compareTo(o.getName());
    }
}