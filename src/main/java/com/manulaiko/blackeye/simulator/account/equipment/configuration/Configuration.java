package com.manulaiko.blackeye.simulator.account.equipment.configuration;

import org.json.JSONArray;

/**
 * Configuration class
 *
 * @author Manulaiko <manulaiko@gmail.com>
 *
 * @package com.manulaiko.blackeye.simulator.account.equipment.configuration
 */
public class Configuration
{
    /**
     * Configuration ID
     */
    public int id;

    /**
     * Lasers
     */
    public JSONArray lasers;

    /**
     * Lasers JSON
     */
    public String lasersJSON;

    /**
     * Hellstorms
     */
    public JSONArray hellstorms;

    /**
     * Hellstorms JSON
     */
    public String hellstormsJSON;

    /**
     * Generators
     */
    public JSONArray generators;

    /**
     * Generators JSON
     */
    public String generatorsJSON;

    /**
     * Extras
     */
    public JSONArray extras;

    /**
     * Extras JSON
     */
    public String extrasJSON;

    /**
     * Constructor
     *
     * @param id         Configuration ID
     * @param lasers     Lasers JSON
     * @param hellstorms Hellstorms JSON
     * @param generators Generators JSON
     * @param extras     Extras JSON
     */
    public Configuration(int id, String lasers, String hellstorms, String generators, String extras)
    {
        try {
            this.id             = id;
            this.lasersJSON     = lasers;
            this.hellstormsJSON = hellstorms;
            this.generatorsJSON = generators;
            this.extrasJSON     = extras;

            this.lasers     = new JSONArray(lasers);
            this.hellstorms = new JSONArray(hellstorms);
            this.generators = new JSONArray(generators);
            this.extras     = new JSONArray(extras);
        } catch(Exception e) {
            //Empty
        }
    }
}
