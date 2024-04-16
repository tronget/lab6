package network;

import commands.Command;
import commands.CommandWithMB;
import models.MusicBand;
import shared.Request;
import utility.Program;

public class RequestHandler {
    Command command;
    MusicBand musicBand;
    String argument;

    public RequestHandler(Request request) {
        this.command = request.getCommand();
        this.musicBand = request.getMusicBand();
        this.argument = request.getStringArg();
    }

    public void handle() {
        Program.getInstance().updateHistory(command.getDescription());
        if (musicBand != null) {
            ((CommandWithMB) command).setMusicBand(musicBand);
        }
        if (argument != null) {
            command.execute(argument);
        } else {
            command.execute();
        }
    }
}
