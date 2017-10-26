
package classesFromXSD;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.company.ClassesFromXSD package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.company.ClassesFromXSD
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Statistics }
     * 
     */
    public Statistics createStatistics() {
        return new Statistics();
    }

    /**
     * Create an instance of {@link Puzzle }
     * 
     */
    public Puzzle createPuzzle() {
        return new Puzzle();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link StatisticItem }
     * 
     */
    public StatisticItem createStatisticItem() {
        return new StatisticItem();
    }

}
