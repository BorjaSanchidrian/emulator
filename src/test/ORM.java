import java.sql.ResultSet;
import java.util.ArrayList;

import com.manulaiko.tabitha.reflection.Instantiation;

/**
 * ORM Class
 * 
 * This class represents a record from the database and contains methods for fetching,
 * adding and deleting records from the table.
 * 
 * In order to use ORM your database MUST follow the naming
 * conventions located in `examples/Database/Conventions.md`,
 * this way Tabitha will find the table name based on the class name, if you don't
 * want to follow this conventions you can set the table name in the property
 * {@see com.manulaiko.tabitha.database.ORM::_table}
 *
 * To retrieve a record from the table use the method {@see com.manulaiko.tabitha.database.ORM::findRecord}
 * which accepts the value of {@see com.manulaiko.tabitha.database.ORM::_primaryKey} as parameter.
 * 
 * To retrieve various records from the table use the method {@see com.manulaiko.tabitha.database.ORM::getRecords}
 * 
 * To add a new record to the table use the method {@see com.manulaiko.tabitha.database.ORM::create}
 *
 * When you want to update the values on the database simply call the method
 * {@see com.manulaiko.tabitha.database.ORM:::save}.
 *
 * To delete the record from the table use the method {@see com.manulaiko.tabitha.database.ORM::delete}
 * 
 * To set a value from the record use the method {@see com.manulaiko.tabitha.database.ORM::set}
 * and to retrieve a value use the method {@see com.manulaiko.tabitha.database.ORM::get}
 *
 * Example:
 *
 *     package test.orm;
 *     
 *     import com.manulaiko.tabitha.database.ORM;
 *     
 *     public class User extends ORM
 *     {
 *         protected static String _table      = "users"; //This can be omitted since class name is the same as table name
 *         protected static String _primaryKey = "userID"; //If this is omitted Alexya assumes that primary key is always "id"
 *     }
 *     
 *     package test;
 *     
 *     import test.orm.User;
 *     
 *     public class Test
 *     {
 *         public static void main(String[] args)
 *         {
 *             User user = User.findRecord(1); //SELECT * FROM `users` WHERE `userID`=1
 *             
 *             user.set("password", "[!52test]::");
 *             user.set("name", "Test name");
 *             
 *             user.save(); //UPDATE `users` SET `password`='[!52test]::', `name`='Test name' WHERE `userID`=1
 *             
 *             User new_user = User.create();
 *             
 *             new_user.set("userID", 2);
 *             new_user.set("name", "test2");
 *             new_user.set("password", "test2");
 *             new_user.set("email", "test2@email.com");
 *
 *             $new_user->save(); //INSERT INTO `users` (`userID`, `name`, `password`, `email`) VALUES (2, 'test2', 'test2', 'test2@email.com')
 *         }
 *     }
 *     
 * @author Manulaiko <manulaiko@gmail.com>
 * 
 * @package com.manulaiko.tabitha.database
 */
public class ORM
{
    ///////////////////////////////////
    // Static methods and properties //
    ///////////////////////////////////
    /**
     * Table name
     * 
     * If empty, will be the lower case single name of the class
     * 
     * @var string
     */
    protected static String _table = "";
    
    /**
     * Table primary key
     * 
     * By default it's always `id`
     * 
     * @var string
     */
    protected static String _primaryKey = "id";
    
    /**
     * Relations array
     * 
     * @see Database/Relations.md For more help about relations
     * 
     * @var array
     */
    protected static String[] _relations;
    
