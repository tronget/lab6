package commands;

import models.MusicBand;

public abstract class CommandWithMB extends Command {
    protected MusicBand musicBand;
    public void setMusicBand(MusicBand musicBand) {
        this.musicBand = musicBand;
    }
}
