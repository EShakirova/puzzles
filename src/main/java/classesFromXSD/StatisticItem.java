
package classesFromXSD;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StatisticItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StatisticItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="puzzle" type="{}Puzzle"/>
 *         &lt;element name="user" type="{}User"/>
 *         &lt;element name="unsuccessfulAttemptsCount" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="elapsedTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="is_solved" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatisticItem", propOrder = {
    "id",
    "puzzle",
    "user",
    "unsuccessfulAttemptsCount",
    "elapsedTime",
    "isSolved"
})
public class StatisticItem {

    protected int id;
    @XmlElement(required = true)
    protected Puzzle puzzle;
    @XmlElement(required = true)
    protected User user;
    protected short unsuccessfulAttemptsCount;
    protected long elapsedTime;
    @XmlElement(name = "is_solved")
    protected boolean isSolved;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the puzzle property.
     * 
     * @return
     *     possible object is
     *     {@link Puzzle }
     *     
     */
    public Puzzle getPuzzle() {
        return puzzle;
    }

    /**
     * Sets the value of the puzzle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Puzzle }
     *     
     */
    public void setPuzzle(Puzzle value) {
        this.puzzle = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUser(User value) {
        this.user = value;
    }

    /**
     * Gets the value of the unsuccessfulAttemptsCount property.
     * 
     */
    public short getUnsuccessfulAttemptsCount() {
        return unsuccessfulAttemptsCount;
    }

    /**
     * Sets the value of the unsuccessfulAttemptsCount property.
     * 
     */
    public void setUnsuccessfulAttemptsCount(short value) {
        this.unsuccessfulAttemptsCount = value;
    }

    /**
     * Gets the value of the elapsedTime property.
     * 
     */
    public long getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Sets the value of the elapsedTime property.
     * 
     */
    public void setElapsedTime(long value) {
        this.elapsedTime = value;
    }

    /**
     * Gets the value of the isSolved property.
     * 
     */
    public boolean isIsSolved() {
        return isSolved;
    }

    /**
     * Sets the value of the isSolved property.
     * 
     */
    public void setIsSolved(boolean value) {
        this.isSolved = value;
    }

}