    /**
     * Builds and returns an array with specified amount of records
     * 
     * The first parameter is an integer that represents the amount of elements
     * the array should have.
     * 
     * The second parameter is can be either a string, an object of type
     * {@see com.manulaiko.tabitha.database.QueryBuilder} or an array. It contains the extra
     * SQL to add to the query, if it's an array it represents the WHERE condition
     * of the query.
     * 
     * Each element from the array is an instance of the model class.
     * 
     * Example:
     * 
     *     ArrayList<Class<?>> posts = test.ORM.Post.getRecords(); // SELECT * FROM `posts`
     *     
     *     ArrayList<Class<?>> posts = test.ORM.Post.getRecords(5, [
     *         "id[>=]" => 10
     *     ]); // SELECT * FROM `posts` WHERE `id`>=10 LIMIT 5
     *     
     *     QueryBuilder query = new QueryBuilder();
     *     query.order("date");
     *     
     *     ArrayList<Class<?>> posts = test.ORM.Post::getRecords(5, $query); // SELECT * FROM `posts` ORDER BY `date` DESC LIMIT 5
     *     
     *     ArrayList<Class<?>> posts = test.ORM.Post::getRecords(5, "WHERE (`title` REGEXP "^([A-Z].*)$")"); // SELECT * FROM `posts` WHERE (`title` REGEXP "^([A-Z].*)$") LIMIT 5
     * 
     * @param length       Array length, if -1 will return the whole table
     * @param extra        Extra SQL to add to the query
     * @param setRelations Whether set relations for the instances or not
     * 
     * @return Array containing records from table
     */
    public static ArrayList<Class<?>> getRecords(int length, String extra, boolean setRelations)
    {
        QueryBuilder query = new QueryBuilder();
        
        query.select()
             .from(ORM.getTable())
             .sql(extra);
        
        if(length > -1) {
            query.limit(length);
        }
        
        ArrayList<ResultSet> results = Database.execute(query);
        ArrayList<Class<?>> toReturn = new ArrayList<Class<?>>();
        
        for(ResultSet result : results) {
            String className = ""; //TODO Dynamically find child class name
            toReturn.add(Instantiation.instance(className, result));
        }
        
        return toReturn;
    }
    
    //////////////////////////////
    // Fallbacks for getRecords //
    //////////////////////////////
    public static ArrayList<Class<?>> getRecords()
    {
        return ORM.getRecords(-1, "", false);
    }
    
    public static ArrayList<Class<?>> getRecords(int length)
    {
        return ORM.getRecords(length, "", false);
    }
    
    public static ArrayList<Class<?>> getRecords(int length, String extra)
    {
        return ORM.getRecords(length, extra, false);
    }
    
    public static ArrayList<Class<?>> getRecords(int length, QueryBuilder extra)
    {
        return ORM.getRecords(length, extra.getString(), false);
    }
    
    public static ArrayList<Class<?>> getRecords(int length, QueryBuilder extra, boolean setRelations)
    {
        return ORM.getRecords(length, extra.getString(), setRelations);
    }

    public static ArrayList<Class<?>> getRecords(int length, boolean setRelations)
    {
        return ORM.getRecords(length, "", setRelations);
    }

    public static ArrayList<Class<?>> getRecords(String extra)
    {
        return ORM.getRecords(-1, extra, false);
    }
    
    public static ArrayList<Class<?>> getRecords(String extra, boolean setRelations)
    {
        return ORM.getRecords(-1, extra, setRelations);
    }

    public static ArrayList<Class<?>> getRecords(QueryBuilder extra)
    {
        return ORM.getRecords(-1, extra.getQuery(), false);
    }
    
    public static ArrayList<Class<?>> getRecords(QueryBuilder extra, boolean setRelations)
    {
        return ORM.getRecords(-1, extra.getQuery(), setRelations);
    }
    
    public static ArrayList<Class<?>> getRecords(boolean setRelations)
    {
        return ORM.getRecords(-1, "", setRelations);
    }
    
    /**
     * Returns a record from the table
     * 
     * Finds a record from the table based on the {@see \Alexya\Database\ORM\Model::$_primaryKey}
     * of the table and returns an instance of the model class.
     * 
     * If the parameter is an array it will represent the WHERE clause of the query.
     * 
     * @param mixed $primaryKey   Table primary key value
     * @param bool  $setRelations Whether set relations for the instance or not
     * 
     * @return \Alexya\Database\ORM\Model Record model from database
     */
    public static ORM findRecord($primaryKey, bool $setRelations = true) : Model
    {
        global $Database;
        
        $query = new QueryBuilder();
        $query->select()
              ->from(static::getTable());
        
        if(is_array($primaryKey)) {
            $query->where($primaryKey);
        } else {
            $query->where([
                static::$_primaryKey => $primaryKey
            ]);
        }
        
        $query->limit(1); //ensure we have just one record
        
        $result = $Database->get($query);
        
        if(empty($result)) {
            return new static([], $setRelations);
        } else {
            return new static($result, $setRelations);
        }
    }
    
