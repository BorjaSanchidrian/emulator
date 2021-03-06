package com.manulaiko.blackeye.net.game.packets;

import com.manulaiko.blackeye.net.game.utils.PacketParser;

/**
 * Command class
 *
 * All the commands extends this class
 *
 * @author Manulaiko <manulaiko@gmail.com>
 *
 * @package com.manulaiko.blackeye.net.game.packets.commands
 */
public abstract class Command
{
    /**
     * Packet parser
     */
    protected PacketParser _packet;

    /**
     * Constructor
     */
    public Command()
    {
        this._packet = new PacketParser();
        this._packet.add(0);
    }

    /**
     * Returns packet name
     */
    public String getName() {
        return getClass().getSimpleName();
    }

    /**
     * Returns packet as a string
     */
    public abstract String toString();
}
