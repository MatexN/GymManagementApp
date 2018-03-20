package pl.edu.wat.wcy.isi.siecsilowni.rest;

import lombok.Data;

@Data
public class TimeAndDate {

    private String currentDateTime;

    private String utcOffset;

    private String dayOfTheWeek;

    @Override
    public String toString() {
        return currentDateTime;
    }
}
