package com.manulaiko.blackeye.simulator.station;

import java.awt.Point;

import org.json.JSONArray;
import org.json.JSONObject;

import com.manulaiko.tabitha.Console;

/**
 * Station class
 *
 * Represents a station on the map.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 *
 * @package com.manulaiko.blackeye.simulator
 */
public class Station
{
    /**
     * Position on map
     */
    public Point position = new Point(0, 0);

    /**
     * Station owner
     */
    public int factionsID = -1;

    /**
     * Constructor
     *
     * @param factionsID Station's owner
     * @param position   Position on map
     */
    public Station(int factionsID, Point position)
    {
        this.position   = position;
        this.factionsID = factionsID;
    }
}
