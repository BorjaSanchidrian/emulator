package com.manulaiko.blackeye.net.game.packets;

import java.util.HashMap;
import java.util.Map.Entry;

import com.manulaiko.blackeye.net.game.packets.handlers.*;
import com.manulaiko.blackeye.net.game.packets.commands.*;

import com.manulaiko.blackeye.net.game.utils.PacketParser;

import com.manulaiko.tabitha.exceptions.NotFound;
import com.manulaiko.tabitha.Console;

/**
 * Packet factory
 *
 * Factory for building packets and retrieving handlers
 *
 * @author Manulaiko <manulaiko@gmail.com>
 *
 * @package com.manulaiko.blackeye.net.game.packets
 */
public class Factory
{
    /**
     * Available packet handlers
     */
    private HashMap<String, Packet> _packets = new HashMap<>();

    /**
     * Available packet commands
     */
    private HashMap<String, Command> _commands = new HashMap<>();

    /**
     * Constructor
     *
     * Sets available packets and their handlers
     */
    public Factory()
    {
        Console.println("Loading game packet handlers and commands...");

        ///////////////////////////
        // Start Packet Handlers //
        ///////////////////////////
        this._packets.put("LOGIN", new LoginRequest());
        this._packets.put("0", new EchoPacket());
        /////////////////////////
        // End Packet Handlers //
        /////////////////////////

        ///////////////////////////
        // Start Packet Commands //
        ///////////////////////////
        this._commands.put("I", new ShipInitialization());
        /////////////////////////
        // End Packet Commands //
        /////////////////////////

        Console.println(this._packets.size() + " packet handlers and commands loaded!");
    }

    /**
     * Finds and returns given packet handler
     *
     * @param packet Packet to find handler
     *
     * @return Packet's handler
     *
     * @throws NotFound If packet handler does not exist
     */
    public Packet get(PacketParser packet) throws NotFound
    {
        String id = packet.readString();

        if(!this._packets.containsKey(id)) {
            throw new NotFound("packet handler", packet.toString());
        }

        Packet p = this._packets.get(id);
        p.setPacket(packet);

        return p;
    }

    /**
     * Finds and returns given packet handler
     *
     * @param packet Packet to find handler
     *
     * @return Packet's handler
     *
     * @throws NotFound If packet handler does not exist
     */
    public Packet getByID(String packet) throws NotFound
    {
        if(!this._packets.containsKey(packet)) {
            throw new NotFound("packet handler", packet);
        }

        return this._packets.get(packet);
    }

    /**
     * Finds and returns given packet handler by its name
     *
     * @param name Packet name
     *
     * @return Packet's handler
     *
     * @throws NotFound If packet handler does not exist
     */
    public Packet getByName(String name) throws NotFound
    {
        for(Entry<String, Packet> packet : this._packets.entrySet()) {
            if(packet.getValue().getName().equalsIgnoreCase(name)) {
                return packet.getValue();
            }
        }

        throw new NotFound("packet", name);
    }

    /**
     * Finds and returns given packet handler
     *
     * @param command Packet to find handler
     *
     * @return Packet's handler
     *
     * @throws NotFound If packet handler does not exist
     */
    public Command getCommandByID(String command) throws NotFound
    {
        if(!this._commands.containsKey(command)) {
            throw new NotFound("packet command", command);
        }

        return this._commands.get(command);
    }

    /**
     * Finds and returns given packet command by its name
     *
     * @param name Command name
     *
     * @return Command's handler
     *
     * @throws NotFound If packet command does not exist
     */
    public Command getCommandByName(String name) throws NotFound
    {
        for(Entry<String, Command> command : this._commands.entrySet()) {
            if(command.getValue().getName().equalsIgnoreCase(name)) {
                return command.getValue();
            }
        }

        throw new NotFound("command", name);
    }
}
