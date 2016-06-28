package com.manulaiko.tabitha.reflection;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

/**
 * Helper methods for dynamically instancing objects
 * 
 * One of the things I love from PHP is the easy way to instance objects
 * dynamically, is as easy as this:
 * 
 *     $name      = "TestObject";
 *     $arguments = ["arg1", false, 1234];
 *     
 *     $object = new $name(... $arguments)
 * 
 * In java it can be quite complex, this class will solve the problem.
 * 
 * The method {@see com.manulaiko.tabitha.reflection.Instantiation.instance} accepts
 * as parameter a string being the class name to instance and an array of strings being
 * the arguments to pass to the constructor.
 * 
 * For example:
 * 
 *     String   name      = "TestObject";
 *     String[] arguments = ["arg1", "false", "1234"];
 *     
 *     TestObject obj = (TestObject) Instantiation.instance(name, arguments);
 * 
 * @author Manulaiko <manulaiko@gmail.com>
 * 
 * @package com.manulaiko.tabitha.reflection
 * 
 * @see http://stackoverflow.com/questions/13868986/dynamically-create-an-object-in-java-from-a-class-name-and-set-class-fields-by-u
 */
public class Instantiation
{
    /**
     * Instances and returns an object of type "name"
     * 
     * @param name      name of the class to instance
     * @param arguments arguments to pass to constructor
     * 
     * @return Instance of "name"
     */
    public static Object instance(String name, ArrayList<Class<?>> arguments) throws Exception
    {
        // Load the class.
        Class<?> cls = Class.forName(name);

        // Search for an "appropriate" constructor.
        for(Constructor<?> ctor : cls.getConstructors()) {
            Class<?>[] paramTypes = ctor.getParameterTypes();

            // If the arity matches, let's use it.
            if(arguments.size() == paramTypes.length) {
                // Convert the String arguments into the parameters' types.
                Object[] convertedArgs = new Object[arguments.size()];
                for(int i = 0; i < convertedArgs.length; i++) {
                    convertedArgs[i] = convert(arguments.get(i).toString(), paramTypes[i]);
                }

                // Instantiate the object with the converted arguments.
                return ctor.newInstance(convertedArgs);
            }
        }

        throw new IllegalArgumentException("Don't know how to instantiate " + name);
    }
    
    /**
     * Converts a string to given type
     * 
     * @param s      String to convert
     * @param target Target type to convert s
     * 
     * @return s as target
     */
    public static Object convert(String s, Class<?> target)
    {
        if(target == Object.class || target == String.class || s == null) {
            return s;
        } else if(target == Character.class || target == char.class) {
            return s.charAt(0);
        } else if(target == Byte.class || target == byte.class) {
            return Byte.parseByte(s);
        } else if(target == Short.class || target == short.class) {
            return Short.parseShort(s);
        } else if(target == Integer.class || target == int.class) {
            return Integer.parseInt(s);
        } else if(target == Long.class || target == long.class) {
            return Long.parseLong(s);
        } else if(target == Float.class || target == float.class) {
            return Float.parseFloat(s);
        } else if(target == Double.class || target == double.class) {
            return Double.parseDouble(s);
        } else if(target == Boolean.class || target == boolean.class) {
            return Boolean.parseBoolean(s);
        }
        
        throw new IllegalArgumentException("Don't know how to convert to " + target);
    }
}
