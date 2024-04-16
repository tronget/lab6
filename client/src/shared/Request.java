package shared;

import commands.Command;
import models.MusicBand;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 666L;

    private MusicBand musicBandArgument;
    private Command command;
    private String stringArgument;

    public Request(Command command) {
        this.command = command;
    }

    public Request(Command command, MusicBand musicBandArgument) {
        this.command = command;
        this.musicBandArgument = musicBandArgument;
    }

    public Request(Command command, String stringArgument) {
        this.command = command;
        this.stringArgument = stringArgument;
    }
    public Request(Command command, String stringArgument, MusicBand musicBandArgument) {
        this.command = command;
        this.stringArgument = stringArgument;
        this.musicBandArgument = musicBandArgument;
    }

    public MusicBand getMusicBand() {
        return musicBandArgument;
    }

    public void setMusicBand(MusicBand musicBand) {
        this.musicBandArgument = musicBand;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Object getStringArg() {
        return stringArgument;
    }

    public void setStringArg(String argument) {
        this.stringArgument = argument;
    }

    @Override
    public String toString() {
        return "Request: " + command + " " + musicBandArgument + " " + stringArgument;
    }
}
