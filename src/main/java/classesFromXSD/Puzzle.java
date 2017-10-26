
package classesFromXSD;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Puzzle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Puzzle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="behavior" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="question" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="answer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="difficultyLevel" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="puzzleStatistic" type="{}StatisticItem" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Puzzle", propOrder = {
    "id",
    "behavior",
    "question",
    "answer",
    "difficultyLevel",
    "puzzleStatistic"
})
public class Puzzle {

    protected int id;
    protected boolean behavior;
    @XmlElement(required = true)
    protected String question;
    @XmlElement(required = true)
    protected String answer;
    protected byte difficultyLevel;
    @XmlElement(required = true)
    protected List<StatisticItem> puzzleStatistic;

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
     * Gets the value of the behavior property.
     * 
     */
    public boolean isBehavior() {
        return behavior;
    }

    /**
     * Sets the value of the behavior property.
     * 
     */
    public void setBehavior(boolean value) {
        this.behavior = value;
    }

    /**
     * Gets the value of the question property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets the value of the question property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuestion(String value) {
        this.question = value;
    }

    /**
     * Gets the value of the answer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Sets the value of the answer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnswer(String value) {
        this.answer = value;
    }

    /**
     * Gets the value of the difficultyLevel property.
     * 
     */
    public byte getDifficultyLevel() {
        return difficultyLevel;
    }

    /**
     * Sets the value of the difficultyLevel property.
     * 
     */
    public void setDifficultyLevel(byte value) {
        this.difficultyLevel = value;
    }

    /**
     * Gets the value of the puzzleStatistic property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the puzzleStatistic property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPuzzleStatistic().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StatisticItem }
     * 
     * 
     */
    public List<StatisticItem> getPuzzleStatistic() {
        if (puzzleStatistic == null) {
            puzzleStatistic = new ArrayList<StatisticItem>();
        }
        return this.puzzleStatistic;
    }

}
