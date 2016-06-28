package com.manulaiko.blackeye.net.game.packets.handlers;

/**
 * Echo packet
 *
 * Echoes receibed packet
 *
 * @author Manulaiko <manulaiko@gmail.com>
 *
 * @package com.manulaiko.blackeye.net.game.packets.handlers
 */
public class EchoPacket extends com.manulaiko.blackeye.net.game.packets.Packet
{
    /**
     * Returns packet name
     *
     * @return Packet name
     */
    public String getName()
    {
        return "EchoPacket";
    }

    /**
     * Handles the packet
     *
     * @param connection Connection object
     */
    public void handle(com.manulaiko.blackeye.net.game.Connection connection)
    {
        connection.send(this._packet);
    }
}
