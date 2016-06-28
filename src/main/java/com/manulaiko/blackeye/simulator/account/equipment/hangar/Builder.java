package com.manulaiko.blackeye.simulator.account.equipment.hangar;

import java.sql.ResultSet;

import org.json.JSONArray;

import com.manulaiko.tabitha.Console;

/**
 * Hangar builder class
 *
 * Implements the builder design pattern
 *
 * @author Manulaiko <manulaiko@gmail.com>
 *
 * @package com.manulaiko.blackeye.simulator.account.equipment.hangar
 */
public class Builder
{
    /**
     * Hangar object
     *
     * The current hangar we're building
     */
    private Hangar _hangar;

    /**
     * Constructor
     *
     * @param rs Query result
     */
    public Builder(ResultSet rs)
    {
        try {
            this._hangar = new Hangar(
                    rs.getInt("id"),
                    rs.getInt("accounts_id"),
                    rs.getString("resources")
            );

        } catch(Exception e) {
            Console.println("Couldn't build hangar!");
            Console.println(e.getMessage());
        }
    }

    /**
     * Cloning constructor
     *
     * Use this constructor for cloning a hangar
     *
     * @para hangar Hangar to clone
     */
    public Builder(Hangar hangar)
    {
        try {
            this._hangar = new Hangar(
                    hangar.id,
                    hangar.accountID,
                    hangar.resourcesJSON
            );

        } catch(Exception e) {
            Console.println("Couldn't clone hangar!");
            Console.println(e.getMessage());
        }
    }

    /**
     * Returns the hangar
     *
     * @return The hangar
     */
    public Hangar getHangar()
    {
        return this._hangar;
    }
}
