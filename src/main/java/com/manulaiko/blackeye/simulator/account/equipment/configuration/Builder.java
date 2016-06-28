package com.manulaiko.blackeye.simulator.account.equipment.configuration;

import java.sql.ResultSet;

import com.manulaiko.tabitha.Console;

/**
 * Configuration builder class
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
     * Configuration object
     *
     * The current configuration we're building
     */
    private Configuration _configuration;

    /**
     * Constructor
     *
     * @param rs Query result
     */
    public Builder(ResultSet rs)
    {
        try {
            this._configuration = new Configuration(
                    rs.getInt("id"),
                    rs.getString("lasers"),
                    rs.getString("hellstorms"),
                    rs.getString("generators"),
                    rs.getString("extras")
            );

        } catch(Exception e) {
            Console.println("Couldn't build configuration!");
            Console.println(e.getMessage());
        }
    }

    /**
     * Cloning constructor
     *
     * Use this constructor for cloning a configuration
     *
     * @para configuration Configuration to clone
     */
    public Builder(Configuration configuration)
    {
        try {
            this._configuration = new Configuration(
                    configuration.id,
                    configuration.lasersJSON,
                    configuration.hellstormsJSON,
                    configuration.generatorsJSON,
                    configuration.extrasJSON
            );

        } catch(Exception e) {
            Console.println("Couldn't clone configuration!");
            Console.println(e.getMessage());
        }
    }

    /**
     * Returns the configuration
     *
     * @return The configuration
     */
    public Configuration getConfiguration()
    {
        return this._configuration;
    }
}