    /**
     * Creates a new record
     * 
     * The parameter is an array that contains the initial columns of the record.
     * 
     * Example:
     * 
     *     $post = \Application\ORM\Post::create([
     *         "id" => 1
     *     ]);
     *     
     *     $post->title = "Test";
     *     $post->text  = "Test 1";
     *     
     *     $post->save();
     *     // INSERT INTO `posts` (`id`, `title`, `text`) VALUES (1, 'Test', 'Test 1')
     * 
     * @param array $columns Initial record columns
     * 
     * @return \Alexya\Database\ORM\Model Record model
     */
    public static function create(array $columns = []) : Model
    {
        $model = new static($columns);
        $model->_isInsert = true;
        
        return $model;
    }
    
    /**
     * Returns table name
     * 
     * @return string Table name
     */
    public static function getTable() : string
    {
        global $Logger;
        
        if(!empty(static::$_table)) {
            return static::$_table;
        }
        
        $_table = Inflector::pluralize(
            strtolower(
                str_replace("\\", "_", str_replace("Application\\ORM\\", "", get_called_class()))
            )
        );
        
        return $_table;
    }

    ///////////////////////////////////////
    // Non static methods and properties //
    ///////////////////////////////////////
    /**
     * Whether this is a new record or not
     */
    protected $_isInsert = false;
    
    /**
     * Constructor
     * 
     * @param array $columns      Table columns
     * @param bool  $setRelations Whether to seet relations for this instance or not
     */
    public function __construct(array $columns, bool $setRelations = true)
    {
        parent::__construct($columns);
        
        if($setRelations) {
            $this->_setRelations();
        }
        
        $this->onInstance();
    }
    
    /**
     * On instance method
     * 
     * This method will be called right after the constructor.
     * You don't need to overwrite the constructor, just rewrite this method.
     */
    public function onInstance()
    {
    }
    
    /**
     * Sets ORM relations
     * 
     * @see exaples/Database/Relations For examples about ORM relations
     */
    private function _setRelations()
    {
        //Set relations
        foreach(static::$_relations as $_class => $_options) {
            if(!is_array($_options)) {
                $_class   = $_options;
                $_options = [];
            }
            
            if(!Functions::startsWith("\\Application\\ORM\\", $_class)) {
                $_class = "\\Application\\ORM\\". $_class;
            }
            
            extract($_options);
            
            if(empty($localKey)) {
                $localKey = static::getTable()."_id";
            }
            if(empty($foreingKey)) {
                $foreingKey = "id";
            }
            if(empty($type)) {
                $type = "has_one";
            }
            if(empty($name)) {
                $name = str_replace("\\Application\\ORM\\", "", $_class);
                $name = explode("\\", $_class);
                $name = $name[count($name) - 1];
            }
            if(empty($_options["setRelations"])) {
                $setRelations = false;
            }
            if(empty($_options["amount"])) {
                $amount = -1;
            }
            if(empty($class)) {
                $class = $_class;
            }
            
            if($type == "has_one") {
                $res = ($_class)::findRecord([
                    $foreingKey => $this->_data[$localKey]
                ], $setRelations);

                $this->_data[$name] = new $class($res);
            } else if($type == "has_many") {
                $results = ($_class)::getRecords($amount, [
                    $foreingKey => $this->_data[$localKey]
                ], $setRelations);
                
                
                if(!empty($condition)) {
                    if(is_callable($condition)) {
                        foreach($results as $r) {
                            if(!$condition($r)) {
                                continue;
                            }
                            
                            $this->_data[$name][] = new $class($r);
                        }
                    }
                } else {
                    foreach($results as $res) {
                       $this->_data[$name][] = new $class($res);
                   }
                }
            }
        }
    }

    /**
     * Builds and saves the changes to the table
     */
    public function save()
    {
        global $Database;
        
        $query   = new QueryBuilder();
        $columns = [];
        
        foreach($this->_columns as $key => $val) {
            if((!is_object($key) && !is_array($key)) &&
               !Functions::With("_", $key)) {
                $columns[$key] = $val;
            }
        }
        
        if($this->_isInsert) {
            $query->insert(static::getTable())
                  ->values($columns);
        } else {
            $query->update(static::getTable())
                  ->set($columns)
                  ->where([
                        static::$_primaryKey => $this->_columns[static::$_primaryKey]
                    ]);
        }
        
        $Database->execute($query);
    }

    /**
     * Deletes this record from table
     */
    public function delete()
    {
        global $Database;
        
        $query = new QueryBuilder();
        $query->delete(static::getTable())
              ->where([
                    static::$_primaryKey => $this->_columns[static::$_primaryKey]
                ]);
                
        $Database->execute($query);
    }
}
