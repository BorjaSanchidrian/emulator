package com.manulaiko.blackeye.simulator.account.equipment.hangar;

import org.json.JSONArray;

import com.manulaiko.blackeye.simulator.account.equipment.ship.Ship;
import com.manulaiko.blackeye.simulator.account.equipment.configuration.Configuration;

/**
 * Hangar class
 *
 * @author Manulaiko <manulaiko@gmail.com>
 *
 * @package com.manulaiko.blackeye.simulator.account.equipment.hangar
 */
public class Hangar
{
    /**
     * Hangar ID
     */
    public int id;

    /**
     * Account ID
     */
    public int accountID;

    /**
     * Ship object
     */
    public Ship ship;

    /**
     * Configurations object
     */
    public Configuration configuration;

    /**
     * Resources
     */
    public JSONArray resources;

    /**
     * Resources JSON
     */

    public String resourcesJSON;

    /**
     * Constructor
     *
     * @param id        Hangar ID
     * @param accountID Account's ID
     * @param resources Resources JSON
     */
    public Hangar(int id, int accountID, String resources)
    {
        this.id        = id;
        this.accountID = accountID;

        try {
            this.resources     = new JSONArray(resources);
            this.resourcesJSON = resources;
        } catch(Exception e) {
            //Empty
        }
    }

    /**
     * Sets ship object
     *
     * @param ship Ship object
     */
    public void setShip(Ship ship)
    {
        this.ship = ship;
    }

    /**
     * Sets configuration object
     *
     * @param config Configuration object
     */
    public void setConfiguration(Configuration config)
    {
        this.configuration = config;
    }

    /**
     * Returns speed points
     *
     * @return Speed points
     */
    public int getSpeed()
    {
        //TODO
        return 0;
    }

    /**
     * Returns current shield points
     *
     * @return Current shield points
     */
    public int getShield()
    {
        //TODO
        return 0;
    }

    /**
     * Returns max shield points
     *
     * @return Max shield points
     */
    public int getMaxShield()
    {
        //TODO
        return 0;
    }

    /**
     * Returns current health points
     *
     * @return Current health points
     */
    public int getHealth()
    {
        //TODO
        return 0;
    }

    /**
     * Returns max health points
     *
     * @return Max health points
     */
    public int getMaxHealth()
    {
        //TODO
        return 0;
    }

    /**
     * Returns current cargo amount
     *
     * @return Current cargo amount
     */
    public int getCargo()
    {
        //TODO
        return 0;
    }

    /**
     * Returns max cargo amount
     *
     * @return Max cargo amount
     */
    public int getMaxCargo()
    {
        //TODO
        return 0;
    }

    /**
     * Returns current laser ammo amount
     *
     * @return Current battery amount
     */
    public int getBatteriesAmount()
    {
        //TODO
        return 0;
    }

    /**
     * Returns current current amount
     *
     * @return Current rockets amount
     */
    public int getRocketsAmount()
    {
        //TODO
        return 0;
    }
}
