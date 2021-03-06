package com.manulaiko.blackeye.net.chat;

import com.manulaiko.blackeye.launcher.Main;

/**
 * Game server
 *
 * Starts game server
 *
 * @author Manulaiko <manulaiko@gmail.com>
 *
 * @package com.manulaiko.blackeye.net.game
 */
public class Server extends com.manulaiko.tabitha.net.Server
{
    /**
     * Constructor
     */
    public Server()
    {
        super(Main.configuration.getShort("core.chat_port"));
    }

    /**
     * Waits for connections and handles them
     */
    public void onRunning()
    {
    }

    /**
     * Stops the server
     *
     * @param timeout Timeout to wait before stoping the server
     */
    public void stop(int timeout)
    {
        try {
            super.stop();
        } catch(java.io.IOException e) {
            //Empty
        }
    }
}
